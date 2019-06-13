package com.example.hemamostafa.hatly_1;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hemamostafa.hatly_1.Adapter.MatchingTripsAdapter;
import com.example.hemamostafa.hatly_1.Model.DummyClass;
import com.example.hemamostafa.hatly_1.Model.SendRequestForTrip;
import com.example.hemamostafa.hatly_1.Model.Shipment;
import com.example.hemamostafa.hatly_1.Model.Trip;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMatchingTrip extends Fragment {
    RecyclerView recyclerview;
    LinearLayoutManager layoutManager;
    MatchingTripsAdapter adapter;
    View view;
    ArrayList<Trip> arrayList;

    public FragmentMatchingTrip() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_fragment_matching_trip, container, false);
        recyclerview = view.findViewById(R.id.matching_recycler_trip);
        layoutManager = new LinearLayoutManager(getContext());
        addAnyValue();
        adapter = new MatchingTripsAdapter(arrayList);

        adapter.setOnButtonClickListener(new MatchingTripsAdapter.OnItemClickListenerInterface() {
            @Override
            public void onClick(int position, Trip trip) {
                startActivity(new Intent(getActivity(), SendRequestForTrip.class));

            }
        });
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);


        return view;
    }
    public  void  addAnyValue(){
        arrayList = new ArrayList<>();
        arrayList.add(new Trip("Minia","Cairo","10/2/2019","8",""));
        arrayList.add(new Trip("Minia","Cairo","10/2/2019","8",""));
    }
}
