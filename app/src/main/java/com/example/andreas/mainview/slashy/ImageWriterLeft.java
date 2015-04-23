package com.example.andreas.mainview.slashy;

import android.graphics.Bitmap;

/**
 * Created by Andreas on 2015-04-10.
 */
public class ImageWriterLeft {
    private Bitmap bmp1,bmp2,bmp3;
    private float x,speed=1;
    private int state;
    private float density;

    public ImageWriterLeft(Bitmap bmp1,Bitmap bmp2,Bitmap bmp3,float density){
        this.bmp1=bmp1;
        this.bmp2=bmp2;
        this.bmp3=bmp3;
        this.density=density;
    }
    public void setStartPosition(int x){
        this.x=x;
    }
    public void setSpeed(float speed){
        this.speed=speed;
    }
    public float getPosition(){
        return x;
    }
    public float updatePosition(){
        x=x+speed*density;
        return x;
    }

    public Bitmap getBmp(){
        if(state==0){
            state++;
            return bmp1;
        }else if(state==1){
            state++;
            return bmp2;
        }
        else{
            state=0;
            return bmp3;
        }
    }
}
