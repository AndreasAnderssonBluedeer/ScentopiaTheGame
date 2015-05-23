package com.example.andreas.mainview.slashy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
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

import com.example.andreas.mainview.MainActivity;
import com.example.andreas.mainview.R;

import java.util.ArrayList;

/**
 * Created by Andreas.
 * Activity class for a minigame called slashy. The games purpose is to kill monsters attacking your knight.
 * communicates with Gameview,ImagewriterLeft,ImageWriterRight and Level.
 * When the game has ended, it sends "Won gold" to Mainactivity.
 */

public class SlashyActivity extends Activity {

    private GameView gv;
    private Level lv;
    private MediaPlayer mp,mpDmg;
    private AttackerThread thread;

    private Button btnL;
    private Button btnR;

    private TextView points;

    private Bitmap bmp;
    private ImageView hpImg;

    private boolean clickedR, clickedL;
    private boolean play=true;

    private ArrayList <ImageWriterLeft> imgLlist;
    private ArrayList <ImageWriterRight> imgRlist;

    private WindowManager wm;
    private Display display;
    private  DisplayMetrics metrics;

    private float speedL=1,speedR=1;
    private float pos,density,screenH;

    private int screenW,axePic,p;
    private int life=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slashyactivity_main);

        //Canvas
        gv = (GameView) findViewById(R.id.gv);
        //Buttons for Left/Right swing.
        btnR = (Button) findViewById(R.id.btnR);
        btnL = (Button) findViewById(R.id.btnL);
        //PointsLabel
        points=(TextView)findViewById(R.id.points);
        //LifeImage.
        hpImg=(ImageView)findViewById(R.id.hpImg);
        hpImg.setBackground(getResources().getDrawable(R.drawable.slashy_hitpoints3));
        //Buttonlisteners.
        btnL.setOnClickListener(new ButtonLeft());
        btnR.setOnClickListener(new ButtonRight());

        lv= new Level(this);
        imgLlist=new ArrayList<ImageWriterLeft>();
        imgRlist=new ArrayList<ImageWriterRight>();
        //Get displaysize.
        wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //Start Background music.
        mp=MediaPlayer.create(getApplicationContext(), R.raw.slashysong);
        mp.start();
        //Start Game/Show StartDialog.
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

    //******************* ******** Game-Methods ******** ********************

    public void start() {   //StartDialog.
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("Axy TIME?...")
                .setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                      thread= new AttackerThread();     //Get those monsters out there!
                      thread.start();
                    }
                }).show();

    }

    public void gameover(){ //Game over, show result dialog.
        play=false;
        Double d =p*0.2;
        final int money=d.intValue();
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("I'm not angry.. Just DISAPPOINTED!!!\n"+
                "Points: "+p+
                 "\nMoney: "+money)

                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mp.stop();
                        //Change activity and bring the "Won gold".
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtra("moneyVar", money);

                        startActivity(i);
                    }
                }).show();

    }

    public void newRight(){     //adds a new attacker from the right side.

        imgRlist.add(new ImageWriterRight(BitmapFactory.decodeResource(getResources(), R.drawable.slashy_monster_right1),
                BitmapFactory.decodeResource(getResources(), R.drawable.slashy_monster_right2)
                ,BitmapFactory.decodeResource(getResources(), R.drawable.slashy_monster_right3),
                screenW,getResources().getDisplayMetrics().density));

        imgRlist.get(imgRlist.size()-1).setSpeed(speedR);   //Give them "level speed"
    }

    public void newLeft(){      //adds a new attacker from the left side.

        imgLlist.add(new ImageWriterLeft(BitmapFactory.decodeResource(getResources(), R.drawable.slashy_monster_left1)
                , BitmapFactory.decodeResource(getResources(), R.drawable.slashy_monster_left2),
                BitmapFactory.decodeResource(getResources(), R.drawable.slashy_monster_left3),
                getResources().getDisplayMetrics().density));

        imgLlist.get(imgLlist.size()-1).setSpeed(speedL);   //Give them "level speed"
        }

    public void knight() {       //Control the knights position/Movement.

            if (clickedR) {     //If Button right is clicked.

                if(axePic >2&& axePic< 5) { //Determine which picture should be shown for animation effect.
                    gv.onDraw(bmp = BitmapFactory.decodeResource(getResources()
                            , R.drawable.slashy_knight_rightfull), screenW / 2 - 15 * density,screenH);
                }else{
                    gv.onDraw(bmp = BitmapFactory.decodeResource(getResources()
                            , R.drawable.slashy_knight_righthalf), screenW / 2 - 15 * density,screenH);
                }
                hitright();     //Control if you hit any monster.


            } else if (clickedL) {      //If Button left is clicked.

                if(axePic >2&& axePic< 5) { //Determine which picture should be shown for animation effect.
                    gv.onDraw(bmp = BitmapFactory.decodeResource(getResources()
                            , R.drawable.slashy_knight_leftfull), screenW / 2 - 85 * density,screenH);
                }else{
                    gv.onDraw(bmp = BitmapFactory.decodeResource(getResources()
                            , R.drawable.slashy_knight_lefthalf), screenW / 2 - 85 * density,screenH);
                }
               hitleft();        //Control if you hit any monster.


            } else {    //If no button is clicked.
                gv.onDraw(bmp = BitmapFactory.decodeResource(getResources(),
                        R.drawable.slashy_knight_neutral)
                        , screenW /2-25 * density,screenH);

            }


        }

    public void damaged(){  // Did a monster attack you?

            float attack;

            for(int i=0;i<imgLlist.size();i++) {    //Control attackers from the left.
                attack=imgLlist.get(i).getPosition();

                 if ((attack>190* density) && (attack<250* density)) { //Limit the attack to one side

                    life++;
                    dmgSound();
                    imgLlist.remove(imgLlist.get(i));
                    newLeft();
                }
            }
             for(int i=0;i<imgRlist.size();i++) {       //Control attacker from the right.
                 attack=imgRlist.get(i).getPosition();

                 if ( (attack<275 * density)&&(attack>250* density)) { //&& to limit attack to one side.

                     life++;
                    dmgSound();
                    imgRlist.remove(imgRlist.get(i));
                    newRight();
                }
            }
            runOnUiThread(new Runnable() {  //Show lifeIMAGE.

                @Override
                public void run() {     //Determine which lifeImage.
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

    public void dmgSound(){     //Play a sound when knight is damaged.

            mpDmg = MediaPlayer.create(getApplicationContext(), R.raw.slashy_dmg);
            mpDmg.start();
            mpDmg.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mpDmg) {
                    mpDmg.release();

                }

                ;
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
            //Play a sound effect for "Killed monster"
            MediaPlayer mpP = MediaPlayer.create(getApplicationContext(), R.raw.slashy_monster);
            mpP.start();
            mpP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mpP) {
                    mpP.release();

                } ;
            });
            //Update points label and check level.
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
    public void onBackPressed() {       //To prevent unintended "QUIT"
    }
    //************************** GraphicsThread *****************************

    private class AttackerThread extends Thread{

            public AttackerThread(){    //Initialize varibles after Gameview.
              density=getResources().getDisplayMetrics().density;

              screenW =gv.getMeasuredWidth();
              screenH=gv.getMeasuredHeight()- 130 * density;
              newRight();
              newLeft();
            }
            public void run(){
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.slashy_background2);

            while(play){    //While your knight isnt dead.

                //If the background song ended-Play it AGAIN!
                if(mp.isPlaying()==false){
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.slashysong);
                    mp.start();
                }
                gv.lock();      //Lock canvas while drawing.

                gv.c.drawBitmap(bitmap,0,0,null);   //First draw background to overwrite everything.
                knight();   //draw your knight.
                draw();     //Draw all enemies.
                gv.unlock();    //Unlock canvas and show drawing.
                damaged();  //Control if you're damaged.
                try {
                    sleep(30);  //Wait 30 ms before drawing again.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
         }
    }

    //*********************BUTTONLISTENERS**************************

    private class ButtonLeft implements View.OnClickListener {  //Left Swing Button

        @Override
        public void onClick(View v) {
            //Play a "Swing axe sound"
            MediaPlayer mpL = MediaPlayer.create(getApplicationContext(), R.raw.slashy_swing);
            mpL.start();
            mpL.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mpL) {
                    mpL.release();

                } ;
            });
            //Countdowntimer to do "Swing" animation.
            new CountDownTimer(400, 50) {
                @Override
                public void onTick(long millisUntilFinished) {
                    axePic++;   //+ to tell knight() which image to show(Animation)
                    clickedL = true;    //Tell knight() that left swing is clicked.
                }
                @Override
                public void onFinish() {    //Reset
                    axePic=0;
                    clickedL = false;
                }
            }.start();
        }
    }

    private class ButtonRight implements View.OnClickListener { //Right Swing Button

        @Override
        public void onClick(View v) {
            //Play a "Swing axe sound"
            MediaPlayer mpR = MediaPlayer.create(getApplicationContext(), R.raw.slashy_swing);
            mpR.start();
            mpR.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mpR) {
                    mpR.release();

                } ;
            });
            //Countdowntimer to do "Swing" animation.
            new CountDownTimer(400, 50) {
                @Override
                public void onTick(long millisUntilFinished) {
                    axePic++;               //+ to tell knight() which image to show(Animation)
                    clickedR = true;        //Tell knight() that right swing is clicked.
                }
                @Override
                public void onFinish() {    //Reset
                    axePic=0;
                    clickedR = false;
                }
            }.start();
        }
    }
    }

