package com.example.andreas.mainview.memtopia;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.andreas.mainview.MainActivity;
import com.example.andreas.mainview.R;

import java.util.ArrayList;
import java.util.Collections;


public class MemActivity extends Activity {
    private ArrayList<ImageButton> buttons = new ArrayList<>();
    private ImageButton button1, button2, button3, button4, button5, button6, button7, button8, button9,
            button10, button11, button12;
    private ArrayList<Drawable> image = new ArrayList<Drawable>();
    private ArrayList<Tile> tiles = new ArrayList<Tile>();
    private Controller controller;
    private TextView pointsViewer;
    private Drawable question;
    private int pointInt = 200;
    private String pointString = new String();
    private boolean music=true;
    private MediaPlayer mp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memactivity_main);
        controller = new Controller(this);

        music();

        this.pointsViewer = (TextView) findViewById(R.id.points);
        pointsViewer.setShadowLayer(1, 0, 0, Color.BLACK);
        pointString = String.valueOf(pointInt);
        pointsViewer.setText(pointString);

        this.button1 = (ImageButton) findViewById(R.id.imageButton1);
        this.button2 = (ImageButton) findViewById(R.id.imageButton2);
        this.button3 = (ImageButton) findViewById(R.id.imageButton3);
        this.button4 = (ImageButton) findViewById(R.id.imageButton4);
        this.button5 = (ImageButton) findViewById(R.id.imageButton5);
        this.button6 = (ImageButton) findViewById(R.id.imageButton6);
        this.button7 = (ImageButton) findViewById(R.id.imageButton7);
        this.button8 = (ImageButton) findViewById(R.id.imageButton8);
        this.button9 = (ImageButton) findViewById(R.id.imageButton9);
        this.button10 = (ImageButton) findViewById(R.id.imageButton10);
        this.button11 = (ImageButton) findViewById(R.id.imageButton11);
        this.button12 = (ImageButton) findViewById(R.id.imageButton12);

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        buttons.add(button7);
        buttons.add(button8);
        buttons.add(button9);
        buttons.add(button10);
        buttons.add(button11);
        buttons.add(button12);

        Collections.shuffle(buttons);

        for (int i = 0; i <= 1; i++) {
            this.image.add(getResources().getDrawable(R.drawable.memtopia_castle));
            this.image.add(getResources().getDrawable(R.drawable.memtopia_heart));
            this.image.add(getResources().getDrawable(R.drawable.memtopia_horse));
            this.image.add(getResources().getDrawable(R.drawable.memtopia_mushroom));
            this.image.add(getResources().getDrawable(R.drawable.memtopia_pirate));
            this.image.add(getResources().getDrawable(R.drawable.memtopia_umbrella));
        }

        this.question = getResources().getDrawable(R.drawable.memtopia_question);

        this.tiles.add(new Tile(this, this.controller, buttons.get(0), image.get(0), question, "memtopia_castle"));

        this.tiles.add(new Tile(this, this.controller, buttons.get(1), image.get(1), question, "memtopia_heart"));

        this.tiles.add(new Tile(this, this.controller, buttons.get(2), image.get(2), question, "memtopia_horse"));

        this.tiles.add(new Tile(this, this.controller, buttons.get(3), image.get(3), question, "memtopia_mushroom"));

        this.tiles.add(new Tile(this, this.controller, buttons.get(4), image.get(4), question, "memtopia_pirate"));

        this.tiles.add(new Tile(this, this.controller, buttons.get(5), image.get(5), question, "memtopia_umbrella"));

        this.tiles.add(new Tile(this, this.controller, buttons.get(6), image.get(6), question, "memtopia_castle"));

        this.tiles.add(new Tile(this, this.controller, buttons.get(7), image.get(7), question, "memtopia_heart"));

        this.tiles.add(new Tile(this, this.controller, buttons.get(8), image.get(8), question, "memtopia_horse"));

        this.tiles.add(new Tile(this, this.controller, buttons.get(9), image.get(9), question, "memtopia_mushroom"));

        this.tiles.add(new Tile(this, this.controller, buttons.get(10), image.get(10), question, "memtopia_pirate"));

        this.tiles.add(new Tile(this, this.controller, buttons.get(11), image.get(11), question, "memtopia_umbrella"));
        controller.addTiles(tiles);

    }

    public void changePoints(int pointsGiven) {
        this.pointInt = pointsGiven;
        pointString = String.valueOf(pointInt);
        pointsViewer.setText(pointString);
    }

    public void dialogBox(String msg,String title){
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        music=false;
                        mp.stop();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtra("moneyVar", pointInt);

                        startActivity(i);
                    }
                })
                .show();
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
    public void music() {   //Controls background music.

        new Thread() {
            public void run() { //repeat song if ended.
                mp=new MediaPlayer();
                while (music) {
                    if (mp.isPlaying() == false) {
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.memtopia_song);
                        mp.start();
                    }
                }
            }
        }.start();
    }
    public void onBackPressed() {       //To prevent unintended "QUIT"
    }

}
