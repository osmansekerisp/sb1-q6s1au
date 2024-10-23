package com.restaurant.ordermanager.models;

public class Customer {
    private long id;
    private String name;
    private String phone;
    private String address;

    public Customer(long id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    // Getters
    public long getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
}