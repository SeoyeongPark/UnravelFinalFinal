package com.example.unravel.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Countrylist extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public Countrylist() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View create = inflater.inflate(R.layout.fragment_countrylist, container, false);

        TextView CameroonText= (TextView) create.findViewById(R.id.textView8);
        CameroonText.setOnClickListener(this);
        ImageView CameroonI = (ImageView) create.findViewById(R.id.imageView3);
        CameroonI.setOnClickListener(this);

        TextView KoreaText= (TextView) create.findViewById(R.id.textView9);
        KoreaText.setOnClickListener(this);
        ImageView KoreaI = (ImageView) create.findViewById(R.id.imageView4);
        KoreaI.setOnClickListener(this);

        TextView USAText = (TextView) create.findViewById(R.id.textView10);
        USAText.setOnClickListener(this);
        ImageView USAI = (ImageView) create.findViewById(R.id.imageView5);
        USAI.setOnClickListener(this);

        return create;
    }


    @Override
    public void onClick(View create) {
        if ((create.getId() == R.id.textView8) || (create.getId() == R.id.imageView3)) {
            Cameroon fragment1 = new Cameroon();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment1);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if ((create.getId() == R.id.imageView4) || (create.getId() == R.id.textView9)) {
            Korea fragment2 = new Korea();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment2);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if ((create.getId() == R.id.imageView5) || (create.getId() == R.id.textView10)) {
            USA fragment3 = new USA();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment3);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }}
