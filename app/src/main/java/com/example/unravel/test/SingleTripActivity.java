package com.example.unravel.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class SingleTripActivity extends AppCompatActivity {

    private String mTrip_key = null;
    private DatabaseReference mDatabase;

    private ImageView mTripSingleImage;
    private TextView mTripSingleTitle;
    private TextView mTripSingleDesc;

    private FirebaseAuth mAuth;
    // private Button mSingleRemoveBtn;

Button takeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_trip);

        //String trip_key = getIntent().getExtras().getString("notes_id");
        //Toast.makeText(SingleTripActivity.this, trip_key, Toast.LENGTH_LONG).show();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Notes");

        mAuth = FirebaseAuth.getInstance();

        mTrip_key = getIntent().getExtras().getString("notes_id");

        mTripSingleImage = (ImageView) findViewById(R.id.singleTripimage);
        mTripSingleTitle = (TextView) findViewById(R.id.singleTripTitle);
        mTripSingleDesc = (TextView) findViewById(R.id.singleTripText);

        // mSingleRemoveBtn = (Button) findViewById(R.id.singleRemoveBtn);


        mDatabase.child(mTrip_key).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String trip_title = (String) dataSnapshot.child("title").getValue();
                String trip_text = (String) dataSnapshot.child("desc").getValue();
                String trip_image = (String) dataSnapshot.child("image").getValue();
                String trip_uid = (String) dataSnapshot.child("uid").getValue();

                mTripSingleTitle.setText(trip_title);
                mTripSingleDesc.setText(trip_text);

                Picasso.with(SingleTripActivity.this).load(trip_image).into(mTripSingleImage);

                //   if (mAuth.getCurrentUser().getUid().equals(trip_uid)) {

                //     mSingleRemoveBtn.setVisibility(View.VISIBLE);
                //}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


         takeBtn = (Button) findViewById(R.id.takeBtn);
        takeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final LinearLayout layout = (LinearLayout) findViewById(R.id.jingle);
                // Bitmap pic = takeScreenShot(layout);

                //   takeScreenShot();


                layout.post(new Runnable() {
                    @Override
                    public void run() {

                        Bitmap pic = takeScreenShot(layout);

                        // Bitmap pic = null;

                        try {
                            if (pic != null) {
                                saveScreenShot(pic);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("*image/jpeg");
                //  shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/unravel_trip.png"));
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory().getPath()));

                startActivity(Intent.createChooser(shareIntent, "Share Trip....."));

               Toast.makeText(SingleTripActivity.this, "Share nn Post", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //  Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        getMenuInflater().inflate(R.menu.single, menu);
        //  getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.add_trips) {

            Toast.makeText(this, "Add a New Trip", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(SingleTripActivity.this, TripActivity.class));
            //noinspection SimplifiableIfStatement
        } else if (id == R.id.nav_settings) {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();


            Log.d("MAuth", "Logout Clicked");
            try {
                Intent intents = new Intent(this, LogoutActivity.class);
                startActivity(intents);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (id == R.id.memories) {
            Toast.makeText(this, "Memories", Toast.LENGTH_SHORT).show();
            Intent intents = new Intent(this, GalleryActivity.class);
            startActivity(intents);

        } else if (id == R.id.delete_trips) {

            mDatabase.child(mTrip_key).removeValue();

            Toast.makeText(this, "Post Deleted", Toast.LENGTH_SHORT).show();


            Intent intents = new Intent(this, GalleryActivity.class);
            startActivity(intents);

        } else if (id == R.id.share_trips) {

            //   imageView = (ImageView) findViewById(R.id.activity_single_trip);

            // mDatabase.child(mTrip_key).removeValue();


            // String message = "Text I want to share.";
            //Intent share = new Intent(Intent.ACTION_SEND);
            //share.setType("text/plain");
            //share.putExtra(Intent.EXTRA_TEXT, message);

//            startActivity(Intent.createChooser(share, "Share From Unravel to... "));

            // Intent intents = new Intent(this, SingleTripActivity.class);
            //  startActivity(intents);
/*
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
           Uri screenshotUri = Uri.parse(path);

            sharingIntent.setType("image/png");
            sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
            startActivity(Intent.createChooser(sharingIntent, "Share image using")); */


            //  Bitmap bitmap = viewToBitmap(mTripSingleImage, mTripSingleImage.getWidth(), mTripSingleImage.getHeight());

            // mTripSingleImage.buildDrawingCache();
            //Bitmap bitmap = mTripSingleImage.getDrawingCache();

            //  BitmapDrawable drawable = (BitmapDrawable) mTripSingleImage.getDrawable();
            //Bitmap bitmap = drawable.getBitmap();


            // }


            //  Intent shareIntent = new Intent(Intent.ACTION_SEND);
            // shareIntent.setType("*image/jpeg");
            //ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            //bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            //File file = new File(Environment.getExternalStorageDirectory() + File.separator+ "ImageDemo.jpg");

            //try {
            //  file.createNewFile();
            //FileOutputStream fileOutputStream = new FileOutputStream(file);
                /*fileOutputStream.write(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/ImageDemo.jpg"));
            startActivity(Intent.createChooser(shareIntent, "Share Trip....."));

*/
           final LinearLayout layout = (LinearLayout) findViewById(R.id.jingle);
           // Bitmap pic = takeScreenShot(layout);

         //   takeScreenShot();


            layout.post(new Runnable() {
                @Override
                public void run() {
                    Bitmap pic = takeScreenShot(layout);

                //   takeScreenshot();
                    //  Bitmap pic = null;

                    try {
                        if (pic != null) {
                            saveScreenShot(pic);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("*image/jpeg");
            //  shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/unravel_trip.png"));
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory().getPath()));

            startActivity(Intent.createChooser(shareIntent, "Share Trip....."));

         /*   Bitmap bitmap;
            View v1 = findViewById(R.id.jingle);// get ur root view id
            v1.setDrawingCacheEnabled(true);
            bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);


            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
            File f = new File(Environment.getExternalStorageDirectory()
                    + File.separator + "test.jpg")
            f.createNewFile();

            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            fo.close();
*/
            //takeScreenshot();
            Toast.makeText(this, "Share Post", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }


        private Bitmap takeScreenShot(View v) {
            Bitmap screenshot = null;
            try {
                int width = v.getMeasuredWidth();
                int height = v.getMeasuredHeight();

                screenshot = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); //Create Bitmap

                //Draw To Canvas
                Canvas c = new Canvas(screenshot);
                v.draw(c);

            } catch (Exception e) {
                e.printStackTrace();

            }
            return screenshot;
        }

            private void saveScreenShot(Bitmap bm)
    {
        ByteArrayOutputStream bao = null;
        File file = null;

        try{
            //Compress
            bao = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, bao);

            //Write to SD CArd
            file = new File(Environment.getExternalStorageDirectory()+File.separator+"unravel_trip.jpeg");
            file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bao.toByteArray());
            fos.close();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }



/*
    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/PICTURES/Screenshots/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);
            File imageFile = new File(mPath);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();


            MediaScannerConnection.scanFile(this,
                    new String[]{imageFile.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("ExternalStorage", "Scanned " + path + ":");
                            Log.i("ExternalStorage", "-> uri=" + uri);
                        }
                    });

              openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }
    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }
*/
}


































