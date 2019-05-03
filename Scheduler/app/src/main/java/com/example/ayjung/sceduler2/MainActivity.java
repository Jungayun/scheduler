package com.example.ayjung.sceduler2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static final int REQ_CODE3=3;
    public static final int REQ_CODE1=1;
    static Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button D_day = (Button) findViewById(R.id.d_day);
        D_day.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DdayList.class);
                startActivityForResult(intent,REQ_CODE1);
            }
        });
        CalendarView cal=(CalendarView) findViewById(R.id.android_calendar);
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                                        @Override
                                        public void onSelectedDayChange(CalendarView view, int year, int monthOfYear, int dayOfMonth) {
                                            calendar.set(year,monthOfYear,dayOfMonth);
                                            Intent intent2 = new Intent(getApplicationContext(), ToDoList.class);
                                            startActivityForResult(intent2,REQ_CODE3);
                                        }
                                    }
        );
    }
}
