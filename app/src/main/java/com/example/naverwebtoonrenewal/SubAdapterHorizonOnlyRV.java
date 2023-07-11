package com.example.naverwebtoonrenewal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubAdapterHorizonOnlyRV extends RecyclerView.Adapter<SubAdapterHorizonOnlyRV.ViewHolderPage> {

    private ArrayList<ComicsDTO> list;

    public SubAdapterHorizonOnlyRV(ArrayList<ComicsDTO> list) {
        this.list = list;

    }


    @NonNull
    @Override
    public SubAdapterHorizonOnlyRV.ViewHolderPage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.subfragment_only_rv, parent, false);
        return new ViewHolderPage(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubAdapterHorizonOnlyRV.ViewHolderPage holder, int position) {
        holder.onBind(list);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolderPage extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;

        public ViewHolderPage(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycler_only_rv_rv);
        }

        public void onBind(ArrayList<ComicsDTO> list){

            GridLayoutManager layoutManager = new GridLayoutManager(recyclerView.getContext(), 3);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(false);
            recyclerView.setAdapter(new ItemGridBasicAdapter(list));

        }
    }
}
