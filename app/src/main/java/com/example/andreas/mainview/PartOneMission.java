package com.example.andreas.mainview;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Oakstedt on 2015-05-04.
 */
public class PartOneMission {

    private int modifier;
    private Context context;
    private ArrayList<MissionView> partOneM;
    private boolean pickaxe;
    private boolean torch;

    public PartOneMission(Context context, boolean pickaxe, boolean torch){
        this.context=context;
        this.pickaxe=pickaxe;
        this.torch=torch;

        if(pickaxe=true){
            modifier+=10;
        }
        if(torch=true){
            modifier+=10;
        }

        partOneM=new ArrayList<MissionView>();
        partOneM.add(new MissionView(11, "It's not over until you sing.", 70 + modifier, 200,
                        "They lost their canary, guess who will have to fill in? Get in the cage!", context));

<<<<<<< HEAD
        partOneM.add(new MissionView(12, "Pick-a-Boo!", 70+ modifier, 75,
                "The mining picks are haunted! Who they gonna call? Well... You.", context));

        partOneM.add(new MissionView(13, "Cart Racer", 70+ modifier, 100,
                "A guy with a red hat wants to race carts down the mine, what could go wrong?", context));

        partOneM.add(new MissionView(14, "Chicken Gold Nuggets", 70+ modifier, 75,
                "The miners need food, they will pay you for it. Get that chicken!", context));

        partOneM.add(new MissionView(15, "Gone Guano", 70+ modifier, 150,
=======
        partOneM.add(new MissionView(12, "Pick-a-Boo!", 70 + modifier, 75,
                "The mining picks are haunted! Who they gonna call? Well... You.", context));

        partOneM.add(new MissionView(13, "Cart Racer", 70 + modifier, 100,
                "A guy with a red hat wants to race carts down the mine, what could go wrong?", context));

        partOneM.add(new MissionView(14, "Chicken Gold Nuggets", 70 + modifier, 75,
                "The miners need food, they will pay you for it. Get that chicken!", context));

        partOneM.add(new MissionView(15, "Gone Guano", 70 + modifier, 150,
>>>>>>> bbe8089558bbd8ffbb11fab14a896673377991f4
                "Big Beard needs some bat droppings for something. What something? Well, that is a shiny mustache.", context));

    }

    public ArrayList<MissionView> listReturn(){
        return partOneM;
    }
    public void updateModifier(){
        modifier+=5;
    }
}
