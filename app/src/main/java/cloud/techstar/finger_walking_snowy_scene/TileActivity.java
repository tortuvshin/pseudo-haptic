package cloud.techstar.finger_walking_snowy_scene;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.qozix.tileview.TileView;
import com.qozix.widget.ScalingScrollView;

public class TileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile);

        ScalingScrollView scalingScrollView = findViewById(R.id.scalingscrollview);
        scalingScrollView.setScaleLimits(0, 1);
        scalingScrollView.setShouldLoopScale(true);
        scalingScrollView.setShouldVisuallyScaleContents(true);

    }
}
