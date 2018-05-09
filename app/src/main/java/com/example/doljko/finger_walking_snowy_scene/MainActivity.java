package com.example.doljko.finger_walking_snowy_scene;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{

    private RelativeLayout myView;
    private ImageButton footStep;

    private float x;
    private float y;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = (RelativeLayout)findViewById(R.id.myView);
        footStep = (ImageButton)findViewById(R.id.footStep);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.snow);
        myView.setOnTouchListener(new OnSwipeTouchListener(this) {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mp.start();
                x = event.getX();
                y = event.getY();
                if (event.getAction() == MotionEvent.ACTION_MOVE) {

                    footStep.setX(x); //set the coordinate
                    footStep.setY(y);
                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {


                    Toast.makeText(MainActivity.this, "Swipe down"+event.getAction(), Toast.LENGTH_SHORT).show();
                }

                return true;
            }

            @Override
            public void onSwipeDown() {
                super.onSwipeDown();

                Toast.makeText(MainActivity.this, "Swipe down", Toast.LENGTH_SHORT).show();
            }


        });
    }

}

