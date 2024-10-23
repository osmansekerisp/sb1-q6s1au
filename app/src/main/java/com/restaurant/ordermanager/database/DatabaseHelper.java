package com.restaurant.ordermanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.restaurant.ordermanager.models.Customer;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "RestaurantDB";
    private static final int DATABASE_VERSION = 1;

    // Tables
    private static final String TABLE_CUSTOMERS = "customers";
    private static final String TABLE_PRODUCTS = "products";
    private static final String TABLE_ORDERS = "orders";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create customers table
        db.execSQL(
            "CREATE TABLE " + TABLE_CUSTOMERS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, " +
            "phone TEXT UNIQUE, " +
            "address TEXT)"
        );

        // Create products table
        db.execSQL(
            "CREATE TABLE " + TABLE_PRODUCTS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, " +
            "price REAL)"
        );

        // Create orders table
        db.execSQL(
            "CREATE TABLE " + TABLE_ORDERS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "customer_id INTEGER, " +
            "date TEXT, " +
            "total REAL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        onCreate(db);
    }

    public Customer getCustomer(String phoneNumber) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CUSTOMERS,
            new String[]{"id", "name", "phone", "address"},
            "phone = ?",
            new String[]{phoneNumber},
            null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Customer customer = new Customer(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
            );
            cursor.close();
            return customer;
        }
        return null;
    }
}