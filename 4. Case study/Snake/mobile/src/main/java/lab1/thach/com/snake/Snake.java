package lab1.thach.com.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.google.android.gms.games.Game;

import java.util.Random;

/**
 * Created by phamn on 2/12/2017.
 */

public class Snake {
    int doDai = 3;
    int []x;
    int []y;

    public static int GO_UP = 1;
    public static int GO_DOWN = -1;
    public static int GO_LEFT = 2;
    public static int GO_RIGHT = -2;

    public static int BEGIN_MAXLEN = 10;//them 2 bien static nay de de dang hon trong viec truyen
    public static int BEGIN_SPEED = 200;//gia tri khi reset level

    int vector = Snake.GO_DOWN;

    long t1 = 0;
    long t2 = 0;

    int speed = BEGIN_SPEED;// thay gia tri bang bien da khai bao

    int maxLen = BEGIN_MAXLEN;// thay gia tri bang bien da khai bao

    boolean udAfterChangeVt = true;

    public Snake(){
        x = new int[20];
        y = new int[20];

        x[0]=5;
        y[0]=4;

        x[1]=5;
        y[1]=3;

        x[2]=5;
        y[2]=2;
    }
    public void resetGame(){
        x = new int[100];
        y = new int[100];

        x[0]=5;
        y[0]=4;

        x[1]=5;
        y[1]=3;

        x[2]=5;
        y[2]=2;

        doDai = 3;
        vector = Snake.GO_DOWN;
    }
    public void setVector(int v){
        if(vector != -v && udAfterChangeVt){
            vector = v;
            udAfterChangeVt = false;
        }

    }
    public boolean toaDoCoNamTrongThanRan(int x1, int y1){
        for(int i=0;i<doDai;i++)
            if(x[i]==x1&&y[i]==y1) return true;
        return false;
    }
    public Point layToaDoMoi(){
        Random r = new Random();
        int x;
        int y;
        do{
            x= r.nextInt(19);
            y = r.nextInt(19);
        }while(toaDoCoNamTrongThanRan(x,y));

        return new Point(x,y);
    }
    public int getCurrentSpeed(){
        int speed = 200;
        for(int i = 0;i<GamePlay.CurrentLevel;i++)
            speed*=0.8;
        return speed;
    }
    public void update(){

        if(doDai == maxLen) {
            GamePlay.isPlaying=false;
            resetGame();
            GamePlay.CurrentLevel++;
            maxLen += 5;
            speed = getCurrentSpeed();
        }

        for(int i = 1;i<doDai;i++){
            if(x[0]==x[i]&&y[0]==y[i]){

                if(GamePlay.diem > GamePlay.hightScoreValue) main.context.showInputUser(GamePlay.diem);

                GamePlay.isPlaying=false;
                GamePlay.isGameOver=true;

                speed = BEGIN_SPEED;// Them vao o 2 dong code o day de reset toc do hien tai
                maxLen = BEGIN_MAXLEN;// va do dai toi da dat duoc de qua level hien tai
                GamePlay.diem=0;
                GamePlay.CurrentLevel=1;


            }
        }

        if(System.currentTimeMillis()-t2>200){

            udAfterChangeVt = true;

            Data.HeadGoUp.update();
            Data.HeadGoDown.update();
            Data.HeadGoLeft.update();
            Data.HeadGoRight.update();

            t2 = System.currentTimeMillis();
        }


        if(System.currentTimeMillis()-t1>speed){




            if(GamePlay.bg[y[0]][x[0]]==2){
                doDai++;
                GamePlay.bg[y[0]][x[0]]=0;
                Point newCoor = layToaDoMoi();
                GamePlay.bg[newCoor.y][newCoor.x]=2;

                GamePlay.diem+=100;
            }

            for(int i=doDai-1;i>0;i--){
                y[i]=y[i-1];
                x[i]=x[i-1];
            }

            if(vector == Snake.GO_UP) y[0]--;
            if(vector == Snake.GO_DOWN) y[0]++;
            if(vector == Snake.GO_LEFT) x[0]--;
            if(vector == Snake.GO_RIGHT) x[0]++;

            if(x[0]<0) x[0]= GamePlay.bg[0].length -1 ;
            if(x[0]>GamePlay.bg[0].length - 1) x[0]=0;
            if(y[0]<0) y[0]= GamePlay.bg.length -1 ;
            if(y[0]> GamePlay.bg.length -1) y[0]=0;

            t1 = System.currentTimeMillis();
        }

    }
    public void veRan(Canvas g){
        Paint p = new Paint();
        p.setColor(Color.RED);
        for(int i=1;i<doDai;i++)
            Data.drawImage(Data.imageBody, x[i]*20+GamePlay.padding, y[i]*20+GamePlay.padding, p, g);

        if(vector==Snake.GO_UP) Data.drawImage(Data.HeadGoUp.getCurrentImage(), x[0]*20-6+GamePlay.padding, y[0]*20-6+GamePlay.padding, p, g);
        else if(vector==Snake.GO_DOWN) Data.drawImage(Data.HeadGoDown.getCurrentImage(), x[0]*20-6+GamePlay.padding, y[0]*20-6+GamePlay.padding, p, g);
        else if(vector==Snake.GO_RIGHT) Data.drawImage(Data.HeadGoRight.getCurrentImage(), x[0]*20-6+GamePlay.padding, y[0]*20-6+GamePlay.padding, p, g);
        else if(vector==Snake.GO_LEFT) Data.drawImage(Data.HeadGoLeft.getCurrentImage(), x[0]*20-6+GamePlay.padding, y[0]*20-6+GamePlay.padding, p, g);
    }
}
