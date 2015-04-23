package com.example.andreas.mainview.shroom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.andreas.mainview.R;

import java.util.Random;

/**
 * Created by Andreas on 2015-03-24.
 */
public class GameController {
    private WindowManager wm;
    private Display display;
    

    private Drawable drawable;
    private Context context;
    private ShroomActivity shroomAct;

    private Random randW = new Random();
    private Random randH = new Random();


    public GameController(ShroomActivity shroomAct, Context context) {     //Behöver Context till ObjectIcon
        //Kommunicerar med GUI i MainActivity


        this.shroomAct = shroomAct;
        this.context = context;
        this.drawable = context.getResources().getDrawable(R.drawable.shroom_shroom);
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        display = wm.getDefaultDisplay();                            //Hämta Displayen för att få dess storlek senare
    }

    public void addObject() {        //Lägg till svamp på skärmen. slumpa en position på skärmen.

      ObjectIcon  btn= new ObjectIcon(context, this, drawable);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 140);

        params.leftMargin = 100 + randW.nextInt(display.getWidth() - 300); //SlumpPosition
        params.topMargin = 150 + randH.nextInt(display.getHeight() - 600);

        shroomAct.layout.addView(btn, params); //Lägg till knapp o dess position till "panelen"




    }

    public class Remove implements Runnable{
        private ObjectIcon btn;
        public Remove(ObjectIcon btn){
            this.btn=btn;
        }
        @Override
        public void run() {
            shroomAct.layout.removeView(btn);
        }
    }

    public void removeObject(ObjectIcon btn) {  //Ta emot id för iconobjektet i listan och radera.

        shroomAct.runOnUiThread(new Remove(btn));



    }

    public void addPoints() {    //Uppdatera poängen
        shroomAct.addPoints();

    }




}



