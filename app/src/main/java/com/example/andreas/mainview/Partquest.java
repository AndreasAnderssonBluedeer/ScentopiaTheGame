package com.example.andreas.mainview;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andreas.mainview.documents.Save;

import java.util.*;
/**
 * Created by Andreas on 2015-05-08.
 * Added content by Oak 2015-05-15
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
                txtQuest.setText("Big Beard: Hi there! So you want to make a perfume to impress the Queen?" +
                        " Well, I have some diamond sweat you can buy. But you need to prove your worth" +
                        " first! Earn your mining equipment first, earn the cash and come talk again. Off to" +
                        " work with ye!"+goldLimit);
                img.setBackground(ma.getResources().getDrawable(R.drawable.quest_bigbeard));
                break;

            case 2:
                goldLimit=2000;
                itemcheck.add("Sickle");
                itemcheck.add("Rope");
                itemcheck.add("Rubber Boots");
                txtQuest.setText("Brother Snurf: Greetings, I know of a plant you need for your quest!" +
                        " It's called a Thunder Plant and you can find it high in the mountains, with me help" +
                        " of course. You need some supplies so get them and a fee for me, your humble servant and guide." +
                        " How much? Oh, just a small sum."+goldLimit);
                img.setBackground(ma.getResources().getDrawable(R.drawable.quest_brothers));
                break;

            case 3:
                goldLimit=3000;
                itemcheck.add("Axe");
                itemcheck.add("Compass");
                itemcheck.add("Lumberjacks Permit");
                itemcheck.add("Horse");
                txtQuest.setText("Mega Mustasch: Oh! Oh, hello! You want some of my jointed petals huh? DO NOT DENY IT!" +
                        " My brother Big Beard told me all about your quest, yes yes... Well! You need some things to " +
                        "get ready and a finders fee for me of course then off we go, yes yes. Let's get cracking!"+goldLimit);
                img.setBackground(ma.getResources().getDrawable(R.drawable.quest_megam));
                break;

            case 4:
                goldLimit=4000;
                itemcheck.add("Golden Wagon");
                itemcheck.add("Fancy Clothes");
                itemcheck.add("Umbrella of Rainbows");
                itemcheck.add("The One Legged Parrot");
                itemcheck.add("Windmill");
                txtQuest.setText("Red Eric: Ahoy! No, you don't need to see my face. We shall keep the fourth wall intact." +
                        " To get into Went you need to have plenty of things to seem wealthy, what things? Just look at the list! I am also " +
                        "greedy so some gold for me, if you please."+goldLimit);
                img.setBackground(ma.getResources().getDrawable(R.drawable.quest_rede));
                break;

            case 5:
                goldLimit=5000;
                itemcheck.add("Diving Suit of Honor");

                itemcheck.add("Shark Harpoon");
                itemcheck.add("The Red Ship");
                itemcheck.add("Treasure Map");
                itemcheck.add("Perfume Shop");
                itemcheck.add("Court Wig");

                txtQuest.setText("Nose Guard Davis: I am the watcher of the fourth wall! Audience with the Queen? Ha! You need a Purple Crystal Vial" +
                        " in which to present your gift. To get that you need loads of things to get to the bottom of the bay. Also a " +
                        "court wig since that is all the rage, a shop here in the city so you atleast seem legitimate. Bribe me after that " +
                        " and I will let you in. Of course I need a bribe, have you seen this helmet!?"+goldLimit);
                img.setBackground(ma.getResources().getDrawable(R.drawable.quest_noseg));
                break;
            case 6:
                txtQuest.setText("What is this? A perfume consisting of th greatest things from all of Scentopia? My goodness! " +
                        "...it's awful! Vile even! But... You got this far and I am a just and now smelly queen! You, have won the game.");
                img.setBackground(ma.getResources().getDrawable(R.drawable.quest_queens));
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
        if(itemBought==itemcheck.size()&&gold>=goldLimit&&part<6){   // If the number of bought itemsTab
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

        if(unlock==false&&getPart()==6){
            return "You've already unlocked the last part!!!";
        }
        if(unlock&&getPart()==6){
            return "Congratulations, You've now presented your gift to the queen and won the game!";
        }
        if(unlock){
            return "Congratulations, You've unlocked part "+part;
        }
        else{
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
            case 6: draw=ma.getResources().getDrawable(R.drawable.menu_map5);
                break;

            default: draw=ma.getResources().getDrawable(R.drawable.menu_map1);
                break;
        }
        return draw;
    }
}
