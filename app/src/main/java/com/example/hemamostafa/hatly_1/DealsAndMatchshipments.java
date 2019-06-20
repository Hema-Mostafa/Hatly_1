package com.example.hemamostafa.hatly_1;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemamostafa.hatly_1.Adapter.ViewPagerAdapter;
import com.example.hemamostafa.hatly_1.Base.MyBaseActivity;
import com.example.hemamostafa.hatly_1.Model.Trip;

public class DealsAndMatchshipments extends MyBaseActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Trip mTrip;
    private TextView to,from,date;
    private ImageView transportationImg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar = (Toolbar) findViewById(R.id.collapsingToolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager_2);
        //
        tabLayout = (TabLayout) findViewById(R.id.tabs_2);
        tabLayout.setupWithViewPager(viewPager);




        inititView();
/*
        //  check if this Activity strart from  Fragment Trip or Review ?
        Intent intent = this.getIntent();
        if(intent !=null){
            String stringData = intent.getExtras().getString("Unique_2");
            if(stringData.equals("FragmentTrip"))
            {
                mTrip=(Trip) getIntent().getSerializableExtra("SelectedTrip");
                Toast.makeText(activity,mTrip.getDate(),Toast.LENGTH_SHORT).show();

            }
        }
        else {
            Toast.makeText(activity,"Intent IS Null",Toast.LENGTH_SHORT).show();

        }// End of Check

        setNewView();   // Set New View in ToolBar After Receveing mTrip ;
        */
    }

    private void inititView(){

        to=findViewById(R.id.to_distination);
        from=findViewById(R.id.from_source);
        date=findViewById(R.id.date_trip_num);
        transportationImg=findViewById(R.id.transportationTripImage);

    }

    // Views Inialization.
//
/*
    private void  setNewView(){

        to.setText(mTrip.getTo());
        from.setText(mTrip.getFrom());
        date.setText(mTrip.getDate());

        if(mTrip.getTransportationType().equals("car")) {
           transportationImg.setImageResource(R.drawable.ic_car_img);
        }
        else if (mTrip.getTransportationType().equals("bus") ){
            transportationImg.setImageResource(R.drawable.ic_bus);
        }
        else if(mTrip.getTransportationType().equals("train")){
            transportationImg.setImageResource(R.drawable.ic_train);
        }

    }
    */
/*

        private void setupViewPager(ViewPager viewPager) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(new FragmentMatchingShipments(), "MATCHING SHIPMENTS");
            adapter.addFragment(new FragmentDeals(), "DEALS");
            viewPager.setAdapter(adapter);
        }
        */
}
