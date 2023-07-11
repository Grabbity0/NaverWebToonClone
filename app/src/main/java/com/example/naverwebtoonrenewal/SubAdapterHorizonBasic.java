package com.example.naverwebtoonrenewal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SubAdapterHorizonBasic extends RecyclerView.Adapter<SubAdapterHorizonBasic.ViewHolderPage> {
    public String subTitle;
    public ArrayList<ComicsDTO> list;

    public SubAdapterHorizonBasic(String subTitle, ArrayList<ComicsDTO> list) {
        this.subTitle = subTitle;
        this.list = list;
    }


    @NonNull
    @Override
    public SubAdapterHorizonBasic.ViewHolderPage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.subfragment_horizon_basic, parent, false);
        return new ViewHolderPage(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubAdapterHorizonBasic.ViewHolderPage holder, int position) {

        holder.onBind(list, list.get(position), subTitle);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolderPage extends RecyclerView.ViewHolder{

        TextView basicTitle;
        RecyclerView basicRecycler;

        ComicsDTO data;
        public ViewHolderPage(@NonNull View itemView) {
            super(itemView);
            this.basicTitle = itemView.findViewById(R.id.text_horizon_basic_title);
            this.basicRecycler = itemView.findViewById(R.id.recycler_horizon_basic_rv);
        }

        public void onBind(ArrayList<ComicsDTO> list, ComicsDTO data, String subTitle){
            this.data = data;

            basicTitle.setText(subTitle);

            LinearLayoutManager layoutManager =new LinearLayoutManager(basicRecycler.getContext(), LinearLayoutManager.HORIZONTAL, false);
            basicRecycler.setLayoutManager(layoutManager);
            basicRecycler.setHasFixedSize(false);
            basicRecycler.setAdapter(new ItemHorizonBasicAdapter(list));
        }
    }
}
