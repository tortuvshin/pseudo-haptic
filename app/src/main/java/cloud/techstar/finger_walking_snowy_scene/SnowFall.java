package cloud.techstar.finger_walking_snowy_scene;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SnowFall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snow_fall);
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

}

