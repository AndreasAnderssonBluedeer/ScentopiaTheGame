package com.example.andreas.mainview.slashy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.andreas.mainview.R;

/**
 * Created by Andreas on 2015-04-02.
 */
     public class GameView extends SurfaceView {
        protected Canvas c;
        private Bitmap bm;
        protected SurfaceHolder holder;
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);


            holder = getHolder();
            holder.addCallback(new SurfaceHolder.Callback() {

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                }

                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    c = holder.lockCanvas(null);
                    onDraw(c);             //Draw()?
                    holder.unlockCanvasAndPost(c);
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format,
                                           int width, int height) {
                }
            });
            bm = BitmapFactory.decodeResource(getResources(), R.drawable.slashy_knight_neutral);
        }



    @Override
        protected void onDraw(Canvas canvas) {

            canvas.drawBitmap(BitmapFactory.decodeResource(getResources()
                    , R.drawable.slashy_background),0,0,null);

        }

        protected void onDraw(Bitmap bmp,float w,int h) {

            c.drawBitmap(bmp, w, h, null);



    }
    protected void onDraw(Bitmap bmp,float w,float h) {

        c.drawBitmap(bmp, w, h, null);



    }
    public void lock(){
        holder.lockCanvas();
    }
    public void unlock(){
        holder.unlockCanvasAndPost(c);
    }
    }

