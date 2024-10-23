package com.restaurant.ordermanager.activities;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.restaurant.ordermanager.R;
import com.restaurant.ordermanager.database.DatabaseHelper;
import com.restaurant.ordermanager.models.Customer;

public class OrderActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private Customer customer;
    private ListView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        dbHelper = new DatabaseHelper(this);
        long customerId = getIntent().getLongExtra("CUSTOMER_ID", -1);
        customer = dbHelper.getCustomer(String.valueOf(customerId));

        setupViews();
        loadProducts();
    }

    private void setupViews() {
        TextView customerInfo = findViewById(R.id.customerInfo);
        customerInfo.setText(customer.getName() + "\n" + customer.getAddress());

        productList = findViewById(R.id.productList);
        Button quickOrderBtn = findViewById(R.id.quickOrderBtn);
        
        quickOrderBtn.setOnClickListener(v -> handleQuickOrder());
    }

    private void loadProducts() {
        // Load and display products in ListView
    }

    private void handleQuickOrder() {
        // Handle quick order functionality
    }
}