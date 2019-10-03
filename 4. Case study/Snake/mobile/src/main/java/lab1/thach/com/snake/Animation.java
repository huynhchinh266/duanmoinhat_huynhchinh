package lab1.thach.com.snake;

import android.graphics.Bitmap;

/**
 * Created by phamn on 2/12/2017.
 */

public class Animation {

    public Bitmap[] images;
    public int n;
    public int currentImage;
    public Animation(){
        n=0;
        currentImage = 0;
    }
    public void addImage(Bitmap image){
        Bitmap[] ar = images;
        images = new Bitmap[n+1];

        for(int i=0;i<n;i++) images[i]=ar[i];
        images[n]=image;
        n++;
    }
    public void update(){
        currentImage++;
        if(currentImage>=n) currentImage=0;
    }
    public Bitmap getCurrentImage(){
        return images[currentImage];
    }
}

