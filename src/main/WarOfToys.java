package main;

import javax.swing.JFrame;

import view.Menu;

public class WarOfToys {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("War of Toys");

        // New Menu
        Menu menu = new Menu(0.5, window);

        window.add(menu);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
