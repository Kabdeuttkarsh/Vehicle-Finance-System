package com.solutionstouch.omsaifinance.ui.dashboard;

import static java.lang.Boolean.FALSE;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.solutionstouch.omsaifinance.LoginActivity;
import com.solutionstouch.omsaifinance.MainActivity;
import com.solutionstouch.omsaifinance.R;
import com.solutionstouch.omsaifinance.util.localstorage.LocalStorage;

public class Splash extends AppCompatActivity {

    LocalStorage localStorage;
    private static int SPLASH_SCREEN_TIME_OUT=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        localStorage = new LocalStorage(getApplicationContext());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (localStorage.isUserLoggedIn() == FALSE){
                    Intent i = new Intent(Splash.this,
                    LoginActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }

}