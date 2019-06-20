package com.example.hemamostafa.hatly_1;

        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.hemamostafa.hatly_1.Base.MyBaseActivity;
        import com.example.hemamostafa.hatly_1.Model.DataHolder;
        import com.example.hemamostafa.hatly_1.Model.Deal;
        import com.example.hemamostafa.hatly_1.Model.DummyClass;
        import com.example.hemamostafa.hatly_1.Model.TripDummyClass;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class SendRequest extends MyBaseActivity implements View.OnClickListener {


    TextView to, from, data, weight, travellerName, start_range_send, end_range_send;
    EditText price, message;
    Button sendBtn;

    private DataHolder dataHolder;
    TripDummyClass mReceviedTripDummyClass;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    String messageString,priceString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_request);
        to = findViewById(R.id.to_txtView_send);
        from = findViewById(R.id.from_txtView_send);
        data = findViewById(R.id.dateNumber_send);
        weight = findViewById(R.id.kilo_total_send);
        start_range_send = findViewById(R.id.start_range_send);
        end_range_send = findViewById(R.id.end_range_send);
        travellerName = findViewById(R.id.shipper_name_send);
        dataHolder = new DataHolder();
        price=findViewById(R.id.price_editText_send);
        sendBtn=findViewById(R.id.sendReuest_btn);
        message=findViewById(R.id.message_editText_send);
        sendBtn.setOnClickListener(this);

        mAuth= FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Intent intent = this.getIntent();

        if (intent != null) {
            mReceviedTripDummyClass = (TripDummyClass) intent.getSerializableExtra("trip");

            if (mReceviedTripDummyClass != null) {
                to.setText(mReceviedTripDummyClass.getTo());
                from.setText(mReceviedTripDummyClass.getFrom());
                data.setText(mReceviedTripDummyClass.getDate());
                weight.setText(mReceviedTripDummyClass.getWeight() + "KG");
                travellerName.setText(mReceviedTripDummyClass.getTraveller_name());


            }

            String str = dataHolder.computeHatlyFees(mReceviedTripDummyClass.getFrom(), mReceviedTripDummyClass.getTo()) + " L.E";
            start_range_send.setText((dataHolder.computeTravellerFees() - 10) + " L.E  ~ ");
            end_range_send.setText(dataHolder.computeTravellerFees() + 5 + " L.E ");
        }
    }

    public void WriteNewDeal(){
        ShowProgressBar();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Deal deal= new Deal(mReceviedTripDummyClass.getCreator_id(),currentUser.getUid(),messageString,priceString,
                mReceviedTripDummyClass.getFrom(),mReceviedTripDummyClass.getTo(),mReceviedTripDummyClass.getDate()
                ,mReceviedTripDummyClass.getWeight(),mReceviedTripDummyClass.getShipmentName());
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
    @Override
    public void onClick(View v) {
        if(v==sendBtn){
            messageString = message.getText().toString();
            priceString = price.getText().toString();
            WriteNewDeal();

        }
    }
}