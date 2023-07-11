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

import java.util.ArrayList;

public class SubAdapterHorizonMultiTabs extends RecyclerView.Adapter<SubAdapterHorizonMultiTabs.ViewHolderPage> {

    private String subject;
    private String[] tabsName;
    private ArrayList<ComicsDTO> list;

    public SubAdapterHorizonMultiTabs(String subject, String[] tabsName, ArrayList<ComicsDTO> list) {
        this.subject = subject;
        this.tabsName = tabsName;
        this.list = list;

    }


    @NonNull
    @Override
    public SubAdapterHorizonMultiTabs.ViewHolderPage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.subfragment_horizon_multi_tabs, parent, false);
        return new ViewHolderPage(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubAdapterHorizonMultiTabs.ViewHolderPage holder, int position) {
        holder.onBind(subject, tabsName, list);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolderPage extends RecyclerView.ViewHolder{

        TextView title;
        TabLayout tabs;
        ViewPager2 viewPager;

        Context context;

        public ViewHolderPage(Context context, @NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_horizon_multi_tabs_title);
            tabs = itemView.findViewById(R.id.tab_horizon_multi_tabs_tab);
            viewPager = itemView.findViewById(R.id.vpager_horizon_multi_tabs_vp);
            this.context = context;
        }

        public void onBind(String subject, String[] tabsName, ArrayList<ComicsDTO> list){
            title.setText(subject);

            FragmentActivity fragmentActivity = (FragmentActivity) context;
            MultiTabsPagerAdapter adapter = new MultiTabsPagerAdapter(fragmentActivity, list);

            viewPager.setOffscreenPageLimit(2);
            viewPager.setAdapter(adapter);


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

            new TabLayoutMediator(tabs, viewPager, (tab, position) -> {
                tab.setText(tabsName[position]);
            }).attach();

            tabs.getTabAt(0).select();


        }
    }
}
