package com.runtimeexception.waroftoys;

import com.runtimeexception.waroftoys.view.Menu;

import javax.swing.*;

public class WarOfToys {

    public static void main(final String[] args) {
        final JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("War of Toys");

        // New Menu
        final Menu menu = new Menu(0.5, window);

        window.add(menu);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
