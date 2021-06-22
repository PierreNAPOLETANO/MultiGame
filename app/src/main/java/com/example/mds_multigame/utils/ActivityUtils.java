package com.example.mds_multigame.utils;

import android.content.Intent;
import com.example.mds_multigame.R;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityUtils {

    public static void launchActivityWithSlide(AppCompatActivity activity, Class classname) {
        Intent intent = new Intent(activity, classname);
        launchActivityWithSlide(activity, intent, true, true);
    }
    public static void launchActivityWithSlide(AppCompatActivity activity, Class classname, boolean finish, boolean slide) {
        Intent intent = new Intent(activity, classname);
        launchActivityWithSlide(activity, intent, finish, slide);
    }
    public static void launchActivityWithSlide(AppCompatActivity activity, Intent intent, boolean finish, boolean slide) {
        activity.overridePendingTransition(R.anim.slide_from_right, R.anim.fade_out);
        activity.startActivity(intent);
        if (finish)
            activity.finish();
    }
}
