package com.example.andreas.mainview;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Oakstedt on 2015-05-04.
 */
public class PartTwoMission {

    private int modifier;
    private Context context;
    private ArrayList<MissionView> partTwoM;

    public PartTwoMission(Context context){
        partTwoM=new ArrayList<MissionView>();
        this.context=context;
        partTwoM.add(new MissionView(21, "Fungus Funk", 60, 300,
                "Brother Snurf loves shrooms so much he wants to sing you a song, his friends will pay you to listen. (So they don't have to.", context));

        partTwoM.add(new MissionView(22, "Shroom Broom!", 60, 175,
                "So. Many. Mushrooms! You need to clean out so you can get more!", context));

        partTwoM.add(new MissionView(23, "Over the hills and kinda close", 60, 200,
                "Over that hill there is some treasure, or was it the next one?", context));

        partTwoM.add(new MissionView(24, "The roof, the roof, the roof has gone missing!", 60, 175,
                "Shroomhouse lost the roof in the strong winds, catch it before it rolls away into the woods!", context));

        partTwoM.add(new MissionView(25, "Find him a shrubbery", 60, 250,
                "How about getting a decoration for the garden? Like a shrubbery, IT would be nice.", context));

    }

    public ArrayList<MissionView> listReturn(){
        return partTwoM;
    }
    public void updateModifier(){
        modifier+=5;
    }

}
