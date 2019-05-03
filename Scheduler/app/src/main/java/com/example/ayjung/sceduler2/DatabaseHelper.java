package com.example.ayjung.sceduler2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 정아연 on 17-06-21.
 */

class DatabaseHelper extends SQLiteOpenHelper {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public DatabaseHelper(Context applicationContext, String schedule, SQLiteDatabase.CursorFactory factory, int i) {
        super(applicationContext, schedule, factory, i);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SCHEDULE (_id INTEGER PRIMARY KEY AUTOINCREMENT"+",date TEXT,todo TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void insert(Date date, String todo){
        SQLiteDatabase db=getWritableDatabase();
        String d=sdf.format(date);
        ContentValues values=new ContentValues();
        values.put("date",d);
        values.put("todo",todo);
        db.insert("SCHEDULE",null,values);
        db.close();
    }
    public void delete(ArrayList<Integer> ids){
        SQLiteDatabase db=getWritableDatabase();
        for(int n=0;n<ids.size();n++){
            db.execSQL("DELETE FROM SCHEDULE WHERE _id = " +ids.get(n) +";");
        }
    }
    public ArrayList<Info> getToday(Date date){
        String d=sdf.format(date);
        String condition="date(date) == '"+d+"'";
        return getResult(condition);
    }
    private ArrayList<Info> getResult(String condition){
        ArrayList<Info> infos=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM SCHEDULE WHERE "+condition,null);

        while(cursor.moveToNext()){
            Info info;
            int id=cursor.getInt(0);
            String date = cursor.getString(1);
            String todo = cursor.getString(2);

            info= new Info(id,date,todo);
            infos.add(info);
        }
        db.close();
        return infos;
    }
}
