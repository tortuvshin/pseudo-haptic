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

        TileView tileView = findViewById(R.id.tileview);
        new TileView.Builder(tileView)
                .setSize(1000, 1000)
                .defineZoomLevel("snow/snow-%1$d-%1$d.jpg")
                .build();


    }
}
