package view;

import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Insets;

import model.Game;

public class ButtonPanel extends JPanel {
    private final Game game;

    public ButtonPanel(final Game game) {
        this.game = game;
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

        Tower1Btn.setText("Tower1");
        Tower1Btn.setPreferredSize(new java.awt.Dimension(100, 75));
        Tower1Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                Tower1BtnActionPerformed(evt);
            }
        });
        add(Tower1Btn);

        Tower2Btn.setText("Tower2");
        Tower2Btn.setPreferredSize(new java.awt.Dimension(100, 75));
        Tower2Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                Tower2BtnActionPerformed(evt);
            }
        });
        add(Tower2Btn);

        Tower3Btn.setText("Tower3");
        Tower3Btn.setPreferredSize(new java.awt.Dimension(100, 75));
        Tower3Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                Tower3BtnActionPerformed(evt);
            }
        });
        add(Tower3Btn);

        Ready.setText("Ready");
        Ready.setPreferredSize(new java.awt.Dimension(100, 75));
        Ready.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                ReadyBtnActionPerformed(evt);
            }
        });
        add(Ready);

        Unit1Btn.setText("Unit1");
        Unit1Btn.setPreferredSize(new java.awt.Dimension(100, 75));
        Unit1Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                Unit1BtnActionPerformed(evt);
            }
        });
        add(Unit1Btn);

        Unit2Btn.setText("Unit2");
        Unit2Btn.setPreferredSize(new java.awt.Dimension(100, 75));
        Unit2Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                Unit2BtnActionPerformed(evt);
            }
        });
        add(Unit2Btn);

        Unit3Btn.setText("Unit3");
        Unit3Btn.setPreferredSize(new java.awt.Dimension(100, 75));
        Unit3Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                Unit3BtnActionPerformed(evt);
            }
        });
        add(Unit3Btn);

        Tower1Btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Tower2Btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Tower3Btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Ready.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Unit1Btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Unit2Btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Unit3Btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void resetColor(){
        Tower1Btn.setForeground(new Color(0,0,0));
        Tower2Btn.setForeground(new Color(0,0,0));
        Tower3Btn.setForeground(new Color(0,0,0));
        Ready.setForeground(new Color(0,0,0));
        Unit1Btn.setForeground(new Color(0,0,0));
        Unit2Btn.setForeground(new Color(0,0,0));
        Unit3Btn.setForeground(new Color(0,0,0));
    }

    private void Tower1BtnActionPerformed(final java.awt.event.ActionEvent evt) {
        resetColor();
        Tower1Btn.setForeground(new Color(255,0,0));
        game.selectedButtonNum = 0;
    }

    private void Tower2BtnActionPerformed(final java.awt.event.ActionEvent evt) {
        resetColor();
        Tower2Btn.setForeground(new Color(255,0,0));
        game.selectedButtonNum = 1;
    }

    private void Tower3BtnActionPerformed(final java.awt.event.ActionEvent evt) {
        resetColor();
        Tower3Btn.setForeground(new Color(255,0,0));
        game.selectedButtonNum = 2;
    }

    private void Unit1BtnActionPerformed(final java.awt.event.ActionEvent evt) {
        resetColor();
        Unit1Btn.setForeground(new Color(255,0,0));
        game.selectedButtonNum = 3;
    }

    private void Unit2BtnActionPerformed(final java.awt.event.ActionEvent evt) {
        resetColor();
        Unit2Btn.setForeground(new Color(255,0,0));
        game.selectedButtonNum = 4;
    }

    private void Unit3BtnActionPerformed(final java.awt.event.ActionEvent evt) {
        resetColor();
        Unit3Btn.setForeground(new Color(255,0,0));
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
