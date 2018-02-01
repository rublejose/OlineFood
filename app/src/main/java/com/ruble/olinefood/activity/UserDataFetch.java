package com.ruble.olinefood.activity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
import java.util.ArrayList;

/**
 * Created by ruble on 26-01-2018.
 */

public class UserDataFetch extends AsyncTask<Void,ItemModel, String> {
    String get_data_url="http://"+IpConfig.ip+"/online_food_app/get_item_list.php";
    String JSON_STRING="";
    Context context;
    Activity activity;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ItemModel> arrayList=new ArrayList<>();
    @Override
    protected void onPreExecute() {
        recyclerView=activity.findViewById(R.id.idRecyclerView);
        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter=new UserItemAdapter(arrayList,context);
        recyclerView.setAdapter(adapter);


    }
    public UserDataFetch(Context context){
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
            String json = stringBuilder.toString().trim();
            JSONObject jsonObject= new JSONObject(json);
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
                count++;
                publishProgress(itemModel);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onProgressUpdate(ItemModel... values) {
      arrayList.add(values[0]);
      adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
    }
}
