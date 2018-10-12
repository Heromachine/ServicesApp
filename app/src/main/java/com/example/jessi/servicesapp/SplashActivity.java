package com.example.jessi.servicesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    ImageView logo;
    TextView title;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        intent = new Intent(this, LoginActivity.class);

        splashScreen(3000);
    }

    public void splashScreen(final int x){
       Thread thread = new Thread(){
           //TODO Give run method

           @Override
           public void run() {
               super.run();
               try {
                   sleep(x);
                   startActivity(intent);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       };
       thread.start();

    }
}
