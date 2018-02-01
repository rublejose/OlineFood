package com.ruble.olinefood.activity;

/**
 * Created by ruble on 26-01-2018.
 */

public interface DataBaseUtil {
    String DB_NAME = "_Online_Food_Db";
    int VERSION = 1;
    //tables
    String CART_TABLE = "_cart_table";


    //cart_TABLE fields
    String ITEM_ID = "_item_id";
    String ITEM_NAME = "_item_name";
    String ITEM_PRICE = "_item_price";
    String BILL_ID ="_bill_id";
    String ITEM_QUANTITY = "_item_quantity";
    String ACCOUNT_NAME = "_account_name";
}
