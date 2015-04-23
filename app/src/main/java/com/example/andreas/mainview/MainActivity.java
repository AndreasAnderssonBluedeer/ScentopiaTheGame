package com.example.andreas.mainview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

    private ViewFlipper flip;

    private RelativeLayout fliplay1;
    private RelativeLayout fliplay2;
    private RelativeLayout fliplay3;
    private RelativeLayout fliplay4;

    private Button btn4;
    private Button btn3;
    private Button btn2;
    private Button btn1;
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

        btn4=(Button)findViewById(R.id.btn4);

        btn3 =(Button)findViewById(R.id.btn3);
        btn3.setOnClickListener(new ButtonThree());

        btn2=(Button)findViewById(R.id.btn2);

        btn1 =(Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new ButtonOne());
        btn1.setBackgroundColor(Color.MAGENTA);

        fliplay1=(RelativeLayout)findViewById(R.id.fliplay1);
        fliplay2=(RelativeLayout)findViewById(R.id.fliplay2);
        fliplay3=(RelativeLayout)findViewById(R.id.fliplay3);
        fliplay4=(RelativeLayout)findViewById(R.id.fliplay4);


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
    public void resetButtoncolor(){
        btn1.setBackgroundColor(Color.parseColor("#ff23339a"));
        btn2.setBackgroundColor(Color.parseColor("#ff23339a"));
        btn3.setBackgroundColor(Color.parseColor("#ff23339a"));
        btn4.setBackgroundColor(Color.parseColor("#ff23339a"));

    }
    public void two(View button){
        resetButtoncolor();
        button.setBackgroundColor(Color.MAGENTA);
        flip.setDisplayedChild(flip.indexOfChild(fliplay2));
    }
    public void four(View button){
        resetButtoncolor();
        button.setBackgroundColor(Color.MAGENTA);
        flip.setDisplayedChild(flip.indexOfChild(fliplay4));
    }
    public void startSlashy(View button){
         Intent intent = new Intent(context, SlashyActivity.class);

          startActivity(intent);
    }
    public void startShroom(View button){
        Intent intent = new Intent(context, ShroomActivity.class);

        startActivity(intent);
    }







    private class ButtonThree implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            resetButtoncolor();
            v.setBackgroundColor(Color.MAGENTA);
            flip.setDisplayedChild(flip.indexOfChild(fliplay3));


        }
    }
    private class ButtonOne implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            resetButtoncolor();
            v.setBackgroundColor(Color.MAGENTA);
            flip.setDisplayedChild(flip.indexOfChild(fliplay1));


        }
    }
}
