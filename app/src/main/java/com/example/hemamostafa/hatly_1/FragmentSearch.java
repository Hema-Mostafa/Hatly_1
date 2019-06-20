package com.example.hemamostafa.hatly_1;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemamostafa.hatly_1.Adapter.MyShipmentAdpter;
import com.example.hemamostafa.hatly_1.Adapter.MyTripsAdpter;
import com.example.hemamostafa.hatly_1.Base.MyBaseFragment;
import com.example.hemamostafa.hatly_1.Model.DataHolder;
import com.example.hemamostafa.hatly_1.Model.Shipment;
import com.example.hemamostafa.hatly_1.Model.Trip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSearch extends Fragment implements View.OnClickListener {

    private AutoCompleteTextView to,from;
    private EditText  weight ;
    private TextView date;
    private Button shipmentBtn,tripBtn,serachBtn;
    private RecyclerView recyclerViewSearch;
    private View view;
    private int mYear, mMonth, mDay;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase,mDatabaseReferance;
    private ArrayList<Trip> tripArrayList;

    private String stringTo,stringFrom,stringWeight,stringDateField,selectedDate;
    ArrayAdapter<String> adapterAutoComplete;
    ArrayList<String>governmentNames;
    int flagSelectedSearch;
   LinearLayoutManager linearLayoutManager ;
   ValueEventListener valueEventListenerTrips,valueEventListenerShipmens;
   private MyTripsAdpter myTripsAdpter;
   private MyShipmentAdpter myShipmentAdpter;

    private ArrayList<Shipment>shipmentArrayList;


    public FragmentSearch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_search, container, false);
        inititView();
        return view;
    }

    private void addlist(){

        governmentNames.add("alexandria");
        governmentNames.add("cairo");
        governmentNames.add("giza");
        governmentNames.add("faiyum");
        governmentNames.add("beni suef");
        governmentNames.add("minya");
        governmentNames.add("asyut");
        governmentNames.add("sohag");
        governmentNames.add("qena");
        governmentNames.add("luxor");
        governmentNames.add("aswan");


    }


    private boolean validateData() {
        stringTo = to.getText().toString().trim();
        stringFrom=from.getText().toString().trim();
        stringWeight=weight.getText().toString().trim();
        stringDateField =date.getText().toString().trim();

        if (stringTo.isEmpty()) {
            to.setError(getString(R.string.required_field));
            to.requestFocus();
            return false;

        }
        else{
            boolean existDistination=false;
            for(int i =0 ; i< governmentNames.size();i++){
                if(governmentNames.get(i).equals(stringTo)) {
                    existDistination = true;
                    break;
                }
            }
            if (existDistination == false){
                to.setError("In-valid place distination");
                to.requestFocus();
                return false;

            }
        }
        if (stringFrom.isEmpty()) {
            from.setError(getString(R.string.required_field));
            from.requestFocus();
            return false;

        }
        else{
            boolean existSource=false;
            for(int i =0 ; i< governmentNames.size();i++){
                if(governmentNames.get(i).equals(stringFrom)) {
                    existSource = true;
                    break;
                }
            }
            if (existSource == false){
                from.setError("in-valid Place Source");
                from.requestFocus();
                return false;

            }
        }
        if (stringWeight.isEmpty()) {
            weight.setError(getString(R.string.required_field));
            weight.requestFocus();
            return false;
        }
        if (stringDateField.isEmpty()) {
            date.setError(getString(R.string.required_field));
            date.requestFocus();
            return false;
        }

        return  true;
    }

    private void inititView(){

         //Firebase Object Inialization
        mAuth= FirebaseAuth.getInstance();
        //mDatabase= FirebaseDatabase.getInstance().getReference();
        // all objects Iniallization
        flagSelectedSearch = 0; // 1 for Trips , 0 for shipment;
        tripArrayList= new ArrayList<>();
        shipmentArrayList = new ArrayList<>();

        // Views Inialization
        recyclerViewSearch = view.findViewById(R.id.serach_recycler_view);
        linearLayoutManager = new LinearLayoutManager(getContext()); //activity = getContext()
        to=view.findViewById(R.id.to_editText_search);
        from=view.findViewById(R.id.from_editText_search);
        weight=view.findViewById(R.id.weight_editText_search);
        date=view.findViewById(R.id.departure_editT_search);
        shipmentBtn=view.findViewById(R.id.search_for_shipment_btn);
        tripBtn=view.findViewById(R.id.search_for_trip_btn);
        serachBtn=view.findViewById(R.id.search_btn);


        //view setOnClickListenr
        shipmentBtn.setOnClickListener(this);
        date.setOnClickListener(this);
        tripBtn.setOnClickListener(this);
        serachBtn.setOnClickListener(this);

        // all flags Inialization

         //Add Adapter to AutoCompleteTextView(To , From)
        governmentNames= new ArrayList<>();
        addlist();

        adapterAutoComplete= new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,
                android.R.id.text1, governmentNames);
        to.setThreshold(0);
        to.setAdapter(adapterAutoComplete);
        from.setThreshold(0);
        from.setAdapter(adapterAutoComplete);

       /*
*/

    }

    public boolean compareDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date strDate = sdf.parse(selectedDate);
        if (new Date().after(strDate)) {
            Log.e("datee","in-valid date");

            return false;

        }
        else{

            Log.e("datee","valid date");
            return true;
        }

    }
    @Override

    public void onClick(View v) {

        if(v==tripBtn){
            flagSelectedSearch =1;
            tripBtn.setBackgroundResource(R.drawable.btn_style_2);
            tripBtn.setTextColor(Color.parseColor("#0077B5"));

            shipmentBtn.setBackgroundResource(R.drawable.btn_style);
            shipmentBtn.setTextColor(Color.parseColor("#FFFFFF"));


        }
        if(v==shipmentBtn){
            flagSelectedSearch=0;
            shipmentBtn.setBackgroundResource(R.drawable.btn_style_2);
            shipmentBtn.setTextColor(Color.parseColor("#0077B5"));

            tripBtn.setBackgroundResource(R.drawable.btn_style);
            tripBtn.setTextColor(Color.parseColor("#FFFFFF"));

        }

        if(v==date){
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.DatePickerDialogTheme,new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    selectedDate=dayOfMonth + "/" + (month + 1) + "/" + year ;
                    date.setText(selectedDate);
                }
            },mYear,mMonth,mDay);
            datePickerDialog.show();
        }
        if(v==serachBtn){

            if (validateData()==true)
            {

                try {

                    if (compareDate()== true){
                        date.setError(null);
                        if(flagSelectedSearch==0) { // user selected user about  shipments

                            Toast.makeText(getContext(),"Search about Shipments ",Toast.LENGTH_SHORT).show();

                            // user selected search about trips
                            Toast.makeText(getContext(),"Search about Shipment ",Toast.LENGTH_SHORT).show();
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
                                            if(shipment.getTo().equals(stringTo) && shipment.getFrom().equals(stringFrom)&&
                                                    Integer.parseInt(shipment.getShipmentWeight()) <= (Integer.parseInt(stringWeight)))
                                            {
                                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                                try {
                                                    Date searchDate = sdf.parse(selectedDate);
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

                                    recyclerViewSearch.setLayoutManager(linearLayoutManager);
                                    myShipmentAdpter =new MyShipmentAdpter(shipmentArrayList);
                                    recyclerViewSearch.setAdapter(myShipmentAdpter);

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
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Log.e("flag",databaseError.toString());

                                }
                            };
                            Toast.makeText(getContext(),"Search about Shipmenets is Ended ",Toast.LENGTH_SHORT).show();
                            mDatabaseReferance.addValueEventListener((valueEventListenerShipmens));

                        }

                        else if(flagSelectedSearch==1)

                        {
                            // user selected search about trips
                            Toast.makeText(getContext(),"Search about Trips ",Toast.LENGTH_SHORT).show();
                            mDatabase = FirebaseDatabase.getInstance().getReference().child("trips");
                            valueEventListenerTrips = new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    tripArrayList.clear();
                                    for (DataSnapshot tripSnapshot : dataSnapshot.getChildren()) {
                                        Trip trip = tripSnapshot.getValue(Trip.class);
                                        //Toast.makeText(activity, trip.toString(), Toast.LENGTH_SHORT).show();
                                        if(trip.getCreator_id().equals(mAuth.getUid())){
                                            continue;
                                        }
                                        else {
                                            if (trip.getTo().equals(stringTo) && trip.getFrom().equals(stringFrom) &&
                                                    Integer.parseInt(trip.getWeight())>= (Integer.parseInt(stringWeight)))

                                            {
                                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                                try {
                                                    Date searchDate = sdf.parse(selectedDate);
                                                    Date tripDate = sdf.parse(trip.getDate());
                                                    if (searchDate.after(tripDate)) {

                                                        //return;
                                                        tripArrayList.add(trip);

                                                    } else {

                                                    }

                                                } catch (ParseException e) {
                                                    Toast.makeText(getContext(), e + "", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                        }

                                    }

                                    recyclerViewSearch.setLayoutManager(linearLayoutManager);
                                    myTripsAdpter = new MyTripsAdpter(tripArrayList);
                                    recyclerViewSearch.setAdapter(myTripsAdpter);
                                    myTripsAdpter.setOnCardViewClickListener(new MyTripsAdpter.OnItemClickListenerInterface() {
                                        @Override
                                        public void onClick(int position, Trip trip) {
                                           // Toast.makeText(getContext(),"The card is Clicked"+ position+"",Toast.LENGTH_SHORT).show();
                                           // startActivity(new Intent(activity,DealsAndMatchshipments.class));

                                        }
                                    });
                                    myTripsAdpter.setOnArrowClickListener(new MyTripsAdpter.OnItemClickListenerInterface() {
                                        @Override
                                        public void onClick(int position, Trip trip) {
                                            //Toast.makeText(activity,"The arrow is Clicked"+ position+"",Toast.LENGTH_SHORT).show();

                                        }
                                    });


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Log.e("flag",databaseError.toString());

                                }
                            };
                            Toast.makeText(getContext(),"Search about Trips is Ended ",Toast.LENGTH_SHORT).show();
                            mDatabase.addValueEventListener(valueEventListenerTrips);
                        }

                    }
                    else
                        date.setError("In-Valid Date");

                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.e("datee", "catch data error");
                }

            }
            else{
                Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
                return;
            }

        }

    }
}
