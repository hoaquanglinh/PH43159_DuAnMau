package com.example.du_an_mau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_mau.DAO.ThuThuDao;
import com.example.du_an_mau.database.DBHelper;

public class ManHinhDangNhap extends AppCompatActivity {
    ThuThuDao dao;
    EditText edtUser, edtPass;
    String strUser, strPass;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_nhap);

        edtUser = findViewById(R.id.edtUserName);
        edtPass = findViewById(R.id.edtPassword);
        checkBox = findViewById(R.id.chkRemember);

        dao = new ThuThuDao(this, new DBHelper(this));

//        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String user = edtUser.getText().toString();
//                String pass = edtPass.getText().toString();
//
//                if(thuThuDao.checkDangNhap(user, pass)){
//                    SharedPreferences sharedPreferences = getSharedPreferences("thongtin", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("matt", user);
//                    editor.commit();
//
//                    Toast.makeText(ManHinhDangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(ManHinhDangNhap.this, ManHinhChinh.class);
//                    startActivity(intent);
//                }else{
//                    Toast.makeText(ManHinhDangNhap.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        SharedPreferences pref = getSharedPreferences("user_file", MODE_PRIVATE);
        String user = pref.getString("username", "");
        String pass = pref.getString("password", "");
        Boolean rem = pref.getBoolean("remember", false);

        edtUser.setText(user);
        edtPass.setText(pass);
        checkBox.setChecked(rem);

        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtUser.setText("");
                edtPass.setText("");
            }
        });

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    public void remember(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("user_file", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

        if(!status){
            // xoa tinh trang luu trc do
            edit.clear();
        }else{
            edit.putString("username", u);
            edit.putString("password", p);
            edit.putBoolean("remember", status);
        }
        // luu lai toan bo
        edit.commit();
    }

    public void checkLogin(){
        strUser = edtUser.getText().toString();
        strPass = edtPass.getText().toString();
        if(strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(this, "Tên đăng nhập và mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        }else{
            if(dao.checkDangNhap(strUser, strPass) == true){
                Toast.makeText(this, "Login thành công", Toast.LENGTH_SHORT).show();
                remember(strUser, strPass, checkBox.isChecked());
                Intent i = new Intent(getApplicationContext(), ManHinhChinh.class);
                i.putExtra("user", strUser);
                startActivity(i);
                finish();
            }else{
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }

}