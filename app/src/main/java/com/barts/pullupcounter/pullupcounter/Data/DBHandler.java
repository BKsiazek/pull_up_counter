package com.barts.pullupcounter.pullupcounter.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.barts.pullupcounter.pullupcounter.Constants;
import com.barts.pullupcounter.pullupcounter.Model.DailyEntry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper{

    public DBHandler(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DAILY_ENTRY_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY," + Constants.KEY_DATE
                + " TEXT," + Constants.KEY_P_UPS + " INTEGER," + Constants.KEY_ASSISTED_P_UPS
                + " INTEGER," + Constants.KEY_CH_UPS + " INTEGER," + Constants.KEY_ASSISTED_CH_UPS + " INTEGER);";

        db.execSQL(CREATE_DAILY_ENTRY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    public long addEntry(DailyEntry entry){
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_DATE, /*Constants.convertStringDateToLong(*/entry.getDate());
        values.put(Constants.KEY_P_UPS, entry.getPullupsCount());
        values.put(Constants.KEY_ASSISTED_P_UPS, entry.getAssistedPullups());
        values.put(Constants.KEY_CH_UPS, entry.getChinupsCount());
        values.put(Constants.KEY_ASSISTED_CH_UPS, entry.getAssistedChinups());

        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(Constants.TABLE_NAME, null, values);
        db.close();

        return id;
    }

    public DailyEntry getEntry(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                Constants.TABLE_NAME,
                Constants.ALL_COLUMNS_NAMES,
                Constants.KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if(cursor == null){
            Log.d("DBHandler:getEntry() ", "Cursor is null.");
            return null;
        }

        cursor.moveToFirst();

        return getEntryFromCursor(cursor);
    }

    public List<DailyEntry> getAllEntries(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                Constants.TABLE_NAME,
                Constants.ALL_COLUMNS_NAMES,
                null,
                null,
                null,
                null, Constants.KEY_DATE + " DESC");

        List<DailyEntry> dailyEntries = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                dailyEntries.add(getEntryFromCursor(cursor));
            } while(cursor.moveToNext());
        }

        return dailyEntries;
    }

    private DailyEntry getEntryFromCursor(Cursor cursor){
        DailyEntry entry = new DailyEntry();

        entry.setId(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID)));
        entry.setPullupsCount(cursor.getInt(cursor.getColumnIndex(Constants.KEY_P_UPS)));
        entry.setAssistedPullups(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ASSISTED_P_UPS)));
        entry.setChinupsCount(cursor.getInt(cursor.getColumnIndex(Constants.KEY_CH_UPS)));
        entry.setAssistedChinups(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ASSISTED_CH_UPS)));
        entry.setDate(cursor.getString(cursor.getColumnIndex(Constants.KEY_DATE)));

        //long dateLong = cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE));
        //entry.setDate(Constants.convertDateToString(new Date(dateLong)));

        return entry;
    }

    public int updateEntry(DailyEntry entry){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_P_UPS, entry.getPullupsCount());
        values.put(Constants.KEY_ASSISTED_P_UPS, entry.getAssistedPullups());
        values.put(Constants.KEY_CH_UPS, entry.getChinupsCount());
        values.put(Constants.KEY_ASSISTED_CH_UPS, entry.getAssistedChinups());
        //values.put(Constants.KEY_DATE, Constants.convertStringDateToLong(entry.getDate()));
        values.put(Constants.KEY_DATE, entry.getDate());

        return db.update(Constants.TABLE_NAME, values, Constants.KEY_ID + "=?", new String[]{String.valueOf(entry.getId())});
    }

    public void deleteEntry(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public int getEntriesCount(){
        SQLiteDatabase db = getReadableDatabase();

        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    public boolean checkIfTheDateExists(Date date){
        String convertedDate = Constants.convertDateToString(date);

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                Constants.TABLE_NAME,
                new String[]{Constants.KEY_ID, Constants.KEY_DATE},
                Constants.KEY_DATE + "=?",
                new String[]{convertedDate}, null, null, null, null);

        if(cursor != null && cursor.getCount() > 0){
            return true;
        }

        return false;
    }
}
