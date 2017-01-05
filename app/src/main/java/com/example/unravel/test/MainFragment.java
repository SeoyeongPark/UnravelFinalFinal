package com.example.unravel.test;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener{


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View create100= inflater.inflate(R.layout.fragment_main, container, false);
        Button Addtrip = (Button) create100.findViewById(R.id.main_button);
        Addtrip.setOnClickListener(this);
        return create100;
    }

    @Override
    public void onClick (View create100){
        if (create100.getId() == R.id.main_button) {
            Intent myIntent = new Intent(getActivity(), TripActivity.class);
            getActivity().startActivity(myIntent);
        }

    }



}
