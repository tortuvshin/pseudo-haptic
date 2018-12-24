package cloud.techstar.finger_walking_snowy_scene;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.os.Vibrator;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SnowyActivity extends Activity {

    int clickCount;
    private ConstraintLayout rootView;

    // Our object to handle the View
    boolean touchCounter = false;

    private SnowySurface snowySurface;
    private float y; // Y тэнхлэгт эхлээд хаана хүрсэн
    private float dy; // Y тэнхлэгт дараа нь хаана хүрч утга нь өөрчлөгдсөн
    private float x; // X тэнхлэгт эхлээд хаана хүрсэн
    private float dx; // X тэнхлэгт дараа нь хаана хүрч утга нь өөрчлөгдсөн
    private int Position_X;
    private int Position_Y;
    Vibrator vibrator;
    private int cdRatioValue;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //        // Дэлгэцийн мэдээллийг харахын тулд Дэлгэцийн объект үүсгэх
        Display display = getWindowManager().getDefaultDisplay();

        // Resolution буюу тухайн төхөөрөмжийн дэлгэцийн хэмжээг Point обьект болгох
        Point resolution = new Point();
        display.getSize(resolution);

        setContentView(R.layout.activity_main);
        snowySurface = (SnowySurface)findViewById(R.id.main_view);
        rootView = (ConstraintLayout)findViewById(R.id.rootView) ;

        Log.e("SIZE",""+resolution.x);
        snowySurface.init(resolution.x, resolution.y + getStatusBarHeight());


        final MediaPlayer mp = MediaPlayer.create(this, R.raw.snow);

        Spinner ratioSpinner = findViewById(R.id.cd_ratio);

        List<String> cdRatios = new LinkedList<>(Arrays.asList("C/D Ratio 0.5", "C/D Ratio 0.6", "C/D Ratio 0.7", "C/D Ratio 0.8","C/D Ratio 0.9","C/D Ratio 1"));
        ArrayAdapter<String> ratioAdapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinner_item, cdRatios);
        ratioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratioSpinner.setAdapter(ratioAdapter);

        //i ni adapteriin utguudiin index 0.5_1 hurtel utguud ym.

        ratioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0 :
                        cdRatioValue =25;
                        break;
                    case 1 :
                        cdRatioValue =30;
                        break;
                    case 2 :
                        cdRatioValue =35;
                        break;
                    case 3 :
                        cdRatioValue =40;
                        break;
                    case 4 :
                        cdRatioValue =45;
                        break;
                    case 5 :
                        cdRatioValue =50;
                        break;
                    default:
                        cdRatioValue =25;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        snowySurface.setOnTouchListener(new View.OnTouchListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int rawX = (int) event.getRawX();
                final int rawY = (int) event.getRawY();

                    switch (event.getAction() & MotionEvent.ACTION_MASK) {


                        case MotionEvent.ACTION_DOWN: {

                            y = event.getY(); // А
                            dy = y - snowySurface.getY(); //
                            x = event.getX();
                            dx = x - snowySurface.getX();

                            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) v.getLayoutParams();
                            Position_X = rawX - layoutParams.leftMargin;
                            Position_Y = rawY - layoutParams.topMargin;

                        }
                        break;
                        case MotionEvent.ACTION_MOVE: {
                            //                        mainView.update(event.getX() - dx);

                            Log.d("", "BEFORE VALUE:  Y " + y + " X " + x +
                                    "\nCURRENT VALUE: Y " + event.getY() + " X " + event.getX() +
                                    "\nCHANGED VALUE: Y " + (event.getY() - dy) + " X " + (event.getX() - dx));
                            if (!touchCounter) {
                             //   mp.start();
                                snowySurface.setmLeft(y);
                                snowySurface.setmTop(x);
//                                mainView.print(mainView.getCanvas(), x,y);
                             //   vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                               // vibrator.vibrate(50);
                                touchCounter = true;
                            }

                            snowySurface.update(cdRatioValue);

                        }

                        case MotionEvent.ACTION_UP: {
                            //your stuff
                        }

                        rootView.invalidate();
                        return true;
                    }

                    rootView.invalidate();
                    touchCounter = false;
                    return true;

                }
        });


//        SnowFlakesLayout snowFlakesLayout = (SnowFlakesLayout)findViewById(R.id.snow_flake);
//        snowFlakesLayout.init();
//        snowFlakesLayout.setWholeAnimateTiming(3000000);
//        snowFlakesLayout.setAnimateDuration(5000);
//        snowFlakesLayout.setGenerateSnowTiming(50);
//        snowFlakesLayout.setRandomSnowSizeRange(40, 1); // snow size
//        snowFlakesLayout.setImageResourceID(R.drawable.snow_flakes_pic);
//        snowFlakesLayout.setEnableRandomCurving(true);
//        snowFlakesLayout.setEnableAlphaFade(true);
//        snowFlakesLayout.startSnowing();
    }

    // If the Activity is paused make sure to pause our thread
    @Override
    protected void onPause() {
        super.onPause();
        snowySurface.pause();
    }

    // If the Activity is resumed make sure to resume our thread
    @Override
    protected void onResume() {
        super.onResume();
        snowySurface.resume();
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void addFootPrint(float x, float y){

        final ImageView iv = new ImageView(this);
            iv.setImageResource(R.drawable.image);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(150, 150);
        iv.setX(x);
        iv.setY(y);
        iv.setRotation(270);
        iv.setLayoutParams(layoutParams);
        rootView.addView(iv, layoutParams);
    }
}
