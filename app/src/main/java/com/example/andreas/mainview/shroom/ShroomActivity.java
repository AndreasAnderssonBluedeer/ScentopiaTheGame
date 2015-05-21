package com.example.andreas.mainview.shroom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.andreas.mainview.MainActivity;
import com.example.andreas.mainview.R;
import com.example.andreas.mainview.slashy.SlashyActivity;

/**
 * Created by Andreas.
 * Activity class with UI for "Picking Mushrooms Minigame."
 * Basically a countdowntimer and score. Communicates with Gamecontroller and sends
 * "won gold" to Mainactivity.
 */

public class ShroomActivity extends Activity {

    private TextView lblPoints;
    protected TextView lblTime;
    private int points;
    private GameController controller;
    protected RelativeLayout layout;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shroomactivity_main);

        //Labels for Score and Countdown
        lblPoints = (TextView) findViewById(R.id.lblPoints);
        lblTime = (TextView) findViewById(R.id.lblTime);
        //Background/Layout
        layout = (RelativeLayout) findViewById(R.id.layout);


        controller = new GameController(this, this);
        //Background Music
        mp = MediaPlayer.create(getApplicationContext(), R.raw.shroomsong);
        mp.start();

        startGame();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds itemsTab to the action bar if it is present.
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

    //*****************************GUI Methods*********************************

    public void startGame() {            //StartDialog, Are you ready?!
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("Are You Ready?...")
                .setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        countdown();
                    }
                }).show();
    }

    public void endGame() {         //EndDialog-Game results.
        Double d = points * 1.2;
        final int money = d.intValue();
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("Game Results:")
                .setMessage("Points:  " + points + "\n" +
                        "Money Earned:  " + money)

                .setPositiveButton("Okay :)", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mp.stop();
                        //Change activity to "Menu-activity" and bring the won gold.
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtra("moneyVar", money);

                        startActivity(i);
                    }
                }) .show();
    }

    public void addPoints() {        //Update Score+Label.

        new Blopsound().start();    //calls the "picked mushroom sound"

        points += 10;
        lblPoints.setText("" + points);
    }

    public void countdown() {            //Countdown from 30 seconds.
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {  //Add mushrooms every second.
                lblTime.setText("" + millisUntilFinished / 1000);
                controller.addObject();
                controller.addObject();
                controller.addObject();
                controller.addObject();
                controller.addObject();
                controller.addObject();

            }

            public void onFinish() {        //On finish, Call Endgame.
                lblTime.setText("0");
                endGame();
            }
        }.start();
    }

    private class Blopsound extends Thread {        // Plays "Picked mushroom sound"
        public void run() {
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.shroom_blop);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    mp.release();

                };
            });
        }
    }
}






