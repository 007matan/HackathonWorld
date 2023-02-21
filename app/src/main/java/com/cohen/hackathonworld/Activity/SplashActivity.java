package com.cohen.hackathonworld.Activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.cohen.hackathonworld.R;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class SplashActivity extends AppCompatActivity {

    private AppCompatImageView splash_IMG_logo;

    private LinearProgressIndicator splash_PRG_download;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        splash_IMG_logo = findViewById(R.id.splash_IMG_logo);
        splash_PRG_download = findViewById(R.id.splash_PRG_download);


        startAnimation(splash_IMG_logo);
    }

    private void startAnimation(View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        view.setY(-height / 2);

        view.setScaleX(0.0f);
        view.setScaleY(0.0f);
        view.setAlpha(0.0f);

        view.animate()
                .scaleY(1.0f)
                .scaleX(1.0f)
                .alpha(1.0f)
                .translationY(0)
                .setDuration(4000)
                //.setInterpolator(new LinearOutSlowInInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        activeDesignPRG();
                        startApp();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }

    private void activeDesignPRG(){
        splash_PRG_download.setVisibility(View.VISIBLE);
        splash_PRG_download.setProgress(0);
        splash_PRG_download.setMax(10);
        final Handler handler = new Handler();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                /*
                handler.postDelayed(()->{
                    splash_PRG_download.setProgress(4);
                }, 1000);

                handler.postDelayed(()->{
                    splash_PRG_download.setProgress(10);
                    finish();
                }, 2000);


                 */

            }
        });
    }

    private void startApp() {
        final Handler handler = new Handler();

        handler.postDelayed(()->{
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}
