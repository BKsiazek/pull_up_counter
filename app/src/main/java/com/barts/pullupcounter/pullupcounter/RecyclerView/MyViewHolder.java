package com.barts.pullupcounter.pullupcounter.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.barts.pullupcounter.pullupcounter.Activities.DetailsActivity;
import com.barts.pullupcounter.pullupcounter.Activities.ItemListActivity;
import com.barts.pullupcounter.pullupcounter.Data.DBHandler;
import com.barts.pullupcounter.pullupcounter.Model.DailyEntry;
import com.barts.pullupcounter.pullupcounter.R;

import static android.R.color.black;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView date, pullUpsCount, pullUpsWithBandCount, chinUpsCount, chinUpsWithBandCount;
    private RecyclerViewAdapter myAdapter;
    CardView cardView;

    public MyViewHolder(View view, final RecyclerViewAdapter myAdapter) {
        super(view);

        this.myAdapter = myAdapter;

        date = view.findViewById(R.id.dateID);
        chinUpsCount = view.findViewById(R.id.chinUpCountID);
        chinUpsWithBandCount = view.findViewById(R.id.chinUpWithBandCountID);
        pullUpsCount = view.findViewById(R.id.pullUpCountID);
        pullUpsWithBandCount = view.findViewById(R.id.pullUpWithBandCountID);

        cardView = view.findViewById(R.id.cardViewID);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetails();
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDeleteDialog();
                return true;
            }
        });
    }

    public void showDetails() {
        Intent intent = new Intent(myAdapter.context, DetailsActivity.class);
        intent.putExtra("adapterPosition", getAdapterPosition());

        myAdapter.context.startActivity(intent);
    }

    private void showDeleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(myAdapter.context);
        builder.setTitle(R.string.delete_question);

        builder
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteItem();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

        builder.show();
    }

    private void deleteItem() {
        DBHandler dbHandler = new DBHandler(myAdapter.context);
        dbHandler.deleteEntry(myAdapter.dailyEntries.get(getAdapterPosition()).getId());

        myAdapter.dailyEntries.remove(getAdapterPosition());
        myAdapter.notifyItemRemoved(getAdapterPosition());
    }
}
