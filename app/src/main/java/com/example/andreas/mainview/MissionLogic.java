package com.example.andreas.mainview;

import java.util.Random;

/**
 * Created by david_000 on 2015-05-19.
 * Missionlogic to determine if a mission is successful or not.
 */
public class MissionLogic {
	
    private Double outcome;
    
    public String getDialog(){     //Returns a string with mission outcome and +/- gold.
        if (outcome < 0){
            return "Mission Failed\n"+outcome.intValue()+" Gold.";
        }
        else{
            return "Mission Succeed\n+"+outcome.intValue()+" Gold.";
        }
    }

    public int doMission(int chance, Integer reward){ //Mission logic.
    	outcome=0.0;                // higher chance= higher probability to succeed.
        Random rand = new Random();
            int roll = rand.nextInt(101);
            if (chance < roll) {
                outcome -= reward*0.1;
            }
            if(chance >= roll & chance != 0){
                outcome = reward.doubleValue();
        }
        return outcome.intValue();
    }

}
