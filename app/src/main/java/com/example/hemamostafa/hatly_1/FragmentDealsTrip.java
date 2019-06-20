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
import android.widget.Toast;

import com.example.hemamostafa.hatly_1.Adapter.DealsTripAdapter;
import com.example.hemamostafa.hatly_1.Adapter.MatchingTripsAdapter;
import com.example.hemamostafa.hatly_1.Adapter.MyTripsAdpter;
import com.example.hemamostafa.hatly_1.Base.MyBaseFragment;
import com.example.hemamostafa.hatly_1.Model.Deal;
import com.example.hemamostafa.hatly_1.Model.Shipment;
import com.example.hemamostafa.hatly_1.Model.Trip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDealsTrip extends MyBaseFragment {



    RecyclerView recyclerview;
    LinearLayoutManager layoutManager;
    DealsTripAdapter adapter;
    View view;
    ArrayList<Deal> arrayList;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    ValueEventListener valueEventListener;

    public FragmentDealsTrip() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_fragment_deals_trip, container, false);
/*
        recyclerview = view.findViewById(R.id.deals_recycler_trip);
        layoutManager = new LinearLayoutManager(activity);
        addAnyValue();
        adapter = new DealsTripAdapter(arrayList);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);

        Toast.makeText(getContext(),"Deals Fragment",Toast.LENGTH_SHORT).show();
*/

      //  arrayList=new ArrayList<>();
       // mAuth=FirebaseAuth.getInstance();
        /*
        mDatabase = FirebaseDatabase.getInstance().getReference().child("deals");

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot dealSnapshot : dataSnapshot.getChildren()) {
                    Deal deal = dealSnapshot.getValue(Deal.class);

                    if(deal.getTraveller_id().equals(mAuth.getUid())){
                        arrayList.add(deal);
                    }
                    else
                        continue;


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("flag",databaseError.toString());

            }
        };
*/

/*
          addAnyValue();
        adapter= new DealsTripAdapter(arrayList);
        adapter.setOnButtonClickListener(new DealsTripAdapter.OnItemClickListenerInterface() {
            @Override
            public void onClick(int position, Deal deal) {
                startActivity(new Intent(activity,DealInfo_1.class));
            }
        });
        */
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);
        return view;
    }
    private void addAnyValue() {
        arrayList = new ArrayList<>();
        arrayList.add(new Deal("dsd","dsds","dsds","dsds","dsd","dsds","dsds","dsds","dsd"));

        arrayList.add(new Deal("dsd","dsds","dsds","dsds","dsd","dsds","dsds","dsds","dsd"));
    }


}
