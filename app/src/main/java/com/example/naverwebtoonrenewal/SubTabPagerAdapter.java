package com.example.naverwebtoonrenewal;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class SubTabPagerAdapter extends FragmentStateAdapter {

    private ArrayList<ComicsDTO> data;

    private static final int PAGE_COUNT_PER_TAB = 2;
    private static final int TOTAL_PAGE_COUNT = 4;

    public SubTabPagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<ComicsDTO> data) {
        super(fragmentActivity);
        this.data = data;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int tabIndex = position / PAGE_COUNT_PER_TAB;
        int subPageIndex = position % PAGE_COUNT_PER_TAB;

        if (tabIndex == 0) {

            if (subPageIndex == 0) {
                return SubTabFragment.newInstance(data);
            } else {
                return SubTabFragment.newInstance(data);
            }
        } else {
            // 두 번째 탭에 대한 Fragment 반환
            if (subPageIndex == 0) {
                return SubTabFragment.newInstance(data);
            } else {
                return SubTabFragment.newInstance(data);
            }
        }
    }

    @Override
    public int getItemCount() {
        return TOTAL_PAGE_COUNT;
    }
}