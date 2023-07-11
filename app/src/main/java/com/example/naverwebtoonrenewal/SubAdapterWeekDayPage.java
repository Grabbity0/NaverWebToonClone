package com.example.naverwebtoonrenewal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SubAdapterWeekDayPage extends RecyclerView.Adapter<SubAdapterWeekDayPage.ViewHolderPage> {

    private ArrayList<ComicsDTO> list;

    public SubAdapterWeekDayPage(ArrayList<ComicsDTO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SubAdapterWeekDayPage.ViewHolderPage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.subfragment_vertical_with_tab_weekday, parent, false);
        return new SubAdapterWeekDayPage.ViewHolderPage(view, context, list);
    }

    @Override
    public void onBindViewHolder(@NonNull SubAdapterWeekDayPage.ViewHolderPage holder, int position) {
        holder.onBind();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolderPage extends RecyclerView.ViewHolder {
        private final Context context;
        private final TabLayout tabs;
        private RecyclerView recyclerViewTop;
        private RecyclerView recyclerViewMain;
        private ArrayList<ComicsDTO> list;

        public ViewHolderPage(@NonNull View itemView, Context context, ArrayList<ComicsDTO> list) {
            super(itemView);

            this.context = context;
            this.tabs = itemView.findViewById(R.id.tab_with_tab_weekday);
            this.list = list;
            this.recyclerViewTop = itemView.findViewById(R.id.recycler_with_tab_fixed_rv);
            this.recyclerViewMain = itemView.findViewById(R.id.recycler_with_tab_main_rv);
        }

        public void onBind() {


            String[] tabNameArray = context.getResources().getStringArray(R.array.weekday_tabs_array);
            for (String tabName : tabNameArray) {
                tabs.addTab(tabs.newTab().setText(tabName));

            }
            tabs.getTabAt(0).select();
            tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    ArrayList<ComicsDTO> sortedList = new ArrayList<>();

                    switch (tab.getPosition()) {
                        case 0:
//                            sortedList = webToonSort("월");
                            sortedList = list;
                            break;
                        case 1:
//                            sortedList = webToonSort("화");
                            sortedList = list;
                            break;
                        case 2:
//                            sortedList = webToonSort("수");
                            sortedList = list;
                            break;
                        case 3:
//                            sortedList = webToonSort("목");
                            sortedList = list;
                            break;
                        case 4:
//                            sortedList = webToonSort("금");
                            sortedList = list;
                            break;
                        case 5:
//                            sortedList = webToonSort("토");
                            sortedList = list;
                            break;
                        case 6:
//                            sortedList = webToonSort("일");
                            sortedList = list;
                            break;
                        case 7:
                            //매일+
                            sortedList = list;
                            break;
                        default:
                            //오류
                    }
                    LinearLayoutManager layoutManager;
                    layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                    recyclerViewTop.setLayoutManager(layoutManager);
                    recyclerViewTop.setHasFixedSize(false);
                    recyclerViewTop.setAdapter(new SubAdapterHorizonWithPageCount("이달의 신작", "R", false, list));

                    ArrayList<ComicsDTO> separationTop, separationBottom;

                    separationTop = new ArrayList<>(sortedList.subList(0, 3)); // list -> sortedList
                    separationBottom = new ArrayList<>(sortedList.subList(3, sortedList.size())); // list -> sortedList

                    SubAdapterHorizonOnlyRV weekdayGridTopOnlyRV, weekdayGridBottomOnlyRV;
                    SubAdapterAdvertisement advertisement = new SubAdapterAdvertisement();

                    weekdayGridTopOnlyRV = new SubAdapterHorizonOnlyRV(separationTop);
                    weekdayGridBottomOnlyRV = new SubAdapterHorizonOnlyRV(separationBottom);


                    ConcatAdapter concatAdapter = new ConcatAdapter(
                            weekdayGridTopOnlyRV
                            , advertisement
                            , weekdayGridBottomOnlyRV
                    );

                    layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                    recyclerViewMain.setLayoutManager(layoutManager);
                    recyclerViewMain.setHasFixedSize(false);
                    recyclerViewMain.setAdapter(concatAdapter);

                }


                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }

        private ArrayList<ComicsDTO> webToonSort(String weekday) {

            ArrayList<ComicsDTO> sortedList = new ArrayList<>();

            for (ComicsDTO getSortedList : list) {
                for (String getWeekList : getSortedList.getWeekday()) {
                    if (getWeekList.equals(weekday)) {
                        sortedList.add(getSortedList);
                    }
                }
            }

            return sortedList;
        }
    }


}
