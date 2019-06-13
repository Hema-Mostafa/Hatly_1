package com.example.hemamostafa.hatly_1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.hemamostafa.hatly_1.Base.MyBaseActivity;

public class allTrips extends MyBaseActivity {



     private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_trip:
                 //   mTextMessage.setText(R.string.title_trips);
                  //  Toast.makeText(activity,"Trips ",Toast.LENGTH_SHORT).show();
                 //   fragment = new FragmentTrips();
                    //return true;
                case R.id.navigation_shipment:
                   // mTextMessage.setText(R.string.title_shipments);
                   // fragment = new shipmentsFragment();
                    //Toast.makeText(activity,"shipment ",Toast.LENGTH_SHORT).show();
                   // return true;

                case R.id.navigation_search:
                    //mTextMessage.setText(R.string.title_search);
                   // fragment = new FragmentSearch();
                   // Toast.makeText(activity,"search ",Toast.LENGTH_SHORT).show();
                  //  return true;

                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notification);
                   // Toast.makeText(activity,"notfication ",Toast.LENGTH_SHORT).show();
                 // fragment = new notficationFragment();
                    //return true;
               // case R.id.navigation_chat:
                 //   mTextMessage.setText(R.string.title_chat);
                   // Toast.makeText(activity,"chat ",Toast.LENGTH_SHORT).show();
                   // return true;

            }


            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trips_screen);

     //   mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_new_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
