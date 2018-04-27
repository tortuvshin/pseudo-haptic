package com.example.doljko.finger_walking_snowy_scene;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoundDemoActivity extends AppCompatActivity {


        Button b;
        static MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_demo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.snow);
        b = (Button)findViewById(R.id.butmove);
        b.setOnClickListener(new View.OnClikListener()

             @Override
                public void onClick(View v){
                mp.start();
                startActivities(new Intent(SoundDemoActivity.this, MainActivity.class));

                }


        });

    }



}
