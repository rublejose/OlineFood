package com.ruble.olinefood.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserOrderConfirmActivity extends AppCompatActivity {
    ListView userOrderListView=null;
    TextView totalPriceTextView=null;
    Button deleteOrderButton=null;
    Button orderConfirmButton=null;
    DataBaseManager dataBaseManager;
    ArrayList<DataBaseModel> list = null;
    CartAdapter cartAdapter;
    int amount=0;
    String account_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order_confirm);
        userOrderListView=findViewById(R.id.userOrderListView);
        totalPriceTextView=findViewById(R.id.totalPriceTextView);
        deleteOrderButton=findViewById(R.id.deleteOrderButton);
        orderConfirmButton=findViewById(R.id.orderConfirmButton);
        dataBaseManager = new DataBaseManager(this);
        SharedPreferences sharedPreferences=getSharedPreferences("account",MODE_PRIVATE);
        account_name=sharedPreferences.getString("account_name","nil");
        list = dataBaseManager.getAllItem(account_name);
        cartAdapter = new CartAdapter(this, list);
        userOrderListView.setAdapter(cartAdapter);
        int i=0;

        while(i<this.list.size()){
            amount=amount+Integer.parseInt(this.list.get(i).getPrice());
            i++;
        }
        totalPriceTextView.setText("Rs. "+amount);
        registerForContextMenu(userOrderListView);
        deleteOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseManager.deleteAllItem(account_name);
                Intent intent=new Intent(UserOrderConfirmActivity.this,UserHomeActivity.class);
                startActivity(intent);

            }
        });
        orderConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
