package com.example.mds_multigame.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mds_multigame.R;
import com.example.mds_multigame.databinding.ActivityDisplayPlayersBinding;

public class DisplayPlayerActivity extends AppCompatActivity {
    ActivityDisplayPlayersBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_players);
    }
}