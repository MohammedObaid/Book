package com.example.hala2.al_sharog;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hala2.al_sharog.DB.DB_Handeler;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

//import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity
         {


    DB_Handeler db_handeler;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawer;
    Toolbar toolbar;
    Fragment lastfragment;
    String tag;
    FrameLayout cartline;
    boolean isCloseCart=true;
    boolean isPayment=true;
    TextView checkout_amount;
    TextView item_count;
    Button Payment;

    Context context;


    public Timer timerStartGreeting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
//        setCustomAnimations(R.anim.slide_in_up,
//                R.anim.slide_out_up);

        context=this;

        db_handeler=new DB_Handeler(this);
        checkout_amount=(TextView) findViewById(R.id.checkout_amount);

        item_count=(TextView) findViewById(R.id.item_count);

        updateAmount();
        updateitemCount();

        Payment=(Button) findViewById(R.id.Payment);
        Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPayment) {

                    db_handeler.open();
                    int size=db_handeler.getAll().size();
                db_handeler.close();
                if(size>=1) {
                    isPayment=false;
                    Payment payment = new Payment();
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_up, R.anim.slide_out_up)
                            .replace(R.id.Activity_layout, payment, "Payment").addToBackStack(null).commit();
                }
                else
                {
                    Toast.makeText(context,getString(R.string.DontItemExist),Toast.LENGTH_LONG).show();
                }
                }


            }
        });
        cartline=(FrameLayout)findViewById(R.id.item_counter);
        cartline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein, R.anim.fadeout).
//                        remove(getSupportFragmentManager().findFragmentByTag("GreetingApp")).commit();
                if(isCloseCart) {
                    isCloseCart=false;


                    ListProductCart listProductCart=new ListProductCart();
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up,R.anim.slide_in_up, R.anim.slide_out_up)
                            .replace(R.id.Activity_layout, listProductCart, "ListProductCart").addToBackStack(null).commit();
                }

            }
        });
         drawer = (DrawerLayout) findViewById(R.id.drawerLayout);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


// For LTR
//        Toast.makeText(this, Locale.getDefault().getDisplayLanguage()+" "+ Locale.getDefault().getLanguage(),Toast.LENGTH_LONG).show();
        if( Locale.getDefault().getLanguage().equals("ar"))
        ViewCompat.setLayoutDirection(toolbar, ViewCompat.LAYOUT_DIRECTION_RTL);
        else
        ViewCompat.setLayoutDirection(toolbar, ViewCompat.LAYOUT_DIRECTION_LTR);
        getSupportActionBar().setTitle(R.string.app_name2);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.drawer_open, R.string.drawer_close);
//        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
//
//        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                drawer.openDrawer(GravityCompat.END);
//            }
//        });
//
//
//        actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ooo3);
        drawer.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

//        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
//        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein, R.anim.fadeout).
                replace(R.id.layout_container, new GreetingApp(), "GreetingApp").commit();
        timerStartGreeting = new Timer();

        timerStartGreeting.schedule(new TimerTask() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein, R.anim.fadeout).
                        remove(getSupportFragmentManager().findFragmentByTag("GreetingApp")).commit();



                ListShowGood listShowGood=new ListShowGood();
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein, R.anim.fadeout)
                        .replace(R.id.Activity_layout, listShowGood, "ListShowGood").addToBackStack(null).commit();



            }
        },3000);
//        navigationView.getMenu().getItem(0).setChecked(true);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));










    }










    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timerStartGreeting !=null)
            timerStartGreeting.cancel();
    }


    @Override
    public void onBackPressed() {
        isCloseCart=true;
        isPayment=true;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(getSupportFragmentManager().getBackStackEntryCount()==1)
            {
                finish();
            }
            else
            super.onBackPressed();
        }






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }






    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // it will not work for right to left navigation drawer
/*        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }*/


        // so we have to open and close the navigation drawer ourselves



        if(item.getItemId() == android.R.id.home) {
            //     Start   For RTL
            if( Locale.getDefault().getLanguage().equals("ar")) {
                if (drawer.isDrawerOpen(Gravity.START)) {
                    drawer.closeDrawer(Gravity.START);
                } else {
                    drawer.openDrawer(Gravity.START);
                }
            }
            //     End   For RTL
            //     Start   For LTR
            else
            {

                if (drawer.isDrawerOpen(Gravity.START)) {
                    drawer.closeDrawer(Gravity.START);
                } else {
                    drawer.openDrawer(Gravity.START);
                }

            }
            //     End   For LTR
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public void Close()
    {
        isCloseCart=true;




       this.onBackPressed();
    }



    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



    public void updateAmount()
    {
        db_handeler.open();
        checkout_amount.setText(  db_handeler.getAmount() );
        db_handeler.close();
    }

    public void updateitemCount()
    {  db_handeler.open();
        item_count.setText(  db_handeler.getitemCount() );
        db_handeler.close();

    }

    CustomDialogFragment fragment1;

    public void ProgressDialog()
    {
        fragment1  =null;
        fragment1 = new CustomDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("Id_Dialog", CustomDialogFragment.PROGRESSDILOG);
        fragment1.setArguments(bundle);
        fragment1.show(getSupportFragmentManager(), "simple custom_dialog");

    }
    public void DismissProgressDialog()
    {

        fragment1.dismiss();
    }



    public void MessageDialog(String message)
    {
        CustomDialogFragment fragment1=null;
        fragment1 = new CustomDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("Id_Dialog", CustomDialogFragment.MESSAGEDILOG);
        bundle.putString("message", message);
        fragment1.setArguments(bundle);
        fragment1.show(getSupportFragmentManager(), "simple custom_dialog");

    }


//    public void getmap()
//    {
//
////        return context;
//
//
//        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//        try {
//            // for activty
//           startActivityForResult(builder.build(this), MainActivity.PLACE_PICKER_REQUEST);
//            // for fragment
//            //startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
//        } catch (GooglePlayServicesRepairableException e) {
//            e.printStackTrace();
//        } catch (GooglePlayServicesNotAvailableException e) {
//            e.printStackTrace();
//        }
//
////        return context;
//
//    }

    public final static int PLACE_PICKER_REQUEST = 999;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        checkPermissionOnActivityResult(requestCode, resultCode, data);

//        if (resultCode == RESULT_OK) {
//            switch (requestCode){
//                case PLACE_PICKER_REQUEST:
//                    Place place = PlacePicker.getPlace(this, data);
//                    String placeName = String.format("Place: %s", place.getName());
//                    double latitude = place.getLatLng().latitude;
//                    double longitude = place.getLatLng().longitude;
//
//            }
//        }
    }

}
