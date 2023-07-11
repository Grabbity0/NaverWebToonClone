package com.example.naverwebtoonrenewal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SubAdapterHorizonSubTab extends RecyclerView.Adapter<SubAdapterHorizonSubTab.ViewHolderPage> {

    private String subTitle;
    private String[] tabName;
    private ArrayList<ComicsDTO> list;

    public SubAdapterHorizonSubTab(String subTitle, String[] tabName, ArrayList<ComicsDTO> list) {
        this.subTitle = subTitle;
        this.tabName = tabName;
        this.list = list;

        for(ComicsDTO l : list) {
            Log.d("array", l.getThumbnail());
        }
    }


    @NonNull
    @Override
    public SubAdapterHorizonSubTab.ViewHolderPage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.subfragment_horizon_sub_tab, parent, false);
        return new ViewHolderPage(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubAdapterHorizonSubTab.ViewHolderPage holder, int position) {
        holder.onBind(list, subTitle, tabName);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolderPage extends RecyclerView.ViewHolder{

        TextView subTitle;
        TabLayout tabs;

        ViewPager2 viewPager;
        ArrayList<ComicsDTO> data;

        Context context;

        public ViewHolderPage(@NonNull View itemView) {
            super(itemView);
            subTitle = itemView.findViewById(R.id.text_horizon_sub_tab_title);
            tabs = itemView.findViewById(R.id.tab_horizon_sub_tab_tab);
            viewPager = itemView.findViewById(R.id.vpager_horizon_sub_tab_viewpager);
            context = itemView.getContext();

        }

        public void onBind(ArrayList<ComicsDTO> data, String subTitle, String[] tabName){
            this.data = data;
            this.subTitle.setText(subTitle);

            FragmentActivity fragmentActivity = (FragmentActivity) context;
            SubTabPagerAdapter adapter = new SubTabPagerAdapter(fragmentActivity, data);

            viewPager.setOffscreenPageLimit(2);
            viewPager.setAdapter(adapter);

            for(int i = 0; i < tabName.length; i++){
                tabs.addTab(tabs.newTab().setText(tabName[i]));
            }



            tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if (tab != null) {
                        int position = tab.getPosition();
                        viewPager.setCurrentItem(position * 2, false);
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });


            viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    int tabIndex = position / 2;
                    tabs.selectTab(tabs.getTabAt(tabIndex));
                }
            });


        }
    }
}
