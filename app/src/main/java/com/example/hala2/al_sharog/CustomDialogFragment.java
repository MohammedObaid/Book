package com.example.hala2.al_sharog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by mohammed_Obaid on 11/08/2018.
 */

public class CustomDialogFragment extends DialogFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    final static int MESSAGEDILOG = 1;
    final static int PROGRESSDILOG = 2;
//    final static int SENDNOTE = 3;

    Dialog custom_dialog;
    AlertDialog.Builder dialog;





    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        switch (getArguments().getInt("Id_Dialog"))
        {
            case MESSAGEDILOG:
                return MessageDailog();
            case PROGRESSDILOG:
                return ProgressDailog();



default:
    return null;

        }



}






    public Dialog ProgressDailog()
    {
        TextView TitleTextView;
        TextView Message;

        custom_dialog = new Dialog(getActivity());



        custom_dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        custom_dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        custom_dialog.setContentView(R.layout.dailog_progress);
        custom_dialog.setCancelable(false);
        custom_dialog.setCanceledOnTouchOutside(false);
        custom_dialog.show();






        dialog = new AlertDialog.Builder(getActivity());


        return custom_dialog;



    }


    @Override
    public void onStop() {
        super.onStop();
        custom_dialog.dismiss();

    }

    public Dialog MessageDailog()
    {
        TextView TitleTextView;
        TextView Message;
        String message=getArguments().getString("message");

        custom_dialog = new Dialog(getActivity());



        custom_dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        custom_dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        custom_dialog.setContentView(R.layout.dailog_message);
        custom_dialog.setCancelable(true);
        custom_dialog.setCanceledOnTouchOutside(true);
        custom_dialog.show();


        TitleTextView = (TextView) custom_dialog.findViewById(R.id.titile);
        Message = (TextView) custom_dialog.findViewById(R.id.message);

        Message.setText(message);


        dialog = new AlertDialog.Builder(getActivity());


        return custom_dialog;



    }







}