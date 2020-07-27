package com.example.oderfood;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtUsername,edtPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ac = getSupportActionBar();
        ac.hide();
        edtUsername = findViewById(R.id.edtUsrename);
        edtPassword =findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                boolean check = checkPassword(username,password);
                if(check){
                    Intent intent = new Intent(getBaseContext(), MenuFood.class);
                    intent.putExtra("username",edtUsername.getText().toString());
                    startActivityForResult(intent,100);
                }
            }
        });
    }
    public boolean checkPassword(String Username, String password){
        if(Username.length()==0){
            Toast.makeText(getBaseContext(),"Tài khoản không được để trống không được để trống",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 8) {
                Toast.makeText(getBaseContext(), "Mật khẩu phải từ 8 kí tự trở lên", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                int check = 0;
                for (int i = 0; i < password.length(); i++) {
                    char let = password.charAt(i);
                    if (isLower(let)) {
                        check = 1;
                        break;
                    }
                }
                if (check == 0) {
                    Toast.makeText(getBaseContext(), "Mật khẩu phải có ít nhất 1 kí tự thường", Toast.LENGTH_SHORT).show();
                    return false;
                }
                check = 0;
                for (int i = 0; i < password.length(); i++) {
                    char let = password.charAt(i);
                    if (isUpper(let)) {
                        check = 1;
                        break;
                    }
                }
                if (check == 0) {
                    Toast.makeText(getBaseContext(), "Mật khẩu phải có ít nhất 1 kí tự in hoa", Toast.LENGTH_SHORT).show();
                    return false;
                }
                check = 0;
                for (int i = 0; i < password.length(); i++) {
                    char let = password.charAt(i);
                    if (isDigit(let)) {
                        check = 1;
                        break;
                    }
                }
                if (check == 0) {
                    Toast.makeText(getBaseContext(), "Mật khẩu phải có ít nhất 1 kí tự số", Toast.LENGTH_SHORT).show();
                    return false;
                }
                check = 0;
                for (int i = 0; i < password.length(); i++) {
                    char let = password.charAt(i);
                    if (isSpecial(let)) {
                        check = 1;
                        break;
                    }
                }
                if (check == 0) {
                    Toast.makeText(getBaseContext(), "Mật khẩu phải có ít nhất 1 kí tự đặc biệt", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }

        return true;
    }
    public static boolean isLower(char let){
        return let >= 'a' && let <='z';
    }
    public static boolean isUpper(char let){
        return let >= 'A' && let <='Z';
    }
    public static boolean isDigit(char let){
        return let >='0' && let<='9';
    }
    public static boolean isSpecial(char let){
        String s = "!@#$%^&*()";
        return s.indexOf(let)!=-1;
    }
}
