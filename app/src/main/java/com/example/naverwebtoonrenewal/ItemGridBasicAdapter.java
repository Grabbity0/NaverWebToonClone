package com.example.naverwebtoonrenewal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ItemGridBasicAdapter extends RecyclerView.Adapter<ItemGridBasicAdapter.ViewHolderPage> {

    private ArrayList<ComicsDTO> list;
    public ItemGridBasicAdapter(ArrayList<ComicsDTO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ItemGridBasicAdapter.ViewHolderPage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.itemview_simple,parent,false);
        return new ViewHolderPage(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemGridBasicAdapter.ViewHolderPage holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolderPage extends RecyclerView.ViewHolder{
        ImageView thumbnail;
        TextView title;
        TextView artist;

        ComicsDTO data;
        public ViewHolderPage(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.image_item_simple_thumbnail);
            title = itemView.findViewById(R.id.text_item_simple_title);
            artist = itemView.findViewById(R.id.text_item_simple_aritst);
        }

        public void onBind(ComicsDTO data){
            this.data = data;

            Glide.with(itemView.getContext())
                    .load(data.getThumbnail())
                    .into(thumbnail);

            title.setText(data.getTitle());
            artist.setText(data.getArtist());

        }
    }
}
