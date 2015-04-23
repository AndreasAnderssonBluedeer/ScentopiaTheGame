package com.example.andreas.mainview.slashy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andreas.mainview.R;

import java.util.ArrayList;

public class SlashyActivity extends Activity {

    private GameView gv;
    private Button btnL;
    private Button btnR;
    private Bitmap bmp;
    private boolean clickedR, clickedL;
    private TextView points;
    private int p;
    private float pos,density;
    private Level lv;
    private ArrayList <ImageWriterLeft> imgLlist;
    private ArrayList <ImageWriterRight> imgRlist;
    private ImageView hpImg;

    private float speedL=1,speedR=1;

    private WindowManager wm;
    private Display display;
    private float screenH;
    private int screenW;
    private int life=0;
    private int axePic;
    private AttackerThread thread;
    private boolean play=true;
    private  DisplayMetrics metrics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slashyactivity_main);

        gv = (GameView) findViewById(R.id.gv);
        btnR = (Button) findViewById(R.id.btnR);
        btnL = (Button) findViewById(R.id.btnL);
        points=(TextView)findViewById(R.id.points);
        hpImg=(ImageView)findViewById(R.id.hpImg);

        btnL.setOnClickListener(new ButtonLeft());
        btnR.setOnClickListener(new ButtonRight());
        lv= new Level(this);
        imgLlist=new ArrayList<ImageWriterLeft>();
        imgRlist=new ArrayList<ImageWriterRight>();
        wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);

        display = wm.getDefaultDisplay();
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        hpImg.setBackground(getResources().getDrawable(R.drawable.slashy_hitpoints3));
        start();
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //******************* Game ********************


    public void start() {   //StartDialog.
        new AlertDialog.Builder(this)
                .setMessage("Axy TIME?...")
                .setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                      thread= new AttackerThread();
                      thread.start();
                    }
                })

                .show();

    }
    public void gameover(){ //The game is over, LOSER!
        play=false;
        new AlertDialog.Builder(this)
                .setMessage("I'm not angry.. Just DISAPPOINTED!!!\n"+
                "Points: "+p+
                 "\nMoney: "+p*1.2)

                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        System.exit(0);
                    }
                })

                .show();

    }

    public void newRight(){     //adds a new attacker from the right side.

        imgRlist.add(new ImageWriterRight(BitmapFactory.decodeResource(getResources(), R.drawable.slashy_monster_right1),
                BitmapFactory.decodeResource(getResources(), R.drawable.slashy_monster_right2)
                ,BitmapFactory.decodeResource(getResources(), R.drawable.slashy_monster_right3), screenW,getResources().getDisplayMetrics().density));

        imgRlist.get(imgRlist.size()-1).setSpeed(speedR);
    }
    public void newLeft(){      //adds a new attacker from the left side.

        imgLlist.add(new ImageWriterLeft(BitmapFactory.decodeResource(getResources(), R.drawable.slashy_monster_left1)
                , BitmapFactory.decodeResource(getResources(), R.drawable.slashy_monster_left2),
                BitmapFactory.decodeResource(getResources(), R.drawable.slashy_monster_left3),getResources().getDisplayMetrics().density));

        imgLlist.get(imgLlist.size()-1).setSpeed(speedL);
        }


        public void knight() {       //Control the knights position/Movement.

            if (clickedR) {
                if(axePic >2&& axePic< 5) {     //R POS behöver ändras.screenW
                    gv.onDraw(bmp = BitmapFactory.decodeResource(getResources()
                            , R.drawable.slashy_knight_rightfull), screenW / 2 - 15 * density,screenH);
                }else{
                    gv.onDraw(bmp = BitmapFactory.decodeResource(getResources()
                            , R.drawable.slashy_knight_righthalf), screenW / 2 - 15 * density,screenH);
                }
                hitright();


            } else if (clickedL) {
                if(axePic >2&& axePic< 5) {
                    gv.onDraw(bmp = BitmapFactory.decodeResource(getResources()
                            , R.drawable.slashy_knight_leftfull), screenW / 2 - 85 * density,screenH);
                }else{
                    gv.onDraw(bmp = BitmapFactory.decodeResource(getResources()
                            , R.drawable.slashy_knight_lefthalf), screenW / 2 - 85 * density,screenH);
                }
               hitleft();


            } else {
                gv.onDraw(bmp = BitmapFactory.decodeResource(getResources(), R.drawable.slashy_knight_neutral)
                        , screenW /2-25 * density,screenH);

            }


        }
        public void damaged(){  // Did your knight get attacked?

            float attack;
            for(int i=0;i<imgLlist.size();i++) {    //Control attackers from the left.
                attack=imgLlist.get(i).getPosition();

                 if ((attack>190* density) && (attack<250* density)) {    //Limit the attack to one side

                    life++;

                    imgLlist.remove(imgLlist.get(i));
                    newLeft();
                }
            }

             for(int i=0;i<imgRlist.size();i++) {       //Control attacker from the right.
                 attack=imgRlist.get(i).getPosition();

                 if (

                         (attack<275 * density)       //&& to limit attack to one side.
                                 &&(attack>250* density)) {

                     life++;

                    imgRlist.remove(imgRlist.get(i));
                    newRight();
                }
            }
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
            switch (life){
                case 0:     //full life.
                          break;

                case 1:   hpImg.setBackground(getResources().getDrawable(R.drawable.slashy_hitpoints2));
                          break; //You've taken one hit.

                case 2:   hpImg.setBackground(getResources().getDrawable(R.drawable.slashy_hitpoints1));
                          break; //You've taken two hits.

                case 3:   gameover();
                          break;
            }
                }
            });
        }


        public void hitleft() {  //Control if you hit enemy to the left.

            for(int i=0;i<imgLlist.size();i++) {
               pos= imgLlist.get(i).getPosition();

                if (pos >= 160 * density && pos < 200 * density) {

                    addPoints();
                    newLeft();
                    imgLlist.remove(i);


                }
            }


        }
        public void hitright(){          //Control if you hit enemy to the right
            for(int i=0;i<imgRlist.size();i++) {
                pos = imgRlist.get(i).getPosition();

                if (pos <= 330 * density && pos > 250 * density) {
                    addPoints();
                    newRight();
                    imgRlist.remove(i);


                }
            }


        }
        public void addPoints(){        //Addpoints when an enemy is killed.
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    p += 20;
                    points.setText(""+p);
                    lv.checkLV(p);


                }
            });

        }
        public void changeAttackRightSpeed(float speed){    //Change rightattackers speed.
            this.speedR=speed;
            for(int i=0;i<imgRlist.size();i++){
                imgRlist.get(i).setSpeed(speed);
            }
        }
    public void changeAttackLeftSpeed(float speed){     //Change leftattackers speed
        this.speedL=speed;
        for(int i=0;i<imgLlist.size();i++){
            imgLlist.get(i).setSpeed(speed);
        }
    }
        public void draw(){                     //Draw all enemies
            for(int i=0;i<imgLlist.size();i++){

                gv.onDraw(imgLlist.get(i).getBmp(),imgLlist.get(i).updatePosition(),
                        screenH);
            }
            for(int i=0;i<imgRlist.size();i++){

                gv.onDraw(imgRlist.get(i).getBmp(),imgRlist.get(i).updatePosition(),
                        screenH);
            }
        }

    //************************** GraphicsThread *****************************

        private class AttackerThread extends Thread{

            public AttackerThread(){    //Initialize some varibles after Gameview.
              density=getResources().getDisplayMetrics().density;

              screenW =gv.getMeasuredWidth();
              screenH=gv.getMeasuredHeight()- 130 * density;
              newRight();
              newLeft();


            }
            public void run(){

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.slashy_background);
            while(play){

                gv.lock();



                gv.c.drawBitmap(bitmap,0,0,null);
                knight();
                draw();
                gv.unlock();
                damaged();
                try {
                    sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            }
        }


    //*********************BUTTONLISTENERS**************************


    private class ButtonLeft implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            new CountDownTimer(400, 50) {
                @Override
                public void onTick(long millisUntilFinished) {
                    axePic++;
                    clickedL = true;
                }

                @Override
                public void onFinish() {
                    axePic=0;
                    clickedL = false;
                }


            }.start();
        }
    }

    private class ButtonRight implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            new CountDownTimer(400, 50) {
                @Override
                public void onTick(long millisUntilFinished) {
                    axePic++;               //För att bestämma vilken bild som ska visas i hugg animation
                    clickedR = true;        //axePic >2&& axePic< 5
                }

                @Override
                public void onFinish() {
                    axePic=0;
                    clickedR = false;
                }


            }.start();
        }
    }
    }

