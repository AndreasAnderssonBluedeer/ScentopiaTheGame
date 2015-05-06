package com.example.andreas.mainview;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Andreas on 2015-05-04.
 */
public class ItemCollection {

    private ArrayList<ItemView> itemList;
    private Context context;
    private int id=1;

    public ItemCollection(int part,Context context){
    this.context=context;
        itemList=new ArrayList<ItemView>();
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
    public void partOne(){
        //Part One Items, Mining Pick, Torch.

        itemList.add(new ItemView(id++,"Mining Pick",150, 1,Boolean.FALSE,null,context));
        itemList.add(new ItemView(id++,"Torch",99, 1,Boolean.FALSE,null,context));
    }
    public void partTwo(){
        //Part Two Items,Sickle, Rope, Rubber Boots.

        itemList.add(new ItemView(id++,"Sickle",191, 2,Boolean.FALSE,null,context));
        itemList.add(new ItemView(id++,"Rope",150, 2,Boolean.FALSE,null,context));
        itemList.add(new ItemView(id++,"Rubber Boots",212,2,Boolean.FALSE,null,context));
    }
    public void partThree(){
        //Part Three Items, Axe,Compass,Lumberjacks Permit, Horse.

        itemList.add(new ItemView(id++,"Axe",400, 3,Boolean.FALSE,null,context));
        itemList.add(new ItemView(id++,"Compass",180, 3,Boolean.FALSE,null,context));
        itemList.add(new ItemView(id++,"Lumberjacks Permit",220, 3,Boolean.FALSE,null,context));
        itemList.add(new ItemView(id++,"Horse",320, 3,Boolean.FALSE,null,context));

    }
    public void partFour(){
        //Part Four Items, Golden Wagon, Fancy Clothes, Umbrella, Parrot, Windmill.

        itemList.add(new ItemView(id++,"Golden Wagon",600, 4,Boolean.FALSE,null,context));
        itemList.add(new ItemView(id++,"Fancy Clothes",343, 4,Boolean.FALSE,null,context));
        itemList.add(new ItemView(id++,"Umbrella of Rainbows",500, 4,Boolean.FALSE,null,context));
        itemList.add(new ItemView(id++,"The One Legged Parrot",300, 4,Boolean.FALSE,null,context));
        itemList.add(new ItemView(id++,"Windmill",643, 4,Boolean.FALSE,null,context));
    }
    public void partFive(){
        //Part Five Items, Diving Suit
        itemList.add(new ItemView(id++,"Diving Suit of Honor",1000, 5,Boolean.FALSE,null,context));

    }
    public ArrayList<ItemView> getItemList(){
        return itemList;
    }
}
