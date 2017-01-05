package com.example.unravel.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class USA extends Fragment implements View.OnClickListener{

    public USA() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View create3 = inflater.inflate(R.layout.fragment_u, container, false);
        TextView USAUSA = (TextView) create3.findViewById(R.id.InformationUSA);
        USAUSA.setOnClickListener(this);
        TextView USACity = (TextView) create3.findViewById(R.id.CitiesUSA);
        USACity.setOnClickListener(this);
        return create3;
    }
    @Override
    public void onClick (View create3) {
        if (create3.getId() == R.id.InformationUSA) {
            AboutUSA fragment4 = new AboutUSA();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment4);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else if(create3.getId() == R.id.CitiesUSA){
            USACities fragment4 = new USACities();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment4);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }

}
