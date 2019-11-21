package com.example.set_a_goal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mBtnaddgoal;
    private Button mBtncalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtncalendar = findViewById(R.id.calendar);
        mBtnaddgoal=findViewById(R.id.btn_addgoal);
        mBtnaddgoal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this,AddContent.class);
                startActivity(intent);
            }
        });

        mBtncalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalendarView.class);
                startActivity(intent);
            }
        });
    }
}
