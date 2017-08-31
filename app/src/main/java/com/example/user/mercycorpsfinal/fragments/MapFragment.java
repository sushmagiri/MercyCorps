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
import android.widget.Toast;

import com.example.user.mercycorpsfinal.R;
import com.example.user.mercycorpsfinal.activity.CallListActivity;
import com.example.user.mercycorpsfinal.model.Contact;
import com.example.user.mercycorpsfinal.model.LatLon;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
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
//    KmlLayer layer;

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

//        try {
//            layer = new KmlLayer(mgoogleMap, R.raw.majorriver, this.getContext());
//            layer.addLayerToMap();
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
       /* int i=0;
        for (KmlPlacemark placemark : layer.getPlacemarks()) {
            // Do something to Placemark
            i++;
            if(placemark.hasProperty("name")){
                Log.e(TAG, "onMapReady: "+placemark.getProperty("name") );
                Log.e(TAG, "onMapReady: "+i );
            }
        }*/

       /* for (KmlContainer container : layer.getContainers()) {

            if (container.hasProperty("name")) {
                Log.e(TAG, "onMapReady: "+container.getProperty("name") );
                System.out.println(container.getProperty("name"));
            }
        }*/
//        layer.setOnFeatureClickListener(new GeoJsonLayer.GeoJsonOnFeatureClickListener() {
//            @Override
//            public void onFeatureClick(Feature feature) {
//                Log.e(TAG, "onFeatureClick: "+feature.getProperty("name") );
//                Toast.makeText(getContext(), ""+feature.getProperty("name"), Toast.LENGTH_SHORT).show();
//
//
//            }
//        });


        //Data prepare

        prepareData();

        for (final LatLon latlon : latLons
                ) {
            LatLng location = new LatLng(latlon.getLat(), latlon.getLon());
            if (latlon.getGauze()=="नदि")
            {
                mgoogleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.scale)).position(location).title(latlon.getGauze()).snippet(latlon.getBasedOn()));
                mgoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.scale));
                        marker.setSnippet(latlon.getBasedOn());
                        return false;
                    }
                });
            }
            else {
                mgoogleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.drop1)).position(location).title(latlon.getGauze()).snippet(latlon.getBasedOn()));
            }

            mgoogleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            mgoogleMap.setOnInfoWindowClickListener(this);

        }
        mgoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(28.8318, 80.51835), 10.0f));



        /*layer.setOnFeatureClickListener(new Layer.OnFeatureClickListener() {
            @Override
            public void onFeatureClick(Feature feature) {
                if (feature!=null){

                }
                else {
                    Log.e(TAG, "onFeatureClick: "+feature.getId() );
                }

            }
        });*/
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
        TextView name, location,base;
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
            base=(TextView)markerView.findViewById(R.id.based);
            String[] str = marker.getId().split("m");
            LatLon l1 = latLons.get(Integer.parseInt(str[1]));
            //Log.e("TAG", "getInfoContents:id is "+marker.getId() );
            name.setText(l1.getContacts().get(0).getName());
            location.setText(l1.getLocation());
            base.setText(l1.getBasedOn());
            //contact.setText(l1.getContacts().get(0).toString());
            return markerView;
        }
    }

    public void prepareData() {
        latLons = new ArrayList<LatLon>();
        contacts=new ArrayList<Contact>();
        contact=new Contact("जय बहादुर राना","9801378472");
        contacts.add(contact);
        LatLon latLon = new LatLon("तिल्की","नदि", "मछेलि","समुदायमा आधारित", 80.37805,28.81965, contacts);
        latLons.add(0, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("धनु देबीचौधरी","9815697482");
        contacts.add(contact);
        latLon = new LatLon("कटान","नदि", "वनरा","समुदायमा आधारित",80.40397 ,28.84973, contacts);
        latLons.add(1, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("शंकरदत्तजोशी","9848627174");
        contacts.add(contact);
        latLon = new LatLon("मछेलीपुल","नदि", "मछेलि","समुदायमा आधारित",80.34897 ,28.90224, contacts);
        latLons.add(2, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("पल्टा खुना","9805789383");
        contacts.add(contact);
        latLon = new LatLon("बेलकण्डी","नदि", "मछेलि","समुदायमा आधारित",80.4582 ,28.87165, contacts);
        latLons.add(3, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("आशिका सिंह","9868728412");
        contacts.add(contact);
        latLon = new LatLon("सुनवरा पुल","नदि", "सुनवरा","समुदायमा आधारित",80.34897 ,28.90224, contacts);
        latLons.add(4, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("मनिसा खोलीया","9812721162");
        contacts.add(contact);
        latLon = new LatLon("शिबनगर टोल चादनि ","नदि", "जोगुडा","समुदायमा आधारित",80.068973 ,28.917335, contacts);
        latLons.add(5, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("गायत्रीमिश्र","9812727519");
        contacts.add(contact);
        latLon = new LatLon("स्यालीपुल","नदि", "स्यालि","डी एच एम आधारित",80.32819 ,28.91551, contacts);
        latLons.add(6, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("लाल ब।बडेला","9868530916");
        contacts.add(contact);
        latLon = new LatLon("सोत्यासरकोल्मोडा","नदि", "मछेलि","समुदायमा आधारित", 80.531778,28.93113, contacts);
        latLons.add(7, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("तारा दत्तजोशी","9847619752");
        contacts.add(contact);
        latLon = new LatLon("परिगाउं, डडेल्धुरा","नदि", "महाकाली","डी एच एम आधारित",80.270619 ,29.149502, contacts);
        latLons.add(8, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("कृष्ण सिंह नगारी","9749524851");
        contacts.add(contact);
        latLon = new LatLon("दत्तु, दार्चुला","नदि", "महाकाली","डी एच एम आधारित",80.421403 ,29.795898, contacts);
        latLons.add(9, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("लोकेन्द्र बोहरा","9848705022");
        contacts.add(contact);
        latLon = new LatLon("बेदकोट १३ दैजी","नदि", "चौधर","डी एच एम आधारित",80.254325 ,28.950865, contacts);
        latLons.add(10, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("चन्द्रा शाहु","9812720236");
        contacts.add(contact);
        latLon = new LatLon("बेलडाडी ९","नदि", "चौधर","समुदायमा आधारित",80.243388 ,28.797824, contacts);
        latLons.add(11, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("दशरथ चौधरी","9809486826");
        contacts.add(contact);
        latLon = new LatLon("बागफाटा","नदि", "चौधर","समुदायमा आधारित", 80.23855,28.914559, contacts);
        latLons.add(12, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("रमेश लुहार","9806467439");
        contacts.add(contact);
        latLon = new LatLon("बैतोडा ४","नदि", "चौधर","समुदायमा आधारित",  80.258204,28.957506, contacts);
        latLons.add(13, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("हरि किशन राना","9806437727");
        contacts.add(contact);
        latLon = new LatLon("कञ्ज","नदि", "दोदा","समुदायमा आधारित", 80.399991,28.730379, contacts);
        latLons.add(14, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("लाल प्रसाद शर्मा","9749019825");
        contacts.add(contact);
        latLon = new LatLon("पुनर्वास","नदि", "दोदा","समुदायमा आधारित", 80.52383,28.57177, contacts);
        latLons.add(15, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("लाल ब। बोकटी","9810676418");
        contacts.add(contact);
        latLon = new LatLon("रातो ताल","नदि", "दोदा","समुदायमा आधारित", 80.48196,28.6593, contacts);
        latLons.add(16, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("दिल ब। चौधरी","9809481986");
        contacts.add(contact);
        latLon = new LatLon("बनहरा पुल","नदि", "वनरा","समुदायमा आधारित", 80.39687,28.88796, contacts);
        latLons.add(17, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("राम प्रसाद राना","9810656797");
        contacts.add(contact);
        latLon = new LatLon("आन्ध्रजाला","नदि", "स्यालि र सुनवरा","समुदायमा आधारित",80.366162 ,28.773494, contacts);
        latLons.add(18, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("सुनिता राना","9812757928");
        contacts.add(contact);
        latLon = new LatLon("सिमरी","वर्षा", "","समुदायमा आधारित",80.47768 ,28.66817, contacts);
        latLons.add(19, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("लिलाअधिकारी","9812797709");
        contacts.add(contact);
        latLon = new LatLon("बेलौरी,शान्तिपुर","वर्षा", "","डी एच एम आधारित", 80.35,28.683333, contacts);
        latLons.add(20, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("मंगल प्र। चौधरी","9806499855");
        contacts.add(contact);
        latLon = new LatLon("तिल्की","वर्षा", "","समुदायमा आधारित",80.37647 ,28.81766, contacts);
        latLons.add(21, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("कृष्णलाल चौधरी","9848078923");
        contacts.add(contact);
        latLon = new LatLon("दयाअमरपुर","वर्षा", "","समुदायमा आधारित", 80.4994,28.84897, contacts);
        latLons.add(22, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("सितारामचौधरी","9806460771");
        contacts.add(contact);
        latLon = new LatLon("कटान","वर्षा", "","समुदायमा आधारित", 80.40968,28.8518, contacts);
        latLons.add(23, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("कनैया चौधरी","9812751588");
        contacts.add(contact);
        latLon = new LatLon("बेलकुन्डी","वर्षा", "","समुदायमा आधारित", 80.46074,28.87005, contacts);
        latLons.add(24, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("कृष्ण राना","9806440829");
        contacts.add(contact);
        latLon = new LatLon("बनहरा","वर्षा", "","समुदायमा आधारित",80.39783 ,28.88453, contacts);
        latLons.add(25, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("तिर्थ ब. डगौरा","9848763244");
        contacts.add(contact);
        latLon = new LatLon("लालपुर","वर्षा", "","समुदायमा आधारित", 80.233,28.942, contacts);
        latLons.add(26, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("तिलक भण्डारी","9848616174");
        contacts.add(contact);
        latLon = new LatLon("सहजपुर कुमैना","वर्षा", "","समुदायमा आधारित", 80.591182,29.004854, contacts);
        latLons.add(27, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("प्रेम बहादुर महता","9848764323");
        contacts.add(contact);
        latLon = new LatLon("खल्लागोठ","वर्षा", "","समुदायमा आधारित", 80.440338,28.862318, contacts);
        latLons.add(28, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("जय बहादुर बिष्ट","9848715668");
        contacts.add(contact);
        latLon = new LatLon("झलारी","वर्षा", "","समुदायमा आधारित",80.350954 ,28.901229, contacts);
        latLons.add(29, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("संजय सारु","9806486126");
        contacts.add(contact);
        latLon = new LatLon("बगुन दैजी","वर्षा", "","समुदायमा आधारित", 80.294406,28.986302, contacts);
        latLons.add(30, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("बिसन ब। मानन्धर","9806447471");
        contacts.add(contact);
        latLon = new LatLon("दोधारा जोगबुढा","वर्षा", "","समुदायमा आधारित", 80.065207,28.910749, contacts);
        latLons.add(31, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("टेक बहादुर बिष्ट","9749007522");
        contacts.add(contact);
        latLon = new LatLon("रैकवार ६","वर्षा", "","समुदायमा आधारित",80.518681 ,28.770314, contacts);
        latLons.add(32, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("रतन ब। शाह","9805753513");
        contacts.add(contact);
        latLon = new LatLon("हनुमाननगर दैजी","वर्षा", "","समुदायमा आधारित", 80.302126,29.002234, contacts);
        latLons.add(33, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("मन्दोधरी राना","9810654379");
        contacts.add(contact);
        latLon = new LatLon("शंकरपुर,पर्सिया","वर्षा", "","समुदायमा आधारित", 80.389306,28.770922, contacts);
        latLons.add(34, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("जनक राजपन्त","9858790084");
        contacts.add(contact);
        latLon = new LatLon("आपत्कालिन संचालन केन्द्र","वर्षा", "","", 80.184154,28.969355, contacts);
        latLons.add(35, latLon);

        contacts=new ArrayList<Contact>();
        contact=new Contact("राममनि मिश्र","9848318777");
        contacts.add(contact);
        latLon = new LatLon("जलबायु तथा मौसम बिभाग","वर्षा", "","डी एच एम आधारित", 80.55997,28.81271, contacts);
        latLons.add(36, latLon);

    }
}


