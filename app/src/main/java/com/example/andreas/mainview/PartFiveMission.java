package com.example.andreas.mainview;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Oakstedt on 2015-05-22.
 * Missions for part 5. Booleans in constructor affects mission-probability if true.
 */
public class PartFiveMission {

    private int modifier;
    private Context context;
    private ArrayList<MissionView> partFiveM;
    private boolean d_suit;
    private boolean boat;
    private boolean map;
    private boolean shop;
    private boolean wig;
    private boolean harpoon;

    public PartFiveMission(Context context, boolean d_suit, boolean boat, boolean map, boolean shop,boolean harpoon, boolean wig){
        this.context=context;
        this.d_suit=d_suit;
        this.boat=boat;
        this.map=map;
        this.shop=shop;
        this.harpoon=harpoon;
        this.wig=wig;

        if (d_suit==true){
            modifier+=5;
        }
        if (boat==true){
            modifier+=5;
        }
        if (map==true){
            modifier+=5;
        }
        if (shop==true){
            modifier+=5;
        }
        if (harpoon==true){
            modifier+=5;
        }
        if (wig==true){
            modifier+=5;
        }

        partFiveM =new ArrayList<MissionView>();

        //Add missionsTab to list.
        partFiveM.add(new MissionView(46, "Color me none!", 30 + modifier, 1000,
                "Go to the water plant and help remove color from the water, who wants to drink purple?", context));

        partFiveM.add(new MissionView(47, "Shopping spree.", 30 + modifier, 750,
                "You there! Carry my bags in the market!", context));

        partFiveM.add(new MissionView(48, "Biggest fan!", 30 + modifier, 800,
                "Keep the nobles cool with this, I need a break. They tip well!", context));

        partFiveM.add(new MissionView(49, "New Watch", 30 + modifier, 750,
                "Fill in for Nose Guard Davis, he needs a break.", context));

        partFiveM.add(new MissionView(40, "Old friends", 20 + modifier, 1500,
                "It's me Red Eric, let me into the city gates, just one night. I'll cut you in... (HIGH RISK)", context));

    }

    public ArrayList<MissionView> listReturn(){ //Return list.
        return partFiveM;
    }
}

