package com.ruble.olinefood.activity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText phoneLoginEditText=null;
    EditText passwordLoginEditText=null;
    Button loginButton=null;
    TextView signUpTextView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phoneLoginEditText=findViewById(R.id.phoneLoginEditText);
        passwordLoginEditText=findViewById(R.id.passwordLoginEditText);
        loginButton=findViewById(R.id.loginButton);
        signUpTextView=findViewById(R.id.signUpTextView);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phoneLoginEditText.getText().toString().equals("")){
                    phoneLoginEditText.setError("invalid phone number");
                }else if (passwordLoginEditText.getText().toString().equals("")){
                    phoneLoginEditText.setError("invalid password");
                }else{
                    String password=passwordLoginEditText.getText().toString();
                    String phone=phoneLoginEditText.getText().toString();
                    BackgroundTask backgroundTask=new BackgroundTask(LoginActivity.this);
                    backgroundTask.execute("login",password,phone);
                }
            }
        });
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,AddAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}
