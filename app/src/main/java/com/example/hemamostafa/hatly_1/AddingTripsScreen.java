package com.example.hemamostafa.hatly_1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ImageViewCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemamostafa.hatly_1.Base.MyBaseActivity;
import com.example.hemamostafa.hatly_1.Model.DataHolder;
import com.example.hemamostafa.hatly_1.Model.Trip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
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

public class AddingTripsScreen extends MyBaseActivity implements View.OnClickListener {


    private ImageView carImage,trainImage,busImage;
    private EditText  weight;
    private AutoCompleteTextView to,from;
    private TextView date;
    private Button done_btn;
    private int mYear, mMonth, mDay;
    private boolean imageFlag;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DataHolder dataHolder;

    ArrayList<Trip> tripArrayList;
    ArrayAdapter<String> adapterAutoComplete;
    private String selectedDate;
    private String stringTo,stringFrom,stringWeight,stringDateField,stringTransportationType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_trips_screen);
        inititView();
    }

    /*
    public void whenImageSelceted(View view){

        int color;
        if (view.getId() == R.id.img_car){
            Toast.makeText(activity,"image Clicked",Toast.LENGTH_SHORT).show();
            color = R.color.mainColor;
            ImageViewCompat.setImageTintList(carImage, ColorStateList.valueOf(color));
            carImage.setBackgroundColor(R.drawable.img_after_clicked_style);

        }
        else if( view.getId() ==R.id.img_bus){
            Toast.makeText(activity,"image Clicked",Toast.LENGTH_SHORT).show();
            color = R.color.mainColor;
            ImageViewCompat.setImageTintList(busImage, ColorStateList.valueOf(color));
            busImage.setBackgroundColor(R.drawable.img_after_clicked_style);
        }

        else if (view.getId() == R.id.img_train){
            Toast.makeText(activity,"image Clicked",Toast.LENGTH_SHORT).show();
            color = R.color.mainColor;
           // ImageViewCompat.setImageTintList(trainImage, ColorStateList.valueOf(color));
            trainImage.setImageTintList(ColorStateList.valueOf(color));
            trainImage.setBackgroundColor(R.drawable.img_after_clicked_style);
        }


    }
    */

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
            for(int i =0 ; i< dataHolder.governmentNames.size();i++){
                if(dataHolder.governmentNames.get(i).equals(stringTo)) {
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
            for(int i =0 ; i< dataHolder.governmentNames.size();i++){
                if(dataHolder.governmentNames.get(i).equals(stringFrom)) {
                    existSource = true;
                    break;
                }
            }
            if (existSource == false){
                to.setError("in-valid Place Source");
                to.requestFocus();
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
        if(!imageFlag){

            Toast.makeText(activity,"Select Transportation Type", Toast.LENGTH_SHORT).show();
            return false;
        }


        return  true;
    }

    private void inititView(){

        // Firebase Object Inialization
        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();
        // all objects Iniallization
        dataHolder= new DataHolder();
        tripArrayList= new ArrayList<>();

        // Views Inialization
        carImage = findViewById(R.id.img_car);
        busImage = findViewById(R.id.img_bus);
        trainImage=findViewById(R.id.img_train);
        to=findViewById(R.id.to_editT);
        from=findViewById(R.id.from_editT);
        weight=findViewById(R.id.weight_editText);
        date=findViewById(R.id.departure_editT);
        done_btn=findViewById(R.id.doneBtn);

        //view setOnClickListenr
        done_btn.setOnClickListener(this);
        date.setOnClickListener(this);
        carImage.setOnClickListener(this);
        busImage.setOnClickListener(this);
        trainImage.setOnClickListener(this);

        // all flags Inialization
        imageFlag = false;

        // Add Adapter to AutoCompleteTextView(To , From)
        adapterAutoComplete= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                android.R.id.text1, dataHolder.getGovernmentNames());
        to.setThreshold(0);
        to.setAdapter(adapterAutoComplete);
        from.setThreshold(0);
        from.setAdapter(adapterAutoComplete);


    }



    @Override
    public void onClick(View v) {
       if ( v==done_btn){

           if (validateData()==true){
               try {
                   if (compareDate()== true){
                       Log.e("datee","compareDaTe Suscces");
                       date.setError(null);

                       WriteNewTrip();


                   }
                   else
                       date.setError("In-Valid Date");

               } catch (ParseException e) {
                   e.printStackTrace();
                   Log.e("datee","catch data error");
               }

           }
           else
               return;

       }
       if (v == date){
           // Get Current Date
           final Calendar c = Calendar.getInstance();
           mYear = c.get(Calendar.YEAR);
           mMonth = c.get(Calendar.MONTH);
           mDay = c.get(Calendar.DAY_OF_MONTH);
           DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DatePickerDialogTheme,new DatePickerDialog.OnDateSetListener() {
               @Override
               public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                   selectedDate=dayOfMonth + "/" + (month + 1) + "/" + year ;
                   date.setText(selectedDate);
               }
           },mYear,mMonth,mDay);

           datePickerDialog.show();

       }
       if(v== carImage){
           stringTransportationType= "car";

           carImage.setColorFilter(ContextCompat.getColor(activity, R.color.black));
           busImage.setColorFilter(ContextCompat.getColor(activity,R.color.white));
           trainImage.setColorFilter(ContextCompat.getColor(activity,R.color.white));
           imageFlag =true;
       }
       if (v== trainImage){
           stringTransportationType= "train";

           trainImage.setColorFilter(ContextCompat.getColor(activity, R.color.black));
           busImage.setColorFilter(ContextCompat.getColor(activity,R.color.white));
           carImage.setColorFilter(ContextCompat.getColor(activity,R.color.white));
           imageFlag =true;
       }
       if (v==busImage){
           stringTransportationType= "bus";
           busImage.setColorFilter(ContextCompat.getColor(activity,R.color.black));
           carImage.setColorFilter(ContextCompat.getColor(activity,R.color.white));
           trainImage.setColorFilter(ContextCompat.getColor(activity,R.color.white));
           imageFlag =true;
       }




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

    public void WriteNewTrip(){
        FirebaseUser currentUser = mAuth.getCurrentUser();

        Log.e("user",currentUser.getUid());
        Trip mTrip=new Trip(stringTo,stringFrom,selectedDate,stringWeight,stringTransportationType);

        String trip_id=mDatabase.push().getKey();
        mTrip.setTrip(trip_id);
        mTrip.setCreator_id(currentUser.getUid());

        mDatabase.child("trips").child(trip_id).setValue(mTrip);

    }
}
