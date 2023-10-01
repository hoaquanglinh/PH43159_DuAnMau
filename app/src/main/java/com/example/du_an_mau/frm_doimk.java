package com.example.du_an_mau;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.du_an_mau.DAO.ThuThuDao;
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
                SharedPreferences pref = getActivity().getSharedPreferences("user_file", Context.MODE_PRIVATE);
                String user = pref.getString("username", "");
                if(validate()>0){
//                    thuthu thuthu = dao.get
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