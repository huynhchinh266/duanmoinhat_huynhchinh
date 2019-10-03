package lab1.thach.com.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by phamn on 4/10/2016.
 */
public class GamePlay {

    public static final float[] BtUp = {640, 270, 50, 50};
    public static final float[] BtDown = {640, 360, 50, 50};
    public static final float[] BtLeft = {590, 315, 50, 50};
    public static final float[] BtRight = {690, 315, 50, 50};
    public static final float[] BtMiddle = {665 - 18, 340 - 18, 36, 36};


    public static final int TOUCH_UP = 1;
    public static final int TOUCH_DOWN = 2;
    public static final int TOUCH_LEFT = 3;
    public static final int TOUCH_RIGHT = 4;
    public static final int TOUCH_MIDDLE= 5;
    public static final int TOUCH_NONE= -1;




    public int getTouchEvent(float xTouch, float yTouch){

        int touchEvent = TOUCH_NONE;

        if(xTouch/main.ScaleX > BtUp[0] && xTouch/main.ScaleX < (BtUp[0]+BtUp[2])
                && yTouch/main.ScaleY > BtUp[1] && yTouch/main.ScaleY < (BtUp[1]+BtUp[3]))
            touchEvent = TOUCH_UP;
        else if(xTouch/main.ScaleX > BtDown[0] && xTouch/main.ScaleX < (BtDown[0]+BtDown[2])
                && yTouch/main.ScaleY > BtDown[1] && yTouch/main.ScaleY < (BtDown[1]+BtDown[3]))
            touchEvent = TOUCH_DOWN;
        else if(xTouch/main.ScaleX > BtLeft[0] && xTouch/main.ScaleX < (BtLeft[0]+BtLeft[2])
                && yTouch/main.ScaleY > BtLeft[1] && yTouch/main.ScaleY < (BtLeft[1]+BtLeft[3]))
            touchEvent = TOUCH_LEFT;
        else if(xTouch/main.ScaleX > BtRight[0] && xTouch/main.ScaleX < (BtRight[0]+BtRight[2])
                && yTouch/main.ScaleY > BtRight[1] && yTouch/main.ScaleY < (BtRight[1]+BtRight[3]))
            touchEvent = TOUCH_RIGHT;
        else if(xTouch/main.ScaleX > BtMiddle[0] && xTouch/main.ScaleX < (BtMiddle[0]+BtMiddle[2])
                && yTouch/main.ScaleY > BtMiddle[1] && yTouch/main.ScaleY < (BtMiddle[1]+BtMiddle[3]))
            touchEvent = TOUCH_MIDDLE;

        return touchEvent;
    }

    static int [][] bg = new int [20][25];

    static int padding = 10;

    static boolean isPlaying = false;
    static boolean enableTextStartGame = true;

    Snake snake;

    static int CurrentLevel = 1;
    static int diem = 0;

    static String hightScoreName;
    static int hightScoreValue;

    static boolean isGameOver = false;
    public GamePlay(Context context){

        snake = new Snake();

        bg[10][10]=2;

        SaveData saveData = new SaveData(context);
        hightScoreName = saveData.getHightScoreName();
        hightScoreValue = saveData.getHightScoreValue();

    }
    long t = 0;
    long t2 = 0;
    public void Update(){

        if(System.currentTimeMillis()-t2>500){
            enableTextStartGame=!enableTextStartGame;
            t2 = System.currentTimeMillis();
        }

        if(isPlaying){
            if(System.currentTimeMillis()-t>200){
                Data.Worm.update();
                t=System.currentTimeMillis();
            }
            snake.update();
        }

    }
    public void paintBg(Canvas g){
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        g.drawRect(0, 0, main.WIDTH, main.HEIGHT, p);
        for(int i=0;i<bg.length;i++)
            for(int j=0;j<bg[0].length;j++){
                if(bg[i][j]==2) {
                    g.drawBitmap(Data.Worm.getCurrentImage(), j * 20 - 7 + padding, i * 20 - 7 + padding, p);
                }
            }

    }
    private void veKhung(Canvas g){
        Paint p = new Paint();
        p.setColor(Color.YELLOW);
        g.drawRect(padding + bg[0].length*20, padding + 0, bg[0].length*20 + 4 + padding, bg.length * 20 + padding, p);
    }
    public void paint(Canvas g){
        paintBg(g);
        snake.veRan(g);
        veKhung(g);

        Paint p = new Paint();

        if(!isPlaying){
            if(enableTextStartGame){
                p.setColor(Color.WHITE);
                g.drawText("NHẤN NÚT MÀU TRẮNG ĐỂ BẮT ĐẦU GAME!", 160, 200, p);
            }
        }
        if(isGameOver){
            p.setColor(Color.WHITE);
            g.drawText("THUA CUỘC!", 200, 250, p);
        }
        p.setColor(Color.WHITE);
        g.drawText("Cấp độ: "+CurrentLevel, 550, 50, p);

        g.drawText("Điểm: "+diem, 550, 90, p);


        p.setColor(Color.BLUE);
        g.drawRect(650, 280, 680, 310, p);
        g.drawRect(650, 370, 680, 400, p);
        g.drawRect(600, 325, 630, 355, p);
        g.drawRect(700, 325, 730, 355, p);

        p.setColor(Color.WHITE);
        g.drawCircle(665, 340, 18, p);

        if(hightScoreName != "") {
            g.drawText("Dẫn đầu:", 550, 130, p);
            g.drawText(hightScoreName + " : " + hightScoreValue, 560, 160, p);
        }
    }
}
/**
 * Đoạn 1
 */
