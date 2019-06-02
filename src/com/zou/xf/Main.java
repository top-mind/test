package com.zou.xf;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    private Main() {
        JFrame jFrame = new JFrame("Game Square");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(new Drawer());
        jFrame.setSize(400, 600);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
