import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        openUI();
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.setVisible(true);
    }

    private void openUI() {
        add(new Game());
        setTitle("Burger Builder");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        pack();

    }
}
