package com.restaurant.ordermanager.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.restaurant.ordermanager.R;
import com.restaurant.ordermanager.database.DatabaseHelper;

public class NewCustomerActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private EditText nameInput;
    private EditText addressInput;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);

        dbHelper = new DatabaseHelper(this);
        phoneNumber = getIntent().getStringExtra("PHONE_NUMBER");

        setupViews();
    }

    private void setupViews() {
        nameInput = findViewById(R.id.nameInput);
        addressInput = findViewById(R.id.addressInput);
        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> saveCustomer());
    }

    private void saveCustomer() {
        String name = nameInput.getText().toString();
        String address = addressInput.getText().toString();

        // Save customer to database and start OrderActivity
        // ...
    }
}