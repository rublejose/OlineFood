package com.ruble.olinefood.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class UserHomeActivity extends AppCompatActivity {
    RecyclerView idRecyclerView=null;
    SharedPreferences sharedPreferences;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        findViewById(R.id.idRecyclerView);
        name=getIntent().getStringExtra("username");
        sharedPreferences=getSharedPreferences("account",MODE_PRIVATE);
        if(name!=null){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("account_name",name);
            editor.commit();
        }
        name=sharedPreferences.getString("account_name","nill");
        UserDataFetch userDataFetch = new UserDataFetch(UserHomeActivity.this);
        userDataFetch.execute();

    }
    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.cart_button){
            Intent intent = new Intent(UserHomeActivity.this,UserOrderConfirmActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
