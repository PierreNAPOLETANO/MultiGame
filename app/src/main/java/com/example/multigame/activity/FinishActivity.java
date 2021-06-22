package com.example.multigame.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.multigame.R;
import com.example.multigame.databinding.ActivityFinishBinding;
import com.example.multigame.utils.ActivityUtils;

public class FinishActivity extends AppCompatActivity {

    public final static String SCORE_KEY ="score";
    public final static String NAME_KEY ="name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFinishBinding binding =  DataBindingUtil.setContentView(this, R.layout.activity_finish);

        int currentScore = getIntent().getIntExtra(SCORE_KEY, 0);
        String currentName = getIntent().getStringExtra(NAME_KEY);

        binding.finishName.setText(currentName);
        binding.finishScore.setText(getString(R.string.final_score, currentScore));


        binding.finishMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.launchActivity(FinishActivity.this, MainActivity.class);
            }
        });

        binding.finishExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}