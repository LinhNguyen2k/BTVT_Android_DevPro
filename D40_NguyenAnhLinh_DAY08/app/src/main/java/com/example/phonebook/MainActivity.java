package com.example.phonebook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Contact> contactList;
    Contact contact1, contact2, contact3, contact4;
    TextView search;
    ImageView add;
    int mposition;
    AdapterContact adapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.imgadd);
        search = findViewById(R.id.searchView);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        contactList = new ArrayList<>();

        SQL_Helper sql_helper = new SQL_Helper(this);
//        contact1 = new Contact("Nguyễn Anh Linh", "012345678", R.drawable.android_avt1);
//        contact2 = new Contact("Nguyễn Đức Điệp", "012345678", R.drawable.android_avt6);
//        contact3 = new Contact("Vũ Văn Doan", "012345678", R.drawable.android_avt10);
//        contact4 = new Contact("Đoàn Duy Nam", "012345678", R.drawable.android_avt6);
//
//        contactList.add(contact1);
//        contactList.add(contact2);
//        contactList.add(contact3);
//        contactList.add(contact4);

//        sql_helper.insertPhone(contact1);
//        sql_helper.insertPhone(contact2);
//        sql_helper.insertPhone(contact3);
//        sql_helper.insertPhone(contact4);

        contactList = sql_helper.GetallPhoneNumber();
        adapterContact = new AdapterContact(contactList);
        final ListView listView = findViewById(R.id.lvcontact);
        AdapterContact adapterContact = new AdapterContact(MainActivity.this, R.layout.item_contact, contactList);

        listView.setAdapter(adapterContact);


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ArrayList<Contact> newlist = new ArrayList<>();
                for (int i = 0; i < contactList.size(); i++) {
                    if (contactList.get(i).name.toLowerCase().contains(s.toString().toLowerCase())) {
                        newlist.add(contactList.get(i));
                    }
                }
                AdapterContact chatAdapter = new AdapterContact(MainActivity.this, R.layout.item_contact, newlist);
                listView.setAdapter(chatAdapter);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = contactList.get(position).getName();
                String phoneNumber = contactList.get(position).getPhoneNumber();
                int img = contactList.get(position).getImg();

                Intent intent = new Intent(getBaseContext(), ProFilePhoneNumber.class);

                intent.putExtra("keyname", name);
                intent.putExtra("keyPhoneNumber", phoneNumber);
                intent.putExtra("keyimg", img);
                startActivityForResult(intent, 1);
                mposition = position;
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddPhoneNumber.class);
                startActivityForResult(intent, 2);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {

            case RESULT_OK:
                String name = data.getStringExtra("keynewNam");
                String phone = data.getStringExtra("keynewPhoneNumber");
                int img = data.getIntExtra("keynewimg", 1);
                if (requestCode == 2) {
                contactList.add(new Contact(name, phone, img));
                SQL_Helper sql_helper;
                sql_helper = new SQL_Helper(this);
                sql_helper.insertPhone(new Contact(name, phone, img));
                adapterContact.notifyDataSetChanged();
                }

                break;
            case RESULT_CANCELED:

                break;
        }
    }
}
