package com.example.phonebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterContact extends BaseAdapter {

    List<Contact> contactList;


    public AdapterContact(MainActivity mainActivity, int item_contact, List<Contact> contactList) {
        this.contactList = contactList;
    }

    public AdapterContact(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_contact,parent,false);

        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvSDT = view.findViewById(R.id.tvSDT);
        CircleImageView avt = view.findViewById(R.id.image_avt);

        Contact contact = contactList.get(position);

        tvName.setText(contact.getName());
        tvSDT.setText(contact.getPhoneNumber());
        avt.setImageResource(contact.getImg());
        return view;

    }
}
