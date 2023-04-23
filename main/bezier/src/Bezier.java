package bezier.src;

import java.awt.*;

public class Bezier {

    /**
     * Returns a point array representing the points of a Bézier curve, with the given control points.
     *
     * @param anchor1 The first anchor point.
     * @param control The control point.
     * @param anchor2 The second anchor point.
     */
    public static Point[] quadratic(Point anchor1, Point control, Point anchor2, int roundness) {
        return quadratic(new Point[]{anchor1, control, anchor2}, roundness == 0 ? 20 : roundness);
    }

    /**
     * <p>
     * Returns a {@link Point} array representing the points of a Bézier curve, with the given control points and with the given
     * number of elements (specified as {@code roundness}).
     * </p>
     * <p>
     * As specified in the <a href="https://en.wikipedia.org/wiki/B%C3%A9zier_curve">Wikipedia article on Bézier curves</a>,
     * the formula for the quadratic Bézier is:<br>
     * {@code B(t) = (1-t)^2 * P0 + 2(1-t) * t * P1 + t^2 * P2}, where:
     * <ul>
     *     <li>{@code t} is the parameter, ranging from 0 to 1;</li>
     *     <li>{@code P0} is the first anchor point;</li>
     *     <li>{@code P1} is the control point;</li>
     *     <li>{@code P2} is the second anchor point.</li>
     * </ul>
     * </p>
     *
     * @param points    The control points of the Bézier curve.
     * @param roundness The number of elements of the Bézier curve.
     * @return The points of the Bézier curve.
     */
    public static Point[] quadratic(Point[] points, int roundness) {
        if (points.length != 3) {
            throw new IllegalArgumentException("The number of control points must be 3.");
        }

        int y0 = points[0].y;
        int x0 = points[0].x;
        int x1 = points[1].x;
        int y1 = points[1].y;
        int x2 = points[2].x;
        int y2 = points[2].y;

        Point[] result = new Point[roundness];

        for (int i = 0; i < roundness; i++) {
            double t = (double) i / (double) roundness;

            int px = (int) (Math.pow(1.0 - t, 2.0) * (double) x0 + 2.0 * (1.0 - t) * t * (double) x1 + Math.pow(t, 2.0) * (double) x2);
            int py = (int) (Math.pow(1.0 - t, 2.0) * (double) y0 + 2.0 * (1.0 - t) * t * (double) y1 + Math.pow(t, 2.0) * (double) y2);

            result[i] = new Point(px, py);
        }

        return result;
    }

}

























