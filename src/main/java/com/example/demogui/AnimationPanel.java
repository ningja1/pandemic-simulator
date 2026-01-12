package com.example.demogui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Separate animation panel that displays moving circles.
 */
public class AnimationPanel extends JPanel implements ActionListener {
    private final List<Circle> circles = new ArrayList<>();
    private final Timer timer = new Timer(20, this);
    private final Random rand = new Random();
    private boolean running = true;
    private final int count;

    public AnimationPanel(int count) {
        this.count = count;
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        initKeyBindings();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (circles.isEmpty()) initCircles();
            }
        });
    }

    public void start() { timer.start(); }
    public void stop() { timer.stop(); }

    private void initCircles() {
        int w = getWidth();
        int h = getHeight();
        for (int i = 0; i < count; i++) {
            int r = 10 + rand.nextInt(28);
            int x = rand.nextInt(Math.max(1, w - 2 * r)) + r;
            int y = rand.nextInt(Math.max(1, h - 2 * r)) + r;
            int dx = rand.nextInt(7) - 3;
            int dy = rand.nextInt(7) - 3;
            if (dx == 0) dx = 1;
            if (dy == 0) dy = 1;
            Color color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
            circles.add(new Circle(x, y, dx, dy, r, color));
        }
    }

    private void initKeyBindings() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "toggle");
        getActionMap().put("toggle", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                running = !running;
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!running) return;
        int w = getWidth();
        int h = getHeight();
        for (Circle c : circles) {
            c.x += c.dx;
            c.y += c.dy;
            if (c.x - c.r < 0) { c.x = c.r; c.dx = -c.dx; }
            if (c.y - c.r < 0) { c.y = c.r; c.dy = -c.dy; }
            if (c.x + c.r > w) { c.x = w - c.r; c.dx = -c.dx; }
            if (c.y + c.r > h) { c.y = h - c.r; c.dy = -c.dy; }
            if (rand.nextInt(100) < 3) c.color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Circle c : circles) {
            g2.setColor(c.color);
            g2.fillOval(c.x - c.r, c.y - c.r, c.r * 2, c.r * 2);
        }
        g2.dispose();
    }

    private static class Circle { int x, y, dx, dy, r; Color color; Circle(int x, int y, int dx, int dy, int r, Color color) { this.x = x; this.y = y; this.dx = dx; this.dy = dy; this.r = r; this.color = color; } }
}
