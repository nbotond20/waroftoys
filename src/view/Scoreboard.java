package view;

import javax.swing.*;
import java.awt.*;

public class Scoreboard extends JPanel {

    public Scoreboard() {
        initComponents();
    }

    private JLabel Player1Balance;
    private JLabel Player1CoinIcon;
    private JLabel Player1Name;
    private JLabel Player2Balance;
    private JLabel Player2CoinIcon;
    private JLabel Player2Name;
    private JLabel TimerLabel;
// teszt
    private void initComponents() {

        Player1Name = new JLabel();
        Player2Name = new JLabel();
        TimerLabel = new JLabel();
        Player1Balance = new JLabel();
        Player2Balance = new JLabel();
        Player2CoinIcon = new JLabel();
        Player1CoinIcon = new JLabel();

        // JPanel alapvető beállításai: 
        // setPrefferedSize = new Dimension(főablak mérete, 130) ? 
        setPreferredSize(new Dimension(1300, 130));
        // setRequestFocusEnabled(false); - ötletem sincs mit csinál.

        // Labelek és szövegeik beállításai:
        Player1Name.setFont(new Font("Tahoma", 1, 24));
        Player1Name.setHorizontalAlignment(SwingConstants.CENTER);
        Player1Name.setText("Player 2"); //  Player 2 lekért nevének a helye

        Player2Name.setFont(new Font("Tahoma", 1, 24));
        Player2Name.setHorizontalAlignment(SwingConstants.CENTER);
        Player2Name.setText("Player 1");  // Player 1 lekért nevének a helye

        TimerLabel.setFont(new Font("Tahoma", 0, 36)); 
        TimerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        TimerLabel.setText("0:00");

        Player1Balance.setFont(new Font("Tahoma", 0, 14));
        Player1Balance.setHorizontalAlignment(SwingConstants.CENTER);
        Player1Balance.setText("1200");

        Player2Balance.setFont(new Font("Tahoma", 0, 14));
        Player2Balance.setHorizontalAlignment(SwingConstants.CENTER);
        Player2Balance.setText("1200");

        Player2CoinIcon.setIcon(new ImageIcon("../../res/scoreboard/coinicon.png"));
        Player2CoinIcon.setPreferredSize(new Dimension(16, 16));

        Player1CoinIcon.setIcon(new ImageIcon("../../res/scoreboard/coinicon.png"));
        Player1CoinIcon.setPreferredSize(new Dimension(16, 16));


        // Elrendezés: NetBeans GUI Builder által.
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 277, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Player1CoinIcon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Player2Balance, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        .addGap(170, 170, 170))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Player2Name, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123)))
                .addComponent(TimerLabel, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(Player1Name, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(Player2CoinIcon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Player1Balance, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
                .addGap(384, 384, 384))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Player2Name, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Player1CoinIcon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Player1Name, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(Player2Balance)
                            .addComponent(Player1Balance)
                            .addComponent(Player2CoinIcon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TimerLabel)
                        .addGap(1, 1, 1)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
    }
}
