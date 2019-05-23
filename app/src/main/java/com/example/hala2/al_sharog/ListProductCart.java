/*
 * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
 * along with the project.
 *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
 */

package com.example.hala2.al_sharog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hala2.al_sharog.DB.DB_Handeler;

import java.util.ArrayList;



public class ListProductCart extends Fragment {
    Context context;
TextView title;
    RecyclerView recyclerView;
    ArrayList<Book> books;
    DB_Handeler db_handeler;

    interface CloseListProductCart
    {
        public  void close();
    }

    boolean isShoppingList=true;



    ImageView slider_down;
    public ListProductCart() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listproductcart, container,
                false);
        context=getActivity();
        db_handeler=new DB_Handeler(getActivity());
        title=(TextView) view.findViewById(R.id.title);
        slider_down=(ImageView)view.findViewById(R.id.slider);
        slider_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) (getContext())).Close();
            }
        });
        view.findViewById(R.id.slide_down).setVisibility(View.VISIBLE);
        view.findViewById(R.id.slide_down).setOnTouchListener(
                new OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

//                        Utils.switchFragmentWithAnimation(R.id.frag_container,
//                                new HomeFragment(),
//                                ((MainActivity) (getContext())),
//                                Utils.HOME_FRAGMENT, AnimationType.SLIDE_DOWN);

                        return false;
                    }
                });

        db_handeler.open();
        books=db_handeler.getAll();

        db_handeler.close();
        if(books.size()>0)
        {
            title.setText(getString(R.string.ItemInCard));
        }


         recyclerView = (RecyclerView) view
                .findViewById(R.id.product_list_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);



        ProductListAdapter adapter = new ProductListAdapter(books,
                getActivity());

        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);


        adapter.SetOnItemClickListener(new ProductListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {





            }
        });


        return view;



    }

    @Override
    public void onStart() {
        super.onStart();



    }



}