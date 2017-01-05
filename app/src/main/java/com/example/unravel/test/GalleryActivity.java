package com.example.unravel.test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class GalleryActivity extends AppCompatActivity {


    private RecyclerView mGalleryList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Notes");

        mGalleryList = (RecyclerView) findViewById(R.id.gallery_row);


        mGalleryList.setHasFixedSize(true);

        mGalleryList.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {

        super.onStart();

        FirebaseRecyclerAdapter<Gallery, GalleryViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Gallery, GalleryViewHolder>(

                Gallery.class,
                R.layout.gallery_row,
                GalleryViewHolder.class,
                mDatabase


        ) {
            @Override
            protected void populateViewHolder(GalleryViewHolder viewHolder, Gallery model, int position) {

                final String trip_key = getRef(position).getKey();

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(), model.getImage());


                viewHolder.mView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent singleTripIntent = new Intent(GalleryActivity.this, SingleTripActivity.class);
                        singleTripIntent.putExtra("notes_id", trip_key);
                        startActivity(singleTripIntent);
                    }
                });
            }
        };

        mGalleryList.setAdapter(firebaseRecyclerAdapter);

    }


    public static class GalleryViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public GalleryViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setTitle(String title) {

            TextView trip_title = (TextView) mView.findViewById(R.id.trip_title);
            trip_title.setText(title);
        }

        public void setDesc(String desc) {

            TextView trip_text = (TextView) mView.findViewById(R.id.trip_text);
            trip_text.setText(desc);
        }

        public void setImage(Context ctn, String image) {

            ImageView trip_image = (ImageView) mView.findViewById(R.id.trip_image);
            Picasso.with(ctn).load(image).into(trip_image);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //  Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

            startActivity(new Intent(GalleryActivity.this, TripActivity.class));
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

        }


        return super.onOptionsItemSelected(item);
    }

    public void showDatePickerDialog(View v) {
        android.app.DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "DatePicker");
    }

}
