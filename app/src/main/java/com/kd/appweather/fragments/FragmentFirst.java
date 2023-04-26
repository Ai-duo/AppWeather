package com.kd.appweather.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.kd.appweather.R;
import com.kd.appweather.beans.Elements;
import com.kd.appweather.databinding.ActivityFirstBinding;
import com.kd.appweather.databinding.ActivitySecondBinding;

public class FragmentFirst extends Fragment {
    public ActivityFirstBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(binding==null){
            binding = DataBindingUtil.inflate(inflater, R.layout.activity_first,container,false);
        }
        if(e!=null){
            binding.setElements(e);
        }
        return binding.getRoot();
    }
    Elements e;
    public void update(Elements e){
        this.e = e;
        if(binding!=null){
            binding.setElements(e);
        }
    }
}
