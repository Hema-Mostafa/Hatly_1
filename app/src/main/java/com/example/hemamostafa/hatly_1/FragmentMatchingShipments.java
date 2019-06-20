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
import com.example.hemamostafa.hatly_1.Base.MyBaseFragment;
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
public class FragmentMatchingShipments extends MyBaseFragment {
    RecyclerView recyclerview;
    LinearLayoutManager layoutManager;
    MatchingShipmentAdapter adapter;
    View view;
    DummyClass dummyClass;
    ArrayList<DummyClass> arrayList;
    private ArrayList<User> mUserArrayList;
    private ArrayList<Shipment> mshipmentArrayList;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReferanceShipment,mDatabaseReferanceUser;
    private ValueEventListener valueEventListenerShipmens,valueEventListenerUsers;


    Trip mTrip;

    public void setmTrip(Trip mTrip) {
        this.mTrip = mTrip;
    }

    public FragmentMatchingShipments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_matching_shipments, container, false);

        // Firebase Object
        mAuth = FirebaseAuth.getInstance();
        mDatabaseReferanceUser = FirebaseDatabase.getInstance().getReference().child("users");
        Toast.makeText(getContext(),"trip is "+ mTrip.getFrom() ,Toast.LENGTH_SHORT).show();
/*

        // ArrayList Initalization
        mshipmentArrayList = new ArrayList<>();
        mUserArrayList = new ArrayList<>();
        arrayList = new ArrayList<>();

        recyclerview = view.findViewById(R.id.match_shipments_reecycler_view_);
        layoutManager = new LinearLayoutManager(getContext());
        addDummyValue();
        adapter = new MatchingShipmentAdapter(arrayList);

        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);

*/
//            searchAboutShipment();

        return view;
    }


    public  void  addDummyValue(){
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
        arrayList.add(new DummyClass("dsd","from",
                "car","26/10",
               "dsdsd","name sh"
                ,"6",
                "no photo","dsds"
                ,"sds"));

        arrayList.add(new DummyClass("dsd","from",
                "car","26/10",
                "dsdsd","name sh"
                ,"6",
                "no photo","dsds"
                ,"sds"));



    }

    private void searchAboutShipment(){

        String from = mTrip.getFrom();
        String to =mTrip.getTo();
        String date=mTrip.getDate();



        Toast.makeText(getContext(),"Search about Shipments Start !  ",Toast.LENGTH_SHORT).show();
        mDatabaseReferanceShipment=FirebaseDatabase.getInstance().getReference().child("shipments");
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
                                    dummyClass = new DummyClass();
                                    Query query = FirebaseDatabase.getInstance().getReference().child("users")
                                            .orderByChild("user_id")
                                            .equalTo(shipment.getCreator_id());

                                    valueEventListenerUsers = new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            mUserArrayList.clear();
                                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                User user = snapshot.getValue(User.class);
                                                if (user.getUser_id().equals(shipment.getCreator_id())) {

                                                    mUserArrayList.add(user);
                                                    // Toast.makeText(getContext() , user.getUser_mail()+"Name is "+user.getUser_name() , Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                            // for (int i = 0; i<mshipmentArrayList.s)
                                            //Toast.makeText(getContext() , mUserArrayList.get(0).getUser_name() + "User Name" , Toast.LENGTH_SHORT).show();

                                            //arrayList.clear();
                                            for (int i = 0; i < mUserArrayList.size(); i++) {
                                                dummyClass = new DummyClass();

                                                dummyClass.setDate(shipment.getBeforeDate());
                                                dummyClass.setFrom(shipment.getFrom());
                                                dummyClass.setTo(shipment.getTo());
                                                dummyClass.setShipment_name(shipment.getShipmentName());
                                                dummyClass.setTotal_weight(shipment.getShipmentWeight());
                                                dummyClass.setTransportationType("car");
                                                dummyClass.setCreator_id(mUserArrayList.get(i).getUser_id());
                                                dummyClass.setShipper_name(mUserArrayList.get(i).getUser_name());
                                                dummyClass.setShipper_photo("");
                                                dummyClass.setShipper_rate("5");
                                                arrayList.add(dummyClass);

                                            }


                                            adapter = new MatchingShipmentAdapter(arrayList);
                                            adapter.setOnButtonClickListener(new MatchingShipmentAdapter.OnItemClickListenerInterface() {
                                                @Override
                                                public void onClick(int position, DummyClass dummyClass) {
                                                    Intent intent = new Intent(getContext(), SendRequestForTrip.class);
                                                    intent.putExtra("ship", dummyClass);
                                                    startActivity(intent);

                                                }
                                            });
                                            recyclerview.setLayoutManager(layoutManager);
                                            recyclerview.setAdapter(adapter);

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    };
                                    query.addValueEventListener(valueEventListenerUsers);
                                    //Toast.makeText(getContext(),dummyClass.getShipper_name()+" Is A Shipper Name ",Toast.LENGTH_SHORT).show();
                                    mshipmentArrayList.add(shipment);
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
                    //Toast.makeText(getContext(),mshipmentArrayList.get(0).getShipmentName(),Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getContext(),mshipmentArrayList.get(0).getTo(),Toast.LENGTH_SHORT).show();
                    // Toast.makeText(getContext(),mshipmentArrayList.get(0).getFrom(),Toast.LENGTH_SHORT).show();

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
//    public void search() {
//        Intent intent = getActivity().getIntent();
//        // ID = intent.getStringExtra("ID");
//        String fromt = intent.getStringExtra("textfrom");
//
//        Query query = dref.orderByChild("source").equalTo(textfrom);
//        //   Toast.makeText(Buses.this,query.toString()+"jk" ,Toast.LENGTH_SHORT).show();
//
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    //   Log.e(TAG + "out", String.valueOf(snapshot.child("job")) + "" + String.valueOf(snapshot.child("name").getValue()));
//                    String destinationValue = (String) snapshot.child("destination").getValue();
//                    String dateValue = (String) snapshot.child("day").getValue();
//
//                    if (destinationValue.equals(textto) && dateValue.equals(go_date)) {
//                        String k = snapshot.getKey();
//
//                        String from = (String) snapshot.child("source").getValue();
//                        String to = (String) snapshot.child("destination").getValue();
//                        String bus_num = (String) snapshot.child("buse_number").getValue();
//                        String dat_go = (String) snapshot.child("day").getValue();
//                        String seatss_ = (String) snapshot.child("seats").getValue();
//                        String driver_name = (String) snapshot.child("Driver_name").getValue();
//                        String duration = (String) snapshot.child("duration").getValue();
//                        String time = (String) snapshot.child("time_for_go").getValue();
//                        String price = (String) snapshot.child("price").getValue();
//                        pr.dismiss();
//                        temp.add(k);
//                        temp.daily_booking.add(k);
//                        Log.i("fff", "text to  " + temp.getKeys());
//                        Object_Class model = new Object_Class(from, to, bus_num, dat_go, seatss_, driver_name, duration, time, price);
//                        objects.add(model);
//
//                    }
//
//
//                }
//
//
//                if (objects.size() != 0) {
//
//                    rec.setVisibility(View.VISIBLE);
//                    adapter = new AdapterItems(objects, getContext());
//                    rec.setAdapter(adapter);
//
//                } else {
//                    rec.setVisibility(View.GONE);
//                    pr.dismiss();
//
//                    //searchResult.setVisibility(View.VISIBLE);
//
//                    // show no one
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//    }




}
