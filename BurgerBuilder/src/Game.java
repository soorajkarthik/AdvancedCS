import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel implements ActionListener {

    private final Font CONTENT_FONT = new Font(Font.DIALOG_INPUT, Font.BOLD, 20);
    private final Font TITLE_FONT = new Font(Font.DIALOG, Font.BOLD, 25);
    private Burger generatedBurger;
    private Burger playerBurger;
    private boolean hasLost;
    private Thread thread;
    private final Timer timer;
    private int score;
    private int timeRemaining;


    public Game() {

        hasLost = false;
        playerBurger = new Burger();
        timer = new Timer(1000, this);
        startScreen();

    }

    private void startScreen() {

        removeAll();
        setBackground(Color.WHITE);

        setLayout(null);
        setPreferredSize(new Dimension(400, 400));
        setFocusable(true);
        setDoubleBuffered(true);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(this);
        startButton.setBounds(145, 140, 110, 50);

        JButton instructionsButton = new JButton("Instructions");
        instructionsButton.addActionListener(this);
        instructionsButton.setBounds(145, 200, 110, 50);

        add(startButton);
        add(instructionsButton);

        revalidate();
        repaint();
    }

    private void startGame() {
        playerBurger = new Burger();
        score = 0;
        generatedBurger = null;
        hasLost = false;
        thread = null;

        buildUI();
        startThread();
    }

    private void buildUI() {
        removeAll();

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel ingredientPanel = new JPanel();
        ingredientPanel.setMaximumSize(new Dimension(100, 400));
        ingredientPanel.setLayout(new BoxLayout(ingredientPanel, BoxLayout.Y_AXIS));
        ingredientPanel.add(new JButton("Bun", new ImageIcon("images/topBun.png")));
        ingredientPanel.add(new JButton("Patty", new ImageIcon("images/patty.png")));
        ingredientPanel.add(new JButton("Cheese", new ImageIcon("images/cheese.png")));
        ingredientPanel.add(new JButton("Lettuce", new ImageIcon("images/lettuce.png")));
        ingredientPanel.add(new JButton("Onion", new ImageIcon("images/onion.png")));
        ingredientPanel.add(new JButton("Tomato", new ImageIcon("images/tomato.png")));
        ingredientPanel.add(new JButton("Pickle", new ImageIcon("images/pickle.png")));
        ingredientPanel.add(new JButton("Ketchup", new ImageIcon("images/ketchup.png")));

        for (Component c : ingredientPanel.getComponents()) {
            JButton jb = (JButton) c;
            jb.setPreferredSize(new Dimension(100, 50));
            jb.addActionListener(this);
        }

        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
        imagePanel.setMaximumSize(new Dimension(200, 400));

        JPanel actionPanel = new JPanel();
        actionPanel.setBackground(Color.WHITE);
        actionPanel.setLayout(null);
        actionPanel.setMaximumSize(new Dimension(100, 400));

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(this);
        removeButton.setBounds(0, 340, 100, 30);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setBounds(0, 370, 100, 30);

        actionPanel.add(removeButton);
        actionPanel.add(submitButton);

        add(ingredientPanel);
        add(imagePanel);
        add(actionPanel);

        revalidate();
        repaint();
    }

    private void startThread() {

        thread = new Thread() {
            @Override
            public void run() {

                while (!hasLost) {
                    generatedBurger = Burger.generateBurger();
                    timeRemaining = 10;
                    paintComponents(getGraphics());
                    paintComponents(getGraphics());
                    timer.start();

                    try {
                        synchronized (this) {
                            wait(11000);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    timer.stop();
                    checkBurgers();

                }

                JFrame popupFrame = new JFrame();

                ActionListener popupListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        popupFrame.dispose();
                        requestFocus();
                    }
                };

                JButton quitButton = new JButton("Quit");
                quitButton.addActionListener(Game.this);
                quitButton.addActionListener(popupListener);

                JButton continueButton = new JButton("Restart");
                continueButton.addActionListener(Game.this);
                continueButton.addActionListener(popupListener);

                JButton[] options = {quitButton, continueButton};

                JOptionPane.showOptionDialog(
                        popupFrame,
                        "Game Over!\n Your score is " + score + "!",
                        "Game Over!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]);
            }
        };

        thread.start();
    }

    @Override
    public void paintComponents(Graphics g) {

        super.paintComponents(g);

        int y = 55;


        g.setFont(TITLE_FONT);
        g.setColor(Color.BLACK);
        g.drawString("ORDER", 300, 25);

        g.setFont(CONTENT_FONT);

        for (Ingredient i : generatedBurger.getIngredients()) {
            if (i.getName().equals("Bun"))
                continue;

            g.drawString(i.getName(), 300, y);
            y += 25;
        }

        y = 350;

        for (Ingredient i : playerBurger.getIngredients()) {
            g.drawImage(i.getImage(), 150, y, this);
            y -= 50;
        }

        paintTime(g);
    }

    public void paintTime(Graphics g) {
        if (timeRemaining >= 0) {
            g.setFont(CONTENT_FONT);

            g.setColor(Color.WHITE);
            g.fillRect(105, 0, 30, 20);

            if (timeRemaining <= 3) {
                g.setColor(Color.RED);
            } else if (timeRemaining <= 6) {
                g.setColor(Color.ORANGE);
            } else {
                g.setColor(Color.BLACK);
            }

            g.drawString("" + timeRemaining, 105, 20);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == timer) {
            timeRemaining--;
            paintTime(getGraphics());
        } else {
            String buttonTitle = ((JButton) e.getSource()).getText();
            switch (((JButton) e.getSource()).getText()) {
                case "Start":
                    startGame();
                    return;
                case "Instructions":
                    JOptionPane.showMessageDialog(new JFrame(), "Fill the orders before time runs out! Once you're done, click submit. ");
                    return;
                case "Restart":
                    removeAll();
                    revalidate();
                    startScreen();
                    return;
                case "Remove":
                    playerBurger.removeIngredient();
                    paintComponents(getGraphics());
                    return;
                case "Submit":
                    synchronized (thread) {
                        thread.notify();
                    }
                    return;
                case "Continue":
                    playerBurger = new Burger();
                    synchronized (thread) {
                        thread.notify();
                        thread.notify();
                    }
                    paintComponents(getGraphics());
                    return;
                case "Quit":
                    SwingUtilities.getWindowAncestor(this).dispose();
                    return;
                case "Bun":
                    if (playerBurger.getIngredients().size() < 8) {
                        playerBurger.addIngredient(new Ingredient("Bun", Ingredient.INGREDIENT_IMAGES.get(playerBurger.numOfBuns() == 0 ? "Bottom Bun" : "Top Bun")));
                        paintComponents(getGraphics());
                    }
                    return;
                default:
                    if (playerBurger.getIngredients().size() < 8) {
                        playerBurger.addIngredient(new Ingredient(buttonTitle, Ingredient.INGREDIENT_IMAGES.get(buttonTitle)));
                        paintComponents(getGraphics());
                    }

            }
        }

    }

    private void checkBurgers() {

        if (playerBurger.equals(generatedBurger)) {

            score += (1000 + timeRemaining * 100);

            JFrame popupFrame = new JFrame();
            popupFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            ActionListener popupListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    popupFrame.dispose();
                    requestFocus();
                }
            };

            JButton quitButton = new JButton("Quit");
            quitButton.addActionListener(this);
            quitButton.addActionListener(popupListener);

            JButton continueButton = new JButton("Continue");
            continueButton.addActionListener(this);
            continueButton.addActionListener(popupListener);

            JButton[] options = {quitButton, continueButton};

            JOptionPane.showOptionDialog(
                    popupFrame,
                    "Awesome!\n Your score is " + score + "!",
                    "Correct Answer!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

        } else {
            hasLost = true;
        }
    }


}