package com.example.admin.zivima;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    EditText login,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    }
    public void nextLayout(View view){
        login=(EditText) findViewById(R.id.editText);
        pwd=(EditText) findViewById(R.id.editText2);
        String loginid=login.getText().toString();
        String pass=pwd.getText().toString();
        String button_text;
        button_text=((TextView) view).getText().toString();
        if(button_text.equalsIgnoreCase("Login")){
            if(loginid.equals("admin@skcet.ac.in")&& pass.equals("admin")) {
                Intent intent = new Intent(this, ViewUploadsActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"Incorrect Password or login ID",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
