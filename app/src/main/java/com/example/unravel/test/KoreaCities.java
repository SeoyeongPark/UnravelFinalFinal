package com.example.unravel.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class KoreaCities extends Fragment implements View.OnClickListener{

    public KoreaCities() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View create6 = inflater.inflate(R.layout.fragment_korea_cities, container, false);
        TextView XXX = (TextView) create6.findViewById(R.id.textView11);
        XXX.setOnClickListener(this);
        return create6;
    }

    @Override
    public void onClick (View create6){
        if(create6.getId()== R.id.textView11){
            Seoul fragment6 = new Seoul();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment6);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
