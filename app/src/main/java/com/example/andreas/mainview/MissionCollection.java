package com.example.andreas.mainview;

import android.content.Context;
import android.view.View;
import android.widget.Switch;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Andreas on 2015-04-29.
 */
public class MissionCollection {

    private int min=0;
    private int max=4;
    private Context context;
    private ArrayList<MissionView> listAll;
    private PartOneMission p1m;
    private PartTwoMission p2m;
    private PartThreeMission p3m;
    private PartFourMission p4m;

   public MissionCollection(int part,Context context){
       this.context=context;

        p1m = new PartOneMission(context,Boolean.FALSE,Boolean.FALSE);

        p2m = new PartTwoMission(context,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE);

        p3m = new PartThreeMission(context,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE);

        p4m = new PartFourMission(
                context,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,Boolean.FALSE);

       listAll=new ArrayList<MissionView>();



       switch (part){
           case 1: partOne();
               break;

           case 2: partOne();
                   partTwo();
               break;

           case 3: partOne();
                   partTwo();
                   partThree();
               break;

           case 4: partOne();
                   partTwo();
                   partThree();
                   partFour();
               break;

           case 5: partOne();
                   partTwo();
                   partThree();
                   partFour();
              break;
       }


   }

    public synchronized void partOne(){
        //Part One Missions

        Random rand1 = new Random();
        int randomNum1 = rand1.nextInt((max-min)+1)+min;
        listAll.add(p1m.listReturn().get(randomNum1));
       /* for(int i=0; i < p1m.listReturn().size(); i++){
            listAll.add(p1m.listReturn().get(i));
        }*/
    }
    public synchronized void partTwo(){
        //Part Two Missions
        Random rand2 = new Random();
        int randomNum2 = rand2.nextInt((max - min) + 1) + min;
        listAll.add(p2m.listReturn().get(randomNum2));
    }
    public synchronized void partThree(){
        //Part Three Missions
        Random rand3 = new Random();
        int randomNum3 = rand3.nextInt((max - min) + 1) + min;
        listAll.add(p3m.listReturn().get(randomNum3));
    }
    public synchronized void partFour(){
        //Part Four Missions
        Random rand4 = new Random();
        int randomNum4 = rand4.nextInt((max - min) + 1) + min;
        listAll.add(p4m.listReturn().get(randomNum4));
    }

    public ArrayList<MissionView> getMissions(){
        return listAll;
    }


}
