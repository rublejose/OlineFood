package com.ruble.olinefood.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddToCartActivity extends AppCompatActivity {
    TextView cartItemNameTextView=null;
    EditText quantityEditText=null;
    Button cartBackButton=null;
    Button addToCartButton=null;
    String item_id=null;
    String item_name=null;
    String item_price=null;
    String item_stock=null;
    String item_type=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        cartItemNameTextView=findViewById(R.id.cartItemNameTextView);
        quantityEditText=findViewById(R.id.quantityEditText);
        cartBackButton=findViewById(R.id.cartBackButton);
        addToCartButton=findViewById(R.id.addToCartButton);
        item_id=getIntent().getStringExtra("item_id");
        item_name=getIntent().getStringExtra("item_name");
        item_price=getIntent().getStringExtra("item_price");
        item_stock=getIntent().getStringExtra("item_stock");
        item_type=getIntent().getStringExtra("item_type");
        cartItemNameTextView.setText(item_name);
        cartBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddToCartActivity.this,UserHomeActivity.class);
                startActivity(intent);
            }
        });
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantityEditText.getText().toString().equals("")){
                    quantityEditText.setError("invalid Number");
                }else{
                    String quantity=quantityEditText.getText().toString();
                    BackgroundTask backgroundTask=new BackgroundTask(AddToCartActivity.this);
                    SharedPreferences sharedPreferences=getSharedPreferences("account",MODE_PRIVATE);
                    String account_name=sharedPreferences.getString("account_name","nil");
                    Toast.makeText(AddToCartActivity.this, "addaccount"+account_name, Toast.LENGTH_SHORT).show();
                    backgroundTask.execute("stock_check",item_id,quantity,account_name);
                }
            }
        });
    }
}
