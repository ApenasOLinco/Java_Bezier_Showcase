package bezier.src.ui;

import java.awt.*;

public class ControlPoint extends Point {

    private boolean selected = false;

    public ControlPoint(int x, int y) {
        super(x, y);
    }

    public ControlPoint(Point p) {
        super(p.x, p.y);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
