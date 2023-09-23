package com.miui.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    TextView tv;
    Button btn;
    ConstraintLayout registerDonar, requestBlood, donarList, donateBlood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        registerDonar = findViewById(R.id.registerDonarCardView);
        requestBlood = findViewById(R.id.requestBloodCardView);
        donarList = findViewById(R.id.donarListCardView);
        donateBlood = findViewById(R.id.donateBloodCardView);


        registerDonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterDonar.class);
                startActivity(intent);
            }
        });

        requestBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RequestBlood.class);
                startActivity(intent);
            }
        });

        donarList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AllDonar.class);
                startActivity(intent);
            }
        });

        donateBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AllPatient.class);
                startActivity(intent);
            }
        });



    }
}