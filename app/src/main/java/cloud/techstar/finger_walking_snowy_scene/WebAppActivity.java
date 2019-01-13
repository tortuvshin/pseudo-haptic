package cloud.techstar.finger_walking_snowy_scene;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import cloud.techstar.finger_walking_snowy_scene.component.SnowFlakesLayout;

public class WebAppActivity extends AppCompatActivity {

    private Vibrator vibrator;
    private MediaPlayer mp;
    private double cdRatioValue;

    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_app);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setVerticalScrollBarEnabled(false);
        myWebView.setHorizontalScrollBarEnabled(false);
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


        Spinner ratioSpinner = findViewById(R.id.ratio_spinner);

        List<String> cdRatios = new LinkedList<>(Arrays.asList("C/D Ratio 0.5", "C/D Ratio 0.6", "C/D Ratio 0.7", "C/D Ratio 0.8","C/D Ratio 0.9","C/D Ratio 1"));
        ArrayAdapter<String> ratioAdapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinner_item, cdRatios);
        ratioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratioSpinner.setAdapter(ratioAdapter);

        ratioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0 :
                        cdRatioValue = 2.0;
                        break;
                    case 1 :
                        cdRatioValue = 1.66;
                        break;
                    case 2 :
                        cdRatioValue = 1.42;
                        break;
                    case 3 :
                        cdRatioValue = 1.25;
                        break;
                    case 4 :
                        cdRatioValue = 1.11;
                        break;
                    case 5 :
                        cdRatioValue = 1.0;
                        break;
                    default:
                        cdRatioValue = 25;
                        break;
                }
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    myWebView.evaluateJavascript("javascript: " +
                                    "setCdRatio(\"" + cdRatioValue + "\")",
                            null);
                } else {
                    myWebView.loadUrl("javascript:setCdRatio('"+cdRatioValue+"');");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
