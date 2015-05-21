package com.example.andreas.mainview;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andreas.mainview.documents.Save;

import java.util.*;
/**
 * Created by Andreas on 2015-05-08.
 * A class to set Quest information in menu-tab "Quest".
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
        //Fetch Imageview and Label.
        img=(ImageView)ma.findViewById(R.id.questImg);
        txtQuest=(TextView)ma.findViewById(R.id.questTxt);

        setQuest(part);
    }

    public void setQuest(int part){     //Set resources and data for current questTab.

        itemcheck=new ArrayList<String>();  //List of which itemsTab is needed to unlock questTab.

        switch (part){  //Part=questTab. part 1= case/questTab 1 and so on.

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
        //Control if everything needed to unlock is ok, if true-unlock.

        int itemBought=0;

        for(int i=0;i<itemcheck.size();i++){
            for(int k=0;k<itemlist.size();k++){

                if(itemcheck.get(i).equals(itemlist.get(k).getName())&&
                        itemlist.get(k).isBought()){    //If its the same name and it's bought,add to itembought.

                    itemBought++;
                }
            }
        }
        if(itemBought==itemcheck.size()&&gold>=goldLimit&&part<5){   // If the number of bought itemsTab
                                            // is the same as nbr of itemsTab in itemcheck

            this.part++;
            save.writePart(this.part);  //Save the new unlocked part.
            this.part=save.getPart();
            setQuest(this.part);        //set next questTab.

            return true;
        }else {
            return false;
        }
    }

    public String getDialogMsg(boolean unlock){ //return Quest-result String.

        if(unlock==false&&getPart()==5){
            return "You've already unlocked the last part!!!";
        }
        if(unlock){
            return "Congratulations, You've unlocked part "+part+1;
        }else{
            return "Sorry, You're missing Gold and/or Items to unlock part "+part+".";
        }
    }

    public int getPart(){       //Get current part.
      return  save.getPart();
    }

    public int getGoldLimit(){  //Return goldlimit for questTab-unlock.
        return goldLimit;
    }

    public Drawable getMap(){   //Return map for each part.
        Drawable draw;
        switch (part){
            case 1: draw=ma.getResources().getDrawable(R.drawable.menu_map1);
                break;
            case 2: draw=ma.getResources().getDrawable(R.drawable.menu_map2);
                break;
            case 3: draw=ma.getResources().getDrawable(R.drawable.menu_map3);
                break;
            case 4: draw=ma.getResources().getDrawable(R.drawable.menu_map4);
                break;
            case 5: draw=ma.getResources().getDrawable(R.drawable.menu_map5);
                break;


            default: draw=ma.getResources().getDrawable(R.drawable.menu_map1);
                break;
        }
        return draw;
    }
}
