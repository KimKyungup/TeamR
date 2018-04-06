package com.samsung.hackerton18.teamr.belive;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

/**
 * Created by nogan on 2018-04-06.
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logoBilive = (ImageView) findViewById(R.id.logo_bilive);
        logoBilive.setAdjustViewBounds(true);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(logoBilive);
        Glide.with(this).load(R.drawable.logo_bilive).into(gifImage);

        initialize();
    }

    private void initialize()
    {
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg)
            {
                finish();
            }
        };

        handler.sendEmptyMessageDelayed(0, 3000);
    }
}