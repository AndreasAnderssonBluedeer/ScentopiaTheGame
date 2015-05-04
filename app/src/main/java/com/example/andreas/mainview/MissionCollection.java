package com.example.andreas.mainview;

import android.content.Context;
import android.view.View;
import android.widget.Switch;
import java.util.ArrayList;

/**
 * Created by Andreas on 2015-04-29.
 */
public class MissionCollection {
    private int id=1;
    private Context context;
    private ArrayList<MissionView> listAll;


   public MissionCollection(int part,Context context){
       this.context=context;


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
                   partFive();
              break;
       }


   }

    public synchronized void partOne(){
        //Part One Missions
       listAll.add(new MissionView(id++,"Testing Missions 1",31,41543,"Flammande bägare, o sånt",context));

       listAll.add(new MissionView(id++,"Something something 1",51,443,"Knaspatruller, o sånt" +
               "BLA BLA BLA BLABA LHEJSAN HOPPSA NEBALA",context));
    }
    public synchronized void partTwo(){
        //Part Two Missions
        listAll.add(new MissionView(id++,"Del två",31,51543,"uppdrag 3",context));
    }
    public synchronized void partThree(){
        //Part Three Missions
        listAll.add(new MissionView(id++,"Del tre",31,51543,"uppdrag 3",context));
    }
    public synchronized void partFour(){
        //Part Four Missions
        listAll.add(new MissionView(id++,"Del fyra",31,51543,"uppdrag 3",context));
    }
    public synchronized void partFive(){
        //Part Five Missions
        listAll.add(new MissionView(id++,"Del fem",31,51543,"uppdrag 3",context));
    }
    public ArrayList<MissionView> getMissions(){

        return listAll;
    }


}
