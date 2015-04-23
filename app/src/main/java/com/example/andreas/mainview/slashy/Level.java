package com.example.andreas.mainview.slashy;

import android.os.CountDownTimer;

/**
 * Created by Andreas on 2015-04-07.
 */
public class Level {
    private SlashyActivity sa;
    public Level(SlashyActivity sa){
        this.sa=sa;
    }

    public void checkLV(int points){    //kontrollerar poängen och uppgraderar svårigheten.

        if(points==200){

            countdown(1);           //Lvl 1
        }
        if(points==400){
             countdown(2);          //Lvl 2 osv.
        }
        if(points==600){
            countdown(3);
        }
        if(points==800){
            countdown(4);
        }
        if(points==1000){
            countdown(5);
        }
        if(points==1200){
            countdown(6);
        }
        if(points==1400){
            countdown(7);
        }
        if(points==1600){
            countdown(8);
        }
        if(points==2000){
            countdown(9);
        }

    }
    public void countdown(final int level){  //Funkar detta med final? pga onFinish()

        new CountDownTimer(400, 400) {
            @Override
            public void onTick(long millisUntilFinished){
            }

            @Override
            public void onFinish() {
            newAttack(level);
            }


        }.start();
    }
    public void newAttack(int level){
        switch (level){
            case 1: sa.newLeft();       //Level 1=Case 1, Level 2= Case 2 osv.
                    break;

            case 2: sa.newRight();
                    break;

            case 3: sa.newLeft();
                    sa.newRight();
                    break;

            case 4: sa.changeAttackRightSpeed(2);
                    break;

            case 5: sa.newRight();
                    break;

            case 6: sa.changeAttackLeftSpeed(2);
                    break;

            case 7: sa.newLeft();
                    break;

            case 8: sa.changeAttackRightSpeed(3);
                    break;

            case 9: sa.changeAttackLeftSpeed(3);
        }
    }
}
