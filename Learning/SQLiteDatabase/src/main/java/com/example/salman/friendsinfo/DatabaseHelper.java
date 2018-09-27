package com.example.salman.friendsinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "db_friends";
    public static final String tableName= "tbl_friends";
    public static final String col1= "ID";
    public static final String col2 = "NAME";
    public static final String col3 = "EMAIL";

    public  DatabaseHelper(Context context){
        super(context, databaseName, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+tableName+" ("+col1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+col2+" TEXT, "+col3+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+ tableName);
        onCreate(db);
    }

    public boolean insertData(String name, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(col2, name);
        contentValues.put(col3, email);

        long res = db.insert(tableName, null, contentValues);

        if(res==-1)
            return  false;
        else
            return true;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+tableName, null);
        return  res;
    }

    public void updateData(String id,  String name, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(col1, id);
        contentValues.put(col2, name);
        contentValues.put(col3, email);

        db.update(tableName, contentValues, "ID = ?", new String[] {id});
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(tableName, "ID = ?", new String[] {id});
    }


}
