package com.example.naverwebtoonrenewal;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

public class ItemHorizonSideTextRAdapter extends RecyclerView.Adapter<ItemHorizonSideTextRAdapter.ViewHolderPage> {

    private boolean background;
    private ArrayList<ComicsDTO> list;
    public ItemHorizonSideTextRAdapter(boolean background, ArrayList<ComicsDTO> list) {
        this.background = background;
        this.list = list;
    }

    @NonNull
    @Override
    public ItemHorizonSideTextRAdapter.ViewHolderPage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.itemview_sidetext_r,parent,false);
        return new ViewHolderPage(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHorizonSideTextRAdapter.ViewHolderPage holder, int position) {
        holder.onBind(background, list.get(position % list.size()));
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public static class ViewHolderPage extends RecyclerView.ViewHolder{

        ConstraintLayout constraintLayout;
        ImageView thumbnail;
        TextView title;
        TextView artist;

        TextView summary;
        ComicsDTO data;
        public ViewHolderPage(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.cl_item_sidetext_r_background);
            thumbnail = itemView.findViewById(R.id.image_item_sidetext_r_thumbnail);
            title = itemView.findViewById(R.id.text_item_sidetext_r_title);
            artist = itemView.findViewById(R.id.text_item_sidetext_r_artist);
            summary = itemView.findViewById(R.id.text_item_sidetext_r_summary);
        }

        public void onBind(boolean background, ComicsDTO data){
            this.data = data;

            if(background){
                constraintLayout.setBackgroundColor(new RandomColor().generateRandomColor());
            }

            Glide.with(itemView.getContext())
                    .load(data.getThumbnail())
                    .into(thumbnail);

            title.setText(data.getTitle());
            artist.setText(data.getArtist());
            summary.setText(data.getSummary());

        }

    }
}
