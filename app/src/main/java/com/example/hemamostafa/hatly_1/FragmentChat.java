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

import com.example.hemamostafa.hatly_1.Adapter.MatchingShipmentAdapter;
import com.example.hemamostafa.hatly_1.Adapter.MyShipmentAdpter;
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
public class FragmentChat extends Fragment {

    private ArrayList<User> mUserArrayList;
    private ArrayList<Shipment> shipmentArrayList,mshipmentArrayList;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReferance, mDatabaseReferanceUser,mDatabaseReferanceShipment;
    private ValueEventListener valueEventListenerShipmens, valueEventListenerUsers;

    private MyShipmentAdpter myShipmentAdpter;


    Trip mTrip;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    MatchingShipmentAdapter adapter;
    View view;
    DummyClass dummyClass;
    ArrayList<DummyClass> arrayList;

    public void setmTrip(Trip mTrip) {
        this.mTrip = mTrip;
    }

    public FragmentChat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        recyclerView = view.findViewById(R.id.rec_view);
        layoutManager = new LinearLayoutManager(getContext());
        arrayList = new ArrayList<>();

       /* mAuth= FirebaseAuth.getInstance();
       //mDatabaseReferanceShipment = FirebaseDatabase.getInstance().getReference().child("shipments");



        mDatabaseReferance = FirebaseDatabase.getInstance().getReference().child("shipments");
        valueEventListenerShipmens = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                shipmentArrayList.clear();
                for (DataSnapshot shipmentSnapshot : dataSnapshot.getChildren()) {
                    Shipment shipment = shipmentSnapshot.getValue(Shipment.class);
                    //Toast.makeText(activity, trip.toString(), Toast.LENGTH_SHORT).show();
                    if(shipment.getCreator_id().equals(mAuth.getUid())){
                        continue;
                    }
                    else{
                        if(shipment.getTo().equals(mTrip.getTo()) && shipment.getFrom().equals(mTrip.getFrom())&&
                                Integer.parseInt(shipment.getShipmentWeight()) <= (Integer.parseInt(mTrip.getWeight())))
                        {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                Date searchDate = sdf.parse(mTrip.getDate());
                                Date shipmentsDate = sdf.parse(shipment.getBeforeDate());
                                if (searchDate.after(shipmentsDate)) {

                                    //return;

                                }
                                else{
                                    shipmentArrayList.add(shipment);

                                }

                            } catch (ParseException e) {
                                Toast.makeText(getContext(),e +"",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                }
                if(shipmentArrayList.size() == 0){
                    Toast.makeText(getContext(),"No Shipment Found ",Toast.LENGTH_SHORT).show();
                }

                recyclerView.setLayoutManager(layoutManager);
                myShipmentAdpter =new MyShipmentAdpter(shipmentArrayList);


                myShipmentAdpter.setOnCardViewClickListener(new MyShipmentAdpter.OnItemClickListenerInterface() {
                    @Override
                    public void onClick(int position, Shipment shipment) {

                    }
                });
                myShipmentAdpter.setOnArrowClickListener(new MyShipmentAdpter.OnItemClickListenerInterface() {
                    @Override
                    public void onClick(int position, Shipment shipment) {

                    }
                });
                recyclerView.setAdapter(myShipmentAdpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("flag",databaseError.toString());

            }
        };

        Toast.makeText(getContext(),"Search about Shipmenets is Ended ",Toast.LENGTH_SHORT).show();
        mDatabaseReferance.addValueEventListener((valueEventListenerShipmens));
*/



        Toast.makeText(getContext(), "Chat Fragment ", Toast.LENGTH_SHORT).show();
 // mTrip have value

       // mshipmentArrayList = new ArrayList<>();
      //  mUserArrayList = new ArrayList<>();


       // searchAboutShipment();


        addDummyValue();
        adapter = new MatchingShipmentAdapter(arrayList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }


