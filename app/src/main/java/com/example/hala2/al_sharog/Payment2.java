package com.example.hala2.al_sharog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.app.Activity.RESULT_OK;

//import com.google.android.gms.location.places.Place;
//import com.google.android.gms.location.places.ui.PlacePicker;

/**
 * Created by mohammed_Obaid on 13/09/2018.
 */

public class Payment2 extends Fragment   {

    Context context;
    View view;
    String TAG=getClass().getName().toString();
//http://localhost:8080/huoil/service.php?method=insert_Appountments&&name=aiii&&phone=88888&&longtudi=1234.4654456&&laitudi=567.8978&&banknumber=5666&&methodname=fgdsg&&accountnumber=5443
//    String URL=MainActivity.IP+"?method=insert_Appountments&&";
    String tempurl="";

    LocationListener locationListener;

    private int PLACE_PICKER_REQUEST = 1;
    EditText  EditRecipientName, EditRecipientNumber;
    CardView paycard, locationCard;
    ImageView imagelocation;




//    private static MyRequestQueue myRequestQueue;

    List<String> compayActivities;
int mpostion=0;





    LocationManager locationManager;
    String provider;


    final  int AcountBankOne=11111;
    final  int AcountBankTwo=22222;


    boolean LocationRegisterState=false;
    Double latitude,longtude;

    int PLACE_PICKER_REQ_CODE=10033;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.payment2, container, false);
        context=getActivity();





//        myRequestQueue=new MyRequestQueue(getActivity());


        imagelocation=(ImageView)view.findViewById(R.id.imagelocation);

        locationManager = (LocationManager) getActivity().getSystemService(
                Context.LOCATION_SERVICE);

        EditRecipientName = (EditText) view.findViewById(R.id.EditRecipientName);
        EditRecipientNumber = (EditText) view.findViewById(R.id.EditRecipientNumber);














        locationCard = (CardView) view.findViewById(R.id.locationCard);
        paycard = (CardView) view.findViewById(R.id.paycard);

        paycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




//
//                if(EditRecipientName.getText().toString().isEmpty())
//                {
//                    EditRecipientName.setError(getString(R.string.EnterRecipienName));
//                    Toast.makeText(getActivity(),getString(R.string.EnterRecipienName), Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//                if(EditRecipientNumber.getText().toString().isEmpty())
//                {
//                    EditRecipientNumber.setError(getString(R.string.EnterRecipienNumber));
//                    Toast.makeText(getActivity(),getString(R.string.EnterRecipienNumber), Toast.LENGTH_LONG).show();
//
//                    return;
//                }
//
//                if(!LocationRegisterState)
//                {
//                   Toast.makeText(getActivity(),getString(R.string.RgisterYourLocation),Toast.LENGTH_LONG).show();
//                    return;
//                }
//
////longtude
////           latitude
////                Editpaymentnumber
////                EditRecipientName
////                EditRecipientNumber
////                textViewAcountNumber
////                SpinnerMethodPayment
//                //http://localhost:8080/huoil/service.php?method=insert_Appountments&&name=aiii&&phone=88888&&longtudi=1234.4654456&&laitudi=567.8978&&banknumber=5666&&methodname=fgdsg&&accountnumber=5443
//                String Name=EditRecipientName.getText().toString();
//                String phoneNumber=EditRecipientName.getText().toString();
//                String AccountNumber=getArguments().getString("AccountNumber");
//                String methodpayment=getArguments().getString("methodpayment");
//                String Accountmentnumber=getArguments().getString("Accountmentnumber");
//                tempurl="";
//                try {
//                    int iii=000;
//                    tempurl=URL+"name="+ URLEncoder.encode(Name, "UTF-8")+"&&phone="+phoneNumber+"&&longtudi="+longtude+"&&laitudi="+latitude+"&&banknumber="+AccountNumber+"&&methodname="+URLEncoder.encode(methodpayment, "UTF-8")+"&&accountnumber="+Accountmentnumber;
//
//                    CheckInternet(tempurl);
//
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }


            }
        });


        locationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {








            }
        });


        return view ;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }





    @Override
    public void onDestroy() {
        super.onDestroy();


    }
//
//    public void statusCheck() {
//        final LocationManager manager = (LocationManager) getActivity().getSystemService(
//                Context.LOCATION_SERVICE);
//
//        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            buildAlertMessageNoGps();
//
//        }
//    }


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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

         if(requestCode == PLACE_PICKER_REQUEST ){
            if (resultCode == RESULT_OK) {


            }
        }
    }

//    @Override
//    public void onLocationChanged(Location location) {
//        if(!LocationRegisterState) {
////            imagelocation.setImageResource(R.drawable.ic_pin_un);
//
//            Toast.makeText(getActivity(), location.getLatitude() + "  " + location.getLongitude(), Toast.LENGTH_LONG).show();
//
//           if(location!=null) {
//               LocationRegisterState = true;
//               latitude=location.getLatitude();
//               longtude=location.getLongitude();
//           }
//            if (locationManager != null) {
//                locationManager.removeUpdates(locationListener);
//                locationManager = null;
//            }
//        }
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//
//    }




//    class CustomAdapter extends BaseAdapter {
//        List<String> list;
//
//        CustomAdapter(List<String> list) {
//            this.list = list;
//        }
//        @Override
//        public int getCount() {
//
//            return list.size();
//
//
//        }
//
//        @Override
//        public Object getItem(int position) {
//
//            return list.get(position);
//
//
//        }
//
//        @Override
//        public long getItemId(int position) {
//
//            return position;
//
//
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            convertView = getActivity().getLayoutInflater().inflate(R.layout.spinner_item, null);
//
//            TextView tvItem = (TextView) convertView.findViewById(R.id.spinnerItem);
//            tvItem.setText(list.get(position));
//
//            return convertView;
//
//        }
//    }
//










}
