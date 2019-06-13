package com.example.hemamostafa.hatly_1;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.hemamostafa.hatly_1.Base.MyBaseActivity;
        import com.example.hemamostafa.hatly_1.Model.DataHolder;
        import com.example.hemamostafa.hatly_1.Model.Item;
        import com.example.hemamostafa.hatly_1.Model.Shipment;

public class ReviewPrices extends MyBaseActivity implements View.OnClickListener{

    private TextView totalWeight,travellerFees,discount,hatlyFees;
    private Button doneBtn;
    private DataHolder dataHolder;
    private Shipment myReceivedShipment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_prices);
        inititView();
        setData();
    }

    private void inititView(){
            // Received Object from previous Activity
            myReceivedShipment=(Shipment) getIntent().getSerializableExtra("myShipment");
            // Views Inialization
            totalWeight=findViewById(R.id.total_weight_num);
            travellerFees=findViewById(R.id.traveller_fees_num);
            hatlyFees=findViewById(R.id.hatly_fees_num);
            discount=findViewById(R.id.discount_num);
            doneBtn=findViewById(R.id.done_btn);

            // all objects Iniallization
            dataHolder= new DataHolder();


            //view setOnClickListenr
            doneBtn.setOnClickListener(this);



        }
    private void setData(){
        int weightSum=0;

        for(int i=0 ; i<myReceivedShipment.getItemList().size() ; i++){
            weightSum+=Integer.parseInt(myReceivedShipment.getItemList().get(i).getItem_weight());
        }
       totalWeight.setText(weightSum +" Kg");
        hatlyFees.setText(dataHolder.computeHatlyFees(myReceivedShipment.getFrom(),myReceivedShipment.getTo()) + " L.E");
        travellerFees.setText((dataHolder.computeTravellerFees() -10 ) +" L.E  ~ " + (dataHolder.computeTravellerFees()+5 ) + " L.E ");

    }

    @Override
    public void onClick(View v) {

        if (v==doneBtn){
            Toast.makeText(activity,myReceivedShipment.getShipmentName(),Toast.LENGTH_SHORT).show();
            Toast.makeText(activity,myReceivedShipment.getItemList().size() + "/"+ myReceivedShipment.getItemList().get(0).getItem_category(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ReviewPrices.this, AddingShipmentDetails.class);
            intent.putExtra("shipmentReview",myReceivedShipment);
            intent.putExtra("Uniqid","Review");

            startActivity(intent);

        }
    }

}
