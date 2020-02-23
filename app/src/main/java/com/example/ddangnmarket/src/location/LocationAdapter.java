package com.example.ddangnmarket.src.location;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.location.models.Result;
import com.example.ddangnmarket.src.main.MainActivity;

import java.util.ArrayList;

public class LocationAdapter extends BaseAdapter {
    private ArrayList<Result> mLocationList;
    private LayoutInflater mInflater;
    private Context mContext;

    public LocationAdapter(ArrayList<Result> mLocationList, Context mContext) {
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
        final Result location = mLocationList.get(position);

        TextView mTvLocation = convertView.findViewById(R.id.location_tv);

        mTvLocation.setText(location.getAddress());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MainActivity.class);
                //intent.putExtra("dong",location.getDong());
                mContext.startActivity(intent);
                ((Activity)mContext).finish();
            }
        });
        return convertView;
    }
}
