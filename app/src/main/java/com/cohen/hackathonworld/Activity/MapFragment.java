package com.cohen.hackathonworld.Activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;

import com.cohen.hackathonworld.Manager.MyDBManager_MapFragment;
import com.cohen.hackathonworld.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment {


    private GoogleMap googleMap;

    private MyDBManager_MapFragment myDBManager_mapFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize view
        View view=inflater.inflate(R.layout.fragment_map, container, false);

        // Initialize map fragment
        SupportMapFragment supportMapFragment=(SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        myDBManager_mapFragment = new MyDBManager_MapFragment();

        myDBManager_mapFragment.setCallBack_mapFragmentProtocol(callBack_mapFragmentProtocol);



        // Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MapFragment.this.googleMap = googleMap;
            }
        });

        myDBManager_mapFragment.ShowClassRoomsOnMap();



        return view;
    }
    public void markerLocationUI(double lat, double log, String className){
        LatLng location = new LatLng(lat, log);
        // Initialize marker options
        MarkerOptions markerOptions=new MarkerOptions();
        // Set position of marker
        markerOptions.position(location);
        // Set title of marker
        markerOptions.title(className);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,17));
        googleMap.addMarker(markerOptions);
    }

    CallBack_MapFragmentProtocol callBack_mapFragmentProtocol = new CallBack_MapFragmentProtocol() {
        @Override
        public void markerLocation(double lat, double log, String className) {
            markerLocationUI(lat, log, className);
        }
    };
}