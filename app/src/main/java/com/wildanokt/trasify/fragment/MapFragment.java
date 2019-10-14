package com.wildanokt.trasify.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.wildanokt.trasify.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers

                //set camera pos
                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(-7.952383, 112.614029))
                        .zoom(16)
                        .bearing(0)
                        .tilt(30)
                        .build();

                //move camera
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10, null);

//                for (int i = 0; i < 2; i++) {
//                    Trash trash = mTrashes.get(i);
//                    mMap.addMarker(new MarkerOptions()
//                            .position(new LatLng(trash.getLat(), trash.getLng()))
//                            .title(trash.getLokasi())
//                            .snippet("Organik : "+trash.getOrganik()+"%, Anorganik : "+trash.getAnorganik()+"%, Logam : "+trash.getLogam()+"%")
//                    );
//                }

//                mMap.addMarker(new MarkerOptions()
//                        .position(new LatLng(-7.953341, 112.614669))
//                        .title("Lokasi A")
//                        .snippet("Organik : 40%, Anorganik : 10%, Logam : 5%")
//                );
//
//                mMap.addMarker(new MarkerOptions()
//                        .position(new LatLng(-7.951762, 112.616007))
//                        .title("Lokasi B")
//                        .snippet("Organik : 50%, Anorganik : 10%, Logam : 1%")
//                );
//
//                mMap.addMarker(new MarkerOptions()
//                        .position(new LatLng(-7.950158, 112.613380))
//                        .title("Lokasi C")
//                        .snippet("Organik : 5%, Anorganik : 50%, Logam : 1%")
//                );
//
//                mMap.addMarker(new MarkerOptions()
//                        .position(new LatLng(-7.952682, 112.611325))
//                        .title("Lokasi D")
//                        .snippet("Organik : 30%, Anorganik : 5%, Logam : 20%")
//                );
            }
        });
    }

}
