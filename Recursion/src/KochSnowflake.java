import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.Point2D;

public class KochSnowflake extends JPanel implements ChangeListener {

    /**
     * Panel dimension.
     */
    private static final Dimension PANEL_DIM = new Dimension(640, 480);
    /**
     * One hundred times the maximum size of Koch snowflake protrusion.
     */
    private static final int MAX_SIZE = 100;
    /**
     * One hundred times the minimum size of Koch snowflake protrusion.
     */
    private static final int MIN_SIZE = -100;
    /**
     * One hundred times the initial size of Koch snowflake protrusion.
     */
    private static final int INIT_SIZE = 33;
    /**
     * Maximum order of Koch snowflake.
     */
    private static final int MAX_ORDER = 10;
    /**
     * Minimum order of Koch snowflake.
     */
    private static final int MIN_ORDER = 1;
    /**
     * Initial order of Koch snowflake.
     */
    private static final int INIT_ORDER = 1;
    /**
     * Slider to specify the order of the Koch snowflake.
     */
    private final JSlider orderSlider;
    /**
     * Slider to specify the size of the protrusion.
     */
    private final JSlider sizeSlider;

    /**
     * Initialize the window contents.
     */
    public KochSnowflake() {
        JLabel orderLabel = new JLabel("Order:");
        JLabel sizeLabel = new JLabel("Size:");
        orderSlider = new JSlider(JSlider.HORIZONTAL, MIN_ORDER, MAX_ORDER,
                INIT_ORDER);
        sizeSlider = new JSlider(JSlider.HORIZONTAL, MIN_SIZE, MAX_SIZE,
                INIT_SIZE);
        orderSlider.addChangeListener(this);
        sizeSlider.addChangeListener(this);
        add(orderLabel);
        add(orderSlider);
        add(sizeLabel);
        add(sizeSlider);
        setBackground(Color.white);
        setPreferredSize(PANEL_DIM);
    }

    /**
     * Run the program.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        JFrame frame = new JFrame("Koch Snowflake");
        frame.getContentPane().add(new KochSnowflake());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Paint a Koch snowflake to the window.
     *
     * @param g graphics object to draw to
     */
    public final void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final double oneHalf = 0.5;
        final double oneThird = 0.33;
        final double twoThirds = 0.66;
        final double oneHundredth = 0.01;
        final int order = orderSlider.getValue();
        final double size = sizeSlider.getValue() * oneHundredth;
        final double width = getWidth();
        final double height = getHeight();
        Point2D point1, point2;
        point1 = new Point2D.Double(width * oneHalf, height * oneThird);
        point2 = new Point2D.Double(width * oneThird, height * twoThirds);
        koch(g, point1, point2, order, size);
        point1 = new Point2D.Double(width * oneThird, height * twoThirds);
        point2 = new Point2D.Double(width * twoThirds, height * twoThirds);
        koch(g, point1, point2, order, size);
        point1 = new Point2D.Double(width * twoThirds, height * twoThirds);
        point2 = new Point2D.Double(width * oneHalf, height * oneThird);
        koch(g, point1, point2, order, size);
    }

    /**
     * Draw a Koch snowflake segment.
     *
     * @param g      the graphics object to draw to
     * @param point1 the first vertex of the line to draw
     * @param point2 the second vertex of the line to draw
     * @param order  the order of the snowflake
     * @param size   the size of the protrusion
     */
    public void koch(final Graphics g, final Point2D point1,
                     final Point2D point2, final int order, final double size) {

        if (order == 0) {
            g.drawLine(((int) point1.getX()), (int) point1.getY(), (int) point2.getX(), (int) point2.getY());
        } else {

            koch(g, point1, calculateOneThird(point1, point2), order - 1, size);
            koch(g, calculateOneThird(point1, point2), calculateApex(point1, point2, size), order - 1, size);
            koch(g, calculateApex(point1, point2, size), calculateTwoThirds(point1, point2), order - 1, size);
            koch(g, calculateTwoThirds(point1, point2), point2, order - 1, size);

        }

    }

    public Point2D calculateOneThird(Point2D point1, Point2D point2) {

        double x = point1.getX() + (point2.getX() - point1.getX()) / 3;
        double y = point1.getY() + (point2.getY() - point1.getY()) / 3;
        return new Point2D.Double(x, y);
    }

    public Point2D calculateTwoThirds(Point2D point1, Point2D point2) {

        double x = point1.getX() + (point2.getX() - point1.getX()) * 2 / 3;
        double y = point1.getY() + (point2.getY() - point1.getY()) * 2 / 3;
        return new Point2D.Double(x, y);
    }

    public Point2D calculateApex(Point2D point1, Point2D point2, double p) {

        double dx = point2.getX() - point1.getX();
        double dy = point2.getY() - point1.getY();

        double x = point1.getX() + dx / 2 - dy * p;
        double y = point1.getY() + dy / 2 + dx * p;

        return new Point2D.Double(x, y);
    }


    /**
     * Repaint the window if the slider moves.
     *
     * @param event change event
     */
    public final void stateChanged(final ChangeEvent event) {
        repaint();
    }

}
