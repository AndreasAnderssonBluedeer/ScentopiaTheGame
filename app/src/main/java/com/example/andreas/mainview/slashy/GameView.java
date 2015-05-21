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
 * This Class is the graphical drawing canvas.
 */
     public class GameView extends SurfaceView {

        protected Canvas c;
        protected SurfaceHolder holder;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

            holder = getHolder();
            holder.addCallback(new SurfaceHolder.Callback() {   //Logic for what happends onDraw.
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
        }



    @Override
        protected void onDraw(Canvas canvas) {  //Only used as background at the game start.

            canvas.drawBitmap(BitmapFactory.decodeResource(getResources()
                    , R.drawable.slashy_background2),0,0,null);
        }

    protected void onDraw(Bitmap bmp,float w,float h) { //Drawing Method.

        c.drawBitmap(bmp, w, h, null);
    }
    public void lock(){ //Locks canvas while drawing.
        holder.lockCanvas();
    }

    public void unlock(){   //Unlocks canvas and shows it.
        holder.unlockCanvasAndPost(c);
    }
    }

