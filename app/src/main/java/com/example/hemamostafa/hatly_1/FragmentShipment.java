package com.example.hemamostafa.hatly_1;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.hemamostafa.hatly_1.Adapter.MyShipmentAdpter;

import com.example.hemamostafa.hatly_1.Base.MyBaseFragment;
import com.example.hemamostafa.hatly_1.Model.Shipment;
import com.example.hemamostafa.hatly_1.Model.Trip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentShipment extends MyBaseFragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MyShipmentAdpter myShipmentAdpter;
    View view;
    ArrayList<Shipment> mShipmentArrayList;
    private DatabaseReference mDatabase;
    private ValueEventListener valueEventListener;

    public FragmentShipment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
       view=inflater.inflate(R.layout.fragment_shipment, container, false);

       recyclerView= view.findViewById(R.id.recyclerView_shipFragment);
       linearLayoutManager = new LinearLayoutManager(activity);
       mShipmentArrayList= new ArrayList<>();

       //addShipment();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("shipments");
        valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mShipmentArrayList.clear();
                for (DataSnapshot shipmentSnapshot : dataSnapshot.getChildren()) {
                    Shipment shipment = shipmentSnapshot.getValue(Shipment.class);
                    //Toast.makeText(activity, trip.toString(), Toast.LENGTH_SHORT).show();
                    mShipmentArrayList.add(shipment);
                }
                recyclerView.setLayoutManager(linearLayoutManager);
                myShipmentAdpter= new MyShipmentAdpter(mShipmentArrayList);
                recyclerView.setAdapter(myShipmentAdpter);
                myShipmentAdpter.setOnArrowClickListener(new MyShipmentAdpter.OnItemClickListenerInterface() {
                    @Override
                    public void onClick(int position, Shipment shipment) {

                    }
                });

                myShipmentAdpter.setOnCardViewClickListener(new MyShipmentAdpter.OnItemClickListenerInterface() {
                    @Override
                    public void onClick(int position, Shipment shipment) {
                        startActivity(new Intent(activity,DealsAndMatchinTrips.class));
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

       mDatabase.addValueEventListener(valueEventListener);
        return  view;
    }

    private void addShipment() {


        mShipmentArrayList.add(new Shipment("10","hema_10","Mobile","Cairo","Minia",
                "10/2/2019"));
        mShipmentArrayList.add(new Shipment("10","hema_10","Mobile","Cairo","Minia",
                "10/2/2019"));

        mShipmentArrayList.add(new Shipment("10","hema_10","Mobile","Cairo","Minia",
                "10/2/2019"));

        mShipmentArrayList.add(new Shipment("10","hema_10","Mobile","Cairo","Minia",
                "10/2/2019"));

        mShipmentArrayList.add(new Shipment("10","hema_10","Mobile","Cairo","Minia",
                "10/2/2019"));


    }

}
