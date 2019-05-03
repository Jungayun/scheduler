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
 * Created by 정아연 on 17-06-22.
 */

public class DatabaseHelperDDAY extends SQLiteOpenHelper{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public DatabaseHelperDDAY(Context applicationContext, String schedule, SQLiteDatabase.CursorFactory factory, int i) {
        super(applicationContext, schedule, factory, i);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SCHEDULE (_id INTEGER PRIMARY KEY AUTOINCREMENT"+",date Text, dday TEXT, ddayname TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void insert(Date date, String ddayresult, String ddayname){
        SQLiteDatabase db=getWritableDatabase();
        String d=sdf.format(date);
//        String dd=sdf.format(dday);
        ContentValues values=new ContentValues();
        values.put("date",d);
        values.put("dday",ddayresult);
        values.put("ddayname",ddayname);
        db.insert("SCHEDULE",null,values);
        db.close();
    }
    public void delete(ArrayList<Integer> ids){
        SQLiteDatabase db=getWritableDatabase();
        for(int n=0;n<ids.size();n++){
            db.execSQL("DELETE FROM SCHEDULE WHERE _id = " +ids.get(n) +";");
        }
    }
    public ArrayList<DInfo> getToday(Date date){
        String d=sdf.format(date);
        String condition="date(date) == '"+d+"'";
        return getResult(condition);
    }
    private ArrayList<DInfo> getResult(String condition){
        ArrayList<DInfo> Dinfos=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM SCHEDULE WHERE "+condition,null);

        while(cursor.moveToNext()){
            DInfo info;
            int id=cursor.getInt(0);
            String date = cursor.getString(1);
            String dday = cursor.getString(2);
            String ddayname= cursor.getString(3);

            info= new DInfo(id,date,dday,ddayname);
            Dinfos.add(info);
        }
        db.close();
        return Dinfos;
    }
}
