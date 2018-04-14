package com.barts.pullupcounter.pullupcounter.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.barts.pullupcounter.pullupcounter.Data.DBHandler;
import com.barts.pullupcounter.pullupcounter.Model.DailyEntry;
import com.barts.pullupcounter.pullupcounter.R;
import com.barts.pullupcounter.pullupcounter.RecyclerView.MyViewHolder;
import com.barts.pullupcounter.pullupcounter.RecyclerView.RecyclerViewAdapter;
import com.barts.pullupcounter.pullupcounter.UI.AddEntryPopup;
import com.barts.pullupcounter.pullupcounter.UI.AddEntryPopup2;

import java.util.Date;

public class ItemListActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    static RecyclerViewAdapter recyclerViewAdapter;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialize();
    }

    private void initialize(){
        dbHandler = new DBHandler(this);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(this, dbHandler.getAllEntries());
        recyclerViewAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void addNewEntry(DailyEntry entry){
        int id = (int)dbHandler.addEntry(entry);

        entry.setId(id);

        recyclerViewAdapter.dailyEntries.add(0, entry);
        recyclerViewAdapter.notifyItemInserted(0);
        recyclerView.smoothScrollToPosition(0);

        Toast.makeText(this, "Entry Saved!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if (dbHandler.checkIfTheDateExists(new Date()))
            recyclerView.findViewHolderForAdapterPosition(0).itemView.performClick();
        else new AddEntryPopup2(ItemListActivity.this);
    }
}
