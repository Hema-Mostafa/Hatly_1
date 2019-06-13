package com.example.hemamostafa.hatly_1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

import com.example.hemamostafa.hatly_1.Adapter.ItemShipmentAdapter;
import com.example.hemamostafa.hatly_1.Base.MyBaseActivity;
import com.example.hemamostafa.hatly_1.Model.DataHolder;
import com.example.hemamostafa.hatly_1.Model.Item;
import com.example.hemamostafa.hatly_1.Model.Shipment;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class    AddingShipmentDetails extends MyBaseActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private TextView toolbarTitle,date;
    private EditText shipmentName,shipmentNote;
    private AutoCompleteTextView to,from;
    private Button addNewItemBtn,submitShipmentBtn;
    private String selectedDate;
    private String stringTo,stringFrom,stringShipmentName,stringDateField,stringShipmentNote;
    private int mYear, mMonth, mDay;
    private ImageView arrow_back;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mStorageReference;
    private Shipment myShipment,myReceivedShipment;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ItemShipmentAdapter myItemShipmentAdapter;
    private boolean receivedShipmentFlag;
    private DataHolder dataHolder;
    private ArrayAdapter<String> adapterAutoComplete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_shipment_details);
        inititView();

        toolbarTitle.setText("Add Shipment Details");

         //  check if this Activity strart from  MyNewHome or Review ?
        Intent intent = this.getIntent();
        if(intent !=null){
            String stringData = intent.getExtras().getString("Uniqid");
            if(stringData.equals("Review"))
            {

                //myReceivedShipment=(Shipment) getIntent().getSerializableExtra("hema");
                myReceivedShipment= (Shipment)intent.getSerializableExtra("shipmentReview");
                if(myReceivedShipment== null){
                    Toast.makeText(activity,"myReceivedShipment is Null ",Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(activity,"The Activity start From Profile Activity",Toast.LENGTH_SHORT).show();
                Toast.makeText(activity,myReceivedShipment.getItemList().get(0).getItem_name(),Toast.LENGTH_SHORT).show();
                fillFields();
                showRecyclerView();
                receivedShipmentFlag=true;

            }
            if(stringData.equals("MyNewHome"))
            {
                Toast.makeText(activity,"The Activity start From MyNewHome Activity",Toast.LENGTH_SHORT).show();
                // Hide REcycler View
                //Hide Button
            }



        }
        else {
            Toast.makeText(activity,"Intent IS Null",Toast.LENGTH_SHORT).show();

        }

        // End of Check

    }

    private boolean validateData() {
        stringTo = to.getText().toString().trim();
        stringFrom=from.getText().toString().trim();
        stringShipmentName=shipmentName.getText().toString().trim();
        stringDateField = date.getText().toString().trim();
        stringShipmentNote=shipmentNote.getText().toString().trim();

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
        if (stringShipmentName.isEmpty()) {
            shipmentName.setError(getString(R.string.required_field));
            shipmentName.requestFocus();
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
        // Firebase Object Inialization
        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();
        mFirebaseStorage = FirebaseStorage.getInstance();
        mStorageReference = mFirebaseStorage.getReference().child("shipment_img");
        // all objects Iniallization
        dataHolder= new DataHolder();

        // Views Inialization
        toolbar=findViewById(R.id.custom_toolBar);
        toolbarTitle=findViewById(R.id.toolbar_title);
        arrow_back=findViewById(R.id.arrow_back);
        to=findViewById(R.id.to_editText_sh);
        from=findViewById(R.id.from_editText_sh);
        date =findViewById(R.id.before_date_editText_sh);
        shipmentName=findViewById(R.id.shipment_name_editText);
        shipmentNote=findViewById(R.id.shipment_note_editText);
        recyclerView=findViewById(R.id.shippment_item_recyclerView);
        addNewItemBtn = findViewById(R.id.add_new_item_btn);
        submitShipmentBtn=findViewById(R.id.submit_shipment_btn);

        //view setOnClickListenr
        submitShipmentBtn.setOnClickListener(this);
        addNewItemBtn.setOnClickListener(this);
        date.setOnClickListener(this);
        arrow_back.setOnClickListener(this);
        // all flags Inialization
        receivedShipmentFlag=false;

        // Add Adapter to AutoCompleteTextView(To , From)
        adapterAutoComplete= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                android.R.id.text1, dataHolder.getGovernmentNames());
        to.setThreshold(0);
        to.setAdapter(adapterAutoComplete);
        from.setThreshold(0);
        from.setAdapter(adapterAutoComplete);


    }
    private boolean compareDate() throws ParseException {
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
        if (v == date){
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DatePickerDialogTheme,new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    selectedDate =dayOfMonth + "/" + (month + 1) + "/" + year ;
                    date.setText(selectedDate);
                }
            },mYear,mMonth,mDay);

            datePickerDialog.show();

        }
        if (v == addNewItemBtn) {
            if (receivedShipmentFlag == true) {   // if of ReceivedShipment is exsist
                Toast.makeText(activity,myReceivedShipment.getBeforeDate(),Toast.LENGTH_SHORT).show();

                if (validateData() == true) {    // if of Validation
                    Toast.makeText(activity,myReceivedShipment.getBeforeDate(),Toast.LENGTH_SHORT).show();
                    selectedDate=stringDateField;

                   try {
                        if (compareDate() == true) {
                            Log.e("datee", "compareDaTe Suscces");
                            date.setError(null);


                            myReceivedShipment.setShipmentName(stringShipmentName);
                            myReceivedShipment.setShipment_Note(stringShipmentNote);
                            myReceivedShipment.setTo(stringTo);
                            myReceivedShipment.setFrom(stringFrom);
                            myReceivedShipment.setBeforeDate(stringDateField);

                            Intent intent = new Intent(AddingShipmentDetails.this, AddShippingItemDetails_new.class);
                            intent.putExtra("myShipment", myReceivedShipment);


                            startActivity(intent);


                        } else
                            date.setError("In-Valid Date");

                    } catch (ParseException e) {
                       e.printStackTrace();
                        Log.e("datee", "catch data error");
                    }

                } else  // else of Validation
                    return;


            } else {                          // else of ReceivedShipment is exsist
                if (validateData() == true) {

                    try {
                        if (compareDate() == true) {
                            Log.e("datee", "compareDaTe Suscces");
                            date.setError(null);
                            myShipment = new Shipment(stringShipmentName, stringShipmentNote, stringFrom, stringTo, selectedDate);
                            Intent intent = new Intent(AddingShipmentDetails.this, AddShippingItemDetails_new.class);

                            intent.putExtra("myShipment", myShipment);
                            //intent.putExtra("shipmentDetails","AddNewItem");
                            startActivity(intent);


                        } else
                            date.setError("In-Valid Date");

                    } catch (ParseException e) {
                        e.printStackTrace();
                        Log.e("datee", "catch data error");
                    }

                } else {
                    return;
                }


            }


        }

        if (v==submitShipmentBtn){

            int totalWeight=0;

            for(int i=0 ; i<myReceivedShipment.getItemList().size() ; i++){
                totalWeight+=Integer.parseInt(myReceivedShipment.getItemList().get(i).getItem_weight());
            }
            // complete other attribute of myReceivedShipment object
            myReceivedShipment.setShipmentWeight(String.valueOf(totalWeight));
            String dummy=myReceivedShipment.getItemList().get(0).getItem_photo();
            myReceivedShipment.setShipmentPhoto(myReceivedShipment.getItemList().get(0).getItem_photo());

            Toast.makeText(activity,myReceivedShipment.getShipmentWeight(),Toast.LENGTH_SHORT).show();
            FirebaseUser currentUser = mAuth.getCurrentUser();

            String shipment_id=mDatabase.push().getKey();
            myReceivedShipment.setShipment_id(shipment_id);
            myReceivedShipment.setCreator_id(currentUser.getUid());


            // The Block of this Code to Upload image in Firebase Storage then take Url of it to store in RealtimeDatabase
            Uri selectedsImageUri =Uri.parse( dummy );
            final StorageReference newRef=mStorageReference.child(selectedsImageUri.getLastPathSegment());

            UploadTask uploadTask = newRef.putFile(selectedsImageUri);
                        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if (!task.isSuccessful()) {
                                    throw task.getException();
                                }
                                // Continue with the task to get the download URL
                                return newRef.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    Uri downloadUri = task.getResult();
                                    myReceivedShipment.setShipmentPhoto(String.valueOf(downloadUri));
                                    Toast.makeText(activity," GET URL Image Succesfully ",Toast.LENGTH_SHORT).show();
                        Toast.makeText(activity,downloadUri.toString(),Toast.LENGTH_SHORT).show();
                        Log.e("img",String.valueOf(downloadUri));
                    } else {
                        // Handle failures
                        Toast.makeText(activity,"The Error in GET URL Image to ",Toast.LENGTH_SHORT).show();
                        // ...
                    }
                }
            });

            /*
            newRef.putFile(selectedsImageUri).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(activity,"The Error in Uploading Image to firebase Storage ",Toast.LENGTH_SHORT).show();

                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    newRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String generatedFilePath = uri.toString();
                            myReceivedShipment.setShipmentPhoto(generatedFilePath);
                            Toast.makeText(activity," Uploading Image to firebase Storage Succesfully ",Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(activity," Uploading Image to firebase Storage Failed ",Toast.LENGTH_SHORT).show();

                        }
                    });


                    Task<Uri> downloadUri  = taskSnapshot.getStorage().getDownloadUrl();
                    if(downloadUri.isSuccessful()){
                        String generatedFilePath = downloadUri.getResult().toString();
                        myReceivedShipment.setShipmentPhoto(generatedFilePath);


                    }
                }
         //   });

*/



            //mStorageReference.child("image/"+selectedsImageUri.getLastPathSegment()); // The path of image


