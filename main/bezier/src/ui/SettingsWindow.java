package bezier.src.ui;

import bezier.src.Main;

import javax.swing.*;
import javax.swing.SpinnerNumberModel;
import java.awt.*;
import java.awt.Font;
import java.text.ParseException;
import java.util.Arrays;

public class SettingsWindow extends JFrame {

    private final Main main;
    private JSpinner numberOfControlPoints;
    private JPanel buttonsPanel, settingsPanel;

    public SettingsWindow(Main main) {
        super("Settings");
        this.main = main;
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        setPanels();
        pack();
        setLocationRelativeTo(main.getMainWindow());
    }

    private void setPanels() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
        buttonsPanel.add(new JButton("Save"));
        buttonsPanel.add(new JButton("Cancel"));

        settingsPanel = new JPanel();
        settingsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel controlPointsLabel = new JLabel("Number of control points");
        controlPointsLabel.setPreferredSize(new Dimension(150, 30));
        numberOfControlPoints = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        numberOfControlPoints.setPreferredSize(new Dimension(150, 30));

        settingsPanel.add(controlPointsLabel);
        settingsPanel.add(numberOfControlPoints);

        add(settingsPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
}




















