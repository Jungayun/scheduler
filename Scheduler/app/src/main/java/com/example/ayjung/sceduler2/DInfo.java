package com.example.ayjung.sceduler2;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

/**
 * Created by 정아연 on 17-06-22.
 */

public class DInfo extends AppCompatActivity implements Serializable{
    private int id;
    private String date;
    private String dday;
    private String ddayname;

    public DInfo(int id,String date,String dday,String ddayname){
        this.id=id;
        this.date=date;
        this.dday=dday;
        this.ddayname=ddayname;
    }
    public int getId(){return id;}
    public String getDate(){return date;}
    public String getDday(){return dday;}
    public String getDdayname(){return ddayname;}
    public void setDday(String date){this.dday=dday;}
    public void setDdayname(String todo){this.ddayname=ddayname;}
}


