package com.ruble.olinefood.activity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ItemRegistrationActivity extends AppCompatActivity {
    EditText itemIdEditText=null;
    EditText itemNameEditText=null;
    EditText itemTypeEditText=null;
    EditText itemPriceEditText=null;
    Button itemRegistrationButton=null;
    EditText updateIdEditText=null;
    EditText updatePriceEditText=null;
    Button updatePriceButton=null;
    Button itemListsButton=null;
    Button updateStockButton=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_registration);
        updateIdEditText=findViewById(R.id.updateIdEditText);
        updatePriceEditText=findViewById(R.id.updatePriceEditText);
        updatePriceButton=findViewById(R.id.updatePriceButton);
        itemIdEditText=findViewById(R.id.itemIdEditText);
        itemNameEditText=findViewById(R.id.itemNameEditText);
        itemTypeEditText=findViewById(R.id.itemTypeEditText);
        itemPriceEditText=findViewById(R.id.itemPriceEditText);
        itemRegistrationButton=findViewById(R.id.itemRegistrationButton);
        updateStockButton=findViewById(R.id.updateStockButton);
        itemListsButton=findViewById(R.id.itemListsButton);
        updateStockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (updateIdEditText.getText().toString().equals("")){
                    updateIdEditText.setError("invalid item id");
                }else if (updatePriceEditText.getText().toString().equals("")){
                    updatePriceEditText.setError("invalid item name");
                }else{
                    String id=updateIdEditText.getText().toString();
                    String stock=updatePriceEditText.getText().toString();
                    BackgroundTask backgroundTask=new BackgroundTask(ItemRegistrationActivity.this);
                    WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    if (wifi.isWifiEnabled()) {
                        backgroundTask.execute("item_stock_update", id, stock);
                    }else{
                        Toast.makeText(ItemRegistrationActivity.this,"Please Enable Wifi",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        itemListsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ItemRegistrationActivity.this,ManagerHomeActivity.class);
                startActivity(intent);
            }
        });
        itemRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemIdEditText.getText().toString().equals("")){
                    itemIdEditText.setError("invalid item id");
                }else if (itemNameEditText.getText().toString().equals("")){
                    itemNameEditText.setError("invalid item name");
                }else if(itemTypeEditText.getText().toString().equals("")){
                    itemTypeEditText.setError("invalid type");
                }else if (itemPriceEditText.getText().toString().equals("")){
                    itemPriceEditText.setError("invalid item price");
                }else{
                    String id=itemIdEditText.getText().toString();
                    String name=itemNameEditText.getText().toString();
                    String type=itemTypeEditText.getText().toString();
                    String price=itemPriceEditText.getText().toString();
                    BackgroundTask backgroundTask=new BackgroundTask(ItemRegistrationActivity.this);
                    WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    if (wifi.isWifiEnabled()) {
                        backgroundTask.execute("item_register", id, name, type, price);
                    }else{
                        Toast.makeText(ItemRegistrationActivity.this,"Please Enable Wifi",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        updatePriceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (updateIdEditText.getText().toString().equals("")){
                    updateIdEditText.setError("invalid item id");
                }else if (updatePriceEditText.getText().toString().equals("")){
                    updatePriceEditText.setError("invalid item name");
                }else{
                    String id=updateIdEditText.getText().toString();
                    String price=updatePriceEditText.getText().toString();
                    BackgroundTask backgroundTask=new BackgroundTask(ItemRegistrationActivity.this);
                    WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    if (wifi.isWifiEnabled()) {
                        backgroundTask.execute("item_update", id, price);
                    }else{
                        Toast.makeText(ItemRegistrationActivity.this,"Please Enable Wifi",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
