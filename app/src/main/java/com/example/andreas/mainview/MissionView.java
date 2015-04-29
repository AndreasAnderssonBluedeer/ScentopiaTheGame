package com.example.andreas.mainview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.andreas.mainview.R;

/**
 * A Class with a defined Layout for every Mission.
 * Created by Andreas on 2015-04-29.
 */
public class MissionView extends RelativeLayout {

    private int id, probability, gold;
    private String title, items;
    private View mission;
    private RelativeLayout layout;
    private Context context;

    private boolean marked = false;

    private TextView titleTxt, probsTxt, goldTxt, itemsTxt;


    public MissionView(int id, String title, int probability, int gold, String items, Context context) {
        super(context);
        this.id = id;
        this.title = title;
        this.probability = probability;
        this.gold = gold;
        this.items = items;
        this.context = context;

        this.setId(id);

        setInfo();
    }

    public void setInfo() {
        //Fetch the MissionLayout.
        LayoutInflater factory = LayoutInflater.from(context);
        mission = factory.inflate(R.layout.mission_view, null);

        layout = (RelativeLayout) mission.findViewById(R.id.missionView);
        layout.setId(id);

        titleTxt = (TextView) mission.findViewById(R.id.missionTitle);
        probsTxt = (TextView) mission.findViewById(R.id.probability);
        goldTxt = (TextView) mission.findViewById(R.id.gold);
        itemsTxt = (TextView) mission.findViewById(R.id.items);

        titleTxt.setText(title);
        probsTxt.setText(probability + "%");
        goldTxt.setText(gold + " G");
        itemsTxt.setText(items);

        this.addView(mission);
    }

    public int getId() {
        return this.id;
    }
    public int getProbability(){
        return this.probability;
    }
    public int getGold(){
        return gold;
    }
    public void mark() { //Marks the view when you select it.
        marked = true;
        //Highlights the missionLayouts background
        layout.setBackgroundColor(Color.parseColor("#b5aaaaaa"));
    }

    public void unMark() {   //Unmarks the view
        marked = false;
        //reset the background.
        layout.setBackgroundColor(Color.parseColor("#00000000"));
    }

    public boolean isMarked() {  //Returns true if marked.
        return marked;
    }


}