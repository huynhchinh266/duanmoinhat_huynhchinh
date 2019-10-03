package lab1.thach.com.snake;


import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;

import android.view.Display;
import android.widget.RelativeLayout;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;


public class main extends Activity {

    private Bitmap bufferImage;

    public static final float WIDTH = 800f;
    public static final float HEIGHT = 480f;
    public static float ScaleX = 0;
    public static float ScaleY = 0;

    public static Point size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = this.getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        float w = size.x;
        float h = size.y;
        ScaleX = w/WIDTH;
        ScaleY = h/HEIGHT;

        context = this;

        Data.loadImage(this);
        Data.loadAllAnim();

        bufferImage = Bitmap.createBitmap((int)WIDTH,(int) HEIGHT, Bitmap.Config.RGB_565);
        setContentView(new GameView(this, bufferImage));

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        RelativeLayout layout = new RelativeLayout(this);
        layout.addView(new GameView(this, bufferImage));
        setContentView(layout);

    }
    public Bitmap getBufferImage(){
        return bufferImage;
    }

    public static main context;

    public void showInputUser(int score){
        InputuserDialog dialog = new InputuserDialog();
        dialog.setScore(score);
        dialog.show(getFragmentManager(), "input");
    }
}
