package com.example.andreas.mainview.memtopia;

import android.os.CountDownTimer;

import java.util.ArrayList;

/**
 * Created by david_000 on 2015-04-08.
 */
public class Controller {
    private MemActivity memActivity;
    private int counter = 200, gameFinished = 0;
    private ArrayList<Tile> array = new ArrayList<Tile>();
    private ArrayList<Tile> tiles = new ArrayList<>();


    public Controller(MemActivity memActivity){
        this.memActivity = memActivity;
    }
    public void addTiles(ArrayList<Tile> tiles){
        this.tiles = tiles;
    }

    public Boolean isFull(){
        return array.size() == 2;
    }


    public void check(Tile tile){
        array.add(tile);
            if (array.size() == 2) {
                if (array.get(0).getName().equals(array.get(1).getName())) {
                    array.get(0).locked(true);
                    array.get(1).locked(true);
                    array.clear();
                    gameFinished += 1;
                    if(gameFinished == 6){
                        memActivity.dialogBox("Your final score is: " + String.valueOf(counter),
                                "Congratulations!\n+"+String.valueOf(counter)+" Gold.");
                    }


                }
                else if(!array.get(0).getName().equals(array.get(1).getName())){
                    counter = counter - 10;
                    memActivity.changePoints(counter);
                    new CountDownTimer(2000, 2000) {
                        @Override

                        public void onTick(long millisUntilFinished) {

                            }


                        @Override
                        public void onFinish() {
                            for(int i = 0; i<tiles.size(); i++){
                                tiles.get(i).locked(false);
                            }
                            array.get(0).locked(false);
                            array.get(1).locked(false);
                            array.get(0).hide();
                            array.get(1).hide();
                            array.clear();

                        }
                    }.start();

                }

            }

            }

                }




