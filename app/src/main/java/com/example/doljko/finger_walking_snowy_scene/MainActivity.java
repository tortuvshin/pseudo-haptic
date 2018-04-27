package com.example.doljko.finger_walking_snowy_scene;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout myLayout;

    private ImageButton luckyID;

    private float x;
    private float y;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       myLayout = (RelativeLayout)findViewById(R.id.myLayout);
       luckyID = (ImageButton)findViewById(R.id.luckyID);

       final MediaPlayer mp = MediaPlayer.create(this, R.raw.snow);
       myLayout.setOnTouchListener(new View.OnTouchListener() {

           @Override
           public boolean onTouch(View v, MotionEvent event) {
               mp.start();
               x = event.getX();
               y = event.getY();
               if (event.getAction() == MotionEvent.ACTION_MOVE) {

                   luckyID.setX(x); //set the coordinate
                   luckyID.setY(y);
               }

                return true;
           }

       });

    }
}
