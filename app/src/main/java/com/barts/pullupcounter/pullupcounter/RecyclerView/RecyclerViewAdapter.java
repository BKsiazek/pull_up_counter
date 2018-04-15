package com.barts.pullupcounter.pullupcounter.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Debug;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barts.pullupcounter.pullupcounter.Constants;
import com.barts.pullupcounter.pullupcounter.Model.DailyEntry;
import com.barts.pullupcounter.pullupcounter.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder>{

    public List<DailyEntry> dailyEntries;
    public Context context;

    public RecyclerViewAdapter(Context context, List<DailyEntry> dailyEntries) {
        this.context = context;
        this.dailyEntries = dailyEntries;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DailyEntry entry = dailyEntries.get(position);

        holder.date.setText(Constants.getDateToShow(entry.getDate()));
        holder.chinUpsCount.setText(String.valueOf(entry.getChinupsCount()));
        holder.chinUpsWithBandCount.setText(String.valueOf(entry.getAssistedChinups()));
        holder.pullUpsCount.setText(String.valueOf(entry.getPullupsCount()));
        holder.pullUpsWithBandCount.setText(String.valueOf(entry.getAssistedPullups()));

        if(Constants.isTheDateToday(entry.getDate()))
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.current_day_fill_color, null));
    }

    @Override
    public int getItemCount() {
        return dailyEntries.size();
    }


}
