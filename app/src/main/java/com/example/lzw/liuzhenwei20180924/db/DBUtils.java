package com.example.lzw.liuzhenwei20180924.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBUtils {

    private final SQLiteDatabase db;

    public  DBUtils(Context context){

        DBHelper dbHelper = new DBHelper(context,"news.db",null,1);
       db = dbHelper.getWritableDatabase();

    }

    public  void  insert(String data){
        ContentValues values = new ContentValues();
        values.put("data",data);
        db.insert("news",null,values);

    }
    public  String  query(){
        Cursor cursor = db.rawQuery("select data from news", null);
        String data="";
        while (cursor.moveToNext()){

           data = cursor.getString(cursor.getColumnIndex("data"));

        }
         return  data;
    }

}
