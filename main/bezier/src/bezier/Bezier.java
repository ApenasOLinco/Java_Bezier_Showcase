package bezier.src.bezier;

import java.awt.*;

/**
 * <p>
 * Contains methods for generating Bézier curves. <br>
 * The methods in this class are based on the <a href="https://en.wikipedia.org/wiki/B%C3%A9zier_curve">Wikipedia article on Bézier curves</a>.
 * </p>
 * <p>
 * Currently supported:
 * </p>
 * <ul>
 *  <li>{@link #linear(Point[], int)}</li>
 *  <li>{@link #quadratic(Point[], int)}</li>
 *  <li>{@link #cubic(Point[], int)}</li>
 * </ul>
 */
public class Bezier {

    /**
     * Returns a {@link Point} array representing the points of a Linear Bézier curve with the given control points.
     * The more {@code stops} in the curve, the less "spotty" it will look, as the number of stops determines the amount
     * of points in between the anchor points. A linear Bézier curve uses only two anchor points with no control points.
     * <p>
     * As specified in the <a href="https://en.wikipedia.org/wiki/B%C3%A9zier_curve">Wikipedia article on Bézier curves</a>,
     * the formula for the linear Bézier is: <br>
     * <br>
     * {@code B(t) = P0 + t * (P1 - P0)}, where:
     *
     * <ul>
     *  <li>{@code t} is the current fraction of the curve, ranging from 0 to 1;</li>
     *  <li>{@code P0} is the first anchor point;</li>
     *  <li>{@code P1} is the second anchor point.</li>
     * </ul>
     *
     * @param points the array containing the two {@code anchor points} of the curve.
     * @param stops  the number of stops in the curve.
     * @return the points of a linear Bézier curve, based on the given control points and with the given number of stops.
     */
    public static Point[] linear(Point[] points, int stops) {
        int x0 = points[0].x;
        int y0 = points[0].y;
        int x1 = points[1].x;
        int y1 = points[1].y;

        Point[] result = new Point[stops + 1];

        for (int i = 0; i <= stops; i++) {
            double t = (double) i / (double) stops;

            double px = x0 + (x1 - x0) * t;
            double py = y0 + (y1 - y0) * t;
            result[i] = new Point((int) px, (int) py);
        }

        return result;
    }

    /**
     * Returns a {@link Point} array representing the points of a Linear Bézier curve with the given control points.
     * The more {@code stops} in the curve, the less "spotty" it will look, as the number of stops determines the amount
     * of points in between the anchor points. A linear Bézier curve uses only two anchor points with no control points.
     * <p>
     * For details on the mathematical formula, see {@link #linear(Point[], int) the overloaded method}.
     * </p>
     */
    public static Point[] linear(Point anchor1, Point anchor2, int stops) {
        return linear(new Point[]{anchor1, anchor2}, stops);
    }

    /**
     * Returns a {@link Point} array representing the points of a Quadratic Bézier curve with the given control points.
     * The "roundness" of the curve is specified by the {@code stops} parameter, which determines the number of points
     * in the resulting curve. A quadratic Bézier curve uses two anchor points and one control point.
     * <p>
     * As specified in the <a href="https://en.wikipedia.org/wiki/B%C3%A9zier_curve">Wikipedia article on Bézier curves</a>,
     * the formula for the quadratic Bézier is: <br>
     * <br>
     * {@code B(t) = (1-t)^2 * P0 + 2(1-t) * t * P1 + t^2 * P2}, where:
     *
     *  <ul>
     *     <li>{@code t} is the current fraction of the curve, ranging from 0 to 1;</li>
     *     <li>{@code P0} is the first anchor point;</li>
     *     <li>{@code P1} is the control point;</li>
     *    <li>{@code P2} is the second anchor point.</li>
     * </ul>
     * </p>
     *
     * @param points the control points of the Bézier curve.
     * @param stops  the number of elements of the Bézier curve.
     * @return the points of a quadratic Bézier curve, based on the given control points and with the given number of stops.
     */
    public static Point[] quadratic(Point[] points, int stops) {
        if (points.length != 3) {
            throw new IllegalArgumentException("The number of control points must be 3.");
        }

        int x0 = points[0].x;
        int y0 = points[0].y;
        int x1 = points[1].x;
        int y1 = points[1].y;
        int x2 = points[2].x;
        int y2 = points[2].y;

        Point[] result = new Point[stops + 1];

        for (int i = 0; i <= stops; i++) {
            double t = (double) i / (double) stops;

            int px = (int) (Math.pow(1.0 - t, 2.0) * (double) x0 + 2.0 * (1.0 - t) * t * (double) x1 + Math.pow(t, 2.0) * (double) x2);
            int py = (int) (Math.pow(1.0 - t, 2.0) * (double) y0 + 2.0 * (1.0 - t) * t * (double) y1 + Math.pow(t, 2.0) * (double) y2);

            result[i] = new Point(px, py);
        }

        return result;
    }

