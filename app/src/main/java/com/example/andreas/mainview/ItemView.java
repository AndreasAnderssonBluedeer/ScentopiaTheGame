package com.example.andreas.mainview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Andreas on 2015-05-04.
 */
public class ItemView extends RelativeLayout {

    private int id,gold,part;
    private String title;
    private Context context;
    private View item;
    private RelativeLayout layout;
    private boolean own = false;
    private boolean marked=false;
    private TextView txtItem, txtGold,txtPart;
    private ImageView background;
    private Drawable drawable;
    private CheckBox cb;

    public ItemView(int id, String title, int gold,int part,boolean own,Drawable drawable,Context context) {
        super(context);

        this.id=id;
        this.gold=gold;
        this.part=part;
        this.title=title;
        this.own=own;
        this.drawable=drawable;
        this.context=context;

        this.setId(id);

        create();
    }
    public void create(){
        LayoutInflater factory = LayoutInflater.from(context);
        item = factory.inflate(R.layout.item_layout, null);

        layout = (RelativeLayout) item.findViewById(R.id.itemView);
        layout.setId(id);
        item.setId(id);

        background=(ImageView) item.findViewById(R.id.itemImg);
        background.setBackground(drawable);

        txtGold = (TextView) item.findViewById(R.id.txtGold);
        txtItem = (TextView) item.findViewById(R.id.itemTitle);
        txtPart=(TextView) item.findViewById(R.id.txtDesc);
        cb=(CheckBox) item.findViewById(R.id.checkBox);

        cb.setEnabled(Boolean.FALSE);

        txtPart.setText("Increases Missionprobability in Part "+part);
        txtItem.setText(title);

        if(own){
            txtGold.setText("Bought");
            cb.toggle();
            this.setEnabled(Boolean.FALSE);
        }else {
            txtGold.setText(gold + " G");
        }
        this.addView(item);
    }
    public void mark(){
        marked=true;
        layout.setBackgroundColor(Color.parseColor("#b5aaaaaa"));
    }
    public void unMark(){
        marked=false;
        layout.setBackgroundColor(Color.parseColor("#00000000"));
    }
    public int getId(){
        return this.id;
    }

    public boolean isMarked(){
        return marked;
    }
    public boolean isBought(){
        return own;
    }
    public int getPrice(){
        return gold;
    }
    public String getName(){
        return title;
    }
    public void bought(){
        own=true;
        cb.toggle();
    }
    public int getPart(){
        return part;
    }
}
