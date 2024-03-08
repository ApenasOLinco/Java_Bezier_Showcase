package bezier.src;

import bezier.src.ui.MainWindow;
import bezier.src.ui.SettingsWindow;
import bezier.src.ui.WindowManager;
import com.formdev.flatlaf.FlatDarkLaf;

public class Main {

    private MainWindow mainWindow;

    private SettingsWindow settingsWindow;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> new Main().start());
        thread.start();
    }

    private void start() {
        FlatDarkLaf.setup();

        setupWindow();
    }

    private void setupWindow() {
        mainWindow = new MainWindow(this);
        settingsWindow = new SettingsWindow(this);
        WindowManager.openWindow(mainWindow);
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public SettingsWindow getSettingsWindow() {
        return settingsWindow;
    }

}
