package bezier.src;

import bezier.src.ui.MainWindow;
import bezier.src.ui.SettingsWindow;
import bezier.src.ui.WindowManager;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatPropertiesLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

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
