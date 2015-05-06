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
    private boolean sickle;
    private boolean rope;
    private boolean r_boots;

    public PartTwoMission(Context context, boolean sickle, boolean rope, boolean r_boots){
        this.context=context;
        this.sickle=sickle;
        this.rope=rope;
        this.r_boots=r_boots;

        if(sickle==true){
            modifier+=10;
        }
        if(rope==true){
            modifier+=10;
        }
        if(r_boots==true){
            modifier+=10;
        }

        partTwoM=new ArrayList<MissionView>();

        partTwoM.add(new MissionView(21, "Fungus Funk", 60+modifier, 300,
                "Brother Snurf loves shrooms so much he wants to sing you a song, his friends will pay you to listen. (So they don't have to.", context));

        partTwoM.add(new MissionView(22, "Shroom Broom!", 60+modifier, 175,
                "So. Many. Mushrooms! You need to clean out so you can get more!", context));

        partTwoM.add(new MissionView(23, "Over the hills and kinda close", 60+modifier, 200,
                "Over that hill there is some treasure, or was it the next one?", context));

        partTwoM.add(new MissionView(24, "The roof, the roof, the roof has gone missing!", 60+modifier, 175,
                "Shroomhouse lost the roof in the strong winds, catch it before it rolls away into the woods!", context));

        partTwoM.add(new MissionView(25, "Find him a shrubbery", 60+modifier, 250,
                "How about getting a decoration for the garden? Like a shrubbery, IT would be nice.", context));

    }

    public ArrayList<MissionView> listReturn(){
        return partTwoM;
    }


}
