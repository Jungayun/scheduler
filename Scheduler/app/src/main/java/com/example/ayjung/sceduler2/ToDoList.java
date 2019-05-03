package com.example.ayjung.sceduler2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.ayjung.sceduler2.MainActivity.calendar;

/**
 * Created by 정아연 on 17-06-21.
 */

public class ToDoList extends AppCompatActivity {
    DatabaseHelper db;
    T_ListViewAdapter Adapter;
    ListView listView;

    private static final int REQ_CODE1=1;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist_main);
        ImageButton btn = (ImageButton) findViewById(R.id.todo_plus);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ToDoListplus.class);
                startActivityForResult(intent, REQ_CODE1);
            }
        });
        showEditList();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_CANCELED){
            return;
        }
        switch(requestCode){
            case REQ_CODE1:
                ListView listView=(ListView) findViewById(R.id.todo_list);
                db=new DatabaseHelper(getApplicationContext(),"SCHEDULE",null,1);
                Bundle bundle=data.getExtras();
                ArrayList<Info> infos=(ArrayList<Info>)bundle.getSerializable("value");
                Adapter=new T_ListViewAdapter(this,infos);
                listView.setAdapter(Adapter);
                break;
        }

    }
    public void showEditList(){
        db=new DatabaseHelper(this,"SCHEDULE",null,1);
        Adapter=new T_ListViewAdapter(this,db.getToday(calendar.getTime()));
        listView=(ListView) findViewById(R.id.todo_list);
        listView.setAdapter(Adapter);
        listView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
    }
    public void delete(View view){
        db.delete(Adapter.ids);
        showEditList();
    }
}