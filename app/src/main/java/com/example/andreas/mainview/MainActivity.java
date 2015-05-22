package com.example.andreas.mainview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.ViewFlipper;
import java.util.*;

import com.example.andreas.mainview.documents.Save;
import com.example.andreas.mainview.memtopia.MemActivity;
import com.example.andreas.mainview.shroom.ShroomActivity;
import com.example.andreas.mainview.slashy.SlashyActivity;

/**
 * Created by Andreas.
 * Meddled with by Oak
 * Activity class with Map/Menu for the entire Game. Lore, Mission-tab, Minigame-tab,
 * Item-tab and Quest-tab. Communicates with Save,MissionCollection,PartQuest,Lore,
 * ItemCollection, SlashyActivity and ShroomActivity.
 */

public class MainActivity extends Activity {

    //Class variables.
    private Lore lore=new Lore();
    private Save save;
    private MissionLogic ml=new MissionLogic();
    private Partquest pQ;
    private MissionCollection mc;
    private ItemCollection ic;

    //Music Variables.
    private MediaPlayer mp;
    private boolean music=true;

    //Layouts
    private RelativeLayout layout,loreLay;                  //Map layouts.
    private RelativeLayout missionScrollLay,itemScroll;            //Scroll layouts.
    private RelativeLayout mgLay,missLay,itemLay,questLay;  //Tab-layouts.
    private RelativeLayout mgShroom,mgSlashy,mgMemory;      //Minigame layouts.

    //Graphical components.
    private ViewFlipper flip;                               //Flipper for "Tab-function".
    private SlidingDrawer menuSlide;                        //Menuslide for tabs.
    private HorizontalScrollView map;                       //Map scrollview.
    private Button btnCred,btnItem,btnMG,btnMiss,handle;    //Buttons.
    private TextView txtGold;                               //Gold Label.
    private ProgressBar loadProg;                           //Loader bar.

