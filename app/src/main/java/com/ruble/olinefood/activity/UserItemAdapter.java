package com.ruble.olinefood.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruble on 26-01-2018.
 */

public class UserItemAdapter extends RecyclerView.Adapter<UserItemAdapter.RecyclerViewHolder>{
    ArrayList<ItemModel> arrayList=new ArrayList<>();
    Context context;
    public UserItemAdapter(ArrayList<ItemModel> arrayList,Context context){
        this.arrayList=arrayList;
        this.context=context;


    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_layout,parent,false);
        RecyclerViewHolder recyclerViewHolder =new RecyclerViewHolder(view,context,arrayList );
        return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        ItemModel itemModel=arrayList.get(position);
        holder.item_name.setText(itemModel.getItem_name());
        holder.item_price.setText(itemModel.getItem_price());
        holder.item_type.setText(itemModel.getItem_type());
        if (Integer.parseInt(itemModel.getItem_Stock())<1){
            holder.image.setBackgroundResource(R.drawable.soldoutimage);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView item_name,item_price,item_type,image;
        ArrayList<ItemModel>itemModels=new ArrayList<ItemModel>();
        Context context;
        public RecyclerViewHolder(View itemView,Context context,ArrayList<ItemModel>itemModels) {
            super(itemView);
            this.itemModels=itemModels;
            this.context=context;
            itemView.setOnClickListener(this);
            image=itemView.findViewById(R.id.imageTextView);
            item_name=itemView.findViewById(R.id.uRNameTextView);
            item_price=itemView.findViewById(R.id.rsTextView);
            item_type=itemView.findViewById(R.id.uRTypetextView);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            ItemModel itemModel=this.itemModels.get(position);
            Intent intent=new Intent(context,AddToCartActivity.class);
            String stock= itemModel.getItem_Stock();
            if (Integer.parseInt(stock)>0){
                intent.putExtra("item_id",itemModel.getItem_id());
                intent.putExtra("item_name",itemModel.getItem_name());
                intent.putExtra("item_price",itemModel.getItem_price());
                intent.putExtra("item_type",itemModel.getItem_type());
                intent.putExtra("item_stock",stock);
                this.context.startActivity(intent);
            }else{
                Toast.makeText(this.context,"Sorry...The Item Sold Out or Not Available Now",Toast.LENGTH_LONG).show();
            }
        }
    }
}