package com.app.jpr.market.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.jpr.market.R;

public class
LocationActivity extends AppCompatActivity {
  private Button location_BTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        location_BTN=(Button)findViewById(R.id.location_BTN);

        location_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LocationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
