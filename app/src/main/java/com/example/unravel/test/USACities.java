package com.example.unravel.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class USACities extends Fragment implements View.OnClickListener{

    public USACities() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View create7 = inflater.inflate(R.layout.fragment_usacities, container, false);
        TextView AAA = (TextView) create7.findViewById(R.id.textView11);
        AAA.setOnClickListener(this);
        return create7;
    }

    @Override
    public void onClick (View create7){
        if(create7.getId()== R.id.textView11){
            NYC fragment7 = new NYC();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment7);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

}
