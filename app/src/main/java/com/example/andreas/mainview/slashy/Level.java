package com.example.andreas.mainview.slashy;

import android.os.CountDownTimer;

/**
 * Created by Andreas on 2015-04-07.
 * A Level class to change difficulty depending on score.
 */
public class Level {

    private SlashyActivity sa;

    public Level(SlashyActivity sa){
        this.sa=sa;
    }

    public void checkLV(int points){    //Control points and upgrade difficulty.

        if(points==200){
            newAttack(1);           //Lvl 1
        }
        if(points==400){
             newAttack(2);          //Lvl 2
        }
        if(points==600){
            newAttack(3);           //Lvl 3
        }
        if(points==800){
            newAttack(4);           //Lvl 4
        }
        if(points==1000){
            newAttack(5);           //Lvl 5
        }
        if(points==1200){
            newAttack(6);           //Lvl 6
        }
        if(points==1400){
            newAttack(7);           //Lvl 7
        }
        if(points==1600){
            newAttack(8);           //Lvl 8
        }
        if(points==2000){
            newAttack(9);           //Lvl 9
        }

    }

    public void newAttack(int level){   //Switch case for what happens on each level.
        switch (level){
            case 1: sa.newLeft();       //Level 1=Case 1, Level 2= Case 2 and so fourth.
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
