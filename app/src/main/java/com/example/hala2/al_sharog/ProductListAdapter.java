/*
 * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
 * along with the project.
 *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
 */

package com.example.hala2.al_sharog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hala2.al_sharog.DB.DB_Handeler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hitesh Sahu (hiteshsahu.com)
 */
public class ProductListAdapter extends
        RecyclerView.Adapter<ProductListAdapter.VersionViewHolder> {
    DB_Handeler db_handeler;



    private List<Book> bookList;
    private OnItemClickListener clickListener;


    private Context context;
    boolean isCartlist;

    public ProductListAdapter(ArrayList<Book>products, Context context
                              ) {



    this.bookList=products;
        this.context = context;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_product_list, viewGroup, false);
        db_handeler=new DB_Handeler(context);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final VersionViewHolder holder,
                                 final int position) {
        if(bookList.get(position).getName()!=null)
        holder.itemName.setText(bookList.get(position).getName());

        if(bookList.get(position).getPrice()!=null)
        holder.itemCost.setText(bookList.get(position).getPrice()+" "+ holder.itemCost.getText());
//        if(bookList.get(position).getImage()!=0)
//        holder.imagView.setImageResource((int)bookList.get(position).getImage());

        db_handeler.open();

        int quantity=db_handeler.getQuantity(bookList.get(position));
        bookList.get(position).setQuantity(quantity);
        int iii=quantity;
                holder.quanitity.setText(String.valueOf(iii));
      //        Add Item in  Card      //////
        db_handeler.close();



        holder.addItem.findViewById(R.id.add_item).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {


                        //current object
                        Book tempObj = bookList.get(position);

                        db_handeler.open();
                        //if current object is lready in shopping list
                        if (db_handeler.isExist(tempObj)) {
                            db_handeler.open();
                            int tempquantity=db_handeler.getQuantity(bookList.get(position));
                            tempObj.setQuantity(tempquantity+1);
                            db_handeler.close();
                            // increase quantity of current item in shopping list
                            db_handeler.open();
                            db_handeler.UpdateProductQuantity(tempObj);
                            db_handeler.close();
                            // update quanity in shopping list
                            //update checkout amount

                            // update current item quanitity
                            holder.quanitity.setText(String.valueOf(tempObj.getQuantity()));


                            db_handeler.close();

                        } else {

                            tempObj.setQuantity(1);
                            db_handeler.insert(tempObj);


                            holder.quanitity.setText(String.valueOf(tempObj.getQuantity()));
                            db_handeler.close();
                        }

                        ((MainActivity)context). updateAmount();
                        ((MainActivity)context).   updateitemCount();

                    }
                });




        holder.removeItem.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Book tempObj = (bookList).get(position);

                db_handeler.open();
                //if current object is lready in shopping list
                if (db_handeler.isExist(tempObj)) {



                    if (Integer.valueOf(tempObj.getQuantity()) != 0) {


                        db_handeler.open();
                        int tempquantity=db_handeler.getQuantity(bookList.get(position));
                        tempObj.setQuantity(tempquantity-1);
                        db_handeler.close();
                        // increase quantity of current item in shopping list
                        db_handeler.open();
                        db_handeler.UpdateProductQuantity(tempObj);
                        db_handeler.close();
                        // update quanity in shopping list
                        //update checkout amount
                        // update current item quanitity
                        holder.quanitity.setText(String.valueOf(tempObj.getQuantity()));


                        db_handeler.close();

                    db_handeler.open();
                     int temp=   db_handeler.getQuantity(bookList.get(position));
                        db_handeler.close();
                        if (temp == 0) {

                                db_handeler.open();
                            db_handeler.DeleteRowById(bookList.get(position));
                            db_handeler.close();

                                if(isCartlist) {
                                    bookList.remove(bookList.get(position));
                                    notifyDataSetChanged();
                                }




                        }
                        ((MainActivity)context). updateAmount();
                        ((MainActivity)context).   updateitemCount();

                    }

                } else {

                }

            }

        });






    }



    @Override
    public int getItemCount() {
        return bookList == null ? 0 : bookList.size();
    }

    public void SetOnItemClickListener(
            final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements
            OnClickListener {
        TextView itemName, itemDesc, itemCost,  quanitity, addItem, removeItem;

        ImageView imagView;

        public VersionViewHolder(View itemView) {
            super(itemView);

            itemName = (TextView) itemView.findViewById(R.id.item_name);

            itemDesc = (TextView) itemView.findViewById(R.id.item_short_desc);

            itemCost = (TextView) itemView.findViewById(R.id.item_price);

            quanitity = (TextView) itemView.findViewById(R.id.iteam_amount);
            addItem = (TextView) itemView.findViewById(R.id.add_item);
            removeItem = (TextView) itemView.findViewById(R.id.remove_item);


            itemName.setSelected(true);

            imagView = ((ImageView) itemView.findViewById(R.id.product_thumb));

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition());
        }
    }

}
