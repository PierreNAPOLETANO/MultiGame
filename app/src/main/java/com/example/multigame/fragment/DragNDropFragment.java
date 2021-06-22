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

public class DragNDropFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentFastTapBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fast_tap, container, false);
        binding.test.setText(getString(R.string.drag_n_drop));
        binding.fastTapStart.setOnClickListener(v -> {
            ActivityUtils.addFragmentToFragment(this, new DragNDropGameFragment(), R.id.fast_tap_main_container);
        });
        return binding.getRoot();
    }
}