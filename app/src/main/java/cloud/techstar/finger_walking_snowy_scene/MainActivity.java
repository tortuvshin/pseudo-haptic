package cloud.techstar.finger_walking_snowy_scene;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    // Our object to handle the View
    private MainView mainView;
    private TreeLayout treeView;
    private float y; // Y тэнхлэгт эхлээд хаана хүрсэн
    private float dy; // Y тэнхлэгт дараа нь хаана хүрч утга нь өөрчлөгдсөн
    private float x; // X тэнхлэгт эхлээд хаана хүрсэн
    private float dx; // X тэнхлэгт дараа нь хаана хүрч утга нь өөрчлөгдсөн

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Дэлгэцийн мэдээллийг харахын тулд Дэлгэцийн объект үүсгэх
        Display display = getWindowManager().getDefaultDisplay();

        // Resolution буюу тухайн төхөөрөмжийн дэлгэцийн хэмжээг Point обьект болгох
        Point resolution = new Point();
        display.getSize(resolution);

        setContentView(R.layout.activity_main);

        mainView = (MainView)findViewById(R.id.main_view);

        Log.e("SIZE",""+resolution.x);
        mainView.init(resolution.x, resolution.y + getStatusBarHeight());
        treeView = (TreeLayout) findViewById(R.id.tree);
        treeView.init(500, 500);




        mainView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN: {
                        y = event.getY(); // А
                        dy = y - mainView.getY(); //
                        x = event.getX();
                        dx = x - mainView.getX();
                    }
                    break;
                    case MotionEvent.ACTION_MOVE: {
//                        mainView.update(event.getX() - dx);
                        treeView.update(80);
                        mainView.update(40);
                        Log.d("", "BEFORE VALUE:  Y "+y +" X "+x +
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
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
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
}
