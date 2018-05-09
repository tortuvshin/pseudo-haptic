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

    private float x, y, dx, dy;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = (RelativeLayout)findViewById(R.id.myView);
        footStep = (ImageButton)findViewById(R.id.footStep);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.snow);
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mp.start();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        x = event.getX();
                        y = event.getY();
                        dx = x - myView.getX();
                        dy = y - myView.getY();
                    }
                    break;
                    case MotionEvent.ACTION_MOVE: {
                        myView.setX(event.getX() - dx);
                        myView.setY(event.getY() - dy);
                    }
                    break;
                    case MotionEvent.ACTION_UP: {
                        //your stuff
                    }
                    return true;
                }
                mp.stop();
                return true;
            }
        });
    }

}

