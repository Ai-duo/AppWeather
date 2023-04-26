package com.kd.appweather.fragments;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.kd.appweather.DataModel;
import com.kd.appweather.R;
import com.kd.appweather.databinding.ActivityFifthBinding;
import com.kd.appweather.databinding.ActivitySixBinding;

public class FragmentSix extends Fragment {
    ActivitySixBinding binding;
    AnimationDrawable drawables;
    boolean show = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        show = true;
        if (drawables == null) {
            drawables = new AnimationDrawable();
        }
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.activity_six, container, false);
            binding.img.setBackground(drawables);
        }
        if (update) {
            if (drawables != null) {
                for (int i = 0; i < 6; i++) {
                    Drawable d = BitmapDrawable.createFromPath(DataModel.getInstance().ytPath + (5 - i) + ".jpg");
                    if (d != null)
                        drawables.addFrame(d, 1666);
                }
                drawables.setOneShot(false);
            }
        }
        drawables.start();
        update = false;
        return binding.getRoot();
    }

    @Override
    public void onStop() {

        super.onStop();
        drawables.stop();
        show = false;
    }

    boolean update = false;

    public void update() {
        update = true;
        if (show) {
            if (drawables != null) {
                for (int i = 0; i < 6; i++) {
                    Drawable d = BitmapDrawable.createFromPath(DataModel.getInstance().ytPath + (5 - i) + ".jpg");
                    if (d != null)
                        drawables.addFrame(d, 1666);
                }
                drawables.setOneShot(false);
            }
            drawables.start();
            update = false;
        }
    }
}
