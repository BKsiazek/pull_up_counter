package com.barts.pullupcounter.pullupcounter.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.barts.pullupcounter.pullupcounter.Data.DBHandler;
import com.barts.pullupcounter.pullupcounter.R;

public class MainActivity extends AppCompatActivity {

    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(this);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButtonID);

        if(dbHandler.getEntriesCount() > 0){
            startActivity(new Intent(this, ItemListActivity.class));
            finish();
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, ItemListActivity.class));
            }
        });
    }
}
