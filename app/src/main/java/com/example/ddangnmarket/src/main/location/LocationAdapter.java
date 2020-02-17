package com.example.ddangnmarket.src.main.location;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.main.MainActivity;

import java.util.ArrayList;

public class LocationAdapter extends BaseAdapter {
    private ArrayList<Location> mLocationList;
    private LayoutInflater mInflater;
    private Context mContext;

    public LocationAdapter(ArrayList<Location> mLocationList, Context mContext) {
        this.mLocationList = mLocationList;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mLocationList.size();
    }

    @Override
    public Object getItem(int position) {
        return mLocationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.location_data, parent, false);
        Location location = mLocationList.get(position);

        TextView tvSi = convertView.findViewById(R.id.location_tv_si);
        TextView tvGu = convertView.findViewById(R.id.location_tv_gu);
        TextView tvDong = convertView.findViewById(R.id.location_tv_dong);
        LinearLayout llLocationButton = convertView.findViewById(R.id.location_btn);

        tvSi.setText(location.getSi());
        tvGu.setText(location.getGu());
        tvDong.setText(location.getDong());
        llLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToMain();
            }
        });
        return convertView;
    }

    public void intentToMain(){
        Intent intent = new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
    }
}
