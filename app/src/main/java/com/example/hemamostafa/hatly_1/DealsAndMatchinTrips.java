package com.example.hemamostafa.hatly_1;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemamostafa.hatly_1.Adapter.ViewPagerAdapter;
import com.example.hemamostafa.hatly_1.Model.Shipment;
import com.example.hemamostafa.hatly_1.Model.Trip;

public class DealsAndMatchinTrips extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    TextView from , to , date, weight,shipmentName;
    private Shipment mShipment;

    FragmentMatchingTrip mFragmentMatchingTrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals_and_matchin_trips);
        toolbar = (Toolbar) findViewById(R.id.collapsingToolbar);
        from= findViewById(R.id.from_txtView_tr);
        to=findViewById(R.id.to_txtView_tr);
        date=findViewById(R.id.dateNumber_sh);
        shipmentName= findViewById(R.id.shipment_name_sh);
        weight = findViewById(R.id.shipment_weight_tr);
        mFragmentMatchingTrip = new FragmentMatchingTrip();



        //  check if this Activity strart from  MyNewHome or Review ?
        Intent intent = this.getIntent();
        if(intent !=null)
        {
            String stringData = intent.getExtras().getString("Uniqid_3");
            if(stringData.equals("FragmentShipments"))
            {

                //myReceivedShipment=(Shipment) getIntent().getSerializableExtra("hema");
                mShipment = (Shipment) intent.getSerializableExtra("shipment");
                if (mShipment == null) {
                    Toast.makeText(this, "myReceived shipment is Null ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "myReceived shipment is  "+mShipment.getShipmentName(), Toast.LENGTH_SHORT).show();

                    from.setText(mShipment.getFrom());
                    to.setText(mShipment.getTo());
                    date.setText(mShipment.getBeforeDate());
                    weight.setText("Weight : "+mShipment.getShipmentWeight()+" KG");
                    shipmentName.setText(mShipment.getShipmentName());
                    mFragmentMatchingTrip.setShipment(mShipment);


                }
            }
            if (stringData.equals("MyNewHome")){
                    Toast.makeText(this,"The Activity Start From MyNewHome",Toast.LENGTH_SHORT).show();

                }
        }
        else {
            Toast.makeText(this,"Intent IS Null",Toast.LENGTH_SHORT).show();

        }
        // End of Check

        viewPager = (ViewPager) findViewById(R.id.viewpager_2);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs_2);
        tabLayout.setupWithViewPager(viewPager);

    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(mFragmentMatchingTrip, "MATCHING TRIPS");
        adapter.addFragment(new NewFragment(), "DEALS");
        viewPager.setAdapter(adapter);
    }
}
