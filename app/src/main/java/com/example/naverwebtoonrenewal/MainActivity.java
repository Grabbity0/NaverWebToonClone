package com.example.naverwebtoonrenewal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.main_viewpager);
        viewPager.setUserInputEnabled(false);
        viewPager.setAdapter(new MainPagerAdapter(this));
        viewPager.setPageTransformer(null);

        tabs = findViewById(R.id.main_tabs);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab != null) {
                    int position = tab.getPosition();
                    viewPager.setCurrentItem(position, false);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        String[] mainTabs = getResources().getStringArray(R.array.main_tabs_array);

        new TabLayoutMediator(tabs, viewPager, (tab, position) -> {
            tab.setText(mainTabs[position]);
        }).attach();

        tabs.getTabAt(0).select();
    }

}