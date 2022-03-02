package com.example.mislugares2022.modelo;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.mislugares2022.R;

public class Preferencias extends PreferenceFragmentCompat {


    /*
     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         addPreferencesFromResource(R.xml.preferencias);
     }
 */
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferencias, rootKey);
    }
}