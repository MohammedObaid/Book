package com.example.hala2.al_sharog.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataSource extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "Library";
	private static final int DATABASE_VERSION = 1;
	

	public static final String TABLE_BOOKS= "TABLE_BOOKS";

	public static final String BOOKS_ID = "BOOKS_ID";
	public static final String BOOKS_NAME = "BOOKS_NAME";
	public static final String BOOKS_IMAGE = "BOOKS_IMAGE";
	public static final String BOOKS_PRICE = "BOOKS_PRICE";
	public static final String BOOKS_QUANTITITY = "BOOKS_QUANTITITY";



	private static final String TABLE_CREATE_BOOKS =
			"CREATE TABLE " + TABLE_BOOKS + " (" +
					BOOKS_ID + " INTEGER, " +
					BOOKS_QUANTITITY + " INTEGER, " +
					BOOKS_NAME + " TEXT , " +
					BOOKS_IMAGE + " TEXT , " +
					BOOKS_PRICE + " TEXT " +
					")";




	public static final String STOR_TABLE_BOOKS= "STOR_TABLE_BOOKS";

	public static final String STOR_BOOKS_ID = "STOR_BOOKS_ID";
	public static final String STOR_BOOKS_NAME = "STOR_BOOKS_NAME";
	public static final String STOR_BOOKS_IMAGE = "STOR_BOOKS_IMAGE";
	public static final String STOR_BOOKS_PRICE = "STOR_BOOKS_PRICE";
	public static final String STOR_BOOKS_QUANTITITY = "STOR_BOOKS_QUANTITITY";



	private static final String TABLE_CREATE_STOR_BOOKS =
			"CREATE TABLE " + STOR_TABLE_BOOKS + " (" +
					STOR_BOOKS_ID + " INTEGER, " +
					STOR_BOOKS_QUANTITITY + " INTEGER, " +
					STOR_BOOKS_NAME + " TEXT , " +
					STOR_BOOKS_IMAGE + " TEXT , " +
					STOR_BOOKS_PRICE + " TEXT " +
					")";




	public DataSource(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE_BOOKS);
		db.execSQL(TABLE_CREATE_STOR_BOOKS);



	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
		db.execSQL("DROP TABLE IF EXISTS " + STOR_TABLE_BOOKS);

		onCreate(db);
	}


}