    private int gold=200;
    private Intent intent;
    private Context context =this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get saved gold
        save=new Save(context);
        gold=save.getGold();
        //if user gets back from minigame-> add gold.
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           gold+= Integer.parseInt(getIntent().getExtras().get("moneyVar").toString());
            save.writeGold(this.gold);
        }
        //Loading components.
        layout=(RelativeLayout)findViewById(R.id.layout);
        layout.setBackground(getResources().getDrawable(R.drawable.menu_loadingscreen));
        loadProg=(ProgressBar)findViewById(R.id.loadProg);
        loadProg.setMax(100);

        //Menu-slidingdrawer.
        handle=(Button)findViewById(R.id.handle);
        handle.setBackground(getResources().getDrawable(R.drawable.menu_arrow_up));
        menuSlide=(SlidingDrawer)findViewById(R.id.menuSlide);
        menuSlide.setEnabled(Boolean.FALSE);
        menuSlide.setVisibility(View.INVISIBLE);

        //To change the "Slide Icon" on handle when Menu is Open/Closed.
        menuSlide.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {//closed
            @Override
            public void onDrawerClosed() {
                handle.setBackground(getResources().getDrawable(R.drawable.menu_arrow_up));
            }
        });
            menuSlide.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {//Open
                @Override
                public void onDrawerOpened() {
                    handle.setBackground(getResources().getDrawable(R.drawable.menu_arrow_down));
                }
            });


        flip=(ViewFlipper)findViewById(R.id.flip);
        //Background map.
        map=(HorizontalScrollView)findViewById(R.id.map);
        map.setVisibility(View.INVISIBLE);
        map.setEnabled(Boolean.FALSE);

        //Tab layouts.
        mgLay=(RelativeLayout)findViewById(R.id.minigameLayout);
        missLay=(RelativeLayout)findViewById(R.id.missionLayout);
        itemLay=(RelativeLayout)findViewById(R.id.itemsLayout);
        questLay=(RelativeLayout)findViewById(R.id.questLayout);
        loreLay=(RelativeLayout)findViewById(R.id.loreLay);

        //Minigame layouts.
        mgShroom=(RelativeLayout)findViewById(R.id.mg1lay);
        mgSlashy=(RelativeLayout)findViewById(R.id.mg2lay);
        mgMemory=(RelativeLayout)findViewById(R.id.mg3lay);
        //Scroll layouts.
        missionScrollLay =(RelativeLayout)findViewById(R.id.scrollLay);
        itemScroll=(RelativeLayout)findViewById(R.id.relativeLayout2);

        //Buttons.
        btnMG=(Button)findViewById(R.id.btnGame);
        btnMiss=(Button)findViewById(R.id.btnMission);
        btnItem=(Button)findViewById(R.id.btnItem);
        btnCred=(Button)findViewById(R.id.btnQuest);

        //Gold Label.
        txtGold=(TextView)findViewById(R.id.txtGold);
        txtGold.setVisibility(View.INVISIBLE);

        //Start Background Music.
        mp = MediaPlayer.create(getApplicationContext(), R.raw.loadingsound);
        mp.start();

        pQ=new Partquest(this,save);

        loading();      //Loading/StartScreen
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds itemsTab to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //********************KOD*****************

    public void loading(){          //StartupScreen Loader
        new CountDownTimer(4000,25){
            int progress;
            @Override
            public void onTick(long millisUntilFinished) {  //Update Loading Bar.
                progress+=1;
                loadProg.setProgress(progress);
            }
            @Override
            public void onFinish() {

                layout.removeView(loadProg);        //Remove loading views and show "Menu/Map screen".
                map.setEnabled(Boolean.TRUE);       //Make visible and enabled.
                map.setVisibility(View.VISIBLE);
                loreLay.setBackground(pQ.getMap());
                menuSlide.setEnabled(Boolean.TRUE);
                menuSlide.setVisibility(View.VISIBLE);
                txtGold.setVisibility(View.VISIBLE);

                //Fetch and show Gold.
                gold=save.getGold();
                txtGold.setText(gold+" G");
                //Fill Tab-Lists.
                fillItemsList(save.getItems());
                fillMissionList();
                //Start Background music.
                mp = MediaPlayer.create(getApplicationContext(), R.raw.tripping);
                mp.start();
                music();    // replay song if ended.

            }
        }.start();
    }

    public void markMission(View lay){  //Highlight selected Mission

        unMarkMissions();   //Unmark all missionsTab
        for(int i=0;i<mc.getMissions().size();i++){ //Then highlight the selected one.

            if(mc.getMissions().get(i).getId()==lay.getId()) {
                mc.getMissions().get(i).mark();
            }
        }
    }

    public void unMarkMissions(){   //Unmarks all missionsTab/removes highlight.
        for(int i=0;i<mc.getMissions().size();i++){
            mc.getMissions().get(i).unMark();
        }
    }

    public void startMission(View btn){     //do selected mission.
        for(int i=0;i<mc.getMissions().size();i++){
          if(mc.getMissions().get(i).isMarked()){

                int missiongold=ml.doMission(mc.getMissions().get(i).getProbability(),
                         mc.getMissions().get(i).getGold());    //Receive gold from mission

                dialogBox(ml.getDialog(),null); //Show dialog, Mission OK/Not OK

              gold = gold +missiongold;         //Update Gold.
              save.writeGold(gold);
              gold=save.getGold();
              txtGold.setText(gold + " G");

            }
        }
    }

    public void resetTabButtoncolor(){ //Reset highlight on "tab-buttons".
        btnMiss.setBackgroundColor(Color.parseColor("#8c213e42"));
        btnMG.setBackgroundColor(Color.parseColor("#8c213e42"));
        btnItem.setBackgroundColor(Color.parseColor("#8c213e42"));
        btnCred.setBackgroundColor(Color.parseColor("#8c213e42"));
    }

    public void missionsTab(View button){  //Highlight and show MissionTab.
        resetTabButtoncolor();
        button.setBackgroundColor(Color.parseColor("#9e330e80"));
        flip.setDisplayedChild(flip.indexOfChild(missLay));
    }

    public void minigamesTab(View button){   //Highlight and show MinigamesTab.
        resetTabButtoncolor();
        button.setBackgroundColor(Color.parseColor("#9e330e80"));
        flip.setDisplayedChild(flip.indexOfChild(mgLay));
    }

    public void itemsTab(View button){  //Highlight and show ItemsTab.
        resetTabButtoncolor();
        button.setBackgroundColor(Color.parseColor("#9e330e80"));
        flip.setDisplayedChild(flip.indexOfChild(itemLay));
    }

    public void questTab(View button){  //Highlight and show QuestTab.
        resetTabButtoncolor();
        button.setBackgroundColor(Color.parseColor("#9e330e80"));
        flip.setDisplayedChild(flip.indexOfChild(questLay));
    }

    public void startSlashy(View layout){   //Highlight Slashy in MinigameTab.
        unMarkGames();
        intent = new Intent(context, SlashyActivity.class);
        mgSlashy.setBackgroundColor(Color.parseColor("#b5aaaaaa"));
    }

    public void startShroom(View layout){   //Highlight Shroom in MinigameTab.
        unMarkGames();
        intent = new Intent(context, ShroomActivity.class);
        mgShroom.setBackgroundColor(Color.parseColor("#b5aaaaaa"));
    }

    public void startMemory(View layout){   //Highlight Memory in MinigameTab.
        unMarkGames();
        intent = new Intent(context, MemActivity.class);
        mgMemory.setBackgroundColor(Color.parseColor("#b5aaaaaa"));
    }

    public void unMarkGames(){  //Unmark minigames in MinigamesTab.

          mgShroom.setBackgroundColor(Color.parseColor("#00000000"));
          mgSlashy.setBackgroundColor(Color.parseColor("#00000000"));
          mgMemory.setBackgroundColor(Color.parseColor("#00000000"));
    }

    public void startMinigame(View button){ //Start highlighted/Selected Minigame in MinigameTab.
                                            // Changes Activity.
        music=false;
        mp.stop();
        startActivity(intent);
    }

    public void fillMissionList(){  //Fill Missions-scrollView with Missions in MissionsTab.

        missionScrollLay.removeAllViews(); //remove old Missions( to make sure that there's no duplicates.
        mc=new MissionCollection(pQ.getPart(),ic.getItemList(),context);  //Fetch an updated missionlist.

        for(int i=0;i<mc.getMissions().size();i++){ //Add missions to scrollview with specific positions.
            if(i>0){
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                params.addRule(RelativeLayout.BELOW,mc.getMissions().get(i-1).getId());
                missionScrollLay.addView(mc.getMissions().get(i), params);
            }else{
                missionScrollLay.addView(mc.getMissions().get(i));
            }
        }
    }

    public void fillItemsList(ArrayList<String> saveList){ //Fill Items-scrollView with Items in ItemsTab.

        itemScroll.removeAllViews(); //Remove old items to make sure that there's no duplicates.
        ic=new ItemCollection(pQ.getPart(),context);    //Fetch an updated Itemlist.

        for(int i=0;i<ic.getItemList().size();i++){ //Fill Item-checkbox if it's already bought.
            for(int k=0;k<saveList.size();k++){
                if(ic.getItemList().get(i).getName().equals(saveList.get(k))){
                    ic.getItemList().get(i).buy();
                }
            }
            if(i>0){    //Add Items with specific positions.
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                params.addRule(RelativeLayout.BELOW,ic.getItemList().get(i-1).getId());
                itemScroll.addView(ic.getItemList().get(i),params);
            }else{
                itemScroll.addView(ic.getItemList().get(i));
            }
        }
    }

    public void markItem(View item){    //Highlight selected Item.

        unMarkItems();  //Unmark all items.
        for(int i=0;i<ic.getItemList().size();i++){ //Mark/Highlight Selected item.

            if(ic.getItemList().get(i).getId()==item.getId()) {
                ic.getItemList().get(i).mark();
            }
        }
    }

    public void unMarkItems(){  //Unmarks all Items.

        for(int i=0;i<ic.getItemList().size();i++){
            ic.getItemList().get(i).unMark();
        }
    }

    public void buyItem(View btn) { //Try to Buy selected Item.

        //Loop through list until you find marked Item.
        for (int i = 0; i < ic.getItemList().size(); i++) {
            if (ic.getItemList().get(i).isMarked()) {   //If item is marked.
                if (!ic.getItemList().get(i).isBought()) {  //Check if it's already bought.
                    if (ic.getItemList().get(i).getPrice() <= gold) {   //In case its not,Can you afford it?

                        //If Yes, Update Gold.
                        gold = gold - ic.getItemList().get(i).getPrice();
                        save.writeGold(gold);
                        gold=save.getGold();
                        txtGold.setText(gold + " G");
                        //Fill Items checkbox as Bought.
                        ic.getItemList().get(i).buy();
                        save.writeItem( ic.getItemList().get(i).getName());   //save item to file
                        //Remove all Missions to update Probability.
                        missionScrollLay.removeAllViews();
                        fillMissionList();

                        //Show a confirmation dialog if item is bought or not.
                        new AlertDialog.Builder(this)
                                .setCancelable(false)
                                .setMessage(ic.getItemList().get(i).getName() + " is now Yours.")
                                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
                    }else{
                        dialogBox("Sorry,You need more GOLD.",null);
                    }
                }
                    else {
                        dialogBox("You already own this ITEM.",null);
                    }
                }
            }
        }

    public void dialogBox(String msg,String title){ //Show a dialog with Okay button.

        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    public void unlockPart(View btn){   //Try to unlock next Part.

       int unlockPrice=pQ.getGoldLimit();
       boolean ul= pQ.unlockPart(ic.getItemList(),gold);

       if(ul){  //If unlock succeeded.
           gold = gold - unlockPrice;   //Update Gold.
           save.writeGold(gold);
           gold=save.getGold();
           txtGold.setText(gold + " G");

           //Show a dialog with result,change map and update items- & mission-lists.
           dialogBox(pQ.getDialogMsg(ul),null);
           fillItemsList(save.getItems());
           fillMissionList();
           loreLay.setBackground(pQ.getMap());

       }else{   //If unlock failed, show dialog.
           dialogBox(pQ.getDialogMsg(ul),null);
       }
    }

    public void loreBtn1(View btn){ //Show Lore 1
    if(pQ.getPart()>=1){
        dialogBox(lore.getLore1(),"Plain Plains");
    }
    }

    public void loreBtn2(View btn){  //Show Lore 2
        if(pQ.getPart()>=2) {
            dialogBox(lore.getLore2(), "Eastern Hills");
        }
    }

    public void loreBtn3(View btn) {     //Show Lore 3
        if (pQ.getPart() >= 3) {
            dialogBox(lore.getLore3(), "Wood Forest");
        }
    }

    public void loreBtn4(View btn) {     //Show Lore 4
        if (pQ.getPart() >= 4) {
            dialogBox(lore.getLore4(), "Golden Plains & Salty Sea");
        }
    }

    public void loreBtn5(View btn) {     //Show Lore 5
        if (pQ.getPart() >= 5) {
            dialogBox(lore.getLore5(), "Went");
        }
    }

    public void music() {   //Controls background music.

        new Thread() {
            public void run() { //repeat song if ended.
                while (music) {
                    if (mp.isPlaying() == false) {
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.tripping);
                        mp.start();
                    }
                }
            }
        }.start();
    }



}
