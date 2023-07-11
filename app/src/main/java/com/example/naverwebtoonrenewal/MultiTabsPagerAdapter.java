package com.example.naverwebtoonrenewal;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class MultiTabsPagerAdapter extends FragmentStateAdapter {
    private ArrayList<ComicsDTO> data;
    private final int NUM_PAGES = 6;

    public MultiTabsPagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<ComicsDTO> data) {
        super(fragmentActivity);
        this.data = data;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return SubTabFragment.newInstance(data);
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
