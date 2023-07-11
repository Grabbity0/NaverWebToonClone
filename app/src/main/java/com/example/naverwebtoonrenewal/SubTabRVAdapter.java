package com.example.naverwebtoonrenewal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SubTabRVAdapter extends RecyclerView.Adapter<SubTabRVAdapter.ViewHolderPage> {

    ArrayList<ComicsDTO> data;


    public SubTabRVAdapter(ArrayList<ComicsDTO> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public SubTabRVAdapter.ViewHolderPage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.itemview_with_ranking, parent, false);
        return new ViewHolderPage(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubTabRVAdapter.ViewHolderPage holder, int position) {
        holder.onBind(data.get(position), position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolderPage extends RecyclerView.ViewHolder {

        ImageView thumbNail;
        TextView rank;
        TextView title;
        TextView newUpdate;


        public ViewHolderPage(@NonNull View itemView) {
            super(itemView);
            thumbNail = itemView.findViewById(R.id.image_item_with_ranking_thumbnail);
            rank = itemView.findViewById(R.id.text_item_with_ranking_rank);
            title = itemView.findViewById(R.id.text_item_with_ranking_title);
            newUpdate = itemView.findViewById(R.id.text_item_with_ranking_recent);
        }

        public void onBind(ComicsDTO data, int position) {

            rank.setText((position + 1) + "");

            Glide.with(itemView.getContext())
                    .load(data.getThumbnail())
                    .into(thumbNail);

            title.setText(data.getTitle());


        }
    }
}