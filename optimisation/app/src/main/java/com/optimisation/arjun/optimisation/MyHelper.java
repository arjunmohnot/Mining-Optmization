package com.optimisation.arjun.optimisation;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyHelper extends SQLiteOpenHelper {
    private Context con;
    public MyHelper(Context context) {
        super(context, "MyDatabase", null, 1);
        con=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable="create table MyTable(xValues INTEGER,yValues FLOAT,zValues FLOAT,aValues FLOAT);";
        db.execSQL(createTable);
//        Toast.makeText(con, "Table Created", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertData(int xval, float yval,float zval, float aval){

        SQLiteDatabase  db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("xValues",xval);
        contentValues.put("yValues",yval);
        contentValues.put("zValues",zval);
        contentValues.put("aValues",aval);
        db.insert("MyTable",null,contentValues);
//        Toast.makeText(con, "Data has been inserted", Toast.LENGTH_LONG).show();
    }
    public Integer deletedata(String id) {
        SQLiteDatabase  db= this.getWritableDatabase();
       return db.delete("MyTable","xValues=?",new String[]{id});
    }
}
