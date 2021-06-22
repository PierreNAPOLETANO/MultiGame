package com.example.multigame.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.multigame.R;
import com.example.multigame.databinding.ActivityMainBinding;
import com.example.multigame.fragment.DragNDropFragment;
import com.example.multigame.fragment.FastTapFragment;
import com.example.multigame.fragment.IpacFragment;
import com.example.multigame.fragment.SettingsFragment;
import com.example.multigame.utils.ActivityUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        ActivityUtils.addFragmentToActivity(MainActivity.this, new DragNDropFragment(),
                R.id.main_fragment_container);


        binding.mainBottomNav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (item.getItemId() == R.id.drag) {
                            ActivityUtils.addFragmentToActivity(MainActivity.this, new DragNDropFragment(),
                                    R.id.main_fragment_container);
                        } else if (item.getItemId() == R.id.fast) {
                            Bundle bundle = new Bundle();
                            bundle.putBoolean(FastTapFragment.KEY_IS_SWIPE_FRAGMENT, false);
                            ActivityUtils.addFragmentToActivity(MainActivity.this, new FastTapFragment(),
                                    R.id.main_fragment_container,bundle);
                        }  else if (item.getItemId() == R.id.swipe) {
                            Bundle bundle = new Bundle();
                            bundle.putBoolean(FastTapFragment.KEY_IS_SWIPE_FRAGMENT, true);
                            ActivityUtils.addFragmentToActivity(MainActivity.this,new FastTapFragment()
                                    , R.id.main_fragment_container, bundle);
                        }  else if (item.getItemId() == R.id.ipac) {
                            ActivityUtils.addFragmentToActivity(MainActivity.this, new IpacFragment(),
                                    R.id.main_fragment_container);
                        }  else if (item.getItemId() == R.id.settings) {
                            ActivityUtils.addFragmentToActivity(MainActivity.this, new SettingsFragment(),
                                    R.id.main_fragment_container);
                        }
                        return true;
                    }
                }
        );
    }
}