    public void addDummyValue() {
/*
        for(int i=0 ; i<mshipmentArrayList.size() ; i++){

            arrayList.add(new DummyClass(mshipmentArrayList.get(i).getTo(),mshipmentArrayList.get(i).getFrom(),
                    "car",mshipmentArrayList.get(i).getBeforeDate(),
                    mUserArrayList.get(i).getUser_id(),mshipmentArrayList.get(i).getShipmentName()
                    ,mshipmentArrayList.get(i).getShipmentWeight(),
                    "no photo",mUserArrayList.get(i).getUser_name()
                    ,mUserArrayList.get(i).getUser_rate()));


        }
*/
        arrayList.add(new DummyClass("sohag", "cairo",
                "car", "26/6/2019",
                "dsdsd", "bag"
                , "6",
                "no photo", "Ahmed"
                , "sds"));

        arrayList.add(new DummyClass("luxor", "minia",
                "car", "22/6/2019",
                "dsdsd", "cloths"
                , "6",
                "no photo", "Mostafa"
                , "sds"));


    }

    private void searchAboutShipment() {

        Toast.makeText(getContext(), "Search about Shipments Start !  ", Toast.LENGTH_SHORT).show();
        mDatabaseReferanceShipment = FirebaseDatabase.getInstance().getReference().child("shipments");
        //Toast.makeText(getContext(),"Search about Shipment ",Toast.LENGTH_SHORT).show();
        valueEventListenerShipmens = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mshipmentArrayList.clear();
                for (DataSnapshot shipmentSnapshot : dataSnapshot.getChildren()) {
                    final Shipment shipment = shipmentSnapshot.getValue(Shipment.class);
                    //Toast.makeText(activity, trip.toString(), Toast.LENGTH_SHORT).show();
                    if (shipment.getCreator_id().equals(mAuth.getUid())) {
                        continue;
                    } else {
                        if (shipment.getTo().equals(mTrip.getTo()) && shipment.getFrom().equals(mTrip.getFrom()) &&
                                Integer.parseInt(shipment.getShipmentWeight()) <= (Integer.parseInt(mTrip.getWeight()))) {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                Date searchDateTrip = sdf.parse(mTrip.getDate());
                                Date shipmentsDate = sdf.parse(shipment.getBeforeDate());
                                if (searchDateTrip.after(shipmentsDate)) {

                                    //return;

                                } else {
                                    Toast.makeText(getContext(),shipment.getShipmentName()+" Is A shipoment Name ",Toast.LENGTH_SHORT).show();
                                    mshipmentArrayList.add(shipment);
/*
                                    dummyClass = new DummyClass();

                                    dummyClass.setDate(shipment.getBeforeDate());
                                    dummyClass.setFrom(shipment.getFrom());
                                    dummyClass.setTo(shipment.getTo());
                                    dummyClass.setShipment_name(shipment.getShipmentName());
                                    dummyClass.setTotal_weight(shipment.getShipmentWeight());
                                    dummyClass.setTransportationType("car");
                                    dummyClass.setCreator_id("YiiMKgsYD8e60XXwYtYXLirQHDw1");
                                    dummyClass.setShipper_name("traveller");
                                    dummyClass.setShipper_photo("");
                                    dummyClass.setShipper_rate("5");
                                    arrayList.add(dummyClass);
*/

                                    //Toast.makeText(getContext(),dummyClass.getShipper_name()+" Is A Shipper Name ",Toast.LENGTH_SHORT).show();

                                    /// Toast.makeText(getContext(),mshipmentArrayList.size()+" Is mShipmentsArray size ",Toast.LENGTH_SHORT).show();

                                }

                            } catch (ParseException e) {
                                Toast.makeText(getContext(), e + "", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                }
                if (mshipmentArrayList.size() == 0) {
                    Toast.makeText(getContext(), "No Shipment Found ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(),mshipmentArrayList.get(0).getShipmentName(),Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(),mshipmentArrayList.get(0).getTo(),Toast.LENGTH_SHORT).show();
                     Toast.makeText(getContext(),mshipmentArrayList.get(0).getFrom(),Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Error on Search Operation ", Toast.LENGTH_SHORT).show();
            }
        };

        mDatabaseReferanceShipment.addValueEventListener((valueEventListenerShipmens));
        Toast.makeText(getContext(), "Search about Shipmenets is Ended ", Toast.LENGTH_SHORT).show();


        // return null;
    }



}
