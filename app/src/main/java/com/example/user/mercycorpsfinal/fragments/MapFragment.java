package com.example.user.mercycorpsfinal.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.mercycorpsfinal.R;
import com.example.user.mercycorpsfinal.activity.CallListActivity;
import com.example.user.mercycorpsfinal.model.Contact;
import com.example.user.mercycorpsfinal.model.LatLon;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {
    GoogleMap mgoogleMap;
    MapView mMapView;
    View mView;
    String TAG = "TAG";
    ArrayList<LatLon> latLons;
    ArrayList<Contact> contacts;
    Contact contact;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_map, container, false);
        return mView;


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = (MapView) mView.findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);

        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mgoogleMap = googleMap;
        mgoogleMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(LayoutInflater.from(getContext())));

        //Data prepare

        prepareData();

        for (LatLon latlon : latLons
                ) {
            LatLng location = new LatLng(latlon.getLat(), latlon.getLon());
            mgoogleMap.addMarker(new MarkerOptions().position(location).title(latlon.getName()).snippet(latlon.getLocation()));
            mgoogleMap.moveCamera(CameraUpdateFactory.newLatLng(location));


        }
        mgoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(28.8318, 80.51835), 10.0f));
        mgoogleMap.setOnInfoWindowClickListener(this);


    }



    @Override
    public void onInfoWindowClick(Marker marker) {
        Log.e("TAG", "onInfoWindowClick: hello");
        String[] str = marker.getId().split("m");
        LatLon l1 = latLons.get(Integer.parseInt(str[1]));

        Intent intent=new Intent(getContext(),CallListActivity.class);
        intent.putExtra("rumi",(Serializable)l1);
        startActivity(intent);
    }

    private class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private LayoutInflater inflater;
        TextView name, location;
        View markerView;
        ViewGroup windowContainer;

        CustomInfoWindowAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(final Marker marker) {
            markerView = inflater.inflate(R.layout.custominfowindow, null);
            //markerView.setLayoutParams(new ViewGroup.LayoutParams(350, 200));
            windowContainer = (ViewGroup) markerView.findViewById(R.id.window_container);
            name = (TextView) markerView.findViewById(R.id.name);
            location = (TextView) markerView.findViewById(R.id.location);
            String[] str = marker.getId().split("m");
            LatLon l1 = latLons.get(Integer.parseInt(str[1]));
            //Log.e("TAG", "getInfoContents:id is "+marker.getId() );
            name.setText(l1.getName());
            location.setText(l1.getLocation());
            //contact.setText(l1.getContacts().get(0).toString());
            return markerView;
        }
    }

    public void prepareData() {
        latLons = new ArrayList<LatLon>();
        contacts=new ArrayList<Contact>();
        contact=new Contact("Ashika Singh","9806457519");
        contacts.add(contact);
        LatLon latLon = new LatLon("Sonbhara River Gauge", "Highway Pool, Jhalari", 28.90224,80.34897, contacts);
        latLons.add(0, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("Krishna Lal Rana","9805741073");
        contacts.add(contact);
        latLon = new LatLon("Banara Rain Gauge", "Banara, Kanchanpur", 28.88453, 80.39783, contacts);
        latLons.add(1, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("Madan Raj Bhatta","091-690074");
        contacts.add(contact);
        latLon = new LatLon("Rain Gauge", "Godawari, Kailali", 28.87621, 80.57928, contacts);
        latLons.add(2, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("Tilak Bandari","9848616174");
        contacts.add(contact);
        latLon = new LatLon("Rain Gauge", "Sahajpur, Kailali", 29.004854, 80.591182, contacts);
        latLons.add(3, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("Kanaiya Chaudhary","9812751588");
        contacts.add(contact);
        latLon = new LatLon("Rain Gauge", "Bayelkundi, Kanchanpur", 28.87005, 80.46074, contacts);
        latLons.add(4, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("Krishna Lal Dagaura","9848078923");
        contacts.add(contact);
        latLon = new LatLon("Rain Gauge", "Dayaamarpur, Kanchanpur", 28.84897, 80.4994, contacts);
        latLons.add(5, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("Sita Ram Chaudhary","9806460771");
        contacts.add(contact);
        latLon = new LatLon("Rain Gauge", "Kattan, Kanchanpur", 28.8518, 80.40968, contacts);
        latLons.add(6, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("Mangal Chaudhary","9806499855");
        contacts.add(contact);
        latLon = new LatLon("Rain Gauge", "Tilki, Kanchanpur",28.81766 ,80.37647 , contacts);
        latLons.add(7, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("Jaya Bahadur Rana","9801378472");
        contacts.add(contact);
        latLon = new LatLon("Tilki River Gauge", "Tilki, Kanchanpur",28.81965, 80.37805,  contacts);
        latLons.add(8, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("Dhanu Devi Chaudhary","9848742247");
        contacts.add(contact);
        latLon = new LatLon("Kataan River Gauge", "Kataan, Kanchanpur",28.84973 ,80.40397 , contacts);
        latLons.add(9, latLon);

    }
}


