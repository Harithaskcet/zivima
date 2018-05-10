package com.example.admin.zivima;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Ministrieslist extends AppCompatActivity {
    Spinner spinner;
    String[] SPINNERVALUES = {"Education and Welfare","Farmer Welfare","Health and Social Welfare"};
    String SpinnerValue;
    Button GOTO;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ministrieslist);

        spinner =(Spinner)findViewById(R.id.spinner1);

        GOTO = (Button)findViewById(R.id.button1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Ministrieslist.this, android.R.layout.simple_list_item_1, SPINNERVALUES);

        spinner.setAdapter(adapter);

        //Adding setOnItemSelectedListener method on spinner.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValue = (String)spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        GOTO.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                switch(SpinnerValue){

                    case "Education and Welfare":
                        intent = new Intent(Ministrieslist.this, Main4Activity.class);
                        intent.putExtra("Editname",getIntent().getStringExtra("Editname"));
                        intent.putExtra("Editcollege",getIntent().getStringExtra("Editcollege"));
                        intent.putExtra("Editpnum",getIntent().getStringExtra("Editpnum"));
                        intent.putExtra("Editemail",getIntent().getStringExtra("Editemail"));
                        intent.putExtra("Editloginemail",getIntent().getStringExtra("Editloginemail"));
                        startActivity(intent);
                        break;

                    case "Farmer Welfare":
                        intent = new Intent(Ministrieslist.this, FarmerWelfare.class);
                        intent.putExtra("Editname",getIntent().getStringExtra("Editname"));
                        intent.putExtra("Editcollege",getIntent().getStringExtra("Editcollege"));
                        intent.putExtra("Editpnum",getIntent().getStringExtra("Editpnum"));
                        intent.putExtra("Editemail",getIntent().getStringExtra("Editemail"));
                        intent.putExtra("Editloginemail",getIntent().getStringExtra("Editloginemail"));
                        startActivity(intent);
                        break;

                    case "Health and Social Welfare":
                        intent = new Intent(Ministrieslist.this, Health.class);
                        intent.putExtra("Editname",getIntent().getStringExtra("Editname"));
                        intent.putExtra("Editcollege",getIntent().getStringExtra("Editcollege"));
                        intent.putExtra("Editpnum",getIntent().getStringExtra("Editpnum"));
                        intent.putExtra("Editemail",getIntent().getStringExtra("Editemail"));
                        intent.putExtra("Editloginemail",getIntent().getStringExtra("Editloginemail"));
                        startActivity(intent);
                        break;


                }
            }
        });
    }
}
