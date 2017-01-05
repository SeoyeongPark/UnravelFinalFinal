package com.example.unravel.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.widget.CompoundButton;
import android.widget.Switch;

public class NightmodeActivity extends AppCompatActivity {

    private Switch nightMode;
    private boolean defaultNightMode = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nightmode);


        nightMode = (Switch) findViewById(R.id.nightmode);

        final SharedPreferences settings = getSharedPreferences("nightMode", 0);

        //set default theme
        defaultNightMode = settings.getBoolean("nightMode", false);
        if(!defaultNightMode) { nightMode.setChecked(true);  }
        else {nightMode.setChecked(false);}



        nightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(!nightMode.isChecked())
                {
                    //if switch is off, set daymode
                    AppCompatDelegate
                            .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("nightMode", false);
                    editor.commit();

                }
                else
                {
                    //if switch is on, set nightmode
                    AppCompatDelegate
                            .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("nightMode", true);
                    editor.commit();
                }
            }

        });
    }

    //retain state of switch
    @Override
    public void onPause() {
        super.onPause();
        save(nightMode.isChecked());
    }

    @Override
    public void onResume() {
        super.onResume();
        nightMode.setChecked(load());
    }

    private void save(final boolean isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("check", isChecked);
        editor.commit();
    }

    private boolean load() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("check", false);
    }
}
