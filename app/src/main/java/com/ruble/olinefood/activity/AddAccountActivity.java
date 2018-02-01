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

public class AddAccountActivity extends AppCompatActivity {
    EditText usernameEditText=null;
    EditText passwordEditText=null;
    EditText addressEditText=null;
    EditText phoneEditText=null;
    Button registerButton=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        usernameEditText=findViewById(R.id.usernameEditText);
        passwordEditText=findViewById(R.id.passwordEditText);
        addressEditText=findViewById(R.id.addressEditText);
        phoneEditText=findViewById(R.id.phoneEditText);
        registerButton=findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usernameEditText.getText().toString().equals("")){
                    usernameEditText.setError("invalid username");
                }else if (passwordEditText.getText().toString().equals("")){
                    passwordEditText.setError("invalid password");
                }else if(addressEditText.getText().toString().equals("")){
                    addressEditText.setError("invalid address");
                }else if (phoneEditText.getText().toString().equals("")){
                    phoneEditText.setError("invalid phone number");
                }else{
                    String username=usernameEditText.getText().toString();
                    String password=passwordEditText.getText().toString();
                    String address=addressEditText.getText().toString();
                    String phone=phoneEditText.getText().toString();
                    BackgroundTask backgroundTask=new BackgroundTask(AddAccountActivity.this);
                    WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    if (wifi.isWifiEnabled()) {
                        backgroundTask.execute("register", username, password, address, phone);
                    }else{
                        Toast.makeText(AddAccountActivity.this,"Please Enable Wifi",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
