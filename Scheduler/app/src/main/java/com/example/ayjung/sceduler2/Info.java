package com.example.ayjung.sceduler2;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

/**
 * Created by 정아연 on 17-06-21.
 */

public class Info extends AppCompatActivity implements Serializable {
    private int id;
    private String date;
    private String todo;


    public Info(int id,String date,String todo){
        this.id=id;
        this.date=date;
        this.todo=todo;

    }
    public int getId(){return id;}
    public String getDate(){return date;}
    public String getTodo(){return todo;}
    public void setDate(String date){this.date=date;}
    public void setTodo(String todo){this.todo=todo;}
}