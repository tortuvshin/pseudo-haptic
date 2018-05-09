package com.example.doljko.finger_walking_snowy_scene;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{

    private ImageView snowBackground;
    private ImageButton footStep;

    private float y;
    private float dy;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        snowBackground = (ImageView)findViewById(R.id.snow_background);
        footStep = (ImageButton)findViewById(R.id.footStep);


//        final ImageView backgroundOne = (ImageView) findViewById(R.id.background_one);
//        final ImageView backgroundTwo = (ImageView) findViewById(R.id.background_two);
//        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
//        animator.setRepeatCount(ValueAnimator.INFINITE);
//        animator.setInterpolator(new LinearInterpolator());
//        animator.setDuration(10000L);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                final float progress = (float) animation.getAnimatedValue();
//                final float width = backgroundOne.getWidth();
//                final float translationX = width * progress;
//                backgroundOne.setTranslationX(translationX);
//                backgroundTwo.setTranslationX(translationX - width);
//            }
//        });
//        animator.start();
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.snow);
        snowBackground.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mp.start();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        y = event.getY();
                        dy = y - snowBackground.getY();
                    }
                    break;
                    case MotionEvent.ACTION_MOVE: {
                        snowBackground.setY(event.getY() - dy);
                    }
                    break;
                    case MotionEvent.ACTION_UP: {
                        //your stuff
                    }
                    return true;
                }
                return true;
            }
        });
    }

}

