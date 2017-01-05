package com.example.unravel.test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class TripActivity extends AppCompatActivity {
    private ImageButton mSelectImage;
    private EditText mTripTitle;
    private EditText mTripNote;

    private Button mSaveBtn;
    private Uri mImageUri = null;

    private static final int GALLERY_REQUEST = 1;

    private StorageReference mStorage;
    private DatabaseReference mDatabase;

    private ProgressDialog mProgress;

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();


        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Notes");

        mSelectImage = (ImageButton) findViewById(R.id.imageButton);

        mTripTitle = (EditText) findViewById(R.id.Title);
        mTripNote = (EditText) findViewById(R.id.Note);

        mSaveBtn = (Button) findViewById(R.id.Save);

        mProgress = new ProgressDialog(this);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });


        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startSaving();
            }
        });
    }

    private void startSaving() {

        mProgress.setMessage("Unravel is Uploading your Trip ....");
        final String title_val = mTripTitle.getText().toString().trim();
        final String desc_val = mTripNote.getText().toString().trim();

        if (!TextUtils.isEmpty(title_val) || !TextUtils.isEmpty(desc_val) || mImageUri != null) {

            mProgress.show();

            if (mImageUri == null) {

                DatabaseReference newTrip = mDatabase.push();

                newTrip.child("title").setValue(title_val);
                newTrip.child("desc").setValue(desc_val);

                newTrip.child("uid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());

                mProgress.dismiss();

                Toast.makeText(TripActivity.this, "Upload Done.", Toast.LENGTH_LONG).show();

                startActivity(new Intent(TripActivity.this, GalleryActivity.class));

                finish();

            }
            else {
                mProgress.dismiss();
                Toast.makeText(TripActivity.this, "Upload Done.", Toast.LENGTH_LONG).show();

                startActivity(new Intent(TripActivity.this, GalleryActivity.class));



                StorageReference filepath = mStorage.child("Unravel_Images").child(mImageUri.getLastPathSegment());

                filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {


                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Uri downloadUrl = taskSnapshot.getDownloadUrl();

                        DatabaseReference newTrip = mDatabase.push();

                        newTrip.child("title").setValue(title_val);
                        newTrip.child("desc").setValue(desc_val);
                        newTrip.child("image").setValue(downloadUrl.toString());
                        newTrip.child("uid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());

                        mProgress.dismiss();

                        Toast.makeText(TripActivity.this, "Upload Done.", Toast.LENGTH_LONG).show();

                        startActivity(new Intent(TripActivity.this, GalleryActivity.class));

                        finish();
                    }


                });
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            mImageUri = data.getData();
            mSelectImage.setImageURI(mImageUri);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //  Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        //getMenuInflater().inflate(R.menu.main, menu);
      //  getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }


}
