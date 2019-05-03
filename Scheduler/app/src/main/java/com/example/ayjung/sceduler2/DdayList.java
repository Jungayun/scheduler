package com.example.ayjung.sceduler2;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.ayjung.sceduler2.MainActivity.REQ_CODE1;
import static com.example.ayjung.sceduler2.MainActivity.calendar;

/**
 * Created by 정아연 on 17-06-21.
 */

public class DdayList extends AppCompatActivity{
    DatabaseHelperDDAY db;
    D_ListViewAdapter Adapter;
    ListView listView;

    private static final int REQ_CODE=1;
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.dday_main);
        ImageButton btn = (ImageButton) findViewById(R.id.dday_plus);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),DdayList_plus.class);
                startActivityForResult(intent,REQ_CODE1);
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
                ListView listView=(ListView) findViewById(R.id.dday_list);
                db=new DatabaseHelperDDAY(getApplicationContext(),"SCHEDULE",null,1);
                Bundle bundle=data.getExtras();
                ArrayList<DInfo> Dinfos=(ArrayList<DInfo>)bundle.getSerializable("value");
                Adapter=new D_ListViewAdapter(this,Dinfos);
                listView.setAdapter(Adapter);
                break;
        }
    }
    public void showEditList(){
        db=new DatabaseHelperDDAY(this,"SCHEDULE",null,1);
        Adapter=new D_ListViewAdapter(this,db.getToday(calendar.getTime()));
        listView=(ListView) findViewById(R.id.dday_list);
        listView.setAdapter(Adapter);
        listView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
    }
    public void delete(View view){
        db.delete(Adapter.ids);
        showEditList();
    }
}
