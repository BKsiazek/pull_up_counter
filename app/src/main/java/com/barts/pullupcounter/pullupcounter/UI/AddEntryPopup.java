package com.barts.pullupcounter.pullupcounter.UI;

import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.barts.pullupcounter.pullupcounter.Activities.ItemListActivity;
import com.barts.pullupcounter.pullupcounter.Constants;
import com.barts.pullupcounter.pullupcounter.Model.DailyEntry;
import com.barts.pullupcounter.pullupcounter.R;

import java.util.Date;

public class AddEntryPopup {
    private View view;
    private AlertDialog dialog;
    private EditText chinupsET, chinupsWithBandET, pullupsET, pullupsWithBandET;

    public AddEntryPopup(final ItemListActivity itemListActivity) {
        view = LayoutInflater.from(itemListActivity).inflate(R.layout.new_entry_popup, null);

        chinupsET = view.findViewById(R.id.chinupsPopupID);
        chinupsWithBandET = view.findViewById(R.id.chinupsWithBandPopupID);
        pullupsET = view.findViewById(R.id.pullupsPopupID);
        pullupsWithBandET = view.findViewById(R.id.pullupsWithBandPopupID);

        view.findViewById(R.id.createEntryButtonPopupID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!chinupsET.getText().toString().isEmpty()
                        && !chinupsWithBandET.getText().toString().isEmpty()
                        && !pullupsET.getText().toString().isEmpty()
                        && !pullupsWithBandET.getText().toString().isEmpty())
                    saveNewEntry(itemListActivity);
            }
        });

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(itemListActivity);
        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        dialog.show();
    }

    private void saveNewEntry(ItemListActivity itemListActivity){
        DailyEntry dailyEntry = new DailyEntry();
        dailyEntry.setDate(Constants.convertDateToString(new Date()));
        dailyEntry.setChinupsCount(Integer.parseInt(chinupsET.getText().toString()));
        dailyEntry.setAssistedChinups(Integer.parseInt(chinupsWithBandET.getText().toString()));
        dailyEntry.setPullupsCount(Integer.parseInt(pullupsET.getText().toString()));
        dailyEntry.setAssistedPullups(Integer.parseInt(pullupsWithBandET.getText().toString()));

        itemListActivity.addNewEntry(dailyEntry);

        dialog.dismiss();
    }
}
