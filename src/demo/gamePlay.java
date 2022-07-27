package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class gamePlay extends JPanel implements ActionListener, KeyListener{
    private boolean play=false;
    private int score=0;
    private int totalBricks=10;
    private Timer timer;
    private int delay=0;
    private int ballposX=200;
    private int ballposY=350;
    private int ballXdir=-1;
    private int ballYdir=-2;
    private int playerX=350;
    private Breeks  brick;

    public gamePlay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        timer=new Timer(delay,this);
        timer.start();
        brick=new Breeks(3,7);

    }

    public void paint(Graphics g){
        //black canvas

        g.setColor(Color.BLUE);
        g.fillRect(1,1,692,592);

        //border
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,692,3);
        g.fillRect(0,3,3,592);

        //palate

        g.setColor(Color.BLACK);
        g.fillRect(playerX,550,100,8);

        //bricks

        brick.draw((Graphics2D) g);

        //ball
        g.setColor(Color.RED);
        g.fillOval(ballposX,ballposY,15,15);

        //score
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,20));
        g.drawString("Score: "+score,550,30);

        //game Over
        if(ballposY>=570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;

            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over!! , Score: " + score, 200, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("press Enter to Restart!!", 230, 350);

        }

        //Player win
        if(totalBricks<=0){
            play=false;
            ballXdir=0;
            ballYdir=0;

            g.setColor(Color.white);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("You Won , Score: "+score,200,300);

            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("press Enter to Restart!!",230,350);
        }

    }
    private void moveLeft(){
        play=true;
        playerX-=20;
    }
    private void moveRight(){
        play=true;
        playerX+=20;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(playerX<=0){
                playerX=0;
            }
            else {
                moveLeft();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(playerX>=600){
                playerX=600;
            }
            else {
                moveRight();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                score=0;
                totalBricks=21;
                ballposX=200;
                ballposY=350;
                ballXdir=-1;
                ballYdir=-2;
                playerX=320;
                brick=new Breeks(3,7);
            }
        }
        repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (play){
            if(ballposX<=0){
                ballXdir=-ballXdir;
            }
            if(ballposY<=0){
                ballYdir=-ballYdir;
            }
            if(ballposX>=670){
                ballXdir=-ballXdir;
            }
            Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
            Rectangle paddleRect=new Rectangle(playerX,550,100,8);

            if(ballRect.intersects(paddleRect)){
                ballYdir=-ballYdir;
            }

            A:for(int i=0;i<brick.map.length;i++){
                for(int j=0;j<brick.map[0].length;j++){
                    if(brick.map[i][j]>0){
                        int width=brick.brickWidth;
                        int height=brick.brickHeight;
                        int brickXpos=80+j*width;
                        int brickYpos=50+i*height;

                        Rectangle brickRect=new Rectangle(brickXpos,brickYpos,width,height);

                        if(ballRect.intersects(brickRect)){
                            brick.setBrick(0,i,j);
                            totalBricks--;
                            score+=5;

                            if(ballposX+90<=brickXpos || ballposX+1>=brickXpos+width){
                                ballXdir=-ballXdir;
                            }
                            else{
                                ballYdir=-ballYdir;
                            }

                            break A;
                        }
                    }
                }
            }

            ballposX+=ballXdir;
            ballposY+=ballYdir;

        }
        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
