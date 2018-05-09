package cloud.techstar.finger_walking_snowy_scene;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private ImageView snowBackground;

    private float y;
    private float dy;
    private float x;
    private float dx;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        snowBackground = (ImageView) findViewById(R.id.snow_background);
        final TextView valueText = (TextView) findViewById(R.id.y_value);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.snow);
        snowBackground.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                mp.start();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        y = event.getY();
                        dy = y - snowBackground.getY();
                        x = event.getX();
                        dx = x - snowBackground.getX();

                    }
                    break;
                    case MotionEvent.ACTION_MOVE: {
                        snowBackground.setY(event.getY() - dy);
//                        snowBackground.setX(event.getX() - dx);

                        valueText.setText("BEFORE VALUE:  Y "+y +" X "+x +
                                "\nCURRENT VALUE: Y "+ event.getY()+ " X "+event.getX()+
                                "\nCHANGED VALUE: Y "+ (event.getY()-dy) + " X "+(event.getX() - dx));
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