/*
            //            // The Block of this Code to Upload image in Firebase Storage then take Url of it to store in RealtimeDatabase

            Uri selectedsImageUri =Uri.parse( myReceivedShipment.getShipmentPhoto());
            mStorageReference.child("images/"+selectedsImageUri.getLastPathSegment()); // The path of image

            // Upload File To Database Storage
            mStorageReference.putFile(selectedsImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    // ...
                    mStorageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                       myReceivedShipment.setShipmentPhoto(uri.toString());
                       Toast.makeText(activity," Uploading Image to firebase Storage Succesfully  ",Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(activity,"The Error in Uploading Image to firebase Storage ",Toast.LENGTH_SHORT).show();

                }
            }) ; */

            mDatabase.child("shipments").child(shipment_id).setValue(myReceivedShipment).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(activity,"Write Operation is done",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddingShipmentDetails.this,MyNewHome.class));
                    } else {
                        Toast.makeText(activity,"Write Operation is Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });




        }
        if(v==arrow_back){ // arrow back in toolbar go to Activity Home or Review Activity
            if(receivedShipmentFlag){
                startActivity(new Intent(activity,Profile.class));
            }
            else
                startActivity(new Intent(activity,MyNewHome.class));

        }

    }

    private void showRecyclerView(){

        //addItems();

        myItemShipmentAdapter= new ItemShipmentAdapter(myReceivedShipment.getItemList());

        myItemShipmentAdapter.setOnCardviewClickListener(new ItemShipmentAdapter.OnItemClickListenerInterface() {
            @Override
            public void onClick(int position, Item item) {


                Toast.makeText(activity,item.getItem_weight()+"  pos :" +position,Toast.LENGTH_SHORT).show();
/*
                Intent intent  = new Intent(AddingShipmentDetails.this, AddShippingItemDetails_new.class);

                intent.putExtra("myReceivedShipment", myReceivedShipment);
                intent.putExtra("pos",position);
                intent.putExtra("shipmentDetails","recyclerViewClicked");
                startActivity(intent);

*/


            }
        });
        myItemShipmentAdapter.setOncloseIconClickListener(new ItemShipmentAdapter.OnItemClickListenerInterface() {
            @Override
            public void onClick(int position, Item item) {
                // closeIcon Clicked

                myReceivedShipment.getItemList().remove(position);
                myItemShipmentAdapter= new ItemShipmentAdapter(myReceivedShipment.getItemList());
                recyclerView.setAdapter(myItemShipmentAdapter);
                ShowMessage("Delete the item ", "");
                if (myReceivedShipment.getItemList().size()==0){
                    submitShipmentBtn.setVisibility(View.GONE);
                }

            }



        });

        linearLayoutManager=new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myItemShipmentAdapter);

        // Make the Buttton Visible in case of RecyclerView Is Visible
         submitShipmentBtn.setVisibility(View.VISIBLE);
    }

    private void fillFields(){
        to.setText(myReceivedShipment.getTo());
        from.setText(myReceivedShipment.getFrom());
        shipmentName.setText(myReceivedShipment.getShipmentName());
        date.setText(myReceivedShipment.getBeforeDate());
        shipmentNote.setText(myReceivedShipment.getShipment_Note());

    }




}
