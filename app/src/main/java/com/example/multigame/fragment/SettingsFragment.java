package com.example.multigame.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.multigame.R;
import com.example.multigame.activity.CreatePlayerActivity;
import com.example.multigame.activity.DisplayPlayerActivity;
import com.example.multigame.databinding.FragmentIpacBinding;
import com.example.multigame.databinding.FragmentSettingsBinding;
import com.example.multigame.manager.PlayerManager;
import com.example.multigame.model.Player;
import com.example.multigame.utils.ActivityUtils;



public class SettingsFragment extends Fragment {

    private int dragNDropScore = PlayerManager.getInstance().getPlayer().getDragNDropScore();
    private int swipeScore     = PlayerManager.getInstance().getPlayer().getSwipeScore();
    private int fastTapScore   = PlayerManager.getInstance().getPlayer().getFastTapScore();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentSettingsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);

        Player player = PlayerManager.getInstance().getPlayer();

        binding.gameDragNDropScore.setText(String.valueOf(player.getDragNDropScore()));
        binding.gameFastTapScore.setText(String.valueOf(player.getFastTapScore()));
        binding.gameSwipeScore.setText(String.valueOf(player.getSwipeScore()));
        binding.gameIpacGameScore.setText(String.valueOf(player.getIpacScore()));

        if (dragNDropScore < 10) {
            binding.gameDragNDropScore.setTextColor(Color.RED);
            binding.gameDragNDrop.setTextColor(Color.RED);
        } else if (dragNDropScore >= 10 && dragNDropScore < 30) {
            binding.gameDragNDropScore.setTextColor(Color.rgb(255,139,23)); // https://rgbcolorcode.com/color/orange
            binding.gameDragNDrop.setTextColor(Color.rgb(255,139,23)); // https://rgbcolorcode.com/color/orange
        } else {
            binding.gameDragNDropScore.setTextColor(Color.GREEN);
            binding.gameDragNDrop.setTextColor(Color.GREEN);
        }

        if (swipeScore < 10) {
            binding.gameSwipeScore.setTextColor(Color.RED);
            binding.gameSwipe.setTextColor(Color.RED);
        } else if (swipeScore >= 10 && swipeScore < 30) {
            binding.gameSwipeScore.setTextColor(Color.rgb(255,139,23)); // https://rgbcolorcode.com/color/orange
            binding.gameSwipe.setTextColor(Color.rgb(255,139,23)); // https://rgbcolorcode.com/color/orange
        } else {
            binding.gameSwipeScore.setTextColor(Color.GREEN);
            binding.gameSwipe.setTextColor(Color.GREEN);
        }

        if (fastTapScore < 10) {
            binding.gameFastTapScore.setTextColor(Color.RED);
            binding.gameFastTap.setTextColor(Color.RED);
        } else if (fastTapScore >= 10 && fastTapScore < 30) {
            binding.gameFastTapScore.setTextColor(Color.rgb(255,139,23)); // https://rgbcolorcode.com/color/orange
            binding.gameFastTap.setTextColor(Color.rgb(255,139,23)); // https://rgbcolorcode.com/color/orange
        } else {
            binding.gameFastTapScore.setTextColor(Color.GREEN);
            binding.gameFastTap.setTextColor(Color.GREEN);
        }



        binding.settingsDisconnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.launchActivity(((AppCompatActivity) getActivity()), CreatePlayerActivity.class);
            }
        });

        binding.settingsChangePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.launchActivity(((AppCompatActivity) getActivity()), DisplayPlayerActivity.class);
            }
        });

        return binding.getRoot();
    }
}
