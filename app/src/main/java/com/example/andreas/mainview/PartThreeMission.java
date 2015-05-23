package com.example.andreas.mainview;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Oakstedt on 2015-05-04.
 * Missions for part 3. Booleans in constructor affects mission-probability if true.
 */
public class PartThreeMission {

    private int modifier;
    private Context context;
    private ArrayList<MissionView> partThreeM;
    private boolean compass;
    private boolean axe;
    private boolean horse;
    private boolean permit;

    public PartThreeMission(Context context, boolean compass, boolean axe, boolean horse, boolean permit){
        this.context=context;
        this.compass=compass;
        this.axe=axe;
        this.horse=horse;
        this.permit=permit;

        if(compass==true){
            modifier+=5;
        }
        if(axe==true){
            modifier+=5;
        }
        if (horse==true){
            modifier+=5;
        }
        if (permit==true){
            modifier+=5;
        }

        partThreeM=new ArrayList<MissionView>();

        //Add missionsTab to list.
        partThreeM.add(new MissionView(31, "BEwARe", 20+modifier, 400,
                "Get me some salmon from the falls, watch out for wildlife will you?", context));

        partThreeM.add(new MissionView(32, "Take from the thief and...", 20+modifier, 275,
                "Some merry bandits in tights have stolen gold, get it back!", context));

        partThreeM.add(new MissionView(33, "Like a leaf in the wind", 20+modifier, 300,
                "The rain set in, get me some leaves to patch the roof.", context));

        partThreeM.add(new MissionView(34, "Check the plants", 20+modifier, 275,
                "I have some special potted plants in a hidden clearing, fetch some for me. We can share profits!", context));

        partThreeM.add(new MissionView(35, "Worth 10 in the bush", 20+modifier, 350,
                "Heard the miners need birds, catch 10 and send them off!", context));

    }

    public ArrayList<MissionView> listReturn(){ //return list.
        return partThreeM;
    }

}

