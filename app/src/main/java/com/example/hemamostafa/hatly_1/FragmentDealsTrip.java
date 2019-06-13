package com.example.hemamostafa.hatly_1;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hemamostafa.hatly_1.Adapter.DealsTripAdapter;
import com.example.hemamostafa.hatly_1.Adapter.MatchingTripsAdapter;
import com.example.hemamostafa.hatly_1.Base.MyBaseFragment;
import com.example.hemamostafa.hatly_1.Model.Shipment;
import com.example.hemamostafa.hatly_1.Model.Trip;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDealsTrip extends Fragment {
    RecyclerView recyclerview;
    LinearLayoutManager layoutManager;
    DealsTripAdapter adapter;
    View view;
    ArrayList<Shipment> arrayList;

    public FragmentDealsTrip() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_fragment_deals_trip, container, false);

        recyclerview = view.findViewById(R.id.deals_recycler_trip);
        layoutManager = new LinearLayoutManager(getContext());
        addAnyValue();

        adapter= new DealsTripAdapter(arrayList);
        adapter.setOnButtonClickListener(new DealsTripAdapter.OnItemClickListenerInterface() {
            @Override
            public void onClick(int position, Shipment shipment) {
                startActivity(new Intent(getContext(),DealInfo_1.class));

            }
        });
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);



        return view;
    }

    private void addAnyValue() {
        arrayList = new ArrayList<>();
        arrayList.add( new Shipment("","","Paper","Sohag","Minia","5/2/2019"));
        arrayList.add( new Shipment("","","Paper","Sohag","Minia","5/2/2019"));
    }


}
