package view;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Insets;
import java.io.IOException;

import model.Game;

public class ButtonPanel extends JPanel {
    private final boolean ICONS = true;
    private final boolean BORDERS = false;
    private final Game game;

    public ButtonPanel(final Game game) {
        this.game = game;
        // this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        initComponents();
    }

    private void initComponents() {
        Tower1Btn = new javax.swing.JButton();
        Tower2Btn = new javax.swing.JButton();
        Tower3Btn = new javax.swing.JButton();
        Ready = new javax.swing.JButton();
        Unit1Btn = new javax.swing.JButton();
        Unit2Btn = new javax.swing.JButton();
        Unit3Btn = new javax.swing.JButton();

        setLayout(new java.awt.GridLayout());

        if (ICONS) {
            try {
                Tower1Btn.setIcon(
                        new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/buttons/turrets/turret_attack.png"))
                                .getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Tower1Btn.setText("Tower1");
        }
        Tower1Btn.setPreferredSize(new java.awt.Dimension(100, 75));
        Tower1Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                Tower1BtnActionPerformed(evt);
            }
        });
        if(BORDERS){
            Tower1Btn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        }
        add(Tower1Btn);

        if (ICONS) {
            try {
                Tower2Btn.setIcon(
                        new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/buttons/turrets/turret_speed.png"))
                                .getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Tower2Btn.setText("Tower2");
        }
        Tower2Btn.setPreferredSize(new java.awt.Dimension(100, 75));
        Tower2Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                Tower2BtnActionPerformed(evt);
            }
        });
        if(BORDERS){
            Tower2Btn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        }
        add(Tower2Btn);

        if(ICONS){
            try {
                Tower3Btn.setIcon(
                        new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/buttons/turrets/turret_slow.png"))
                                .getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Tower3Btn.setText("Tower3");
        }
        Tower3Btn.setPreferredSize(new java.awt.Dimension(100, 75));
        Tower3Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                Tower3BtnActionPerformed(evt);
            }
        });
        if(BORDERS){
            Tower3Btn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        }
        add(Tower3Btn);

        Ready.setText("Ready");
        Ready.setPreferredSize(new java.awt.Dimension(100, 75));
        Ready.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                ReadyBtnActionPerformed(evt);
            }
        });
        if(BORDERS){
            // Ready.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        }
        add(Ready);

        if(ICONS){
            try {
                Unit1Btn.setIcon(
                        new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/buttons/units/brute.png"))
                                .getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Unit1Btn.setText("Unit1");
        }
        Unit1Btn.setPreferredSize(new java.awt.Dimension(100, 75));
        Unit1Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                Unit1BtnActionPerformed(evt);
            }
        });
        if(BORDERS){
            Unit1Btn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        }
        add(Unit1Btn);

        if(ICONS){
            try {
                Unit2Btn.setIcon(
                        new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/buttons/units/spec.png"))
                                .getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Unit2Btn.setText("Unit2");
        }
        Unit2Btn.setPreferredSize(new java.awt.Dimension(100, 75));
        Unit2Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                Unit2BtnActionPerformed(evt);
            }
        });
        if(BORDERS){
            Unit2Btn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        }
        add(Unit2Btn);

        if(ICONS){
            try {
                Unit3Btn.setIcon(
                        new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/buttons/units/tank.png"))
                                .getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Unit3Btn.setText("Unit3");
        }
        Unit3Btn.setPreferredSize(new java.awt.Dimension(100, 75));
        Unit3Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                Unit3BtnActionPerformed(evt);
            }
        });
        if(BORDERS){
            Unit3Btn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        }
        add(Unit3Btn);

        Tower1Btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Tower2Btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Tower3Btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Ready.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Unit1Btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Unit2Btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Unit3Btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void resetColor() {
        Tower1Btn.setForeground(new Color(0, 0, 0));
        Tower2Btn.setForeground(new Color(0, 0, 0));
        Tower3Btn.setForeground(new Color(0, 0, 0));
        Ready.setForeground(new Color(0, 0, 0));
        Unit1Btn.setForeground(new Color(0, 0, 0));
        Unit2Btn.setForeground(new Color(0, 0, 0));
        Unit3Btn.setForeground(new Color(0, 0, 0));
    }

    private void Tower1BtnActionPerformed(final java.awt.event.ActionEvent evt) {
        resetColor();
        Tower1Btn.setForeground(new Color(255, 0, 0));
        game.selectedButtonNum = 0;
    }

    private void Tower2BtnActionPerformed(final java.awt.event.ActionEvent evt) {
        resetColor();
        Tower2Btn.setForeground(new Color(255, 0, 0));
        game.selectedButtonNum = 1;
    }

    private void Tower3BtnActionPerformed(final java.awt.event.ActionEvent evt) {
        resetColor();
        Tower3Btn.setForeground(new Color(255, 0, 0));
        game.selectedButtonNum = 2;
    }

    private void Unit1BtnActionPerformed(final java.awt.event.ActionEvent evt) {
        resetColor();
        Unit1Btn.setForeground(new Color(255, 0, 0));
        game.selectedButtonNum = 3;
    }

    private void Unit2BtnActionPerformed(final java.awt.event.ActionEvent evt) {
        resetColor();
        Unit2Btn.setForeground(new Color(255, 0, 0));
        game.selectedButtonNum = 4;
    }

    private void Unit3BtnActionPerformed(final java.awt.event.ActionEvent evt) {
        resetColor();
        Unit3Btn.setForeground(new Color(255, 0, 0));
        game.selectedButtonNum = 5;
    }

    private void ReadyBtnActionPerformed(final java.awt.event.ActionEvent evt) {
        resetColor();
        game.selectedButtonNum = -1;

        game.setNextPlayer();
        if (game.readyBtnPushCount == 1) {
            Ready.setText("Attack");
        } else {
            Ready.setText("Ready");
        }

        if (game.isAttacking) {
            Ready.setText("Attacking...");
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JButton Tower1Btn;
    private javax.swing.JButton Tower2Btn;
    private javax.swing.JButton Tower3Btn;
    public javax.swing.JButton Ready;
    private javax.swing.JButton Unit1Btn;
    private javax.swing.JButton Unit2Btn;
    private javax.swing.JButton Unit3Btn;
    // End of variables declaration

    class RoundBtn implements Border {
        private int r;

        RoundBtn(int r) {
            this.r = r;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.r + 1, this.r + 1, this.r + 2, this.r);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y,
                int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, r, r);
        }
    }
}
