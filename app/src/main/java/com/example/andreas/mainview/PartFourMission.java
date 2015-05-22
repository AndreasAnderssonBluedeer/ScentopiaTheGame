package com.example.andreas.mainview;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Oakstedt on 2015-05-04.
 * Missions for part 4. Booleans in constructor affects mission-probability if true.
 */
public class PartFourMission {

    private int modifier;
    private Context context;
    private ArrayList<MissionView> partFourM;
    private boolean g_wagon;
    private boolean f_clothes;
    private boolean umbrella;
    private boolean parrot;
    private boolean windmill;

    public PartFourMission(Context context, boolean g_wagon, boolean f_clothes, boolean umbrella, boolean parrot, boolean windmill){
        this.context=context;
        this.g_wagon = g_wagon;
        this.f_clothes = f_clothes;
        this.umbrella = umbrella;
        this.parrot = parrot;
        this.windmill = windmill;

        if (g_wagon ==true){
            modifier+=10;
        }
        if (f_clothes ==true){
            modifier+=10;
        }
        if (umbrella ==true){
            modifier+=10;
        }
        if (parrot ==true){
            modifier+=10;
        }
        if (windmill ==true){
            modifier+=10;
        }

        partFourM=new ArrayList<MissionView>();

        //Add missionsTab to list.
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

    public ArrayList<MissionView> listReturn(){ //Return list.
        return partFourM;
    }
}
