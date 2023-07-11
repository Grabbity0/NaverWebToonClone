package com.example.naverwebtoonrenewal;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragment extends Fragment {

    private final static String indexPosition = "position";
    private final static int FIRST_PAGE = 0;
    private final static int SECOND_PAGE = 1;
    private final static int THIRD_PAGE = 2;
    private final static int FOURTH_PAGE = 3;

    private ArrayList<ComicsDTO> list;

    private int position;


    public static Fragment newInstance(int index_position) {
        MainFragment fragment = new MainFragment();
        final Bundle bundle = new Bundle();
        bundle.putInt(indexPosition, index_position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_separation_page, container, false);

        Bundle bundle = getArguments();

        if(null != bundle){
            position = bundle.getInt(indexPosition);
            Log.d("call", Integer.toString(position));
        }

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI dataAPI = retrofit.create(RetrofitAPI.class);

        Call<ComicsPojo> call = dataAPI.getWebToons();

        call.enqueue(new Callback<ComicsPojo>() {

            @Override
            public void onResponse(@NonNull Call<ComicsPojo> call, @NonNull Response<ComicsPojo> response) {

                ConcatAdapter concatAdapter = null;

                if(position == FIRST_PAGE){

                    List<ComicsDTO> itemList = Arrays.asList(response.body().getItemList());
                    list = new ArrayList<>(itemList);

                    SubAdapterHorizonBasic homeFirstBasic,homeFifthBasic, homeSixthBasic;
                    SubAdapterHorizonSubTab homeThirdSubTab;
                    SubAdapterHorizonMultiTabs homeFourthMultiTabs;
                    SubAdapterHorizonWithPageCount homeSecondWithPageCount;
                    SubAdapterAdvertisement advertisement1, advertisement2, advertisement3;
                    //SubAdapterHorizonOnlyRV

                    String[] tabName = {"웹툰", "베스트도전"};
                    String[] tabsName = {"10대여자", "10대남자","20대여자", "20대남자","30대여자", "30대남자"};

                    homeFirstBasic = new SubAdapterHorizonBasic("사실은 희망을 바라요! 아포칼립스", list);
                    homeSecondWithPageCount = new SubAdapterHorizonWithPageCount("이달의 신작", "L", true,list);
                    homeThirdSubTab = new SubAdapterHorizonSubTab("실시간 랭킹 TOP10", tabName, list);
                    homeFourthMultiTabs = new SubAdapterHorizonMultiTabs("연령별º성별 실시간 랭킹", tabsName,list);
                    homeFifthBasic = new SubAdapterHorizonBasic("판타지 소년 만화, 도장깨끼 해볼까?",list);
                    homeSixthBasic = new SubAdapterHorizonBasic("엉뚱한 매력이 있는", list);
                    advertisement1 = new SubAdapterAdvertisement();
                    advertisement2 = new SubAdapterAdvertisement();
                    advertisement3 = new SubAdapterAdvertisement();

                    concatAdapter = new ConcatAdapter(
                            homeFirstBasic
                            ,homeSecondWithPageCount
                            ,advertisement1
                            ,homeThirdSubTab
                            ,homeFourthMultiTabs
                            ,advertisement2
                            ,homeFifthBasic
                            ,advertisement3
                            ,homeSixthBasic);

                }
                else if(position == SECOND_PAGE){

                    List<ComicsDTO> itemList = Arrays.asList(response.body().getItemList());
                    list = new ArrayList<>(itemList);

                    SubAdapterWeekDayPage weekdayTab;

                    weekdayTab = new SubAdapterWeekDayPage(list);

                    concatAdapter = new ConcatAdapter(
                            weekdayTab
                            );


                }
                else if(position == THIRD_PAGE){

                }
                else if(position == FOURTH_PAGE){

                }
                else{

                }

                RecyclerView recyclerView;
                LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView = view.findViewById(R.id.recycler_page_rv);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(false);
                recyclerView.setAdapter(concatAdapter);

            }

            @Override
            public void onFailure(@NonNull Call<ComicsPojo> call, @NonNull Throwable t) {

            }
        });



    }
}
