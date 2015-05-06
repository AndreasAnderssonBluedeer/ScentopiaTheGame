package com.example.andreas.mainview;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Oakstedt on 2015-05-04.
 */
public class PartFourMission {
<<<<<<< HEAD
=======

>>>>>>> bbe8089558bbd8ffbb11fab14a896673377991f4
    private int modifier;
    private Context context;
    private ArrayList<MissionView> partFourM;
    private boolean d_suit;
    private boolean boat;
    private boolean map;
    private boolean shop;
    private boolean wig;

    public PartFourMission(Context context, boolean d_suit, boolean boat, boolean map, boolean shop, boolean wig){
        this.context=context;
        this.d_suit=d_suit;
        this.boat=boat;
        this.map=map;
        this.shop=shop;
        this.wig=wig;

        if (d_suit=true){
            modifier+=10;
        }
        if (boat=true){
            modifier+=10;
        }
        if (map=true){
            modifier+=10;
        }
        if (shop=true){
            modifier+=10;
        }
        if (wig=true){
            modifier+=10;
        }

        partFourM=new ArrayList<MissionView>();

        partFourM.add(new MissionView(41, "Tax this!", 40+modifier, 500,
                "The tax collector now have ships! Blow one up.", context));

        partFourM.add(new MissionView(42, "Smelly tidings", 40+modifier, 375,
                "Loads of rotten weed on the shores, clean up sailor!", context));

        partFourM.add(new MissionView(43, "Who can sail without wind?", 40+modifier, 400,
                "Who can row without oars? Not us, hop to it!", context));

        partFourM.add(new MissionView(44, "Golden Fair", 40+modifier, 375,
                "Heard about the fair? They give away coins to everyone. Except crooks, please go there for me.", context));

        partFourM.add(new MissionView(45, "Then again...", 25+modifier, 700,
                "WE could steal loads of gold. Join us in a raid! (HIGH RISK)", context));

    }

    public ArrayList<MissionView> listReturn(){
        return partFourM;
    }
    public void updateModifier(){
        modifier+=5;
    }

}

