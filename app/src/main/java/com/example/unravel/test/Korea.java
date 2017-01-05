package com.example.unravel.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Korea extends Fragment implements View.OnClickListener{

    public Korea() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View create2 = inflater.inflate(R.layout.fragment_korea, container, false);
        TextView KoreaKorea = (TextView) create2.findViewById(R.id.InformationKorea);
        KoreaKorea.setOnClickListener(this);
        TextView KoreaCity =(TextView) create2.findViewById(R.id.CitiesKorea);
        KoreaCity.setOnClickListener(this);
        return create2;
    }

    @Override
    public void onClick (View create2){
        if (create2.getId() == R.id.InformationKorea) {
            AboutKorea fragment5 = new AboutKorea();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment5);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else if(create2.getId() == R.id.CitiesKorea) {
            KoreaCities fragment5 = new KoreaCities();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment5);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

}
