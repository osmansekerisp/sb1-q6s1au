package com.restaurant.ordermanager.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.restaurant.ordermanager.R;
import com.restaurant.ordermanager.database.DatabaseHelper;
import com.restaurant.ordermanager.models.Customer;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_CODE = 123;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        dbHelper = new DatabaseHelper(this);
        checkPermissions();
        setupCallListener();
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            
            ActivityCompat.requestPermissions(this,
                new String[]{
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_CALL_LOG
                },
                PERMISSIONS_REQUEST_CODE);
        }
    }

    private void setupCallListener() {
        TelephonyManager telephonyManager = 
            (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        
        PhoneStateListener callListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                if (state == TelephonyManager.CALL_STATE_RINGING) {
                    handleIncomingCall(phoneNumber);
                }
            }
        };

        telephonyManager.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    private void handleIncomingCall(String phoneNumber) {
        Customer customer = dbHelper.getCustomer(phoneNumber);
        if (customer != null) {
            showCustomerOrderScreen(customer);
        } else {
            showNewCustomerScreen(phoneNumber);
        }
    }

    private void showCustomerOrderScreen(Customer customer) {
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("CUSTOMER_ID", customer.getId());
        startActivity(intent);
    }

    private void showNewCustomerScreen(String phoneNumber) {
        Intent intent = new Intent(this, NewCustomerActivity.class);
        intent.putExtra("PHONE_NUMBER", phoneNumber);
        startActivity(intent);
    }
}