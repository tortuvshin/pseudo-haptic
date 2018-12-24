package cloud.techstar.finger_walking_snowy_scene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        CardView experiment1 = findViewById(R.id.experiment1);
        CardView experiment2 = findViewById(R.id.experiment2);

        CardView experiment3 = findViewById(R.id.web_exp);

        experiment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, WebAppActivity.class));
            }
        });
        experiment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, SnowyActivity.class));
            }
        });


        experiment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, TileActivity.class));
            }
        });


    }
}