package lab1.thach.com.snake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by phamn on 2/12/2017.
 */

public class Data {

    public static Bitmap sprite;

    public static Bitmap imageHead;
    public static Bitmap imageHead_GoLeft;
    public static Bitmap imageHead_GoRight;
    public static Bitmap imageHead_GoUp;
    public static Bitmap imageHead_GoDown;

    public static Bitmap imageWorm2;
    public static Bitmap imageWorm3;

    public static Bitmap imageBody;
    public static Bitmap imageWorm;

    public static Animation HeadGoUp;
    public static Animation HeadGoDown;
    public static Animation HeadGoRight;
    public static Animation HeadGoLeft;

    public static Animation Worm;

    private static Bitmap subImage(Bitmap source, int startX, int startY, int width, int height){
        Bitmap dest = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_4444);
        Canvas c = new Canvas(dest);
        Rect rectDest = new Rect(-startX, - startY, source.getWidth() - startX, source.getHeight() - startY);
        Log.d("game", "startX = " + startX+", heght 1 = "+source.getHeight()+", height 2 "+rectDest.height());
        c.drawBitmap(source, null, rectDest, new Paint());
        return dest;
    }

    public static void loadImage(Context context){
        try{

            sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite1);
            sprite = Bitmap.createScaledBitmap(sprite, 200, 100, true);

            imageHead = subImage(sprite, 2, 3, 30, 30);
            imageBody = subImage(sprite,7, 79, 20, 20);

            imageHead_GoLeft = subImage(sprite,75, 3, 30, 30);
            imageHead_GoRight = subImage(sprite,110, 3, 30, 30);
            imageHead_GoUp = subImage(sprite,145, 3, 30, 30);
            imageHead_GoDown = subImage(sprite,39, 3, 30, 30);

            imageWorm = subImage(sprite,2, 40, 30, 30);
            imageWorm2 = subImage(sprite,32, 40, 30, 30);
            imageWorm3 = subImage(sprite,63, 40, 30, 30);
        }catch(Exception e){}
    }
    public static void loadAllAnim(){
        HeadGoUp = new Animation();
        HeadGoUp.addImage(imageHead);
        HeadGoUp.addImage(imageHead_GoUp);

        HeadGoDown = new Animation();
        HeadGoDown.addImage(imageHead);
        HeadGoDown.addImage(imageHead_GoDown);

        HeadGoRight = new Animation();
        HeadGoRight.addImage(imageHead);
        HeadGoRight.addImage(imageHead_GoRight);

        HeadGoLeft = new Animation();
        HeadGoLeft.addImage(imageHead);
        HeadGoLeft.addImage(imageHead_GoLeft);

        Worm = new Animation();
        Worm.addImage(imageWorm);
        Worm.addImage(imageWorm2);
        Worm.addImage(imageWorm3);
        Worm.addImage(imageWorm2);
    }

    public static void drawImage(Bitmap bitmap, int x, int y, Paint p, Canvas canvas){
        Rect rect = new Rect(x, y, bitmap.getWidth() + x, bitmap.getHeight() + y);
        canvas.drawBitmap(bitmap, null, rect, p);
    }
}
