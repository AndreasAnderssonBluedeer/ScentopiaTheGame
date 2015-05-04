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
        //Part One Items
        itemList.add(new ItemView(id++,"Mining Pick",150, 1,Boolean.FALSE,null,context));
        itemList.add(new ItemView(id++,"Mining Torch",243, 1,Boolean.TRUE,null,context));
    }
    public void partTwo(){
        //Part One Items
        itemList.add(new ItemView(id++,"Mining Pick",331, 2,Boolean.TRUE,null,context));
        itemList.add(new ItemView(id++,"Mining Torch",542, 2,Boolean.TRUE,null,context));
    }
    public void partThree(){
        //Part One Items
        itemList.add(new ItemView(id++,"Mining Pick",765, 3,Boolean.TRUE,null,context));
        itemList.add(new ItemView(id++,"Mining Torch",855, 3,Boolean.FALSE,null,context));
    }
    public void partFour(){
        //Part One Items
        itemList.add(new ItemView(id++,"Mining Pick",422, 4,Boolean.TRUE,null,context));
        itemList.add(new ItemView(id++,"Mining Torch",443, 4,Boolean.TRUE,null,context));
    }
    public void partFive(){
        //Part One Items
        itemList.add(new ItemView(id++,"Mining Pick",500, 5,Boolean.FALSE,null,context));
        itemList.add(new ItemView(id++,"Mining Torch",553, 5,Boolean.FALSE,null,context));
    }
    public ArrayList<ItemView> getItemList(){
        return itemList;
    }
}
