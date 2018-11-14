package cloud.techstar.finger_walking_snowy_scene;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.os.Vibrator;

public class MainActivity extends Activity {

    int clickCount;
    private ConstraintLayout rootView;

    // Our object to handle the View
    boolean touchCounter = false;

    private MainView mainView;
    private TreeLayout treeView;
    private float y; // Y тэнхлэгт эхлээд хаана хүрсэн
    private float dy; // Y тэнхлэгт дараа нь хаана хүрч утга нь өөрчлөгдсөн
    private float x; // X тэнхлэгт эхлээд хаана хүрсэн
    private float dx; // X тэнхлэгт дараа нь хаана хүрч утга нь өөрчлөгдсөн
    private int Position_X;
    private int Position_Y;
    Vibrator vibrator;


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
        mainView = (MainView)findViewById(R.id.main_view);
        rootView = (ConstraintLayout)findViewById(R.id.rootView) ;

        Log.e("SIZE",""+resolution.x);
        mainView.init(resolution.x, resolution.y + getStatusBarHeight());
        treeView = (TreeLayout) findViewById(R.id.tree);
        treeView.init(500, 500);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.snow);


        mainView.setOnTouchListener(new View.OnTouchListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int rawX = (int) event.getRawX();
                final int rawY = (int) event.getRawY();

                    switch (event.getAction() & MotionEvent.ACTION_MASK) {


                        case MotionEvent.ACTION_DOWN: {

                            y = event.getY(); // А
                            dy = y - mainView.getY(); //
                            x = event.getX();
                            dx = x - mainView.getX();

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
                                mp.start();
                                mainView.setmLeft(y);
                                mainView.setmTop(x);
//                                mainView.print(mainView.getCanvas(), x,y);
                                vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                                vibrator.vibrate(50);
                                touchCounter = true;
                            }

                            treeView.update(80);
                            mainView.update(50);

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


        SnowFlakesLayout snowFlakesLayout = (SnowFlakesLayout)findViewById(R.id.snow_flake);
        snowFlakesLayout.init();
        snowFlakesLayout.setWholeAnimateTiming(3000000);
        snowFlakesLayout.setAnimateDuration(5000);
        snowFlakesLayout.setGenerateSnowTiming(50);
        snowFlakesLayout.setRandomSnowSizeRange(40, 1); // snow size
        snowFlakesLayout.setImageResourceID(R.drawable.snow_flakes_pic);
        snowFlakesLayout.setEnableRandomCurving(true);
        snowFlakesLayout.setEnableAlphaFade(true);
        snowFlakesLayout.startSnowing();
    }

    // If the Activity is paused make sure to pause our thread
    @Override
    protected void onPause() {
        super.onPause();
        mainView.pause();
    }

    // If the Activity is resumed make sure to resume our thread
    @Override
    protected void onResume() {
        super.onResume();
        mainView.resume();
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
