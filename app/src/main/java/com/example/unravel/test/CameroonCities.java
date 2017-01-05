package com.example.unravel.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CameroonCities extends Fragment implements View.OnClickListener{

    public CameroonCities() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View create5 = inflater.inflate(R.layout.fragment_cameroon_cities, container, false);
        TextView YYY = (TextView) create5.findViewById(R.id.textView11);
        YYY.setOnClickListener(this);
        return create5;
    }

    @Override
    public void onClick (View create5){
        if(create5.getId()== R.id.textView11){
            Yaounde fragment6 = new Yaounde();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment6);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

}
