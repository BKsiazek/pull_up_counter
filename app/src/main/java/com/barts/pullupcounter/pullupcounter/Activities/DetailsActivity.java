package com.barts.pullupcounter.pullupcounter.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.barts.pullupcounter.pullupcounter.Constants;
import com.barts.pullupcounter.pullupcounter.Data.DBHandler;
import com.barts.pullupcounter.pullupcounter.Model.DailyEntry;
import com.barts.pullupcounter.pullupcounter.R;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView dateTV, chinupsCountTV, assistedChinupsCountTV, pullupsCountTV, assistedPullupsCountTV;
    private Button lessChinupsButton, lessAssistedChinupsButton, lessPullupsButton, lessAssistedPullupsButton;
    private Button moreChinupsButton, moreAssistedChinupsButton, morePullupsButton, moreAssistedPullupsButton;
    private Button applyButton;
    private int adapterPosition;
    private DailyEntry clickedEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initializeComponents();

        Bundle bundle = getIntent().getExtras();

        if(bundle == null)
            return;

        adapterPosition = bundle.getInt("adapterPosition");
        clickedEntry = ItemListActivity.recyclerViewAdapter.dailyEntries.get(adapterPosition);

        dateTV.setText(Constants.getDateToShow(clickedEntry.getDate()));
        chinupsCountTV.setText(String.valueOf(clickedEntry.getChinupsCount()));
        assistedChinupsCountTV.setText(String.valueOf(clickedEntry.getAssistedChinups()));
        pullupsCountTV.setText(String.valueOf(clickedEntry.getPullupsCount()));
        assistedPullupsCountTV.setText(String.valueOf(clickedEntry.getAssistedPullups()));
    }

    private void initializeComponents(){
        dateTV = findViewById(R.id.dateDetID);
        chinupsCountTV = findViewById(R.id.chinupsCountDetID);
        assistedChinupsCountTV = findViewById(R.id.chinupsWithBandCountDetID);
        pullupsCountTV = findViewById(R.id.pullupsCountDetID);
        assistedPullupsCountTV = findViewById(R.id.pullupsWithBandCountDetID);

        lessChinupsButton = findViewById(R.id.chLessButtonDetID);
        lessAssistedChinupsButton = findViewById(R.id.chWBLessButtonDetID);
        lessPullupsButton = findViewById(R.id.pLessButtonDetID);
        lessAssistedPullupsButton = findViewById(R.id.pWBLessButtonDetID);

        moreChinupsButton = findViewById(R.id.chMoreButtonDetID);
        moreAssistedChinupsButton = findViewById(R.id.chWBMoreButtonDetID);
        morePullupsButton = findViewById(R.id.pMoreButtonDetID);
        moreAssistedPullupsButton = findViewById(R.id.pWBMoreButtonDetID);

        applyButton = findViewById(R.id.applyDetID);

        lessChinupsButton.setOnClickListener(this);
        lessAssistedChinupsButton.setOnClickListener(this);
        lessPullupsButton.setOnClickListener(this);
        lessAssistedPullupsButton.setOnClickListener(this);

        moreChinupsButton.setOnClickListener(this);
        moreAssistedChinupsButton.setOnClickListener(this);
        morePullupsButton.setOnClickListener(this);
        moreAssistedPullupsButton.setOnClickListener(this);

        applyButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.chLessButtonDetID:
                modifyRepCount(chinupsCountTV, -1);
                break;
            case R.id.chWBLessButtonDetID:
                modifyRepCount(assistedChinupsCountTV, -1);
                break;
            case R.id.pLessButtonDetID:
                modifyRepCount(pullupsCountTV, -1);
                break;
            case R.id.pWBLessButtonDetID:
                modifyRepCount(assistedPullupsCountTV, -1);
                break;

            case R.id.chMoreButtonDetID:
                modifyRepCount(chinupsCountTV, 1);
                break;
            case R.id.chWBMoreButtonDetID:
                modifyRepCount(assistedChinupsCountTV, 1);
                break;
            case R.id.pMoreButtonDetID:
                modifyRepCount(pullupsCountTV, 1);
                break;
            case R.id.pWBMoreButtonDetID:
                modifyRepCount(assistedPullupsCountTV, 1);
                break;
            case R.id.applyDetID:
                applyChanges();
                break;
        }
    }

    private void modifyRepCount(TextView textView, int modification){
        int previous = Integer.parseInt(textView.getText().toString());
        int newValue = previous + modification;

        if(newValue < 0)
            return;

        textView.setText(String.valueOf(newValue));
    }

    private void applyChanges() {
        clickedEntry.setChinupsCount(Integer.parseInt(chinupsCountTV.getText().toString()));
        clickedEntry.setAssistedChinups(Integer.parseInt(assistedChinupsCountTV.getText().toString()));
        clickedEntry.setPullupsCount(Integer.parseInt(pullupsCountTV.getText().toString()));
        clickedEntry.setAssistedPullups(Integer.parseInt(assistedPullupsCountTV.getText().toString()));

        DBHandler dbHandler = new DBHandler(this);
        dbHandler.updateEntry(clickedEntry);

        ItemListActivity.recyclerViewAdapter.notifyItemChanged(adapterPosition);

        startActivity(new Intent(this, ItemListActivity.class));
        finish();
    }
}
