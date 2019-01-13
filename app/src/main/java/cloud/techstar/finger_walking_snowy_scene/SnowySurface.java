package cloud.techstar.finger_walking_snowy_scene;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class SnowySurface extends SurfaceView implements Runnable {

    ArrayList<Background> backgrounds; //арын background уудын жагсаалт

    private volatile boolean running;
    private Thread gameThread = null;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder ourHolder;

    Context context;

    private float mLeft, mTop;

    // Өөрчлөх хэмжээс
    static float fps = 0;

    // Дэлгэцийн хэмжээнүүд
    int screenWidth;
    int screenHeight;

    public SnowySurface(Context context) {
        super(context);
        this.context = context;
    }

    public SnowySurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void init(int screenWidth, int screenHeight) {

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        ourHolder = getHolder();
        paint = new Paint();

        // Хооронд нь солих арын зургуудийн array
        backgrounds = new ArrayList<>();

        backgrounds.add(new Background(
                this.context,
                screenWidth,
                screenHeight,
                "snow",  0, 100, 1));

        backgrounds.add(new Background(
                this.context,
                screenWidth,
                screenHeight,
                "snow",  70, 110, 1));
    }

    @Override
    public void run() {

        while (running) {

            update(fps);

            draw();

        }
    }

    public void update(float fps) {
        // Background-ийн байршлыг өөрчлөх
        for (Background bg : backgrounds) {
            bg.update(fps);
        }
    }

    private void draw() {

        if (ourHolder.getSurface().isValid()) {

            canvas = ourHolder.lockCanvas();

            canvas.drawColor(Color.argb(255, 0, 3, 70));

            drawBackground(0);

          //  Bitmap mur  = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.image), 120, 120, false);
            //canvas.drawBitmap(mur, getmLeft(), getmTop(), paint);

            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawBackground(int position) {

        Background bg = backgrounds.get(position);

        // Эхний background-ийн хувьд
        Rect toRect1 = new Rect(0, 0, bg.width - bg.xClip, bg.height);
        Rect fromRect1 = new Rect(bg.xClip, bg.startY, bg.width, bg.endY);

        // Солих background
        Rect toRect2 = new Rect(bg.width - bg.xClip, 0, bg.width, bg.height);
        Rect fromRect2 = new Rect(0, bg.startY, bg.xClip, bg.endY);

        //background зурах
        if (!bg.reversedFirst) {
            canvas.drawBitmap(bg.bitmap, fromRect1, toRect1, paint);
            canvas.drawBitmap(bg.bitmapReversed, fromRect2, toRect2, paint);
        } else {
            canvas.drawBitmap(bg.bitmap, fromRect2, toRect2, paint);
            canvas.drawBitmap(bg.bitmapReversed, fromRect1, toRect1, paint);
        }
    }

    public void pause() {
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void resume() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public float getmLeft() {
        return mLeft;
    }

    public float getmTop() {
        return mTop;
    }

    public void setmLeft(float mLeft) {
        this.mLeft = mLeft;
    }

    public void setmTop(float mTop) {
        this.mTop = mTop;
    }
}