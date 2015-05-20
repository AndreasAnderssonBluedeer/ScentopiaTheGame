package com.example.andreas.mainview;

import java.util.Random;

/**
 * Created by david_000 on 2015-05-19.
 */
public class MissionLogic {
	
    private Double outcome;
    
    public String getDialog(){
        if (outcome < 0){
            return "Mission Failed\n"+outcome.intValue()+" Gold.";
        }
        else{
            return "Mission Succeed\n+"+outcome.intValue()+" Gold.";
        }
    }


    public int doMission(int chance, Integer reward){
    	outcome=0.0;
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
