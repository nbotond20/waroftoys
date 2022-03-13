package view;

import javax.swing.JPanel;

import model.Game;

public class ButtonPanel extends JPanel {
    private Game game;


    public ButtonPanel(Game game) {
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
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tower1BtnActionPerformed(evt);
            }
        });
        add(Tower1Btn);

        Tower2Btn.setText("Tower2");
        Tower2Btn.setPreferredSize(new java.awt.Dimension(100, 75));
        Tower2Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tower2BtnActionPerformed(evt);
            }
        });
        add(Tower2Btn);

        Tower3Btn.setText("Tower3");
        Tower3Btn.setPreferredSize(new java.awt.Dimension(100, 75));
        Tower3Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tower3BtnActionPerformed(evt);
            }
        });
        add(Tower3Btn);

        Ready.setText("READY");
        Ready.setPreferredSize(new java.awt.Dimension(100, 75));
        Ready.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReadyBtnActionPerformed(evt);
            }
        });
        add(Ready);

        Unit1Btn.setText("Unit1");
        Unit1Btn.setPreferredSize(new java.awt.Dimension(100, 75));
        Unit1Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Unit1BtnActionPerformed(evt);
            }
        });
        add(Unit1Btn);

        Unit2Btn.setText("Unit2");
        Unit2Btn.setPreferredSize(new java.awt.Dimension(100, 75));
        Unit2Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Unit2BtnActionPerformed(evt);
            }
        });
        add(Unit2Btn);

        Unit3Btn.setText("Unit3");
        Unit3Btn.setPreferredSize(new java.awt.Dimension(100, 75));
        Unit3Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Unit3BtnActionPerformed(evt);
            }
        });
        add(Unit3Btn);
    }                      

    private void Tower1BtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void Tower2BtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void Tower3BtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void Unit1BtnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void Unit2BtnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void Unit3BtnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }
    
    private void ReadyBtnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        game.isAttacking = !game.isAttacking;
        if(game.isAttacking) Ready.setText("STOP");
        else Ready.setText("READY");
    } 


    // Variables declaration - do not modify                     
    private javax.swing.JButton Tower1Btn;
    private javax.swing.JButton Tower2Btn;
    private javax.swing.JButton Tower3Btn;
    private javax.swing.JButton Ready;
    private javax.swing.JButton Unit1Btn;
    private javax.swing.JButton Unit2Btn;
    private javax.swing.JButton Unit3Btn;
    // End of variables declaration                   
}
