package com.example.naverwebtoonrenewal;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class SubAdapterHorizonWithPageCount extends RecyclerView.Adapter<SubAdapterHorizonWithPageCount.ViewHolderPage> {

    private ArrayList<ComicsDTO> list;
    private String subTitle;

    private Boolean background;
    private String type;

    public SubAdapterHorizonWithPageCount(String subTitle, String type, Boolean background, ArrayList<ComicsDTO> list) {
        this.subTitle = subTitle;
        this.type = type;
        this.background = background;
        this.list = list;

        for(ComicsDTO l : list) {
            Log.d("array", l.getThumbnail());
        }
    }


    @NonNull
    @Override
    public SubAdapterHorizonWithPageCount.ViewHolderPage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.subfragment_horizon_with_page_count, parent, false);
        return new ViewHolderPage(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubAdapterHorizonWithPageCount.ViewHolderPage holder, int position) {
        holder.onBind(subTitle, type, background, list);
    }

    @Override
    public int getItemCount() {
        return 1;
    }



    public static class ViewHolderPage extends RecyclerView.ViewHolder{

        TextView subTitle;
        TextView totalPage;
        TextView currentPage;

        RecyclerView recyclerView;



        public ViewHolderPage(@NonNull View itemView) {
            super(itemView);
            subTitle = itemView.findViewById(R.id.text_horizon_with_page_title);
            totalPage = itemView.findViewById(R.id.text_horizon_with_page_total);
            currentPage = itemView.findViewById(R.id.text_horizon_with_page_current);
            recyclerView = itemView.findViewById(R.id.recycler_horizon_with_page_rv);
        }

        public void onBind(String subTitle, String type, boolean background, ArrayList<ComicsDTO> list){
            this.subTitle.setText(subTitle);

            totalPage.setText(String.valueOf( "/ " + list.size()));

            LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(false);

            if(type.equals("L")){
                recyclerView.setAdapter(new ItemHorizonSideTextLAdapter(background,list));
            }
            else if(type.equals("R")) {
                recyclerView.setAdapter(new ItemHorizonSideTextRAdapter(background,list));
            }


            int middlePosition = (Integer.MAX_VALUE / 2);

            while(middlePosition % list.size() != 0){
                middlePosition++;
            }
            Log.d("dd",String.valueOf(list.size()));
            Log.d("dd", String.valueOf(middlePosition));

            recyclerView.scrollToPosition(middlePosition);
            recyclerView.post(() -> {
               recyclerView.scrollBy(recyclerView.getWidth()/ 20, 0);
            });
            Log.d("rec", ""+recyclerView.getWidth());

            recyclerView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            return false;
                        case MotionEvent.ACTION_UP:
                            recyclerView.stopScroll();
                            return true;
                        default:
                            return false;
                    }
                }

            });

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                int currentPosition = layoutManager.findFirstVisibleItemPosition();
                private int mdx;

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    mdx = dx;
                    currentPage.setText(String.valueOf((layoutManager.findFirstVisibleItemPosition() % list.size())+1));
                }

                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    int setPosition = layoutManager.findFirstVisibleItemPosition();

                    if(newState == RecyclerView.SCROLL_STATE_IDLE) {
                            int offset = recyclerView.getWidth() / 20;
                            layoutManager.scrollToPositionWithOffset(setPosition, offset);

                    }


                }
            });

        }

    }
}
