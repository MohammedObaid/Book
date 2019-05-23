package com.example.hala2.al_sharog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mohammed_Obaid on 13/09/2018.
 */

public class Payment extends Fragment implements LocationListener {
    Context context;
    View view;
    String TAG=getClass().getName().toString();
//http://localhost:8080/huoil/service.php?method=insert_Appountments&&name=aiii&&phone=88888&&longtudi=1234.4654456&&laitudi=567.8978&&banknumber=5666&&methodname=fgdsg&&accountnumber=5443
//    String URL=MainActivity.IP+"?method=insert_Appountments&&";
    String tempurl="";

    LocationListener locationListener;

    TextView textViewAcountNumber;
    EditText Editpaymentnumber;
    CardView paycard, locationCard;
    ImageView imagelocation;


    Spinner SpinnerBanksAccount;
    Spinner SpinnerMethodPayment;

//    private static MyRequestQueue myRequestQueue;

    List<String> compayActivities;
int mpostion=0;





    LocationManager locationManager;
    String provider;


    final  int AcountBankOne=867716;
    final  int AcountBankTwo=22222;


    boolean LocationRegisterState=false;
    Double latitude,longtude;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.payment, container, false);


//        myRequestQueue=new MyRequestQueue(getActivity());
        context=getActivity();
        locationListener=this;
        imagelocation=(ImageView)view.findViewById(R.id.imagelocation);

        locationManager = (LocationManager) getActivity().getSystemService(
                Context.LOCATION_SERVICE);
        SpinnerBanksAccount = (Spinner) view.findViewById(R.id.SpinnerBanksAccount);
        SpinnerMethodPayment = (Spinner) view.findViewById(R.id.spinnerMethodPayment);



        compayActivities = Arrays.asList(getResources().getStringArray(R.array.AcountBank));
       CustomAdapter adapter2 = new CustomAdapter(compayActivities);
        SpinnerBanksAccount.setAdapter(adapter2);


        compayActivities = Arrays.asList(getResources().getStringArray(R.array.MethodPayment));
        CustomAdapter adapter3 = new CustomAdapter(compayActivities);
        SpinnerMethodPayment.setAdapter(adapter3);

        textViewAcountNumber = (TextView) view.findViewById(R.id.textViewAcountNumber);
        Editpaymentnumber = (EditText) view.findViewById(R.id.Editpaymentnumber);








        SpinnerBanksAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    textViewAcountNumber.setText(String.valueOf(AcountBankOne));
                }
                else  {
                    textViewAcountNumber.setText(String.valueOf(AcountBankTwo));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        SpinnerMethodPayment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(mpostion!=position) {
                    Editpaymentnumber.setText("");
                    mpostion=position;
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        locationCard = (CardView) view.findViewById(R.id.locationCard);
        paycard = (CardView) view.findViewById(R.id.paycard);

        paycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(Editpaymentnumber.getText().toString().isEmpty())
                {
                    Editpaymentnumber.setError(getString(R.string.EnterYourAccountNumber));
                    return;
                }








                String AccountNumber=textViewAcountNumber.getText().toString();
                String methodpayment=SpinnerMethodPayment.getSelectedItem().toString();
                String Accountmentnumber=Editpaymentnumber.getText().toString();
                Bundle bundle=new Bundle();
                bundle.putString("AccountNumber",AccountNumber);
                bundle.putString("methodpayment",methodpayment);
                bundle.putString("Accountmentnumber",Accountmentnumber);

                Payment2 payment2 = new Payment2();
                payment2.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_up, R.anim.slide_out_up)
                        .replace(R.id.Activity_layout, payment2, "Payment2").addToBackStack(null).commit();





            }
        });





        return view ;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(locationManager!=null)
        {

            locationManager.removeUpdates(locationListener);
            locationManager = null;
        }
    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getActivity().getSystemService(
                Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }


    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(
                "Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false).setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog,
                                        final int id) {
                        startActivity(new Intent(
                                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog,
                                        final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onLocationChanged(Location location) {
        if(!LocationRegisterState) {
//            imagelocation.setImageResource(R.drawable.ic_pin_un);

            Toast.makeText(getActivity(), location.getLatitude() + "  " + location.getLongitude(), Toast.LENGTH_LONG).show();

           if(location!=null) {
               LocationRegisterState = true;
               latitude=location.getLatitude();
               longtude=location.getLongitude();
           }
            if (locationManager != null) {
                locationManager.removeUpdates(locationListener);
                locationManager = null;
            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    class CustomAdapter extends BaseAdapter {
        List<String> list;

        CustomAdapter(List<String> list) {
            this.list = list;
        }
        @Override
        public int getCount() {

            return list.size();


        }

        @Override
        public Object getItem(int position) {

            return list.get(position);


        }

        @Override
        public long getItemId(int position) {

            return position;


        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getActivity().getLayoutInflater().inflate(R.layout.spinner_item, null);

            TextView tvItem = (TextView) convertView.findViewById(R.id.spinnerItem);
            tvItem.setText(list.get(position));

            return convertView;

        }
    }
















}
