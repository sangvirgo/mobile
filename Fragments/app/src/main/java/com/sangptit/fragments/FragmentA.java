package com.sangptit.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sangptit.fragments.databinding.FragmentABinding;

public class FragmentA extends Fragment {

    private FragmentABinding binding;
    private int count = 0;
    private FragmentB.Counter counter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentABinding.inflate(inflater, container, false);
        binding.button.setOnClickListener(view -> {
            count++;
            Activity activity = getActivity();
            if (activity instanceof FragmentB.Counter) {
                counter = (FragmentB.Counter) activity;
                counter.incrementValue(count);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
