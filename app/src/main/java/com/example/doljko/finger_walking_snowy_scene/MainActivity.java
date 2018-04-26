package com.example.doljko.finger_walking_snowy_scene;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout myLayout = null;

    private Button luckyID= null;

    private float x;
    private float y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       myLayout = (RelativeLayout)findViewById(R.id.myLayout);
       luckyID = (Button)findViewById(R.id.luckyID);




       myLayout.setOnTouchListener(new View.OnTouchListener() {

           int i = 0;
           @Override
           public boolean onTouch(View v, MotionEvent event) {

               x = event.getX();
               y = event.getX();
               if (event.getAction() == MotionEvent.ACTION_MOVE) {

                   luckyID.setX(x); //set the coordinate
                   luckyID.setX(y);
               }
               if (++i >50){

                   return false;

               }
                return true;
           }

       });

    }
}
