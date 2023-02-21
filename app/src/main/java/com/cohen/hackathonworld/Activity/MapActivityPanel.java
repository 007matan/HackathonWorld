package com.cohen.hackathonworld.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cohen.hackathonworld.R;

public class MapActivityPanel extends AppCompatActivity {

    private MapFragment fragment_maps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_activity_map);

        fragment_maps = new MapFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.panel_LAY_map, fragment_maps).commit();
    }
}