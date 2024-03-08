package bezier.src.ui;

import java.awt.*;

public class ControlPoint extends Point {
    public ControlPoint(int x, int y) {
        super(x, y);
    }

    public ControlPoint(Point p) {
        super(p.x, p.y);
    }
}
