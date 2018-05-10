package com.example.admin.zivima;

import android.app.ProgressDialog;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.view.View;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {

    EditText etEmail,etPassword;
    public static final String TAG = Main2Activity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    public void signIn(){
        etEmail = (EditText) findViewById(R.id.editText);
        etPassword = (EditText) findViewById(R.id.editText2);
        String email=etEmail.getText().toString();
        String password=etPassword.getText().toString();
        int c=1;
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(Main2Activity.this, "Authentication falied",
                                    Toast.LENGTH_SHORT).show();

                        }

                        else{
                            Toast.makeText(Main2Activity.this,
                                    "Logged Successfully",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),Ministrieslist.class);

                            intent.putExtra("Editloginemail",etEmail.getText().toString());

                            startActivity(intent);
                          //  startActivity(new Intent(getApplicationContext(),Ministrieslist.class));
                        }
                    }

                });
    }
    public void Page(View view){
        String button_text;
        button_text=((Button) view).getText().toString();
        if(button_text.equals("Login")) {
            //Intent intent = new Intent(this, Main4Activity.class);

            signIn();
        }
    }
    public void Register(View view){
        String button_text;
        button_text=((TextView) view).getText().toString();
        if(button_text.equals("Create a New Account")){
            Intent intent = new Intent(this,Main3Activity.class);
            startActivity(intent);
        }
    }
}
