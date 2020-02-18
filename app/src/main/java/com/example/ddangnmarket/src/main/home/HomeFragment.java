package com.example.ddangnmarket.src.main.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.main.MainActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    MainActivity activity;
    ArrayList<Items> mItemsArrayList = new ArrayList<>();
    ListView mLvItemsList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ItemsAdapter itemsAdapter = new ItemsAdapter(mItemsArrayList, activity);
        mLvItemsList = view.findViewById(R.id.home_lv_product);

        for (int i = 0; i < 9; i++) {
            mItemsArrayList.add(new Items("아이폰" + (i + 1), "서구", "청라" + i + "동",
                    i * 100000000,
                    "https://previews.123rf.com/images/utima/utima1602/utima160200076/53405200-%EB%8B%B9%EA%B7%BC-%EC%95%BC%EC%B1%84%EC%9D%98-%ED%9E%99-%ED%99%94%EC%9D%B4%ED%8A%B8%EC%97%90-%EA%B2%A9%EB%A6%AC.jpg"));
        }

        mLvItemsList.setAdapter(itemsAdapter);
        return view;
    }
}
