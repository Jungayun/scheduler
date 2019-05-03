package com.example.ayjung.sceduler2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import static android.app.Activity.RESULT_CANCELED;
import static com.example.ayjung.sceduler2.MainActivity.calendar;

/**
 * Created by 정아연 on 17-06-21.
 */

public class DdayList_plus extends AppCompatActivity{
    DatePicker datePicker;
    Calendar dcalendar;
    Calendar calendar;
    int dYear;
    int dMonth;
    int dDay;
    int tYear;
    int tMonth;
    int tDay;
    private long d;
    private long t;
    private long r;
    private int resultNumber=0;
    String ddayresult;

    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.ddayplus);
        datePicker = (DatePicker) findViewById(R.id.datepicker);
        datePicker.init(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth(),new DatePicker.OnDateChangedListener(){

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String msg=String.format("%d / %d / %d",year,monthOfYear+1,dayOfMonth);
                Toast.makeText(DdayList_plus.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void Buttons(View view){
        switch (view.getId()){
            case R.id.todo_cancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.todo_ok:
                addDate();
                Intent intent=new Intent(this,DdayList.class);
                Bundle bundle=new Bundle();

                DatabaseHelperDDAY db=new DatabaseHelperDDAY(getApplicationContext(),"SCHEDULE",null,1);
                bundle.putSerializable("value",db.getToday(calendar.getTime()));
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addDate(){
        DatabaseHelperDDAY dbHelper = new DatabaseHelperDDAY(getApplicationContext(),"SCHEDULE",null,1);
        dYear=datePicker.getYear();
        dMonth=datePicker.getMonth()+1;
        dDay=datePicker.getDayOfMonth();
//        String ddayresult = String.format("%d년 %d월 %d일",datePicker.getYear(),datePicker.getMonth() + 1,datePicker.getDayOfMonth());

        dcalendar= Calendar.getInstance();
        dcalendar.set(dYear,dMonth,dDay);

        calendar=Calendar.getInstance();
        tYear=calendar.get(Calendar.YEAR);
        tMonth=calendar.get(Calendar.MONTH)+1;
        tDay=calendar.get(Calendar.DAY_OF_MONTH);

        d=dcalendar.getTimeInMillis();
        t=calendar.getTimeInMillis();
        r=(d-t)/(24*60*60*1000)-30;

        resultNumber=(int)r;

        if(resultNumber>=0){
            ddayresult=String.format("D-%d",resultNumber);
        }
        else{
            int absR=Math.abs(resultNumber);
            ddayresult=String.format("D+%d",absR);
        }


        EditText dday=(EditText) findViewById(R.id.ddayname);
        String ddayDetail=dday.getText().toString();
        dbHelper.insert(calendar.getTime(),ddayresult,ddayDetail);
        Toast.makeText(DdayList_plus.this,"입력완료",Toast.LENGTH_LONG).show();
    }
}
