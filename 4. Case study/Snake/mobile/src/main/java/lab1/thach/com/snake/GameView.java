package lab1.thach.com.snake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.google.android.gms.games.Games;
import com.google.android.gms.games.event.Event;


/**
 * Created by phamn on 4/9/2016.
 */
public class GameView extends SurfaceView implements  Runnable, View.OnTouchListener{

    private Bitmap bufferImage;
    private SurfaceHolder surfaceHolder;

    private GamePlay gamePlay;

    private Thread thread;

    private String expression;

    private boolean isPlay = false;

    private long waitingTime = 3000;
    private long currentTime = waitingTime;

    private SurfaceHolder holder;

    public GameView(Context main, Bitmap bufferImage) {
        super(main);
        this.bufferImage = bufferImage;
        surfaceHolder = getHolder();

        gamePlay = new GamePlay(getContext());


        holder = getHolder();

        this.setOnTouchListener(this);

        thread = new Thread(this);
        thread.start();

    }

    public void update(){

        gamePlay.Update();


    }
    private void DrawGame(){
        Canvas c = new Canvas(bufferImage);
        c.drawColor(Color.BLACK);

        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setTextSize(50);

        gamePlay.paint(c);

    }
    float time = 0;
    public void render(){

        DrawGame();

        Canvas canvas = null;
        Rect dstRect = new Rect();

        Matrix mMatrix = new Matrix();
        RectF src = new RectF(0, 0, bufferImage.getWidth(), bufferImage.getHeight());
        RectF dst = new RectF(0, 0, main.size.x , main.size.y);
        mMatrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);

            canvas = surfaceHolder.lockCanvas();
            canvas.getClipBounds(dstRect);
            //canvas.drawBitmap(bufferImage, 0, 0, null);
            canvas.concat(mMatrix);
            canvas.drawBitmap(bufferImage, 0, 0, null);

                //canvas.scale(main.ScaleX, main.ScaleY);
                //onDraw(canvas);
                surfaceHolder.unlockCanvasAndPost(canvas);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        canvas.drawBitmap(bufferImage, 0, 0, null);
    }

    @Override
    public void run() {

        long beginTime = System.currentTimeMillis();

        while(true){

            if (!holder.getSurface().isValid())
                continue;

            update();
            render();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_UP) {
            float xTouch = event.getX();
            float yTouch = event.getY();

            int Action = gamePlay.getTouchEvent(xTouch, yTouch);

            if (Action == GamePlay.TOUCH_MIDDLE) {
                GamePlay.isPlaying = !GamePlay.isPlaying;
                if (GamePlay.isGameOver) {
                    GamePlay.isGameOver = false;
                    gamePlay.snake.resetGame();
                }
            } else if (Action == GamePlay.TOUCH_UP){
                gamePlay.snake.setVector(Snake.GO_UP);
            } else if (Action == GamePlay.TOUCH_DOWN){
                gamePlay.snake.setVector(Snake.GO_DOWN);
            } else if (Action == GamePlay.TOUCH_LEFT){
                gamePlay.snake.setVector(Snake.GO_LEFT);
            } else if (Action == GamePlay.TOUCH_RIGHT){
                 gamePlay.snake.setVector(Snake.GO_RIGHT);
            }
        }

        return true;
    }
}
