package com.example.admin.zivima;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FarmerWelfare extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_welfare);
    }
    public void submit(View view){
        String button_text;
        button_text=((TextView) view).getText().toString();
        if(button_text.equalsIgnoreCase("Attach File")){
            Intent intent = new Intent(this,Education.class);
            intent.putExtra("Editname",getIntent().getStringExtra("Editname"));
            intent.putExtra("Editcollege",getIntent().getStringExtra("Editcollege"));
            intent.putExtra("Editpnum",getIntent().getStringExtra("Editpnum"));
            intent.putExtra("Editemail",getIntent().getStringExtra("Editemail"));
            intent.putExtra("Editloginemail",getIntent().getStringExtra("Editloginemail"));
            startActivity(intent);
        }
    }
}
