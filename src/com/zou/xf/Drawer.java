package com.zou.xf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Drawer extends JPanel implements ActionListener {
    //  grapeMap[0][1]=grapeMap[1][1]=grapeMap[1][0]=grapeMap[2][1]=-20516;
    private static final int[][][] SQUARE_SHAPE = {
            {{0, 0, 0, 1, -1, 1, 1, 1}, {0, 0, 0, 1, 1, 1, 0, 2}, {0, 1, -1, 1, 1, 1, 0, 2}, {0, 0, 0, 1, -1, 1, 0, 2}},
            {{-1, 0, 0, 0, 1, 0, 2, 0}, {0, -1, 0, 0, 0, 1, 0, 2}, {-1, 0, 0, 0, 1, 0, 2, 0}, {0, -1, 0, 0, 0, 1, 0, 2}},
            {{0, 0, 0, 1, 1, 0, 1, 1}, {0, 0, 0, 1, 1, 0, 1, 1}, {0, 0, 0, 1, 1, 0, 1, 1}, {0, 0, 0, 1, 1, 0, 1, 1}},
            {{-1, 0, -1, 1, 0, 1, 1, 1}, {0, 0, 1, 0, 0, 1, 0, 2}, {-1, 1, 0, 1, 1, 1, 1, 2}, {0, 0, 0, 1, 0, 2, -1, 2}},
            {{1, 0, -1, 1, 0, 1, 1, 1}, {0, 0, 0, 1, 0, 2, 1, 2}, {-1, 1, -1, 2, 0, 1, 1, 1}, {-1, 0, 0, 0, 0, 1, 0, 2}}};
    private int BLOCKS_IN_ROW = 12;
    private int BLOCKS_IN_LINE = 18;
    private int delta = 27;
    private int[] row, line;
    private int[][] grapeMap;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < BLOCKS_IN_ROW; i++)
            for (int j = 0; j < BLOCKS_IN_LINE; j++) {
                if (grapeMap[i][j] == 0)
                    g2d.setColor(new Color((float) (j + 1) / BLOCKS_IN_LINE, (float) (i + j + 2) / (BLOCKS_IN_ROW + BLOCKS_IN_LINE), (float) (i + 1) / BLOCKS_IN_ROW, (float) (i + j + 1) / (BLOCKS_IN_LINE + BLOCKS_IN_ROW)));
                else g2d.setColor(new Color(grapeMap[i][j]));
                g2d.fillRect(row[i], line[j], delta, delta);
            }
        paintBackGround(g2d);
    }

    private void paintBackGround(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(1.875f));
        g2d.setColor(Color.BLACK);
        g2d.drawRect(row[0], line[0], row[BLOCKS_IN_ROW] - row[0], line[BLOCKS_IN_LINE] - line[0]);
        g2d.setStroke(new BasicStroke(0.875f));
        for (int i = 1; i < BLOCKS_IN_ROW; i++) g2d.drawLine(row[i], line[0], row[i], line[BLOCKS_IN_LINE]);
        for (int i = 1; i < BLOCKS_IN_LINE; i++) g2d.drawLine(row[0], line[i], row[BLOCKS_IN_ROW], line[i]);
    }

    Drawer() {
        updateBlock(0,0);
        updateBlock(400, 600);
        grapeMap = new int[BLOCKS_IN_ROW][BLOCKS_IN_LINE];
        // grapeMap[0][1]=grapeMap[1][1]=grapeMap[1][0]=grapeMap[2][1]=-20516;
        // TODO init drawer;
        Timer a = new Timer(1000, this);
        a.start();
        drawIt(0,2,0,1,Color.red);
        drawIt(1,5,10,0,Color.cyan);
    }

    private void drawIt(int type, int x, int y, int dir, Color c) {
        for (int i = 0; i < 4; i++)
            grapeMap[x + SQUARE_SHAPE[type][dir][i << 1]][y + SQUARE_SHAPE[type][dir][(i << 1) | 1]] = c.getRGB();
    }

    private void updateBlock(int width, int height) {
        if (width < 1 || height < 1) return;
        row = new int[BLOCKS_IN_ROW + 1];
        line = new int[BLOCKS_IN_LINE + 1];
        row[0] = width / 2 - delta * BLOCKS_IN_ROW / 2;
        line[0] = 56;
        for (int i = 1; i <= BLOCKS_IN_ROW; i++) row[i] = row[i - 1] + delta;
        for (int i = 1; i <= BLOCKS_IN_LINE; i++) line[i] = line[i - 1] + delta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}