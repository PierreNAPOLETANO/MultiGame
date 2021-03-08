package com.example.multigame.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.multigame.R;
import com.example.multigame.adapter.PlayerAdapter;
import com.example.multigame.dao.AppDatabase;
import com.example.multigame.databinding.ActivityDisplayPlayersBinding;
import com.example.multigame.model.Player;

import java.util.ArrayList;
import java.util.List;

public class DisplayPlayerActivity extends AppCompatActivity {

    ActivityDisplayPlayersBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_players);

        ArrayList<Player> playerList = new ArrayList<>();
        for (int i =0; i< 50; i++){
            playerList.add(new Player("pic","name"+i,"prenom" + i,12,"loc"));
        }
        binding.displayPlayersRv.setLayoutManager(new LinearLayoutManager(this));
        binding.displayPlayersRv.setAdapter(new PlayerAdapter(AppDatabase.getDatabase(this).appDao().getAllPlayers()));
       // binding.displayPlayersRv.setAdapter(new PlayerAdapter(playerList));
    }
}