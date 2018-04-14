package com.barts.pullupcounter.pullupcounter.UI;

import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.barts.pullupcounter.pullupcounter.Activities.ItemListActivity;
import com.barts.pullupcounter.pullupcounter.Constants;
import com.barts.pullupcounter.pullupcounter.Model.DailyEntry;
import com.barts.pullupcounter.pullupcounter.R;

import java.util.Date;

public class AddEntryPopup2 {
    private View view;
    private AlertDialog dialog;
    private NumberPicker chinupsNP, chinupsWithBandNP, pullupsNP, pullupsWithBandNP;

    public AddEntryPopup2(final ItemListActivity itemListActivity) {
        view = LayoutInflater.from(itemListActivity).inflate(R.layout.new_entry_popup2, null);

        chinupsNP = view.findViewById(R.id.chinupsNPID);
        chinupsWithBandNP = view.findViewById(R.id.assistedChinupsNPID);
        pullupsNP = view.findViewById(R.id.pullupsNPID);
        pullupsWithBandNP = view.findViewById(R.id.assistedPullupsNPID);

        chinupsNP.setMinValue(0);
        chinupsNP.setMaxValue(100);
        chinupsWithBandNP.setMinValue(0);
        chinupsWithBandNP.setMaxValue(100);
        pullupsNP.setMinValue(0);
        pullupsNP.setMaxValue(100);
        pullupsWithBandNP.setMinValue(0);
        pullupsWithBandNP.setMaxValue(100);

        view.findViewById(R.id.createEntryButtonPopupPUID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        dailyEntry.setChinupsCount(chinupsNP.getValue());
        dailyEntry.setAssistedChinups(chinupsWithBandNP.getValue());
        dailyEntry.setPullupsCount(pullupsNP.getValue());
        dailyEntry.setAssistedPullups(pullupsWithBandNP.getValue());

        itemListActivity.addNewEntry(dailyEntry);

        dialog.dismiss();
    }
}
