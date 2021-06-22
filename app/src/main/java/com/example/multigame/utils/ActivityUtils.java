package com.example.multigame.utils;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.multigame.R;

public class ActivityUtils {

    public static void launchActivity(AppCompatActivity activity, Class className) {
        Intent intent = new Intent(activity, className);
        launchActivity(activity, intent, true, true);
    }

    public static void launchActivity(AppCompatActivity activity, Class className,
                                      boolean isFinish, boolean isSlide) {
        Intent intent = new Intent(activity, className);
        launchActivity(activity, intent, isFinish, isSlide);
    }

    public static void launchActivity(AppCompatActivity activity, Intent intent,
                                      boolean isFinish, boolean isSlide) {
        activity.startActivity(intent);
        activity.overridePendingTransition(isSlide ? R.anim.slide_in_from_right : R.anim.fade_in, R.anim.fade_out);
        if (isFinish) activity.finish();
    }

    public static void addFragmentToFragment(Fragment parentFragment, Fragment fragment, int frameId) {
        addFragmentToFragment(parentFragment, fragment, frameId, null);
    }

    public static void addFragmentToFragment(Fragment parentFragment, Fragment fragment, int frameId, Bundle bundle) {
        FragmentTransaction transaction = parentFragment.getChildFragmentManager().beginTransaction();
        fragment.setArguments(bundle);
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.replace(frameId, fragment, "tag");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void addFragmentToActivity(AppCompatActivity activity, Fragment fragment, int frameId) {
        addFragmentToActivity(activity, fragment, frameId, null);
    }

    public static void addFragmentToActivity(AppCompatActivity activity, Fragment fragment, int frameId, Bundle bundle) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        fragment.setArguments(bundle);
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.replace(frameId, fragment, "tag");
        transaction.commit();
    }
}