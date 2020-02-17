package com.example.ddangnmarket.src.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.main.Item.Items;
import com.example.ddangnmarket.src.main.Item.ItemsAdapter;
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
                    i * 100000000, ContextCompat.getDrawable(activity, R.drawable.default_error)));
        }

        mLvItemsList.setAdapter(itemsAdapter);
        return view;
    }
}
