package com.example.admin.zivima;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class Education extends AppCompatActivity implements View.OnClickListener{
    //this is the pic pdf code used in file chooser
    final static int PICK_PDF_CODE = 2342;

    //these are the views
    TextView textViewStatus;
    EditText editTextFilename;
    ProgressBar progressBar;

    //the firebase objects for storage and database
    StorageReference mStorageReference;
    DatabaseReference mDatabaseReference;
    List<Upload> uploadList;
    EditText uname,col,pnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        //getting firebase objects
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        //getting the views
        textViewStatus = (TextView) findViewById(R.id.textViewStatus);
        editTextFilename = (EditText) findViewById(R.id.editTextFileName);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

//        LayoutInflater factory=getLayoutInflater();
//        View regisText=factory.inflate(R.layout.activity_main3,null);
//
//
//        uname=(EditText)regisText.findViewById(R.id.editText);
//        col=(EditText)regisText.findViewById(R.id.editText1);
//        pnum=(EditText)regisText.findViewById(R.id.editText4);


        //attaching listeners to views
        findViewById(R.id.buttonUploadFile).setOnClickListener(this);
       // findViewById(R.id.textViewUploads).setOnClickListener(this);
    }

    //this function will get the pdf from the storage
    private void getPDF() {
        //for greater than lolipop versions we need the permissions asked on runtime
        //so if the permission is not available user will go to the screen to allow storage permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));

            startActivity(intent);
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            Uri mydir = Uri.parse("/storage/emulated/0/GBWhatsApp/Media/GBWhatsApp Documents/");
//            intent.setDataAndType(mydir, "*/*");
//            startActivity(intent);
           // return;
        }

        //creating an intent for file chooser
        Intent intent = new Intent();
        intent.setType("application/pdf");
        //intent.setType("application/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PDF_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //when the user choses the file
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //if a file is selected
            if (data.getData() != null) {
                //uploading the file
                uploadFile(data.getData());
              //  next();
            }else{
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //this method is uploading the file
    //the code is same as the previous tutorial
    //so we are not explaining it
    private void uploadFile(Uri data) {
        progressBar.setVisibility(View.VISIBLE);
        StorageReference sRef = mStorageReference.child(Constants.STORAGE_PATH_UPLOADS + System.currentTimeMillis() + ".pdf");

        sRef.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                        progressBar.setVisibility(View.GONE);
                        textViewStatus.setText("File Uploaded Successfully");

//                        Upload upload = new Upload(editTextFilename.getText().toString().toUpperCase(), taskSnapshot.getDownloadUrl().toString(),getIntent().getStringExtra("Editname"),getIntent().getStringExtra("Editcollege"),getIntent().getStringExtra("Editpnum"),getIntent().getStringExtra("Editemail"));
//                        mDatabaseReference.child(mDatabaseReference.push().getKey()).setValue(upload);
                        if(getIntent().getStringExtra("Editcollege")==null) {

                            uploadList=new ArrayList<Upload>();
                            mDatabaseReference=FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

                            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                        Upload upload = postSnapshot.getValue(Upload.class);

                                        uploadList.add(upload);
                                    }


                                }



                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            Toast.makeText(getApplicationContext(),uploadList.size()+" is the size ",Toast.LENGTH_SHORT).show();
                            for(int i=0;i<uploadList.size();i++) {
                                Toast.makeText(getApplication(),getIntent().getStringExtra("Editloginemail")+" "+uploadList.get(i).getEmail(),Toast.LENGTH_SHORT).show();
                                if(uploadList.get(i).getEmail().equals(getIntent().getStringExtra("Editloginemail"))){

                                    Upload upload =new Upload(editTextFilename.getText().toString().toUpperCase(),taskSnapshot.getDownloadUrl().toString(),uploadList.get(i).getUsername().toString(),uploadList.get(i).getCollege().toString(),uploadList.get(i).getPhone(),uploadList.get(i).getEmail());
                                    mDatabaseReference.child(mDatabaseReference.push().getKey()).setValue(upload);

                                }
                            }
                        }
                        else{
                            Toast.makeText(getApplication(),getIntent().getStringExtra("Editcollege")+" is the college in else ",Toast.LENGTH_SHORT).show();
                            Upload upload = new Upload(editTextFilename.getText().toString().toUpperCase(), taskSnapshot.getDownloadUrl().toString(),getIntent().getStringExtra("Editname"),getIntent().getStringExtra("Editcollege"),getIntent().getStringExtra("Editpnum"),getIntent().getStringExtra("Editemail"));
                            mDatabaseReference.child(mDatabaseReference.push().getKey()).setValue(upload);

                        }
                        next();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        textViewStatus.setText((int) progress + "% Uploading...");
                    }
                });
//        try {
//            Thread.sleep(1000);
//            next();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
public  void next(){
    //Toast.makeText(this,getIntent().getStringExtra("Editcollege")+" is the college ",Toast.LENGTH_SHORT).show();
   // startActivity(new Intent(getApplicationContext(),MainActivity.class));
}
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonUploadFile:
                getPDF();
                break;
//            case R.id.textViewUploads:
//                startActivity(new Intent(this, ViewUploadsActivity.class));
//                break;
        }
    }
}
