package bezier.src.ui;

import bezier.src.bezier.Bezier;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MainPanel extends JPanel {

    public int stops = 200;
    ArrayList<Point> controlPoints;

    ArrayList<Point> curvePoints;

    Point selectedControlPoint;

    private float scale = 1.0f;

    private final int controlPointSize = 10;

    int scaledControlPointSize = (int) ((float) controlPointSize * scale);

    private final int curvePointSize = 3;

    int scaledCurvePointSize = (int) ((float) curvePointSize * scale);

    public MainPanel() {
        super();

        initComponents();

        controlPoints = new ArrayList<>();
        curvePoints = new ArrayList<>();

        controlPoints.add(new Point(100, 100));
        controlPoints.add(new Point(200, 200));

        buildCurve();

        setMouseListeners();

        requestFocus();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ((Graphics2D)getGraphics()).addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

        Color curvePointColor = Color.GREEN;
        g.setColor(curvePointColor);

        for (Point p : curvePoints) {
            g.fillOval(p.x - scaledCurvePointSize / 2, p.y - scaledCurvePointSize / 2, scaledCurvePointSize, scaledCurvePointSize);
        }

        Color controlPointColor = Color.RED;
        g.setColor(controlPointColor);

        for (Point p : controlPoints) {
            g.fillOval(p.x - scaledControlPointSize / 2, p.y - scaledControlPointSize / 2, scaledControlPointSize, scaledControlPointSize);
        }
    }

    private void initComponents() {
        ButtonGroup addOrRemovePoints = new ButtonGroup();

        JButton addPoint = new JButton("+");
        JButton removePoint = new JButton("-");

        addPoint.addActionListener(e -> {
            Point[] elevated = new Point[controlPoints.size()];
            elevated = Bezier.elevate(controlPoints.toArray(elevated));
            controlPoints.clear();
            controlPoints.addAll(Arrays.asList(elevated));

            if(controlPoints.size() >= 3) removePoint.setEnabled(true);
            buildCurve();
        });

        removePoint.addActionListener(e -> {
            controlPoints.removeLast();

            if(controlPoints.size() < 3) removePoint.setEnabled(false);
            buildCurve();
        });

        addOrRemovePoints.add(addPoint);
        addOrRemovePoints.add(removePoint);
        add(removePoint);
        add(addPoint);
    }

    private void buildCurve() {
        Point[] points;
        points = new Point[controlPoints.size()];

        for (int i = 0; i < points.length; i++) {
            points[i] = controlPoints.get(i);
        }

        Point[] result = switch(points.length) {
            case 2 -> Bezier.linear(points, stops);
            case 3 -> Bezier.quadratic(points, stops);
            case 4 -> Bezier.cubic(points, stops);
            default -> null;
        };

        curvePoints.clear();
        assert result != null : "result is null";
        curvePoints.addAll(List.of(result));
        repaint();
    }

    private void setMouseListeners() {
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON1) return;

                Stream<Point> eligible = controlPoints.stream().filter(point -> point.distance(e.getPoint()) <= (float) scaledControlPointSize / 2.0);
                selectedControlPoint = eligible.findFirst().orElse(null);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedControlPoint = null;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedControlPoint == null) return;

                selectedControlPoint.setLocation(clamp(e.getPoint()));
                buildCurve();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        addMouseWheelListener(e -> {
            float newScale = scale += (float) ((float) -e.getPreciseWheelRotation() / 10.0);

            if (newScale < 0.7) newScale = 0.7f;
            else if (newScale > 3.0) newScale = 3.0f;

            setScale(newScale);
            repaint();
        });
    }

    private Point clamp(Point point) {
        int newX = Math.max(0, Math.min(point.x, getWidth()));
        int newY = Math.max(0, Math.min(point.y, getHeight()));

        return new Point(newX, newY);
    }

    public void setScale(float scale) {
        this.scale = scale;

        scaledControlPointSize = (int) ((float) controlPointSize * scale);
        scaledCurvePointSize = (int) ((float) curvePointSize * scale);
    }
}
