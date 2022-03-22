package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;

public class Scoreboard extends JPanel {
    private final boolean BORDERS = false;

    Timer timer;
    int second, minute;
    String ddSecond, ddMinute;
    DecimalFormat dFormat = new DecimalFormat("00");

    public JLabel Player1Balance;
    private JLabel Player1CoinIcon;
    public JLabel Player1Name;
    public JLabel Player2Balance;
    private JLabel Player2CoinIcon;
    public JLabel Player2Name;
    private JLabel TimerLabel;
    private Game game;

    public Scoreboard(Game game) {
        this.game = game;
        if(BORDERS){
            this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        }
        initComponents();
        startTimer();
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        Player1Name = new JLabel();
        Player2Name = new JLabel();
        TimerLabel = new JLabel();
        Player2CoinIcon = new JLabel();
        Player1CoinIcon = new JLabel();
        Player1Balance = new JLabel();
        Player2Balance = new JLabel();

        setMaximumSize(new Dimension(1024, 80));
        setMinimumSize(new Dimension(1024, 80));
        setPreferredSize(new Dimension(1024, 80));
        setRequestFocusEnabled(false);
        setLayout(new GridBagLayout());

        Player2Name.setFont(new Font("Tahoma", 1, 24));
        Player2Name.setHorizontalAlignment(SwingConstants.CENTER);
        Player2Name.setText(game.players.get(1).name);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 100, 0, 0);
        add(Player2Name, gridBagConstraints);
        Player2Name.getAccessibleContext().setAccessibleName("Player1Name");

        Player1Name.setFont(new Font("Tahoma", 1, 24));
        Player1Name.setHorizontalAlignment(SwingConstants.CENTER);
        Player1Name.setText(game.players.get(0).name);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 28, 0, 100);
        add(Player1Name, gridBagConstraints);

        TimerLabel.setFont(new Font("Tahoma", 0, 36));
        TimerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        TimerLabel.setText("0:00");
        TimerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new Insets(0, 40, 0, 40);
        add(TimerLabel, gridBagConstraints);

        try {
            Player2CoinIcon
                    .setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/scoreboard/coinicon.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 4, 0, 140);
        add(Player2CoinIcon, gridBagConstraints);

        try {
            Player1CoinIcon
                    .setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/scoreboard/coinicon.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 106, 0, 0);
        add(Player1CoinIcon, gridBagConstraints);

        Player2Balance.setFont(new Font("Tahoma", 0, 14));
        Player2Balance.setHorizontalAlignment(SwingConstants.CENTER);
        Player2Balance.setText(String.valueOf(game.players.get(1).balance));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new Insets(0, 112, 0, 0);
        add(Player2Balance, gridBagConstraints);

        Player1Balance.setFont(new Font("Tahoma", 0, 14));
        Player1Balance.setHorizontalAlignment(SwingConstants.CENTER);
        Player1Balance.setText(String.valueOf(game.players.get(0).balance));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new Insets(0, 0, 0, 60);
        add(Player1Balance, gridBagConstraints);

    }

    public void startTimer() {
        TimerLabel.setText("00:00");
        second = 0;
        minute = 0;
        normalTimer();
        timer.start();
    }

    public void normalTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second++;

                ddSecond = dFormat.format(second);
                ddMinute = dFormat.format(minute);
                TimerLabel.setText(ddMinute + ":" + ddSecond);

                if (second == 60) {
                    second = 0;
                    minute++;

                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);
                    TimerLabel.setText(ddMinute + ":" + ddSecond);
                }
            }
        });
    }
}
