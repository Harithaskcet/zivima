package com.example.admin.zivima;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void GotoSecond(View view){
        String button_text;
        button_text=((Button) view).getText().toString();
        if(button_text.equals("Student")){
            Intent intent = new Intent(this,Main2Activity.class);
            startActivity(intent);
        }
        else if(button_text.equals("Admin")){
            Intent intent = new Intent(this,AdminLogin.class);
            startActivity(intent);
        }
    }
}
