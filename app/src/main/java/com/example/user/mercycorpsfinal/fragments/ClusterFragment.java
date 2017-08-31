package com.example.user.mercycorpsfinal.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.mercycorpsfinal.R;
import com.example.user.mercycorpsfinal.activity.ApatkalinAwasthaActivity;
import com.example.user.mercycorpsfinal.activity.GairSarkariSasthaActivity;
import com.example.user.mercycorpsfinal.activity.KanchanpurList;
import com.example.user.mercycorpsfinal.activity.Sheet10;
import com.example.user.mercycorpsfinal.activity.Sheet11;
import com.example.user.mercycorpsfinal.activity.Sheet12;
import com.example.user.mercycorpsfinal.activity.Sheet13;
import com.example.user.mercycorpsfinal.activity.Sheet14;
import com.example.user.mercycorpsfinal.activity.Sheet3;
import com.example.user.mercycorpsfinal.activity.Sheet6;
import com.example.user.mercycorpsfinal.activity.Sheet7;
import com.example.user.mercycorpsfinal.activity.Sheet8;
import com.example.user.mercycorpsfinal.activity.Sheet9;
import com.example.user.mercycorpsfinal.activity.SuchanaBewasthapan5Activity;
import com.example.user.mercycorpsfinal.adapter.FragmentAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClusterFragment extends Fragment {
    ListView lv;
    ArrayAdapter<String> adapter;
    View mView;
    String[] listItems={ "गैर सरकारी संस्था ",
            "समन्वय तथा सूचना व्यवस्थापन क्षेत्रको सर्म्पर्क व्यक्ति तथा संस्थाको विवरण ",
            "खाद्य कार्य समुह ",
            "आवास तथा गैर खाद्यान्न  ",
            "खानेपानी  सरसफाई तथा स्वास्थ्य पर्रवर्धन क्षेत्रको सर्म्पर्क ",
            "स्वास्थ्य तथा पोषण क्षेत्रको सर्म्पर्क व्यक्ति तथा संस्थाको विवरण",
            "संरक्षण क्षेत्रको सम्पर्क व्यक्ति तथा संस्थाको विवरण  ",
            "शिक्षा क्षेत्रको सम्पर्क व्यक्ति तथा सस्ंथाको विवरण","पूर्नस्थापन तथा पूर्ननिर्माण कार्यदलको विवरण"
    };
    public ClusterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView= inflater.inflate(R.layout.fragment_important_contacts, container, false);
        return mView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = (ListView) mView.findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getActivity(),R.layout.activity_main_list_item, listItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch(position){

                    case 0 :intent = new Intent(getActivity(),GairSarkariSasthaActivity.class);
                        startActivity(intent);
                        break;
                    case 1 :intent = new Intent(getActivity(), SuchanaBewasthapan5Activity.class);
                        startActivity(intent);
                        break;
                    case 2 :intent = new Intent(getActivity(), Sheet6.class);
                        startActivity(intent);
                        break;
                    case 3 :intent = new Intent(getActivity(), Sheet7.class);
                        startActivity(intent);
                        break;
                    case 4 :intent = new Intent(getActivity(), Sheet8.class);
                        startActivity(intent);
                        break;
                    case 5 :intent = new Intent(getActivity(), Sheet9.class);
                        startActivity(intent);
                        break;
                    case 6 :intent = new Intent(getActivity(), Sheet10.class);
                        startActivity(intent);
                        break;
                    case 7 :intent = new Intent(getActivity(), Sheet11.class);
                        startActivity(intent);
                        break;
                    case 8 :intent = new Intent(getActivity(), Sheet12.class);
                        startActivity(intent);
                        break;




                }

            }

        });
    }

}
