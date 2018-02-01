package com.ruble.olinefood.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruble on 25-01-2018.
 */

public class ItemAdapter extends ArrayAdapter {
    List list=new ArrayList();
    public ItemAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    public void add(ItemModel object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row=convertView;
        ItemHolder itemHolder;
        if(row==null){
            LayoutInflater layoutInflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            itemHolder=new ItemHolder();
            itemHolder.item_id=row.findViewById(R.id.rowIdTextView);
            itemHolder.item_name=row.findViewById(R.id.rowNameTextView);
            itemHolder.item_price=row.findViewById(R.id.rowPriceTextView);
            itemHolder.item_type=row.findViewById(R.id.rowTypeTextView);
            itemHolder.item_stock=row.findViewById(R.id.rowStockTextView);
            row.setTag(itemHolder);
        }else {
            itemHolder= (ItemHolder) row.getTag();
        }
        ItemModel itemModel= (ItemModel) this.getItem(position);
        itemHolder.item_id.setText(itemModel.getItem_id());
        itemHolder.item_name.setText(itemModel.getItem_name());
        itemHolder.item_price.setText(itemModel.getItem_price());
        itemHolder.item_type.setText(itemModel.getItem_type());
        itemHolder.item_stock.setText(itemModel.getItem_Stock());
        return row;
    }
    static class ItemHolder{
        TextView item_id,item_name,item_price,item_type,item_stock;
    }
}
