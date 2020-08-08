package com.example.phonebook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProFilePhoneNumber extends AppCompatActivity {

    TextView tvName, phone, tvSua;
    CircleImageView avt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_file_phone_number);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        tvName = findViewById(R.id.name1);
        phone = findViewById(R.id.phoneNumber1);
        avt = findViewById(R.id.image_avt1);
        tvSua = findViewById(R.id.tvSua2);

        final Intent intent = getIntent();

         String name = intent.getStringExtra("keyname");
         String phoneNumber = intent.getStringExtra("keyPhoneNumber");
        final int img = intent.getIntExtra("keyimg", 1);

        tvName.setText(name);
        phone.setText(phoneNumber);
        avt.setImageResource(img);
        tvSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getBaseContext(), EditPhoneNumber.class);
                intent1.putExtra("keyname1", tvName.getText().toString());
                intent1.putExtra("keyPhoneNumber1", phone.getText().toString());
                intent1.putExtra("keyimg1",(int) img);
                startActivityForResult(intent1, 4);
            }
        });


    }
}
