package bezier.src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main {

    private static JFrame window;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> new Main().start());
        thread.start();
    }

    private void start() {
        setupWindow();

        while (true) loop();
    }

    private void loop() {
        BufferStrategy bs = window.getBufferStrategy();

        if (bs == null) {
            window.createBufferStrategy(3);
            return;
        }

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paint(g);
        bs.show();
        g.dispose();
    }

    private void paint(Graphics2D g) {
        window.paint(g);
    }

    private void setupWindow() {
        window = new JFrame("Bezier Curve");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(createCanvas());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);
    }

    private JPanel createCanvas() {
        JPanel canvas = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                g.setColor(Color.BLACK);

                Point anchor1 = new Point(10, 10);

                Point mousePosition = getMousePosition();
                if (mousePosition == null) mousePosition = new Point(0, 0);

                Point control = new Point(mousePosition.x, mousePosition.y);

                Point anchor2 = new Point(10, 590);

                Point[] points = Bezier.quadratic(anchor1, control, anchor2, 30);

                g.setColor(Color.BLACK);
                for (int i = 0; i < points.length - 1; i++) {
                    g.drawLine(points[i].x, points[i].y, points[i + 1].x, points[i + 1].y);
                }

                g.setColor(Color.RED);
                g.fillOval(anchor1.x - 5, anchor1.y - 5, 10, 10);
                g.fillOval(control.x - 5, control.y - 5, 10, 10);
                g.fillOval(anchor2.x - 5, anchor2.y - 5, 10, 10);

            }
        };

        final Dimension size = new Dimension(800, 600);

        canvas.setPreferredSize(size);
        canvas.setMinimumSize(size);
        canvas.setMaximumSize(size);

        return canvas;
    }
}
