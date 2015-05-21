package com.example.andreas.mainview.slashy;

import android.graphics.Bitmap;
import java.util.*;
/**
 * Created by Andreas on 2015-04-10.
 * Writes monsters from the left side of the screen.
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
        setStartPosition();
    }
    public void setStartPosition(){     //A random start position at the left screenside to prevent
        Random rand=new Random();       //two monsters at the exact same position.
        x=rand.nextInt(200)-200;
    }
    public void setSpeed(float speed){  //Set running speed.
        this.speed=speed;
    }
    public float getPosition(){     //Get current position.
        return x;
    }
    public float updatePosition(){  //Update position at screen.
        x=x+speed*density;
        return x;
    }

    public Bitmap getBmp(){//Determine which picture should be displayed to make "running animation".
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
