package com.example.hemamostafa.hatly_1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.hemamostafa.hatly_1.Base.MyBaseActivity;
import com.example.hemamostafa.hatly_1.Model.Trip;

public class MyNewHome extends MyBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    Fragment fragment;
    FloatingActionButton fab;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
             fragment=null;
            switch (item.getItemId()) {
                case R.id.navigation_trip:
                    fragment = new FragmentTrips();
                    if(fab.isOrWillBeHidden()){
                        fab.show();
                    }

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(activity, AddingTripsScreen.class));
                        }
                    });
                    break;

                case R.id.navigation_shipment:
                    fragment = new FragmentShipment();
                    if(fab.isOrWillBeHidden()){
                        fab.show();
                    }
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MyNewHome.this,AddingShipmentDetails.class);
                            intent.putExtra("Uniqid","MyNewHome");
                            startActivity(intent);

                        }
                    });

                    break;

                case R.id.navigation_notifications:
                    fragment = new FragmentNotfication();
                    fab.hide();
                break;
                case R.id.navigation_search:

                    fragment = new FragmentSearch();
                    fab.hide();
                    break;

            }
            Log.e("tag","replaced");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_new_home,fragment)
                    .commit();


            Log.e("tag","replaced2 "+ fragment+"");

            return true;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_new_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        fab=findViewById(R.id.fab_adding_trip);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyNewHome.this,AddingTripsScreen.class));
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_new_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

         //Start With Trip Fragment
       getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_new_home,new FragmentTrips())
                .commit();




       // setSupportActionBar(toolbar);





    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setting_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_view_profile) {
            // Handle the camera action
            startActivity(new Intent(activity,Profile.class));
        } else if (id == R.id.nav_payout_details) {

        } else if (id == R.id.nav_logout) {

        } else if (id == R.id.nav_setting) {


        }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
                }

                }
