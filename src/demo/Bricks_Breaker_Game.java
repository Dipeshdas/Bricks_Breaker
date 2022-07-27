package demo;

import javax.swing.*;

public class Bricks_Breaker_Game {
    public static void main(String[] args) {
        JFrame f=new JFrame();
        f.setTitle("Bricks Breaker");
        f.setSize(700,600);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);

        gamePlay game=new gamePlay();
        f.add(game);
    }
}
