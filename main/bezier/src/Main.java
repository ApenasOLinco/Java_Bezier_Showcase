package bezier.src;

import bezier.src.ui.MainWindow;
import bezier.src.ui.SettingsWindow;
import bezier.src.ui.WindowManager;
import com.formdev.flatlaf.FlatDarculaLaf;

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
        FlatDarculaLaf.setup();
//        try {
//            UIManager.setLookAndFeel(new NimbusLookAndFeel());
//        } catch (UnsupportedLookAndFeelException e) {
//            throw new RuntimeException(e);
//        }

        setupWindow();

//        while (true) loop();
    }

    private void loop() {
        final BufferStrategy[] bufferStrategies = ensureBufferStrategy();
        if (bufferStrategies == null) return;

        Graphics graphics = bufferStrategies[0].getDrawGraphics();

        mainWindow.paint(graphics);
        graphics.dispose();
        bufferStrategies[0].show();

        graphics = bufferStrategies[1].getDrawGraphics();

        settingsWindow.paint(graphics);
        graphics.dispose();
        bufferStrategies[1].show();
    }

    private BufferStrategy[] ensureBufferStrategy() {
        BufferStrategy mainWindowBufferStrategy = mainWindow.getBufferStrategy();
        BufferStrategy settingsWindowBufferStrategy = settingsWindow.getBufferStrategy();

        if (mainWindowBufferStrategy == null || settingsWindowBufferStrategy == null) {
            mainWindow.createBufferStrategy(3);
            settingsWindow.createBufferStrategy(3);
            return null;
        }

        return new BufferStrategy[] {mainWindowBufferStrategy, settingsWindowBufferStrategy};
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
