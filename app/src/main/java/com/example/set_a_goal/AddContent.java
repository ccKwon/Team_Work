package com.example.set_a_goal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddContent extends AppCompatActivity {
private TextView prompt;
private Button today_goal;
    private Button week_goal;
    private Button month_goal;
    private Button year_goal;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_content);
        View v=findViewById(R.id.prompt);
        v.getBackground().setAlpha(100);


        Button b1=findViewById(R.id.today_goal);
        v.getBackground().setAlpha(180);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View b1){
                Intent intent=new Intent(AddContent.this,editor_content.class);
                startActivity(intent);
            }
        });


    Button b2=findViewById(R.id.week_goal);
    v.getBackground().setAlpha(180);
    b2.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View b2){
            Intent intent=new Intent(AddContent.this,editor_content.class);
            startActivity(intent);
        }
    });



    Button b3=findViewById(R.id.month_goal);
    v.getBackground().setAlpha(180);
    b3.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View b3){
            Intent intent=new Intent(AddContent.this,editor_content.class);
            startActivity(intent);
        }
    });


    Button b4=findViewById(R.id.year_goal);
    v.getBackground().setAlpha(180);
    b4.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View b4){
            Intent intent=new Intent(AddContent.this,editor_content.class);
            startActivity(intent);
        }
    });

    }
}
