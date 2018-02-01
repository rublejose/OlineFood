package com.ruble.olinefood.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class ManagerHomeActivity extends AppCompatActivity {
    ListView dataItemListView=null;
    Button goToRegButton=null;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);
        DataFetch dataFetch=new DataFetch(ManagerHomeActivity.this);
        dataFetch.execute();

    }
}
