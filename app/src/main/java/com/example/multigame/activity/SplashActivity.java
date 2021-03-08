package com.example.multigame.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.multigame.R;
import com.example.multigame.dao.AppDatabase;
import com.example.multigame.utils.ActivityUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getDatabase(SplashActivity.this);
                ActivityUtils.launchActivity(SplashActivity.this, CreatePlayerActivity.class);
            }
        }, 2000);
    }
}