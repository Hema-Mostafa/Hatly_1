package com.example.hemamostafa.hatly_1;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.hemamostafa.hatly_1.Adapter.MatchingTripsAdapter;
import com.example.hemamostafa.hatly_1.Model.SendRequestForTrip;
import com.example.hemamostafa.hatly_1.Model.Shipment;
import com.example.hemamostafa.hatly_1.Model.Trip;
import com.example.hemamostafa.hatly_1.Model.TripDummyClass;
import com.example.hemamostafa.hatly_1.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMatchingTrip extends Fragment {


    RecyclerView recyclerview;
    LinearLayoutManager layoutManager;
    MatchingTripsAdapter adapter;
    View view;
    ArrayList<TripDummyClass> arrayList;
    private ArrayList<User> mUserArrayList;
    private ArrayList<Trip> mTripArrayList;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReferanceTrips,mDatabaseReferanceUser;
    private ValueEventListener valueEventListenerTrips,valueEventListenerUsers;
    Shipment shipment;
    TripDummyClass tripDummyClass;

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public FragmentMatchingTrip() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_fragment_matching_trip, container, false);

        mAuth = FirebaseAuth.getInstance();
        mDatabaseReferanceUser = FirebaseDatabase.getInstance().getReference().child("users");

        arrayList = new ArrayList<>();
        mTripArrayList= new ArrayList<>();
        mUserArrayList = new ArrayList<>();

       recyclerview = view.findViewById(R.id.matching_recycler_trip);
       layoutManager = new LinearLayoutManager(getContext());
       // addAnyValue();
       searchAboutTrips();


       // adapter = new MatchingTripsAdapter(arrayList);
        /*adapter.setOnButtonClickListener(new MatchingTripsAdapter.OnItemClickListenerInterface() {
            @Override
            public void onClick(int position, TripDummyClass trip) {



            }
        });
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);
*/


        return view;
    }
     private  void searchAboutTrips(){

         Toast.makeText(getContext(),"Search about Trips Start !  ",Toast.LENGTH_SHORT).show();
         mDatabaseReferanceTrips=FirebaseDatabase.getInstance().getReference().child("trips");
         //Toast.makeText(getContext(),"Search about Shipment ",Toast.LENGTH_SHORT).show();


         valueEventListenerTrips = new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 mTripArrayList.clear();
                 for (DataSnapshot TripSnapshot : dataSnapshot.getChildren()){
                     final Trip trip = TripSnapshot.getValue(Trip.class);
                     if(trip.getCreator_id().equals(mAuth.getUid())){
                         continue;
                     }
                     else {
                         if(trip.getTo().equals(shipment.getTo()) && trip.getFrom().equals(shipment.getFrom())&&
                                 Integer.parseInt(trip.getWeight()) >= (Integer.parseInt(shipment.getShipmentWeight()))){

                             SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                             try {
                                 Date searchDateShipment = sdf.parse(shipment.getBeforeDate());
                                 Date tripDate = sdf.parse(trip.getDate());
                                 if (searchDateShipment.after(tripDate)) {

                                     tripDummyClass= new TripDummyClass();
                                     Query query = FirebaseDatabase.getInstance().getReference().child("users")
                                             .orderByChild("user_id")
                                             .equalTo(trip.getCreator_id());
                                     valueEventListenerUsers= new ValueEventListener() {
                                         @Override
                                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                             mUserArrayList.clear();
                                             for( DataSnapshot snapshot : dataSnapshot.getChildren()){
                                                 User user = snapshot.getValue(User.class);
                                                 if(user.getUser_id().equals(trip.getCreator_id())){

                                                     mUserArrayList.add(user);
                                                     // Toast.makeText(getContext() , user.getUser_mail()+"Name is "+user.getUser_name() , Toast.LENGTH_SHORT).show();
                                                 }

                                             }
                                             for(int i= 0 ; i< mUserArrayList.size() ; i++){
                                                 tripDummyClass = new TripDummyClass();

                                                 tripDummyClass.setTrip(trip.getTrip());
                                                 tripDummyClass.setDate(trip.getDate());
                                                 tripDummyClass.setFrom(trip.getFrom());
                                                 tripDummyClass.setShipmentName(shipment.getShipmentName());
                                                 tripDummyClass.setTo(trip.getTo());
                                                 tripDummyClass.setWeight(trip.getWeight());
                                                 tripDummyClass.setTransportationType("car");
                                                 tripDummyClass.setCreator_id(mUserArrayList.get(i).getUser_id());
                                                 tripDummyClass.setTraveller_name(mUserArrayList.get(i).getUser_name());
                                                 tripDummyClass.setTraveller_photo("");
                                                 tripDummyClass.setTraveller_rate("");
                                                 arrayList.add(tripDummyClass);

                                             }
                                             adapter = new MatchingTripsAdapter(arrayList);
                                             adapter.setOnButtonClickListener(new MatchingTripsAdapter.OnItemClickListenerInterface() {
                                                 @Override
                                                 public void onClick(int position, TripDummyClass tripDummyClass) {
                                                     Intent intent = new Intent(getContext(), SendRequest.class);
                                                     intent.putExtra("trip",tripDummyClass);
                                                     startActivity(intent);

                                                 }
                                             });

                                             recyclerview.setLayoutManager(layoutManager);
                                             recyclerview.setAdapter(adapter);

                                    //Toast.makeText(getContext(),arrayList.get(0).getTraveller_name()+"is  Travellre name ",Toast.LENGTH_SHORT).show();

                                         }


                                         @Override
                                         public void onCancelled(@NonNull DatabaseError databaseError) {

                                         }
                                     };
                                     query.addValueEventListener(valueEventListenerUsers);


                                     mTripArrayList.add(trip);

                                 } else {
                                     continue;

                                 }



                             } catch (ParseException e) {
                                 e.printStackTrace();
                             }



                         }




                     }


                 }



             }
             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {
                 Toast.makeText(getContext(),"Error on Search Operation ",Toast.LENGTH_SHORT).show();

             }
         };
         mDatabaseReferanceTrips.addValueEventListener((valueEventListenerTrips));
         Toast.makeText(getContext(),"Search about Shipmenets is Ended ",Toast.LENGTH_SHORT).show();
     }
/*
    public  void  addAnyValue(){

        arrayList = new ArrayList<>();
        arrayList.add(new TripDummyClass("10","20/20/20","sos","dsd","sds",

                "dsds","6","dsd","sds",""));
    }
    */
}
