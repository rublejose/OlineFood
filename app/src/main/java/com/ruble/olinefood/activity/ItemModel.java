package com.ruble.olinefood.activity;

/**
 * Created by ruble on 25-01-2018.
 */

public class ItemModel {
    String item_id;
    String item_name;
    String item_price;
    String item_type;
    String Item_Stock;
    public ItemModel(String item_id,String item_name,String item_price,String item_type,String item_Stock){
        this.setItem_id(item_id);
        this.setItem_name(item_name);
        this.setItem_price(item_price);
        this.setItem_type(item_type);
        this.setItem_Stock(item_Stock);
    }


    public String getItem_Stock() {
        return Item_Stock;
    }

    public void setItem_Stock(String item_Stock) {
        Item_Stock = item_Stock;
    }



    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }



}
