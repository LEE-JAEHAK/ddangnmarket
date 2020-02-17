package com.example.ddangnmarket.src.main.Item;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.main.ProductInformation;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ItemsAdapter extends BaseAdapter {
    private ArrayList<Items> mItemsList;
    private LayoutInflater mInflater;
    private Context mContext;

    public ItemsAdapter(ArrayList<Items> mItemsList, Context mContext) {
        this.mItemsList = mItemsList;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.product_data, parent, false);
        final Items items = mItemsList.get(position);

        final ImageView product = convertView.findViewById(R.id.home_iv_product_pic);
        final TextView name = convertView.findViewById(R.id.home_tv_product_name);
        TextView gu = convertView.findViewById(R.id.home_tv_gu);
        TextView dong = convertView.findViewById(R.id.home_tv_dong);
        TextView price = convertView.findViewById(R.id.home_tv_price);

        product.setImageDrawable(items.getProduct());
        name.setText(items.getName());
        gu.setText(items.getGu());
        dong.setText(items.getDong());

        String tmp = moneyFormatToWon(items.getPrice());
        price.setText(tmp + "원");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProductInformation.class);
                //intent.putExtra("pic",items.getProduct());
                intent.putExtra("name",items.getName());
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    public static String moneyFormatToWon(int inputMoney) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        return decimalFormat.format(inputMoney);
    }

}
