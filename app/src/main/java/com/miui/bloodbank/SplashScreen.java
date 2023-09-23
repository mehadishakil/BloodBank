package com.miui.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.miui.bloodbank.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {

    //viewBinding
    ActivitySplashScreenBinding ssBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // removing topbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar();

        //viewBinding
        ssBinding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(ssBinding.getRoot());


        // ssBinding.tvAppNameID.animate().translationY(0).setDuration(0).setStartDelay(0);
        // ssBinding.lottieID.animate().translationX(0).setDuration(0).setStartDelay(0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        },4000);



    }
}