package cloud.techstar.finger_walking_snowy_scene;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import cloud.techstar.finger_walking_snowy_scene.component.SnowFlakesLayout;

public class WebAppActivity extends AppCompatActivity {

    private Vibrator vibrator;
    private MediaPlayer mp;
    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_app);

        WebView myWebView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
        myWebView.loadUrl("file:android_asset/snow/viewer.html");

        mp = MediaPlayer.create(this, R.raw.snow);

        SnowFlakesLayout snowFlakesLayout = (SnowFlakesLayout)findViewById(R.id.web_snow_flake);
        snowFlakesLayout.init();
        snowFlakesLayout.setWholeAnimateTiming(3000000);
        snowFlakesLayout.setAnimateDuration(5000);
        snowFlakesLayout.setGenerateSnowTiming(100);
        snowFlakesLayout.setRandomSnowSizeRange(50, 10); // snow size
        snowFlakesLayout.setImageResourceID(R.drawable.snow_flakes_pic);
        snowFlakesLayout.setEnableRandomCurving(true);
        snowFlakesLayout.setEnableAlphaFade(true);
        snowFlakesLayout.startSnowing();
    }

    public class WebAppInterface {
        Context mContext;

        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void soundStart(){
            mp.start();
        }

        @JavascriptInterface
        public void vibrationStart(){
            vibrator = (Vibrator) this.mContext.getSystemService(Context.VIBRATOR_SERVICE);
            assert vibrator != null;
            vibrator.vibrate(50);
        }

        @JavascriptInterface
        public void logger(String message)
        {
            Log.d("WEBVIEW LOG: ", message);
        }
    }
}
