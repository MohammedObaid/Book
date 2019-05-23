package com.example.hala2.al_sharog.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hala2.al_sharog.Book;

import java.util.ArrayList;


/**
 * Created by Shimaa on 4/10/2018.
 */

public class DB_Handeler {
    DataSource DB_Helper ;
    SQLiteDatabase sqLiteDatabase ;

    public DB_Handeler (Context context){
        DB_Helper = new DataSource(context);
    }

    public void open() {sqLiteDatabase = DB_Helper.getWritableDatabase();}
    public  void close(){DB_Helper.close();}
    //id how is it going to be


    //////part of Lab Insert
    public long  insert(Book book){
        ContentValues contentValues ;
        long id=-100;
        int i=0 ;


            try {
                contentValues = new ContentValues();



                contentValues.put(DataSource.BOOKS_ID, book.getId());
                contentValues.put(DataSource.BOOKS_IMAGE, book.getImage());
                contentValues.put(DataSource.BOOKS_QUANTITITY, book.getQuantity());
                contentValues.put(DataSource.BOOKS_PRICE, book.getPrice());
                contentValues.put(DataSource.BOOKS_NAME, book.getName());

                i++;
                id = sqLiteDatabase.insert(DataSource.TABLE_BOOKS, null, contentValues);

            }
            catch (Exception e)
            {
                String message=e.getMessage();
            }

        return id;
    }








    public int UpdateProductQuantity(Book book) {

        // SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataSource.BOOKS_QUANTITITY, book.getQuantity());
        int result= sqLiteDatabase.update(DataSource.TABLE_BOOKS, contentValues, DataSource.BOOKS_ID + "=" + book.getId(), null);
        return result;
    }


    public  void DeleteAllRows() {
        sqLiteDatabase.execSQL("delete from "+ DataSource.TABLE_BOOKS);



    }

    public  void DeleteRowById(Book book) {
        try {
            sqLiteDatabase.execSQL("delete from "+  DataSource.TABLE_BOOKS +" where "+  DataSource.BOOKS_ID+"='"+book.getId()+"'");
        }
        catch (Exception e)
        {
            String mmm=e.getMessage();
        }
    }



    ////////////////get methods
    public ArrayList<Book> getAll() {
        ArrayList<Book> products = new ArrayList<>();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from " + DataSource.TABLE_BOOKS , null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int Id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataSource.BOOKS_ID)));
            int quantity = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataSource.BOOKS_QUANTITITY   )));

            String Name = cursor.getString(cursor.getColumnIndex(DataSource.BOOKS_NAME));
            String Image = cursor.getString(cursor.getColumnIndex(DataSource.BOOKS_IMAGE));
            String price  = cursor.getString(cursor.getColumnIndex(DataSource.BOOKS_PRICE));

            products.add(new Book(Id,Name,price,Image,quantity));
            cursor.moveToNext();
        }
        return products;

    }






    ////////////////get methods
    public int   getQuantity(Book book) {
        int quantity;
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from " + DataSource.TABLE_BOOKS +" Where "+DataSource.BOOKS_ID+"='"+book.getId()+"'" , null);
        cursor.moveToFirst();

        if(cursor.getCount()>0) {
            quantity = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataSource.BOOKS_QUANTITITY)));


            if (quantity > 0) {
                return quantity;
            } else return 0;

        }
        else
            return 0;

    }






    ////////////////get methods
    public boolean  isExist(Book product) {
        ArrayList<Book> books = new ArrayList<>();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from " + DataSource.TABLE_BOOKS +" Where "+DataSource.BOOKS_ID+"='"+product.getId()+"'" , null);

     if(cursor.getCount()>0)
     {
         return true;
     }
     else
     {
         return false;
     }


    }
    public String getAmount()
    {
        float AmountNumber=0;
        float tempAmountNumber=0;

        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from " + DataSource.TABLE_BOOKS , null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int quantity = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataSource.BOOKS_QUANTITITY   )));
            String price  = cursor.getString(cursor.getColumnIndex(DataSource.BOOKS_PRICE));
            tempAmountNumber=quantity*Float.valueOf(price);
            AmountNumber=AmountNumber+tempAmountNumber;
            cursor.moveToNext();


        }

        return String.valueOf(AmountNumber);
    }


    public String getitemCount()
    {
        int ItemCount=0;

        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from " + DataSource.TABLE_BOOKS , null);
        cursor.moveToFirst();
        ItemCount=cursor.getCount();


        return String.valueOf(ItemCount);
    }

    /////////////////////////////////////////////////////////

    //////part of Lab Insert
    public long  insert_Store(Book book){
        ContentValues contentValues ;
        long id=-100;
        int i=0 ;


        try {
            contentValues = new ContentValues();



            contentValues.put(DataSource.STOR_BOOKS_ID, book.getId());
            contentValues.put(DataSource.STOR_BOOKS_IMAGE, book.getImage());
            contentValues.put(DataSource.STOR_BOOKS_QUANTITITY, book.getQuantity());
            contentValues.put(DataSource.STOR_BOOKS_PRICE, book.getPrice());
            contentValues.put(DataSource.STOR_BOOKS_NAME, book.getName());

            i++;
            id = sqLiteDatabase.insert(DataSource.TABLE_BOOKS, null, contentValues);

        }
        catch (Exception e)
        {
            String message=e.getMessage();
        }

        return id;
    }

    ////////////////get methods
    public ArrayList<Book> getAll_Store() {
        ArrayList<Book> products = new ArrayList<>();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from " + DataSource.STOR_TABLE_BOOKS , null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int Id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataSource.STOR_BOOKS_ID)));
            int quantity = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataSource.STOR_BOOKS_QUANTITITY   )));

            String Name = cursor.getString(cursor.getColumnIndex(DataSource.STOR_BOOKS_NAME));
            String Image = cursor.getString(cursor.getColumnIndex(DataSource.STOR_BOOKS_IMAGE));
            String price  = cursor.getString(cursor.getColumnIndex(DataSource.STOR_BOOKS_PRICE));

            products.add(new Book(Id,Name,price,Image,quantity));
            cursor.moveToNext();
        }
        return products;

    }

    public int   getQuantity_Store(Book book) {
        int quantity;
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from " + DataSource.STOR_TABLE_BOOKS +" Where "+DataSource.STOR_BOOKS_ID+"='"+book.getId()+"'" , null);
        cursor.moveToFirst();

        if(cursor.getCount()>0) {
            quantity = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataSource.STOR_BOOKS_QUANTITITY)));


            if (quantity > 0) {
                return quantity;
            } else return 0;

        }
        else
            return 0;

    }


}