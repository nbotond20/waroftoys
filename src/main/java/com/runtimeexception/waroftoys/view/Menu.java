package com.runtimeexception.waroftoys.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Menu extends JPanel {
    private final double scale;
    private final JFrame window;

    public Menu(final double scale, final JFrame window) {
        this.window = window;
        this.setLayout(new FlowLayout());
        this.scale = scale;
        this.setPreferredSize(new Dimension((int) (1920 * scale), (int) (1080 * scale)));
        initComponents();
    }

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        Left = new javax.swing.JPanel();
        Player1Input = new javax.swing.JTextField();
        Buttons = new javax.swing.JPanel();
        Start = new javax.swing.JButton();
        SavedGames = new javax.swing.JButton();
        Right = new javax.swing.JPanel();
        Player2Input = new javax.swing.JTextField();

        Left.setBackground(new java.awt.Color(100, 0, 0));
        Left.setPreferredSize(new java.awt.Dimension((int) (300 * (0.5 / scale)), (int) (540 * (0.5 / scale))));
        Left.setLayout(new java.awt.GridBagLayout());

        Player1Input.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Player1Input.setText("Player1");
        Player1Input.setPreferredSize(new java.awt.Dimension((int) (160 * (0.5 / scale)), (int) (190 * (0.5 / scale))));
        Player1Input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                Player1InputActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets((int) (15 * (0.5 / scale)), 0, (int) (15 * (0.5 / scale)), 0);
        Player1Input.setForeground(Color.WHITE);
        Player1Input.setOpaque(false);
        Player1Input.setMargin(new Insets(0, 0, 0, 0));
        Border line = BorderFactory.createLineBorder(Color.DARK_GRAY);
        Border empty = new EmptyBorder(125, 0, 0, 0);
        Player1Input.setBackground(new Color(0, 0, 0, 40));
        CompoundBorder border = new CompoundBorder(line, empty);
        Player1Input.setBorder(border);
        Left.add(Player1Input, gridBagConstraints);

        Left.setOpaque(false);
        add(Left);

        Buttons.setBackground(new java.awt.Color(0, 100, 0));
        Buttons.setPreferredSize(new java.awt.Dimension((int) (300 * (0.5 / scale)), (int) (540 * (0.5 / scale))));
        final java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] { 0 };
        jPanel2Layout.rowHeights = new int[] { 0, 5, 0, 5, 0 };
        Buttons.setLayout(jPanel2Layout);

        Start.setFont(new java.awt.Font("Segoe UI", 0, (int) (36 * (0.5 / scale)))); // NOI18N
        Start.setText("");
        Start.setPreferredSize(new java.awt.Dimension((int) (200 * (0.5 / scale)), (int) (50 * (0.5 / scale))));
        Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                StartActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets((int) (400 * (0.5 / scale)), 0, 0, 0);

        Start.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Start.setOpaque(false);
        Start.setContentAreaFilled(false);
        Start.setBorderPainted(false);
        Buttons.add(Start, gridBagConstraints);

        SavedGames.setFont(new java.awt.Font("Segoe UI", 0, (int) (10 * (0.5 / scale)))); // NOI18N
        SavedGames.setText("");
        SavedGames.setPreferredSize(new java.awt.Dimension((int) (100 * (0.5 / scale)), (int) (30 * (0.5 / scale))));
        SavedGames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                SavedGamesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets((int) (9 * (0.5 / scale)), 0, (int) (8 * (0.5 / scale)), 0);

        SavedGames.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        SavedGames.setOpaque(false);
        SavedGames.setContentAreaFilled(false);
        SavedGames.setBorderPainted(false);
        Buttons.add(SavedGames, gridBagConstraints);

        Buttons.setOpaque(false);
        add(Buttons);

        Right.setBackground(new java.awt.Color(100, 0, 0));
        Right.setPreferredSize(new java.awt.Dimension((int) (300 * (0.5 / scale)), (int) (540 * (0.5 / scale))));
        Right.setLayout(new java.awt.GridBagLayout());

        Player2Input.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Player2Input.setText("Player2");
        Player2Input.setPreferredSize(new java.awt.Dimension((int) (160 * (0.5 / scale)), (int) (190 * (0.5 / scale))));
        Player2Input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                Player2InputActionPerformed(evt);
            }
        });
        Player2Input.setForeground(Color.WHITE);
        Player2Input.setOpaque(false);
        Player2Input.setMargin(new Insets(0, 0, 0, 0));
        Player2Input.setBackground(new Color(0, 0, 0, 40));
        Player2Input.setBorder(border);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets((int) (15 * (0.5 / scale)), 0, (int) (15 * (0.5 / scale)), 0);
        Player2Input.setForeground(Color.WHITE);
        Player2Input.setOpaque(false);
        Right.add(Player2Input, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets((int) (15 * (0.5 / scale)), 0, (int) (15 * (0.5 / scale)), 0);

        Right.setOpaque(false);
        add(Right);
    }

    private void Player2InputActionPerformed(final java.awt.event.ActionEvent evt) {
    }

    private void Player1InputActionPerformed(final java.awt.event.ActionEvent evt) {
    }

    private void StartActionPerformed(final java.awt.event.ActionEvent evt) {
        window.remove(this);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));

        final GamePanel gamePanel = new GamePanel(Player1Input.getText(), Player2Input.getText());

        final ButtonPanel btnPanel = new ButtonPanel(gamePanel);
        final Scoreboard scoreboard = new Scoreboard(gamePanel.game);

        gamePanel.game.addButtonPanel(btnPanel);
        gamePanel.game.addScoreboardPanel(scoreboard);

        final JPanel p1 = new JPanel();
        p1.setBackground(new Color(200, 246, 250, 98));
        p1.setPreferredSize(new Dimension(138, 0));
        final JPanel p2 = new JPanel();
        p2.setBackground(new Color(148, 246, 250, 98));
        p2.setPreferredSize(new Dimension(138, 0));

        mainPanel.add(scoreboard, BorderLayout.PAGE_START);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.PAGE_END);

        mainPanel.setBackground(new Color(0, 0, 255, 255));

        window.add(mainPanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.game.startGameThread();
        gamePanel.requestFocus();
    }

    private void SavedGamesActionPerformed(final java.awt.event.ActionEvent evt) {
    }

    private javax.swing.JPanel Left;
    private javax.swing.JPanel Buttons;
    private javax.swing.JPanel Right;
    private javax.swing.JTextField Player1Input;
    private javax.swing.JTextField Player2Input;
    private javax.swing.JButton Start;
    private javax.swing.JButton SavedGames;

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        BufferedImage bgImage;
        try {
            bgImage = ImageIO.read(getClass().getResourceAsStream("/menu/menu-bg.png"));
            g.drawImage(bgImage, 0, 0, (int) (1920 * scale), (int) (1080 * scale),
                    null);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        BufferedImage greenImg;
        try {
            greenImg = ImageIO.read(getClass().getResourceAsStream("/menu/greenSoldier.png"));
            g.drawImage(greenImg, 735, 195, (int) (200 * scale), (int) (200 * scale), null);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        BufferedImage legoImg;
        try {
            legoImg = ImageIO.read(getClass().getResourceAsStream("/menu/lego.png"));
            g.drawImage(legoImg, 125, 195, (int) (200 * scale), (int) (200 * scale), null);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
