package com.optimisation.arjun.optimisation;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class mycounter extends SQLiteOpenHelper {
    private Context con;

    public mycounter(Context context) {
        super(context, "Mydatabase", null, 1);
        con = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table Mytable(yValues FLOAT);";
        db.execSQL(createTable);
//        Toast.makeText(con, "Table Created", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(float yval) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("yValues", yval);
        db.insert("Mytable", null, contentValues);
    }

}
