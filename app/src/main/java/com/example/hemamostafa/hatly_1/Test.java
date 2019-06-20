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

public class Test extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView from , to , date;
    ImageView imageView;
    FragmentMatchingShipments mFragmentMatchingShipments;
    BlankFragment_1 blankFragment_1;
    private Trip mTrip;
    FragmentChat mChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        toolbar = (Toolbar) findViewById(R.id.collapsingToolbar);

        from= findViewById(R.id.from_txtView_tr);
        to=findViewById(R.id.to_txtView_tr);
        date=findViewById(R.id.dateNumber_sh);
        mFragmentMatchingShipments = new FragmentMatchingShipments();
        blankFragment_1 = new BlankFragment_1();
        mChat = new FragmentChat();


        //  check if this Activity strart from  MyNewHome or Review ?
        Intent intent = this.getIntent();
        if(intent !=null){
            String stringData = intent.getExtras().getString("Uniqid_2");
            if(stringData.equals("FragmentTrips")) {

                //myReceivedShipment=(Shipment) getIntent().getSerializableExtra("hema");
                mTrip = (Trip) intent.getSerializableExtra("trip");
                if (mTrip == null) {
                    Toast.makeText(Test.this, "myReceived Trip is Null ", Toast.LENGTH_SHORT).show();
                } else {
                    from.setText(mTrip.getFrom());
                    to.setText(mTrip.getTo());
                    date.setText(mTrip.getDate());
                    mFragmentMatchingShipments.setmTrip(mTrip);
                    blankFragment_1.setmTrip(mTrip);
                    mChat.setmTrip(mTrip);

                    Toast.makeText(Test.this, "myReceived Trip Found  ", Toast.LENGTH_SHORT).show();
                }
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
        adapter.addFragment(mChat, "MATCHING SHIPMENTS");
        adapter.addFragment(new NewFragment(), "DEALS");
        viewPager.setAdapter(adapter);
    }

}
