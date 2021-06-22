package com.example.multigame.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.multigame.R;
import com.example.multigame.databinding.FragmentFastTapBinding;
import com.example.multigame.utils.ActivityUtils;

public class FastTapFragment extends Fragment {

    public static final String KEY_IS_SWIPE_FRAGMENT = "isSwipeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentFastTapBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fast_tap,
                container, false);


        if (getArguments().getBoolean(KEY_IS_SWIPE_FRAGMENT)) {
            binding.test.setText(getString(R.string.swipe));
        }

        binding.fastTapStart.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putBoolean(FastTapFragment.KEY_IS_SWIPE_FRAGMENT, getArguments().getBoolean(KEY_IS_SWIPE_FRAGMENT));
                ActivityUtils.addFragmentToFragment(this, new FastTapOrSwipeGameFragment(), R.id.fast_tap_main_container, bundle);
        });
        return binding.getRoot();
    }
}
