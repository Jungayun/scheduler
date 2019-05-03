package com.example.ayjung.sceduler2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.ayjung.sceduler2.MainActivity.calendar;

/**
 * Created by 정아연 on 17-06-22.
 */

public class ToDoListplus extends AppCompatActivity {
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.todolist_plus);
    }
    public void Buttons(View view){
        switch (view.getId()){
            case R.id.todo_cancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.todo_ok:
                addDate();
                Intent intent=new Intent(this,ToDoList.class);
                Bundle bundle=new Bundle();

                DatabaseHelper db=new DatabaseHelper(getApplicationContext(),"SCHEDULE",null,1);
                bundle.putSerializable("value",db.getToday(calendar.getTime()));
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
    private void addDate(){
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext(),"SCHEDULE",null,1);
        EditText todo=(EditText) findViewById(R.id.todo);
        String todoDetail=todo.getText().toString();
        dbHelper.insert(calendar.getTime(),todoDetail);
        Toast.makeText(ToDoListplus.this,"입력완료",Toast.LENGTH_LONG).show();
    }
}
