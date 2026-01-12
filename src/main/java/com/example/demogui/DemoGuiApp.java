package com.example.demogui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Moving Circles Demo:
 * - Shows 10 colorful circles moving randomly and bouncing off the window edges.
 * - Press SPACE to pause/resume.
 */
public class DemoGuiApp {
    public static void main(String[] args) {
        if (GraphicsEnvironment.isHeadless()) {
            System.err.println("ERROR: No X11 DISPLAY variable was set or no headful library support was found.\n" +
                    "Run the demo on a machine with a display, use X11 forwarding (ssh -X), or use Xvfb.\n" +
                    "Example: xvfb-run -s \"-screen 0 1024x768x24\" mvn compile exec:java -Dexec.mainClass=\"com.example.demogui.DemoGuiApp\"");
            System.exit(1);
        }

        SwingUtilities.invokeLater(DemoGuiApp::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Moving Circles Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 180);
        frame.setLocationRelativeTo(null);

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e ->
                JOptionPane.showMessageDialog(frame,
                        "Demo GUI App\nJava Swing example",
                        "About",
                        JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);

        // Main content
        JPanel content = new JPanel(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 10));

        JTextField nameField = new JTextField(15);
        JButton greetButton = new JButton("Greet");

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(greetButton);

        JLabel messageLabel = new JLabel("Enter your name and press Greet.");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        greetButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                        "Please enter a name.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                messageLabel.setText("Hello, " + name + "!");
            }
        });

        content.add(inputPanel, BorderLayout.NORTH);
        content.add(messageLabel, BorderLayout.CENTER);
        content.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // switch to animation panel
        AnimationPanel panel = new AnimationPanel(10);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        JLabel info = new JLabel("Press SPACE to pause/resume. Resize the window.");
        info.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        frame.getContentPane().add(info, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.start();
    }
}