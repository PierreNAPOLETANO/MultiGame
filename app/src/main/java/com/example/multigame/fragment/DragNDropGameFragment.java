package com.example.multigame.fragment;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.multigame.R;
import com.example.multigame.activity.FinishActivity;
import com.example.multigame.dao.AppDatabase;
import com.example.multigame.databinding.FragmentFastTapGameBinding;
import com.example.multigame.databinding.FragmentGameDragNDropBinding;
import com.example.multigame.manager.PlayerManager;
import com.example.multigame.utils.ActivityUtils;

public class DragNDropGameFragment extends Fragment {

    private static final int START_TIME = 20000;
    private int currentTime = START_TIME;
    private int score = PlayerManager.getInstance().getPlayer().getDragNDropScore();

    private View dragViewSelected;

    private int currentScore = -1;
    private CountDownTimer countDownTimer;
    private FragmentGameDragNDropBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_drag_n_drop, container, false);
        countDownTimer = new CountDownTimer(START_TIME, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                currentTime = currentTime - 1000;
                binding.dragTimeLeft.setText(getString(R.string.time_left, currentTime / 1000));
            }

            @Override
            public void onFinish() {
                if(currentScore > score) {
                    PlayerManager.getInstance().getPlayer().setDragNDropScore(currentScore);
                    AppDatabase.getDatabase(getActivity()).appDao().insert(PlayerManager.getInstance().getPlayer());
                }
                
                Intent intent = new Intent(getActivity(), FinishActivity.class);
                intent.putExtra(FinishActivity.SCORE_KEY, currentScore);
                intent.putExtra(FinishActivity.NAME_KEY, getString(R.string.drag_n_drop));
                ActivityUtils.launchActivity(((AppCompatActivity) getActivity()), intent, true, true);
            }
        };
        countDownTimer.start();
        setAction();

        //DETECTER LES DRAG
        binding.dragCircle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    view.startDrag(ClipData.newPlainText("", ""), new View.DragShadowBuilder(view), view, 0);
                    view.setVisibility(View.INVISIBLE);
                    return true;
                }
                return false;
            }
        });

        binding.dragNDropContainer.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DRAG_ENDED) {
                    binding.dragCircle.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });

        // DETECTER LA ZONE OU LE CERCLE EST LACHEE
        View.OnDragListener dragListener = new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (v == dragViewSelected && event.getAction() == DragEvent.ACTION_DROP) {
                    setAction();
                }
                return true;
            }
        };

        binding.dragRed.setOnDragListener(dragListener);
        binding.dragGreen.setOnDragListener(dragListener);
        binding.dragYellow.setOnDragListener(dragListener);
        binding.dragPink.setOnDragListener(dragListener);
        binding.dragBlack.setOnDragListener(dragListener);
        binding.dragBlue.setOnDragListener(dragListener);
        return binding.getRoot();
    }

    private void setAction() {
        currentScore++;
        binding.dragScore.setText(getString(R.string.score, currentScore));
        if (Math.random() < 0.16) {
            dragViewSelected = binding.dragRed;
            binding.dragCircle.getBackground().setTint(ContextCompat.getColor(getActivity(), R.color.red));
        } else if (Math.random() < 0.32) {
            dragViewSelected = binding.dragGreen;
            binding.dragCircle.getBackground().setTint(ContextCompat.getColor(getActivity(), R.color.green));
        } else if (Math.random() < 0.48) {
            dragViewSelected = binding.dragYellow;
            binding.dragCircle.getBackground().setTint(ContextCompat.getColor(getActivity(), R.color.yellow));
        } else if (Math.random() < 0.64) {
            dragViewSelected = binding.dragPink;
            binding.dragCircle.getBackground().setTint(ContextCompat.getColor(getActivity(), R.color.pink));
        } else if (Math.random() < 0.82) {
            dragViewSelected = binding.dragBlack;
            binding.dragCircle.getBackground().setTint(ContextCompat.getColor(getActivity(), R.color.black));
        } else {
            dragViewSelected = binding.dragBlue;
            binding.dragCircle.getBackground().setTint(ContextCompat.getColor(getActivity(), R.color.blue));
        }
    }

    @Override
    public void onDestroyView() {
        countDownTimer.cancel();
        super.onDestroyView();
    }
}
