package demo;

import java.awt.*;

public class Breeks {
    public int map[][];
    public int brickWidth;
    public int brickHeight;


    public Breeks(int r,int c){
        map=new int[r][c];

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                map[i][j]=1;
            }
        }
        brickWidth=540/c;
        brickHeight=150/r;
    }
    public void setBrick(int value,int r,int c){
        map[r][c]=value;
    }
    public void draw(Graphics2D g){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]>0){
                    g.setColor(Color.pink);
                    g.fillRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);
                    g.setColor(Color.black);
                    g.setStroke(new BasicStroke(3));
                    g.drawRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);
                }
            }
        }

    }


}
