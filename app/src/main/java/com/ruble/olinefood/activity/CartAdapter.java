package com.ruble.olinefood.activity;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ruble on 27-01-2018.
 */

public class CartAdapter extends ArrayAdapter {
    Context context;
    ArrayList<DataBaseModel> list = null;

    public CartAdapter(Context context, ArrayList list) {
        super(context, R.layout.user_order_layout, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.user_order_layout,parent,false);
        }
        TextView nameTextView = (TextView) convertView.findViewById(R.id.nameOrderTextView);
        TextView quantityTextView = (TextView) convertView.findViewById(R.id.quantityOrderTextView);
        TextView priceTextView = (TextView) convertView.findViewById(R.id.priceOrderTextView);
        nameTextView.setText(list.get(position).getName());
        priceTextView.setText(list.get(position).getPrice());
        quantityTextView.setText(list.get(position).getQuantity());


        return convertView;
    }
}
