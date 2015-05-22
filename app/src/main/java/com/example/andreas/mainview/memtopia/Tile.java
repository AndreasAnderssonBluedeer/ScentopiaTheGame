package com.example.andreas.mainview.memtopia;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.andreas.mainview.R;

import java.util.HashMap;

/**
 * Created by david_000 on 2015-04-07.
 */
public class Tile {
    private Drawable image, question;
    private String name;
    private final Controller controller;
    private Boolean hidden = true;
    private Boolean locked = false;
    private ImageButton button;
    private Context context;


    public Tile(Context context, final Controller controller,ImageButton button, Drawable image, Drawable question, String name) {
        this.context = context;
        this.button = button;
        this.image = image;
        this.question = question;
        this.name = name;
        this.controller = controller;
        button.setBackground(question);


        if (!locked) {
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    new Blopsound().start();
                    if (hidden & !locked & !controller.isFull()) {
                        show();

                    }
                }
            });
        }
    }

    public void locked(Boolean bool){
        this.locked = bool;
    }

    public Boolean GameFinished(){
        return this.locked;
    }
    public void show(){
        button.setBackground(image);
        hidden = false;
        controller.check(this);

    }
    public void hide(){
        button.setBackground(question);
        hidden = true;

    }
    public String getName(){
        return this.name;
    }

    private class Blopsound extends Thread {        // Plays "Picked card sound"
        public void run() {
            MediaPlayer mp = MediaPlayer.create(context, R.raw.shroom_blop);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    mp.release();

                };
            });
        }
    }
}
