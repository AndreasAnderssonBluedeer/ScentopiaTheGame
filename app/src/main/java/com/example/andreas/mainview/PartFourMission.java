package com.example.andreas.mainview;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Oakstedt on 2015-05-04.
 */
public class PartFourMission {
    private Context context;
    private ArrayList<MissionView> partFourM;

    public PartFourMission(Context context){
        this.context=context;

        partFourM=new ArrayList<MissionView>();

        partFourM.add(new MissionView(41, "Tax this!", 40, 500,
                "The tax collector now have ships! Blow one up.", context));

        partFourM.add(new MissionView(42, "Smelly tidings", 40, 375,
                "Loads of rotten weed on the shores, clean up sailor!", context));

        partFourM.add(new MissionView(43, "Who can sail without wind?", 40, 400,
                "Who can row without oars? Not us, hop to it!", context));

        partFourM.add(new MissionView(44, "Golden Fair", 40, 375,
                "Heard about the fair? They give away coins to everyone. Except crooks, please go there for me.", context));

        partFourM.add(new MissionView(45, "Then again...", 25, 700,
                "WE could steal loads of gold. Join us in a raid! (HIGH RISK)", context));

    }

    public ArrayList<MissionView> listReturn(){
        return partFourM;
    }

}

