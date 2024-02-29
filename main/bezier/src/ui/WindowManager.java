package bezier.src.ui;

import javax.swing.*;
import javax.swing.SwingUtilities;

public class WindowManager {

    public static void openWindow(JFrame window){
        SwingUtilities.invokeLater(() -> {
            window.setVisible(true);
            window.requestFocus();
        });
    }
}
