package com.example.andreas.mainview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.ViewFlipper;

import com.example.andreas.mainview.shroom.ShroomActivity;
import com.example.andreas.mainview.slashy.SlashyActivity;


public class MainActivity extends Activity {

    private RelativeLayout layout;
    private ProgressBar loadProg;
    private HorizontalScrollView map;
    private ImageView imgMap;
    private Context context =this;
    private SlidingDrawer menuSlide;

    private   Intent intent;

    private ViewFlipper flip;

    private RelativeLayout mgLay;
    private RelativeLayout missLay;
    private RelativeLayout fliplay3;
    private RelativeLayout fliplay4;

    private Button btnCred;
    private Button btnItem;
    private Button btnMG;
    private Button btnMiss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout=(RelativeLayout)findViewById(R.id.layout);
        layout.setBackground(getResources().getDrawable(R.drawable.menu_loadingscreen));

        loadProg=(ProgressBar)findViewById(R.id.loadProg);
        loadProg.setMax(100);

        menuSlide=(SlidingDrawer)findViewById(R.id.menuSlide);
        menuSlide.setEnabled(Boolean.FALSE);
        menuSlide.setVisibility(View.INVISIBLE);
        flip=(ViewFlipper)findViewById(R.id.flip);

        map=(HorizontalScrollView)findViewById(R.id.map);
        map.setVisibility(View.INVISIBLE);
        map.setEnabled(Boolean.FALSE);

        mgLay=(RelativeLayout)findViewById(R.id.minigameLayout);
        missLay=(RelativeLayout)findViewById(R.id.missionLayout);


        btnMG=(Button)findViewById(R.id.btnGame);
        btnMiss=(Button)findViewById(R.id.btnMission);
        btnItem=(Button)findViewById(R.id.btnItem);
        btnCred=(Button)findViewById(R.id.btnCred);


        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.loadingsound);
        mp.start();

        loading();      //Loading/StartScreen

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
    //********************KOD*****************

    public void loading(){          //StartupScreen Loader
        new CountDownTimer(4000,25){
            //Sounds???
            int progress;
            @Override
            public void onTick(long millisUntilFinished) {  //Update Loading Bar.
                progress+=1;
                loadProg.setProgress(progress);
            }

            @Override
            public void onFinish() {

                layout.removeView(loadProg);
                map.setEnabled(Boolean.TRUE);
                map.setVisibility(View.VISIBLE);
                imgMap=new ImageView(context);

                imgMap.setBackground(getResources().getDrawable(R.drawable.menu_testmap));
                map.addView(imgMap);
                map.setBackgroundColor(Color.MAGENTA);

                menuSlide.setEnabled(Boolean.TRUE);
                menuSlide.setVisibility(View.VISIBLE);



                //Kalla på Karta och kontrollera mot sparad fil vilken karta som ska laddas.

            }
        }.start();
    }
    public void markMission(View lay){
        lay.setBackgroundColor(Color.parseColor("#b5aaaaaa"));
    }
    public void resetButtoncolor(){
        btnMiss.setBackgroundColor(Color.parseColor("#8c213e42"));
        btnMG.setBackgroundColor(Color.parseColor("#8c213e42"));
        btnItem.setBackgroundColor(Color.parseColor("#8c213e42"));
        btnCred.setBackgroundColor(Color.parseColor("#8c213e42"));

    }
    public void missions(View button){
        resetButtoncolor();
        button.setBackgroundColor(Color.parseColor("#9e330e80"));
        flip.setDisplayedChild(flip.indexOfChild(missLay));
    }
    public void minigame(View button){
        resetButtoncolor();
        button.setBackgroundColor(Color.parseColor("#9e330e80"));
        flip.setDisplayedChild(flip.indexOfChild(mgLay));
    }
    public void items(View button){
        resetButtoncolor();
        button.setBackgroundColor(Color.parseColor("#9e330e80"));
        //FLIP
    }
    public void credits(View button){
        resetButtoncolor();
        button.setBackgroundColor(Color.parseColor("#9e330e80"));
      //  flip.setDisplayedChild(flip.indexOfChild(credLay));
    }

    public void startSlashy(View layout){
         intent = new Intent(context, SlashyActivity.class);
        layout.setBackgroundColor(Color.parseColor("#b5aaaaaa"));
    }
    public void startShroom(View layout){
        intent = new Intent(context, ShroomActivity.class);

      //  layout.setBackgroundColor(Color.parseColor("#00000000"));
        layout.setBackgroundColor(Color.parseColor("#b5aaaaaa"));
    }
    public void startMinigame(View button){
        startActivity(intent);
    }








}
