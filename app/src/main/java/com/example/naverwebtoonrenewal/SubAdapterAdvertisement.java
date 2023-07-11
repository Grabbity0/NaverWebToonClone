package com.example.naverwebtoonrenewal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubAdapterAdvertisement extends RecyclerView.Adapter<SubAdapterAdvertisement.ViewHolderPage> {

    @NonNull
    @Override
    public SubAdapterAdvertisement.ViewHolderPage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.advertisement, parent, false);
        return new ViewHolderPage(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubAdapterAdvertisement.ViewHolderPage holder, int position) {
        holder.onBind();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolderPage extends RecyclerView.ViewHolder{
        ImageView advertisement;

        public ViewHolderPage(@NonNull View itemView) {
            super(itemView);
            advertisement = itemView.findViewById(R.id.iv_advertisement);
        }

        public void onBind(){


        }
    }
}
