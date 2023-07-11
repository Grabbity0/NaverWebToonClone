package com.example.naverwebtoonrenewal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class SubTabFragment extends Fragment {

    RecyclerView recyclerView;

    public SubTabFragment() {

    }

    public static SubTabFragment newInstance(ArrayList<ComicsDTO> data) {
        SubTabFragment fragment = new SubTabFragment();
        Bundle args = new Bundle();

        args.putParcelableArrayList("data", data);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_sub_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        assert bundle != null;
        ArrayList<ComicsDTO> list = bundle.getParcelableArrayList("data");

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.rv_subtab_recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(new SubTabRVAdapter(list));


        super.onViewCreated(view, savedInstanceState);
    }
}