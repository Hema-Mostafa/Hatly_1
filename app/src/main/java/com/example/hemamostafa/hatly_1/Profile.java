package com.example.hemamostafa.hatly_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hemamostafa.hatly_1.Base.MyBaseActivity;
import com.example.hemamostafa.hatly_1.Model.Shipment;

public class Profile extends MyBaseActivity {


    private Shipment myShipment,myReceivedShipment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        myReceivedShipment=(Shipment)getIntent().getExtras().getSerializable("myShipment");
        Toast.makeText(activity,myReceivedShipment.getItemList().get(0).getQuantity()+ "/"+ myReceivedShipment.getItemList().size(),Toast.LENGTH_SHORT).show();




    }


    public void hema(View view) {
        Intent intent=new Intent(Profile.this,AddingShipmentDetails.class);
       intent.putExtra("hema",myReceivedShipment);
       intent.putExtra("Uniqid","profile");

        startActivity(intent);
    }
}
