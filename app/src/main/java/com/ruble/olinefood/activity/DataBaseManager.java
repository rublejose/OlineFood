package com.ruble.olinefood.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by ruble on 26-01-2018.
 */

public class DataBaseManager extends SQLiteOpenHelper {

    public DataBaseManager(Context context) {
        super(context, DataBaseUtil.DB_NAME, null, DataBaseUtil.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CART_TABLE = " CREATE TABLE " + DataBaseUtil.CART_TABLE
                + "(" + DataBaseUtil.BILL_ID
                + " VARCHAR,"
                + DataBaseUtil.ITEM_ID + " VARCHAR,"
                + DataBaseUtil.ITEM_PRICE + " VARCHAR,"
                + DataBaseUtil.ITEM_QUANTITY + " VARCHAR,"
                + DataBaseUtil.ACCOUNT_NAME + " VARCHAR,"
                + DataBaseUtil.ITEM_NAME + " VARCHAR)";
        sqLiteDatabase.execSQL(CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int oldVersion, int newVersion) {
        sqlDB.execSQL("DROP TABLE IF EXISTS " + DataBaseUtil.CART_TABLE);
    }
    public ArrayList<DataBaseModel> getAllItem(String account_name){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + DataBaseUtil.CART_TABLE+" WHERE "+DataBaseUtil.ACCOUNT_NAME+" = "+"'"+account_name+"';";
        Cursor cursor = database.rawQuery(query, null);
        ArrayList<DataBaseModel> list = null;
        if(cursor != null){
            list = new ArrayList<DataBaseModel>();
            while(cursor.moveToNext()){
                DataBaseModel dataBaseModel = new DataBaseModel();
                dataBaseModel.setName(cursor.getString(cursor.getColumnIndex(DataBaseUtil.ITEM_NAME)));
                dataBaseModel.setId_no(cursor.getString(cursor.getColumnIndex(DataBaseUtil.ITEM_ID)));
                dataBaseModel.setPrice(cursor.getString(cursor.getColumnIndex(DataBaseUtil.ITEM_PRICE)));
                dataBaseModel.setQuantity(cursor.getString(cursor.getColumnIndex(DataBaseUtil.ITEM_QUANTITY)));
                list.add(dataBaseModel);
            }
            cursor.close();
            database.close();
        }
        return list;
    }
    public  boolean addItem(String id, String name,String quantity,String price,String account_name){
        SQLiteDatabase sqLiteDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseUtil.ITEM_ID, id);
        values.put(DataBaseUtil.ITEM_PRICE,price);
        values.put(DataBaseUtil.ITEM_QUANTITY,quantity);
        values.put(DataBaseUtil.ITEM_NAME,name);
        values.put(DataBaseUtil.ACCOUNT_NAME,account_name);
        long insert = sqLiteDB.insert(DataBaseUtil.CART_TABLE, null, values);
        sqLiteDB.close();
        if (insert > 0){
            return true;
        }
        return false;
    }
    public void deleteAllItem(String account_name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ DataBaseUtil.CART_TABLE+" WHERE "+DataBaseUtil.ACCOUNT_NAME+" = "+"'"+account_name+"';");
        db.close();
    }
}
