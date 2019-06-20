package com.example.hemamostafa.hatly_1.Model;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemamostafa.hatly_1.Base.MyBaseActivity;
import com.example.hemamostafa.hatly_1.MyNewHome;
import com.example.hemamostafa.hatly_1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SendRequestForTrip extends MyBaseActivity implements View.OnClickListener {

    TextView to ,from ,data , weight , shipmentName,shipperName, start_range_send,end_range_send;
    EditText price, message;
    Button sendBtn;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    String messageString,priceString;


  // iam a traveller

    private DataHolder dataHolder;
    DummyClass mRecevieddummyClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_request_for_trip);
        to= findViewById(R.id.to_txtView_send);
        from =findViewById(R.id.from_txtView_send);
        data =findViewById(R.id.dateNumber_send);
        shipmentName= findViewById(R.id.shipmentName_txtView);
        weight=findViewById(R.id.kilo_total_send);
        start_range_send= findViewById(R.id.start_range_send);
        end_range_send=findViewById(R.id.end_range_send);
        shipperName = findViewById(R.id.shipper_name_send);
        price=findViewById(R.id.price_editText_send);
        sendBtn=findViewById(R.id.sendReuest_btn);
        message=findViewById(R.id.message_editText_send);
        dataHolder= new DataHolder();
        mAuth= FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        sendBtn.setOnClickListener(this);
        Intent intent = this.getIntent();

        if(intent !=null){
            mRecevieddummyClass = (DummyClass) intent.getSerializableExtra("ship");

            if(mRecevieddummyClass != null){
                to.setText(mRecevieddummyClass.getTo());
                from.setText(mRecevieddummyClass.getFrom());
                data.setText(mRecevieddummyClass.getDate());
                shipmentName.setText(mRecevieddummyClass.getShipper_name());
                weight.setText(mRecevieddummyClass.getTotal_weight()+"KG");
                shipperName.setText(mRecevieddummyClass.getShipper_name());


            }
           String str = dataHolder.computeHatlyFees(mRecevieddummyClass.getFrom(),mRecevieddummyClass.getTo()) + " L.E" ;
           start_range_send.setText((dataHolder.computeTravellerFees() -10 ) +" L.E  ~ ") ;
           end_range_send.setText(dataHolder.computeTravellerFees()+5  + " L.E ");



        }


    }

    @Override
    public void onClick(View v) {


        if(v==sendBtn){
            messageString = message.getText().toString();
            priceString = price.getText().toString();
            WriteNewDeal();

        }

    }
    private void WriteNewDeal(){
        ShowProgressBar();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Deal deal= new Deal(currentUser.getUid(),mRecevieddummyClass.getCreator_id(),messageString,priceString,
                mRecevieddummyClass.getFrom(),mRecevieddummyClass.getTo(),mRecevieddummyClass.getDate()
                ,mRecevieddummyClass.getDate(),mRecevieddummyClass.getShipment_name());
        String deal_id= mDatabase.push().getKey();
        deal.setDeal_id(deal_id);
        mDatabase.child("deals").child(deal_id).setValue(deal).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(activity,"Trip Added Succesfully",Toast.LENGTH_SHORT).show();
                HideProgressBar();
                startActivity(new Intent(activity,MyNewHome.class));
            }
        });


    }
}
