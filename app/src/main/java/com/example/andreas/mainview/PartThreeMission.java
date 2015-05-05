package com.example.andreas.mainview;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Oakstedt on 2015-05-04.
 */
public class PartThreeMission {
    private Context context;
    private ArrayList<MissionView> partThreeM;

    public PartThreeMission(Context context){
        this.context=context;
        partThreeM=new ArrayList<MissionView>();
        partThreeM.add(new MissionView(31, "BEwARe", 50, 400,
                "Get me some salmon from the falls, watch out for wildlife will you?", context));

        partThreeM.add(new MissionView(32, "Take from the thief and...", 50, 275,
                "Some merry bandits in tights have stolen gold, get it back!", context));

        partThreeM.add(new MissionView(33, "Like a leaf in the wind", 50, 300,
                "The rain set in, get me some leaves to patch the roof.", context));

        partThreeM.add(new MissionView(34, "Check the plants", 50, 275,
                "I have some special potted plants in a hidden clearing, fetch some for me. We can share profits!", context));

        partThreeM.add(new MissionView(35, "Worth 10 in the bush", 50, 350,
                "Heard the miners need birds, catch 10 and send them off!", context));

    }

    public ArrayList<MissionView> listReturn(){
        return partThreeM;
    }

}

