package com.example.du_an_mau;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.du_an_mau.DAO.ThuThuDao;
import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.thuthu;
import com.google.android.material.textfield.TextInputEditText;

public class frm_doimk extends Fragment {
    TextInputEditText edPassOld, edPass, edRePass;
    Button btnsave, btnCancel;
    ThuThuDao dao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frm_doimk, container, false);
        edPassOld = view.findViewById(R.id.edPassOld);
        edPass = view.findViewById(R.id.edPassChange);
        edRePass = view.findViewById(R.id.edRePassChange);

        btnsave = view.findViewById(R.id.btndoimk);
        btnCancel = view.findViewById(R.id.btnhuytdoimk);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassOld.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = edPassOld.getText().toString();
                String newPass = edPass.getText().toString();
                String newRePass = edRePass.getText().toString();

                if(newPass.equals(newRePass)){
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_file", Context.MODE_PRIVATE);
                    String matt = sharedPreferences.getString("matt", "");

                    ThuThuDao thuThuDao = new ThuThuDao(getContext(), new DBHelper(getContext()));
                    boolean check = thuThuDao.capNhatMatKhau(matt, oldPass, newPass);

                    if(check){
                        Toast.makeText(getContext(), "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), ManHinhDangNhap.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getContext(), "Nhập sai mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Mật khẩu không trùng với nhau", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public int validate(){
        int check = 1;
        if(edPassOld.getText().length()==0 || edPass.getText().length() == 0 || edRePass.getText().length() ==0){
            Toast.makeText(getContext(), "Bạn phải điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else{
            SharedPreferences pref = getActivity().getSharedPreferences("user_file", Context.MODE_PRIVATE);
            String passOld = pref.getString("password", "");
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();

            if(!passOld.equalsIgnoreCase(edPassOld.getText().toString())){
                Toast.makeText(getContext(), "Sai mật khẩu cũ", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }

}