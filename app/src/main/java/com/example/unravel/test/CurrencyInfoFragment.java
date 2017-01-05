package com.example.unravel.test;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

//This fragment is for giving currency information. It supports countries and flags list based on currency.
public class CurrencyInfoFragment extends ListFragment {

    // getting list of countries and flags
    String[] countryname = {" ",
            "AUD (Australia)",
            "BGN (Bulgarian)",
            "CAD (Canada)",
            "CHF (Switzerland)",
            "CNY (China)",
            "CZK (Czech Republic)",
            "DKK (Denmark)",
            "EUR (Euro)","GBP (United Kingdom)","HKD (Hong kong)","HRK (Croatia)","HUF (Hungary)","IDR (Indonesia)","ILS (Israel)",
            "INR (India)","JPY (Japan)","KRW (South Korea)","MXN (Mexico)","MYR (Malaysia)","NOK (Norway)","NZD (New Zealand)",
            "PHP (Philippine)","PLN (Poland)","RON (Romania)","RUB (Russia)","SEK (Sweden)","SGD (Singapore)","THB (Thailand)",
            "TRY (Turkey)","USD (United States)","ZAR (South Africa)"
    } ;

    Integer[] flag = {
            R.drawable.australia,
            R.drawable.australia,
            R.drawable.bulgaria,
            R.drawable.canada,
            R.drawable.switzerland,
            R.drawable.chinaflag,
            R.drawable.czech_republic,
            R.drawable.denmark, R.drawable.european_union, R.drawable.united_kingdom, R.drawable.hong_kong, R.drawable.croatia, R.drawable.hungary,
            R.drawable.indonesia, R.drawable.israel, R.drawable.india, R.drawable.japan, R.drawable.south_korea, R.drawable.mexico,
            R.drawable.malaysia, R.drawable.norway, R.drawable.new_zealand, R.drawable.philippines, R.drawable.poland,
            R.drawable.romania, R.drawable.russia, R.drawable.sweden, R.drawable.singapore, R.drawable.thailand,
            R.drawable.turkey, R.drawable.usaflag, R.drawable.south_africa

    };

    ArrayList<HashMap<String,String>> curinfo=new ArrayList<HashMap<String,String>>();
    SimpleAdapter simpleAdapter;
    public CurrencyInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Add country name and flag for each country
        HashMap<String, String> map = new HashMap<String,String>();
        for(int i=0;i<countryname.length;i++)   {
            map=new HashMap<String, String>();
            map.put("Country Name",countryname[i]);
            map.put("Flag",Integer.toString(flag[i]));
            curinfo.add(map);
        }
        String[] from = {"Country Name","Flag"};
        int[] to = {R.id.txt, R.id.img};
        simpleAdapter =new SimpleAdapter(getActivity(), curinfo, R.layout.currency_flag_list, from, to);
        setListAdapter(simpleAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}


