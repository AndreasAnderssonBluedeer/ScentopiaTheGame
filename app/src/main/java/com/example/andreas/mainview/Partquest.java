package com.example.andreas.mainview;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andreas.mainview.documents.Save;

import java.util.*;
/**
 * Created by Andreas on 2015-05-08.
 */
public class Partquest {

    private int part;

    private ImageView img;
    private TextView txtQuest;
    private MainActivity ma;
    private ArrayList<String> itemcheck;
    private int goldLimit;
    private Save save;

    public Partquest(MainActivity ma,Save save){
        this.save=save;
        this.part=save.getPart();
        this.ma=ma;

        img=(ImageView)ma.findViewById(R.id.questImg);
        txtQuest=(TextView)ma.findViewById(R.id.questTxt);

        setQuest(part);
    }
    public void setQuest(int part){
        itemcheck=new ArrayList<String>();
        switch (part){
            case 1:
                goldLimit=1000;
                itemcheck.add(("Mining Pick"));
                itemcheck.add("Torch");
                txtQuest.setText("Part 1, Mines and stuff"+goldLimit);
                img.setBackground(ma.getResources().getDrawable(R.drawable.shroom_shroom));

                break;
            case 2:
                goldLimit=2000;
                itemcheck.add("Sickle");
                itemcheck.add("Rope");
                itemcheck.add("Rubber Boots");
                txtQuest.setText("Part 2, Bruther Snurfish, crazyness"+goldLimit);
                img.setBackground(ma.getResources().getDrawable(R.drawable.slashy_monster_left1));
                break;
            case 3:
                goldLimit=3000;
                itemcheck.add("Axe");
                itemcheck.add("Compass");
                itemcheck.add("Lumberjacks Permit");
                itemcheck.add("Horse");
                txtQuest.setText("Part 3, Getting high"+goldLimit);
                img.setBackground(ma.getResources().getDrawable(R.drawable.slashy_hitpoints1));
                break;
            case 4:
                goldLimit=4000;
                itemcheck.add("Golden Wagon");
                itemcheck.add("Fancy Clothes");
                itemcheck.add("Umbrella of Rainbows");
                itemcheck.add("The One Legged Parrot");
                itemcheck.add("Windmill");
                txtQuest.setText("Part 4, its getting serious!"+goldLimit);
                img.setBackground(ma.getResources().getDrawable(R.drawable.slashy_knight_leftfull));
                break;
            case 5:
                goldLimit=5000;
                itemcheck.add("Diving Suit of Honor");
                txtQuest.setText("Part 5,WIHOOOOO"+goldLimit);
                img.setBackground(ma.getResources().getDrawable(R.drawable.slashy_knight_righthalf));
                break;

        }
    }
    public boolean unlockPart(ArrayList<ItemView> itemlist,int gold){
        int itemBought=0;
        for(int i=0;i<itemcheck.size();i++){
            for(int k=0;k<itemlist.size();k++){
                System.out.println(itemlist.get(k).getName());
                if(itemcheck.get(i).equals(itemlist.get(k).getName())&&
                        itemlist.get(k).isBought()){    //If its the same name and it's bought,add to itembought.
                    itemBought++;
                    System.out.println(itemcheck.get(i)+","+itemlist.get(k).getName()+","+itemBought
                    +","+itemcheck.size()+","+gold+","+goldLimit+","+itemlist.size());
                }
            }
        }
        if(itemBought==itemcheck.size()&&gold>=goldLimit&&part<5){   // If the number of bought items
                                            // is the same as nbr of items in itemcheck

            this.part++;
            save.writePart(this.part);
            this.part=save.getPart();
            setQuest(this.part);
            //Ny missioncollection och itemlist.
            return true;
        }else {
            return false;
        }
    }
    public String getDialogMsg(boolean unlock){
        if(unlock==false&&getPart()==5){
            return "You've already unlocked the last part!!!";
        }
        if(unlock){
            return "Congratulations, You've unlocked part "+part;
        }else{
            return "Sorry, You're missing Gold and/or Items to unlock part "+part+".";
        }

    }
    public int getPart(){
      return  save.getPart();
    }
    public int getGoldLimit(){
        return goldLimit;
    }
    public Drawable getMap(){
        Drawable draw;
        switch (part){
            case 1: draw=ma.getResources().getDrawable(R.drawable.menu_backgroundtest);
                break;
            case 2: draw=ma.getResources().getDrawable(R.drawable.menu_backgroundtest);
                break;
            case 3: draw=ma.getResources().getDrawable(R.drawable.menu_backgroundtest);
                break;
            case 4: draw=ma.getResources().getDrawable(R.drawable.menu_backgroundtest);
                break;
            case 5: draw=ma.getResources().getDrawable(R.drawable.menu_backgroundtest);
                break;


            default: draw=ma.getResources().getDrawable(R.drawable.menu_backgroundtest);
                break;
        }
        return draw;
    }
}
