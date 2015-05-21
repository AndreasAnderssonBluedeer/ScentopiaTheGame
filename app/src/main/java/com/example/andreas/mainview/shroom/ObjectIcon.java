package com.example.andreas.mainview.shroom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Andreas on 2015-03-24.
 *
 * This Class is a Imagebutton that will call the Gamecontroller
 * to add points and then remove itself.
 */
public class ObjectIcon extends ImageButton{

    private GameController controller;
    private Drawable drawable;

    public ObjectIcon(Context context,GameController controller,Drawable drawable) {
        super(context);

        this.controller = controller;
        this.drawable = drawable;

        setBackground(drawable);       //Set the drawable as background.

        setOnClickListener(new ButtonListener());   //Add an Onclicklistener
    }


    public void remove(){   //Remove this imagebutton from the screen and add points by calling the controller.

        controller.removeObject(this);
        controller.addPoints();
    }

   private class ButtonListener implements OnClickListener {   //OnclickListener.

        @Override
        public void onClick(View v) {
        remove();
        }
    };
}
