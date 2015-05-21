package com.example.andreas.mainview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.andreas.mainview.R;

/**
 * A Class with a defined Layout for each Mission.
 * Created by Andreas on 2015-04-29.
 */
public class MissionView extends RelativeLayout {

    private int id, probability, gold;
    private String title, items;
    private boolean marked = false;

    private View mission;
    private RelativeLayout layout;
    private TextView titleTxt, probsTxt, goldTxt, itemsTxt;

    private Context context;

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

    public void setInfo() {     //Give all components text/data.
        //Fetch the MissionLayout.
        LayoutInflater factory = LayoutInflater.from(context);
        mission = factory.inflate(R.layout.mission_view, null);
        //Fetch layout and give ID.
        layout = (RelativeLayout) mission.findViewById(R.id.missionView);
        layout.setId(id);
        //Fetch Labels.
        titleTxt = (TextView) mission.findViewById(R.id.missionTitle);
        probsTxt = (TextView) mission.findViewById(R.id.probability);
        goldTxt = (TextView) mission.findViewById(R.id.gold);
        itemsTxt = (TextView) mission.findViewById(R.id.items);
        //Set texts.
        titleTxt.setText(title);
        probsTxt.setText(probability + "%");
        goldTxt.setText(gold + " G");
        itemsTxt.setText(items);
        //Add this view to the class-object.
        this.addView(mission);
    }

    public int getId() {    //Return ID.
        return this.id;
    }
    public int getProbability(){    //return Mission Probability
        return this.probability;
    }
    public int getGold(){   //return MissionGold.
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