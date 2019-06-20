package com.example.hemamostafa.hatly_1;


import android.content.Context;
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

import com.example.hemamostafa.hatly_1.Adapter.MyTripsAdpter;
import com.example.hemamostafa.hatly_1.Base.MyBaseFragment;
import com.example.hemamostafa.hatly_1.Model.Trip;
import com.example.hemamostafa.hatly_1.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTrips extends MyBaseFragment {


    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MyTripsAdpter myTripsAdpter;
    private View view;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    ValueEventListener valueEventListener;
    ArrayList<Trip> myTripArrayList;
    public FragmentTrips() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view =inflater.inflate(R.layout.fragment_trips, container, false);


        recyclerView =view.findViewById(R.id.trip_reecycler_view);
        linearLayoutManager = new LinearLayoutManager(activity); //activity = getContext()
        myTripArrayList = new ArrayList<>();
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // addTrips();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("trips");
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myTripArrayList.clear();
                for (DataSnapshot tripSnapshot : dataSnapshot.getChildren()) {
                    Trip trip = tripSnapshot.getValue(Trip.class);
                    Log.e("trip", trip.toString());
                    //Toast.makeText(activity, trip.toString(), Toast.LENGTH_SHORT).show();
                    if(trip.getCreator_id().equals(mAuth.getUid())){
                        myTripArrayList.add(trip);
                    }
                    else
                        continue;


                }



                recyclerView.setLayoutManager(linearLayoutManager);
                myTripsAdpter = new MyTripsAdpter(myTripArrayList);
                recyclerView.setAdapter(myTripsAdpter);
                myTripsAdpter.setOnCardViewClickListener(new MyTripsAdpter.OnItemClickListenerInterface() {
                    @Override
                    public void onClick(int position, Trip trip) {
                        Toast.makeText(activity,"The card is Clicked"+ position+"",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity, Test.class);
                        intent.putExtra("trip",trip);
                        intent.putExtra("Uniqid_2","FragmentTrips");
                        startActivity(intent);


                    }
                });
                myTripsAdpter.setOnArrowClickListener(new MyTripsAdpter.OnItemClickListenerInterface() {
                    @Override
                    public void onClick(int position, Trip trip) {
                        Toast.makeText(activity,"The arrow is Clicked"+ position+"",Toast.LENGTH_SHORT).show();

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("flag",databaseError.toString());

            }
        };
        mDatabase.addValueEventListener(valueEventListener);

/*
        FirebaseDatabase.getInstance().getReference().child("trips")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> list = dataSnapshot.getChildren();

                        // Getting current user Id
                        String uid = mAuth.getUid();

                        // Filter User
                        ArrayList<Trip> tripArrayListFilteration = new ArrayList<>();
                        for (DataSnapshot dataSnapshot1 : list) {
                            if (!dataSnapshot1.getKey().equals(uid)) {
                                tripArrayListFilteration.add(dataSnapshot1.getValue(Trip.class));
                            }
                        }

                        // Setting data
                        mBaseRecyclerAdapter.setItems(tripArrayListFilteration);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

*/

        return view;
    }


/////////////////////////////////////////////////////////
    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view =inflater.inflate(R.layout.fragment_trips, container, false);


        recyclerView =view.findViewById(R.id.trip_reecycler_view);
        linearLayoutManager = new LinearLayoutManager(activity); //activity = getContext()
        myTripArrayList = new ArrayList<>();

       // addTrips();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("trips");
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myTripArrayList.clear();
                for (DataSnapshot tripSnapshot : dataSnapshot.getChildren()) {
                    Trip trip = tripSnapshot.getValue(Trip.class);
                    Log.e("trip", trip.toString());
                    myTripArrayList.add(trip);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("flag",databaseError.toString());

            }
        };

        mDatabase.addValueEventListener(valueEventListener);
        myTripsAdpter = new MyTripsAdpter(myTripArrayList);

        myTripsAdpter.setOnCardViewClickListener(new MyTripsAdpter.OnItemClickListenerInterface() {
            @Override
            public void onClick(int position, Trip trip) {
                Toast.makeText(activity,"The card is Clicked"+ position+"",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(activity,DealsAndMatchshipments.class));
            }
        });
        myTripsAdpter.setOnArrowClickListener(new MyTripsAdpter.OnItemClickListenerInterface() {
            @Override
            public void onClick(int position, Trip trip) {
                Toast.makeText(activity,"The arrow is Clicked"+ position+"",Toast.LENGTH_SHORT).show();

            }
        });



        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myTripsAdpter);
        return view;
    }
*/
    @Override
    public void onResume() {
        super.onResume();
        //Toast.makeText(activity,"onResume Fragment",Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onStart() {
        super.onStart();
        mDatabase.addValueEventListener(valueEventListener);

       // Toast.makeText(activity,"onStart Fragment",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Toast.makeText(activity,"onAttach Fragment",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPause() {
        super.onPause();
        //Toast.makeText(activity,"onPause Fragment",Toast.LENGTH_SHORT).show();
        //deattachDatabaseReaderListner();
    }

    @Override
    public void onStop() {
        super.onStop();
        //Toast.makeText(activity,"onStop Fragment",Toast.LENGTH_SHORT).show();
    }

    public  void  addTrips(){

            myTripArrayList.add(new Trip("Cairo","Sohag","12/4/2019", "5 kg","car" ));
            myTripArrayList.add(new Trip("Cairo","Sohag","12/4/2019", "5 kg","car" ));
            myTripArrayList.add(new Trip("Cairo","Sohag","12/4/2019", "5 kg","car" ));
            myTripArrayList.add(new Trip("Cairo","Sohag","12/4/2019", "5 kg","car" ));
            myTripArrayList.add(new Trip("Cairo","Sohag","12/4/2019", "5 kg","car" ));
            myTripArrayList.add(new Trip("Cairo","Sohag","12/4/2019", "5 kg","car" ));
            myTripArrayList.add(new Trip("Cairo","Sohag","12/4/2019", "5 kg","car" ));
            myTripArrayList.add(new Trip("Cairo","Sohag","12/4/2019", "5 kg","car" ));
            myTripArrayList.add(new Trip("Cairo","Sohag","12/4/2019", "5 kg","car" ));


        }

// Attach DataBase Listner to firbase so it trigger if the changed occur

    public  void attchedDatabaseReadListner(){
        if (valueEventListener == null) {
            valueEventListener=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    myTripArrayList.clear();
                    for (DataSnapshot tripSnapshot : dataSnapshot.getChildren()) {
                        Trip trip = tripSnapshot.getValue(Trip.class);
                        Log.e("trip", trip.toString());
                        myTripArrayList.add(trip);


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            // Add Listener To DataBase if it changed
            mDatabase.addValueEventListener(valueEventListener);
        }


    }

    public void deattachDatabaseReaderListner(){
        if(valueEventListener != null)
            mDatabase.removeEventListener(valueEventListener);

        valueEventListener=null;

    }


}
