package com.example.hala2.al_sharog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by mohammed_Obaid on 01/04/2018.
 */

public class GreetingApp extends Fragment {
//    Main2Activity.ManageToolbar manageToolbar;



//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) return;
//        if (activity instanceof  Main2Activity.ManageToolbar) {
//            manageToolbar = ( Main2Activity.ManageToolbar) activity;
//        } else {
//            throw new RuntimeException(activity.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof Main2Activity.ManageToolbar) {
//            manageToolbar = ( Main2Activity.ManageToolbar) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
//    }
private Animation animShow, animHide;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        manageToolbar.HideToolbar();
        View view= inflater.inflate(R.layout.greetingapp,container,false);


        animShow = AnimationUtils.loadAnimation( getActivity(), R.anim.fadeinin);
        animHide = AnimationUtils.loadAnimation( getActivity(), R.anim.slide_down);

         imageView=(ImageView)view.findViewById(R.id.imageView2);


//        imageView.startAnimation( animHide );
//        imageView.setVisibility(View.GONE);

//


        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        imageView.setVisibility(View.VISIBLE);
        imageView.startAnimation( animShow );

    }
}
