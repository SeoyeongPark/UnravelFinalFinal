package com.example.unravel.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Cameroon extends Fragment implements View.OnClickListener{


    public Cameroon() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View create4 = inflater.inflate(R.layout.fragment_cameroon, container, false);
        TextView CameroonCameroon = (TextView) create4.findViewById(R.id.InformationCameroon);
        CameroonCameroon.setOnClickListener(this);
        TextView CameroonCity = (TextView) create4.findViewById(R.id.CitiesCameroon);
        CameroonCity.setOnClickListener(this);

        return create4;
    }

    @Override
    public void onClick (View create4){
        if (create4.getId() == R.id.InformationCameroon) {
            AboutCameroon fragment6 = new AboutCameroon();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment6);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else if(create4.getId() == R.id.CitiesCameroon){
            CameroonCities fragment6 = new CameroonCities();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment6);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

}
