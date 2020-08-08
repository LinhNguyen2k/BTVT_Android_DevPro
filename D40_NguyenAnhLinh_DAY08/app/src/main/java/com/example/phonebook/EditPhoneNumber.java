package com.example.phonebook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditPhoneNumber extends AppCompatActivity {

    List<Contact> contactList;
    TextView tvDone ,etName, phone;
    CircleImageView avt;
    static  Contact contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phone_number);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        final Intent intent = getIntent();
        tvDone = findViewById(R.id.tvXong3);
        etName = findViewById(R.id.name3);
        phone = findViewById(R.id.sdt3);
        avt = findViewById(R.id.image_avt3);
         final String name = intent.getStringExtra("keyname1");
         String phoneNumber = intent.getStringExtra("keyPhoneNumber1");
         int img = intent.getIntExtra("keyimg1", 1);
        etName.setText(name);
        phone.setText(phoneNumber);
        avt.setImageResource(img);
        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQL_Helper sql_helper = new  SQL_Helper(getBaseContext());

                boolean check = sql_helper.deletePhone( contact.getName());

                Intent intent1 = new Intent(getBaseContext(), MainActivity.class);
//                intent1.putExtra("keyname2", etName.getText().toString());
//                intent1.putExtra("keyPhoneNumber2", phone.getText().toString());
//                intent1.putExtra("keyimg2", (int) R.drawable.android_avt1);




            }
        });
    }
}
