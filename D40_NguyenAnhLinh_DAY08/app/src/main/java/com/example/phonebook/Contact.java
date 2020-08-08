package com.example.phonebook;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact {
    public String name,phoneNumber;
    public int img;

    public Contact(String name, String phoneNumber, int img) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getImg() {
        return img;
    }

}
