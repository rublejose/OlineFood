package com.ruble.olinefood.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ruble on 25-01-2018.
 */

public class DataFetch extends AsyncTask<Void, Void, String> {
    String get_data_url="http://"+IpConfig.ip+"/online_food_app/get_item_list.php";
    String JSON_STRING="";
    Context context;
    Activity activity;
    ListView dataItemListView=null;
    Button goToRegButton=null;
    ItemAdapter itemAdapter;
    public DataFetch(Context context) {
        this.context=context;
        activity= (Activity) context;
    }
    @Override
    protected String doInBackground(Void... voids) {
        URL url= null;
        try {
            url = new URL(get_data_url);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while ((JSON_STRING=bufferedReader.readLine())!=null){
                stringBuilder.append(JSON_STRING+"\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String result) {
        JSONObject jsonObject= null;
        itemAdapter=new ItemAdapter(activity,R.layout.row_layout);
        try {
            jsonObject = new JSONObject(result);
            JSONArray jsonArray=jsonObject.getJSONArray("server_response");
            int count=0;
            String item_id,item_name,item_price,item_type,item_stock;
            while (count<jsonArray.length()){
                JSONObject jo=jsonArray.getJSONObject(count);
                item_id=jo.getString("item_id");
                item_name=jo.getString("item_name");
                item_price=jo.getString("item_price");
                item_type=jo.getString("item_type");
                item_stock=jo.getString("item_stock");
                ItemModel itemModel=new ItemModel(item_id,item_name,item_price,item_type,item_stock);
                itemAdapter.add(itemModel);
                count++;
            }
            dataItemListView=activity.findViewById(R.id.dataItemListView);
            goToRegButton=activity.findViewById(R.id.goToRegButton);
            dataItemListView.setAdapter(itemAdapter);
            goToRegButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(activity,ItemRegistrationActivity.class);
                    activity.startActivity(intent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
