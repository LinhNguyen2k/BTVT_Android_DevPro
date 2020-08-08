package com.example.phonebook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddPhoneNumber extends AppCompatActivity {
    List<Contact> contactList;
    TextView tvName, phone, tvDone;
    CircleImageView avt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone_number);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        tvName = findViewById(R.id.name2);
        phone = findViewById(R.id.sdt2);
        avt = findViewById(R.id.image_avt2);
        tvDone = findViewById(R.id.tvXong);

        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("keynewNam", tvName.getText().toString());
                intent1.putExtra("keynewPhoneNumber", phone.getText().toString());
                intent1.putExtra("keynewimg",R.drawable.android_avt1);
                setResult(RESULT_OK, intent1);
                finish();

            }
        });
    }
}
