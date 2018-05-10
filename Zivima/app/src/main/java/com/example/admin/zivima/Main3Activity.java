package com.example.admin.zivima;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v4.util.LogWriter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.logging.Logger;

public class Main3Activity extends AppCompatActivity {
    EditText etEmail,etPassword,uname,col,pnum;

    public static final String TAG = Main3Activity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        uname=(EditText)findViewById(R.id.editText);
        col=(EditText)findViewById(R.id.editText1);
        pnum=(EditText)findViewById(R.id.editText4);
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
    public void createAccount(){
        etEmail = (EditText) findViewById(R.id.editText7);
        etPassword = (EditText) findViewById(R.id.editText3);
        String email=etEmail.getText().toString();
        String password=etPassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(Main3Activity.this, "Authentication falied",
                                    Toast.LENGTH_SHORT).show();


                        }
                        else{
//                            String username=uname.getText().toString();
//                            String college=col.getText().toString();
//                            String phone=pnum.getText().toString();
//
//                            User user=new User();
//                            user.setUsername(username);
//                            user.setCollege(college);
//                            user.setPhone_num(phone);
//
//                            if(mAuth.getCurrentUser()!=null)
//                            {
//                                // save the user at UserNode under user UID
//                                mDatabase.child("UserNode").child(mAuth.getCurrentUser().getUid()).setValue(user, new DatabaseReference.CompletionListener() {
//                                    @Override
//                                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                                        if(databaseError==null)
//                                        {
//                                            Toast.makeText(Main3Activity.this, "Data is saved successfully",
//                                                    Toast.LENGTH_SHORT).show();
//                                            finish();
//                                        }
//                                    }
//                                });
//                            }

                            Toast.makeText(Main3Activity.this,
                                    "Registered Successfully",
                                    Toast.LENGTH_SHORT).show();
                            Intent intend=new Intent(getApplicationContext(),Ministrieslist.class);
                            intend.putExtra("Editname",uname.getText().toString().toUpperCase());
                            intend.putExtra("Editcollege",col.getText().toString().toUpperCase());
                            intend.putExtra("Editpnum",pnum.getText().toString());
                            intend.putExtra("Editemail",etEmail.getText().toString());
                            startActivity(intend);
                        }
                        // ...
                    }
                });
    }
    public void Ministry(View view){
        String button_text;
        button_text=((Button) view).getText().toString();
        if(button_text.equals("Register")) {

            createAccount();
        }
        }
}
