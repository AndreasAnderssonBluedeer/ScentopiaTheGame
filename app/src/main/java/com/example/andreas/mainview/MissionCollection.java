package com.example.andreas.mainview;

import android.content.Context;
import android.view.View;
import android.widget.Switch;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;

/**
 * Created by Andreas on 2015-04-29.
 */
public class MissionCollection {
    private int id;
    private int min=0;
    private int max=4;
    private Context context;
    private ArrayList<MissionView> listAll;
    private PartOneMission p1m;
    private PartTwoMission p2m;
    private PartThreeMission p3m;
    private PartFourMission p4m;
    private HashMap<String,Boolean> booleanList;
    private ArrayList<ItemView> itemList;

   public MissionCollection(int part,ArrayList<ItemView> itemList,Context context){
       this.context=context;
        this.itemList=itemList;

        booleanList=new HashMap<>();
        fillBooleanMap(part);








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

    public void fillBooleanMap(int part){
       id=0;
       switch (part){
           case 1:
               booleanList1();
               break;
           case 2:
               booleanList1();
               booleanList2();
               break;
           case 3:
               booleanList1();
               booleanList2();
               booleanList3();
               break;
           case 4:
               booleanList1();
               booleanList2();
               booleanList3();
               booleanList4();
               break;
           case 5:
               booleanList1();
               booleanList2();
               booleanList3();
               booleanList4();
               booleanList5();
               break;
       }


    }
    public void booleanList1(){
        //Part one Items.
        booleanList.put("Mining Pick",itemList.get(id++).isBought());
        booleanList.put("Torch",itemList.get(id++).isBought());

        p1m = new PartOneMission(context,booleanList.get("Mining Pick"),booleanList.get("Torch"));
    }
    public void booleanList2(){
        //Part two Items.
        booleanList.put("Sickle",itemList.get(id++).isBought());
        booleanList.put("Rope",itemList.get(id++).isBought());
        booleanList.put("Rubber Boots",itemList.get(id++).isBought());

        p2m = new PartTwoMission(context,booleanList.get("Sickle"),
                booleanList.get("Rope"),booleanList.get("Rubber Boots"));


    }
    public void booleanList3(){
        //Part Three Items.
        booleanList.put("Axe",itemList.get(id++).isBought());
        booleanList.put("Compass",itemList.get(id++).isBought());
        booleanList.put("Lumberjacks Permit",itemList.get(id++).isBought());
        booleanList.put("Horse",itemList.get(id++).isBought());

        p3m = new PartThreeMission(context,booleanList.get("Axe"),booleanList.get("Compass")
                ,booleanList.get("Lumberjacks Permit"),booleanList.get("Horse"));

    }
    public void booleanList4(){
        //Part Four Items.
        booleanList.put("Golden Wagon",itemList.get(id++).isBought());
        booleanList.put("Fancy Clothes",itemList.get(id++).isBought());
        booleanList.put("Umbrella of Rainbows",itemList.get(id++).isBought());
        booleanList.put("The One Legged Parrot",itemList.get(id++).isBought());
        booleanList.put("Windmill",itemList.get(id++).isBought());

        p4m = new PartFourMission(
                context,booleanList.get("Golden Wagon"),booleanList.get("Fancy Clothes")
                ,booleanList.get("Umbrella of Rainbows"),booleanList.get("The One Legged Parrot"),
                booleanList.get("Windmill"));
    }
    public void booleanList5(){
        //Part Five Items.
        booleanList.put("Diving Suit of Honor",itemList.get(id++).isBought());
    }
}
