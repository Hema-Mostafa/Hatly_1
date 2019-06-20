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

import com.example.hemamostafa.hatly_1.Adapter.MatchingShipmentAdapter;
import com.example.hemamostafa.hatly_1.Model.DummyClass;
import com.example.hemamostafa.hatly_1.Model.SendRequestForTrip;
import com.example.hemamostafa.hatly_1.Model.Shipment;
import com.example.hemamostafa.hatly_1.Model.Trip;
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
public class BlankFragment_1 extends Fragment {


    RecyclerView recyclerview;
    LinearLayoutManager layoutManager;
    MatchingShipmentAdapter adapter;
    View view;
    DummyClass dummyClass;
    ArrayList<DummyClass> arrayList;
    private ArrayList<User> mUserArrayList;
    private ArrayList<Shipment> mshipmentArrayList;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReferanceShipment, mDatabaseReferanceUser;
    private ValueEventListener valueEventListenerShipmens, valueEventListenerUsers;

    Trip mTrip;

    public void setmTrip(Trip mTrip) {
        this.mTrip = mTrip;
    }

    public BlankFragment_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_blank_fragment_1, container, false);

        mshipmentArrayList = new ArrayList<>();
        mUserArrayList = new ArrayList<>();
        arrayList = new ArrayList<>();
        recyclerview = view.findViewById(R.id.match_shipments_reecycler_view_blank);
        layoutManager = new LinearLayoutManager(getActivity());

        recyclerview.setLayoutManager(layoutManager);

        addDummyValue();
        adapter = new MatchingShipmentAdapter(arrayList);
        recyclerview.setAdapter(adapter);
        searchAboutShipment();
        Toast.makeText(getContext(),mshipmentArrayList.get(0).getShipmentName()+" Sh Name" ,Toast.LENGTH_SHORT).show();

       searchUser();
        Toast.makeText(getContext(),mUserArrayList.get(0).getUser_mail()+" is user mail ",Toast.LENGTH_SHORT).show();

        return view;


    }
    public  void  addDummyValue(){
        arrayList.add(new DummyClass("dsd","from",
                "car","26/10",
                "dsdsd","name sh"
                ,"6",
                "no photo","dsds"
                ,"sds"));



    }
    private void searchAboutShipment(){

        Toast.makeText(getContext(),"Search about Shipments Start !  ",Toast.LENGTH_SHORT).show();
        mDatabaseReferanceShipment= FirebaseDatabase.getInstance().getReference().child("shipments");
        //Toast.makeText(getContext(),"Search about Shipment ",Toast.LENGTH_SHORT).show();
        valueEventListenerShipmens = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mshipmentArrayList.clear();
                for (DataSnapshot shipmentSnapshot : dataSnapshot.getChildren()) {
                    final Shipment shipment = shipmentSnapshot.getValue(Shipment.class);

                    Toast.makeText(getContext(), shipment.getCreator_id(), Toast.LENGTH_SHORT).show();
                    if(false){
                        continue;
                    }
                    else{
                        if(shipment.getTo().equals(mTrip.getTo()) && shipment.getFrom().equals(mTrip.getFrom())&&
                                Integer.parseInt(shipment.getShipmentWeight()) <= (Integer.parseInt(mTrip.getWeight())))
                        {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                Date searchDateTrip = sdf.parse(mTrip.getDate());
                                Date shipmentsDate = sdf.parse(shipment.getBeforeDate());
                                if (searchDateTrip.after(shipmentsDate)) {

                                    //return;

                                }
                                else{
                                    dummyClass= new DummyClass();
                                  //  Query query = FirebaseDatabase.getInstance().getReference().child("users")
                                      //      .orderByChild("user_id")
                                         //  .equalTo(shipment.getCreator_id());



                                    Toast.makeText(getContext(),shipment.getShipmentName()+" Is A Shipper Name ",Toast.LENGTH_SHORT).show();
                                    mshipmentArrayList.add(shipment);
                                    Toast.makeText(getContext(),mshipmentArrayList.size()+" Is mShipmentsArray size ",Toast.LENGTH_SHORT).show();

                                }

                            } catch (ParseException e) {
                                Toast.makeText(getContext(),e +"",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                }
                if(mshipmentArrayList.size() == 0){
                    Toast.makeText(getContext(),"No Shipment Found ",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(),mshipmentArrayList.get(0).getShipmentName(),Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(),mshipmentArrayList.get(0).getTo(),Toast.LENGTH_SHORT).show();
                     Toast.makeText(getContext(),mshipmentArrayList.get(0).getFrom(),Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Error on Search Operation ",Toast.LENGTH_SHORT).show();
            }
        };

        mDatabaseReferanceShipment.addValueEventListener((valueEventListenerShipmens));
        Toast.makeText(getContext(),"Search about Shipmenets is Ended ",Toast.LENGTH_SHORT).show();


        // return null;
    }
    private  void searchUser(){
        mDatabaseReferanceUser = FirebaseDatabase.getInstance().getReference().child("users");
        valueEventListenerUsers=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    mUserArrayList.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Search about user Failed  ",Toast.LENGTH_SHORT).show();
            }
        };
        mDatabaseReferanceUser.addValueEventListener(valueEventListenerUsers);
    }

}
