package com.example.multigame.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.multigame.R;
import com.example.multigame.activity.FinishActivity;
import com.example.multigame.dao.AppDatabase;
import com.example.multigame.databinding.FragmentFastTapBinding;
import com.example.multigame.databinding.FragmentFastTapGameBinding;
import com.example.multigame.manager.PlayerManager;
import com.example.multigame.utils.ActivityUtils;

public class FastTapOrSwipeGameFragment extends Fragment {

    private static final int START_TIME = 20000;
    private int currentTime = START_TIME;

    private static final int SWIPE_UP = 0;
    private static final int SWIPE_DOWN = 1;
    private static final int SWIPE_LEFT = 2;
    private static final int SWIPE_RIGHT = 3;
    private int swipeDirections;

    private boolean isLongTap;
    private int currentScore = -1;
    private CountDownTimer countDownTimer;
    private FragmentFastTapGameBinding binding;

    private float initialX;
    private float initialY;
    private boolean isSwipeGame;

    private int swipeScore = PlayerManager.getInstance().getPlayer().getSwipeScore();
    private int fastTapScore = PlayerManager.getInstance().getPlayer().getFastTapScore();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fast_tap_game, container, false);
        isSwipeGame = getArguments().getBoolean(FastTapFragment.KEY_IS_SWIPE_FRAGMENT);

        countDownTimer = new CountDownTimer(START_TIME, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                currentTime = currentTime - 1000;
                binding.tapTimeLeft.setText(getString(R.string.time_left, currentTime / 1000));
            }

            @Override
            public void onFinish() {
                if(isSwipeGame) {
                    if(currentScore > swipeScore) {
                        PlayerManager.getInstance().getPlayer().setSwipeScore(currentScore);
                        AppDatabase.getDatabase(getActivity()).appDao().insert(PlayerManager.getInstance().getPlayer());
                    }
                } else {
                    if(currentScore > fastTapScore) {
                        PlayerManager.getInstance().getPlayer().setFastTapScore(currentScore);
                        AppDatabase.getDatabase(getActivity()).appDao().insert(PlayerManager.getInstance().getPlayer());
                    }
                }

                Intent intent = new Intent(getActivity(), FinishActivity.class);
                intent.putExtra(FinishActivity.SCORE_KEY, currentScore);
                intent.putExtra(FinishActivity.NAME_KEY, getString(isSwipeGame ? R.string.swipe : R.string.fast_tap));
                ActivityUtils.launchActivity(((AppCompatActivity) getActivity()), intent, true, true);
            }
        };
        countDownTimer.start();
        setAction();

        if (isSwipeGame) {
            binding.tapGameContainer.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int action = event.getActionMasked();

                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            initialX = event.getX();
                            initialY = event.getY();
                            break;
                        case MotionEvent.ACTION_UP:
                            float finalX = event.getX();
                            float finalY = event.getY();

                            if (initialX < (finalX - 50) && swipeDirections == SWIPE_RIGHT) {
                                setAction();
                            }
                            if (initialX > (finalX + 50) && swipeDirections == SWIPE_LEFT) {
                                setAction();
                            }
                            if (initialY < (finalY - 50) && swipeDirections == SWIPE_DOWN) {
                                setAction();
                            }
                            if (initialY > (finalY + 50) && swipeDirections == SWIPE_UP) {
                                setAction();
                            }
                            break;
                    }
                    return true;
                }
            });
        } else {
            binding.tapGameContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isLongTap) {
                        setAction();
                    }
                }
            });
            binding.tapGameContainer.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (isLongTap) {
                        setAction();
                    }
                    return true;
                }
            });
        }
        return binding.getRoot();
    }

    private void setAction() {
        currentScore++;
        binding.tapScore.setText(getString(R.string.score, currentScore));
        if (isSwipeGame) {
            if (Math.random() < 0.25) {
                swipeDirections = SWIPE_UP;
                binding.tapAction.setText(getString(R.string.swipe_up));
            } else if (Math.random() < 0.5) {
                swipeDirections = SWIPE_DOWN;
                binding.tapAction.setText(getString(R.string.swipe_down));
            } else if (Math.random() < 0.75) {
                swipeDirections = SWIPE_LEFT;
                binding.tapAction.setText(getString(R.string.swipe_left));
            } else {
                swipeDirections = SWIPE_RIGHT;
                binding.tapAction.setText(getString(R.string.swipe_right));
            }
        } else {
            if (Math.random() > 0.5) {
                isLongTap = true;
            } else {
                isLongTap = false;
            }
            binding.tapAction.setText(getString(isLongTap ? R.string.long_tap : R.string.tap));
        }
    }

    @Override
    public void onDestroyView() {
        countDownTimer.cancel();
        super.onDestroyView();
    }
}