    /**
     * Returns a {@link Point} array representing the points of a {@code Quadratic Bézier curve} with the given control points and with the given
     * number of elements (defined by the number of {@code stops}). A quadratic Bézier curve uses two anchor points and one control point.
     * <p>
     * For details on the mathematical formula, see {@link #quadratic(Point[], int) the overloaded method}.
     * </p>
     *
     * @param anchor1 the first anchor point.
     * @param control the control point.
     * @param anchor2 the second anchor point.
     * @param stops   the number of stops in the curve.
     * @return the points of a Bézier curve, based on the given control points and with the given number of stops.
     * @see #quadratic(Point, Point, Point, int) for details on the mathematical formula.
     */
    public static Point[] quadratic(Point anchor1, Point control, Point anchor2, int stops) {
        return quadratic(new Point[]{anchor1, control, anchor2}, stops == 0 ? 2 : stops);
    }

    /**
     * Returns a {@link Point} array representing the points of a cubic Bézier curve with the given control points.
     * The "roundness" of the curve is specified by the {@code stops} parameter, which determines the number of points
     * in the resulting curve. A cubic Bézier curve uses two anchor points and two control points.
     * <p>
     * As specified in the <a href="https://en.wikipedia.org/wiki/B%C3%A9zier_curve">Wikipedia article on Bézier curves</a>,
     * the formula for the cubic Bézier is: <br>
     * <br>
     * {@code B(t) = (1-t)^3 * P0 + 3(1-t)^2 * t * P1 + 3(1-t) * t^2 * P2 + t^3 * P3}, where:
     *  <ul>
     *   <li>{@code t} is the current fraction of the curve, ranging from 0 to 1;</li>
     *   <li>{@code P0} is the first anchor point;</li>
     *   <li>{@code P1} is the first control point</li>
     *   <li>{@code P2} is the second control point</li>
     *   <li>{@code P3} is the second anchor point</li>
     *  </ul>
     * </p>
     *
     * @param points the control points of the Bézier curve.
     * @param stops  the number of elements of the Bézier curve.
     * @return the points of a Bézier curve, based on the given control points and with the given number of stops.
     */
    public static Point[] cubic(Point[] points, int stops) {
        if (points.length != 4) {
            throw new IllegalArgumentException("The number of control points must be 4.");
        }

        int x0 = points[0].x;
        int y0 = points[0].y;
        int x1 = points[1].x;
        int y1 = points[1].y;
        int x2 = points[2].x;
        int y2 = points[2].y;
        int x3 = points[3].x;
        int y3 = points[3].y;

        Point[] result = new Point[stops + 1];

        for (int i = 0; i <= stops; i++) {
            double t = (double) i / (double) stops;

            int px = (int) (Math.pow(1.0 - t, 3.0) * (double) x0 + t * (double) x1 * (3.0 * Math.pow(1.0 - t, 2.0)) + (double) x2 * (3.0 * (1 - t) * Math.pow(t, 2.0)) + (double) x3 * Math.pow(t, 3.0));
            int py = (int) (Math.pow(1.0 - t, 3.0) * (double) y0 + t * (double) y1 * (3.0 * Math.pow(1.0 - t, 2.0)) + (double) y2 * (3.0 * (1 - t) * Math.pow(t, 2.0)) + (double) y3 * Math.pow(t, 3.0));

            result[i] = new Point(px, py);
        }

        return result;
    }

    /**
     * Returns a {@link Point} array representing the points of a cubic Bézier curve with the given control points.
     * The "roundness" of the curve is specified by the {@code stops} parameter, which determines the number of points
     * in the resulting curve. A cubic Bézier curve uses two anchor points and two control points.
     * <p>
     * For details on the mathematical formula, see {@link #cubic(Point[], int) the overloaded method}.
     *
     * @param anchor1  the first anchor point of the curve.
     * @param control1 the first control point of the curve.
     * @param control2 the second control point of the curve.
     * @param anchor2  the second anchor point of the curve.
     * @param stops    the number of stops in the curve.
     * @return the points of a Bézier curve, based on the given control points and with the given number of stops.
     */
    public static Point[] cubic(Point anchor1, Point control1, Point control2, Point anchor2, int stops) {
        return cubic(new Point[]{anchor1, control1, control2, anchor2}, stops == 0 ? 2 : stops);
    }


}

























