package com.example.du_an_mau;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_mau.DAO.ThuThuDao;
import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.thuthu;

import java.util.ArrayList;

public class frm_tao_tai_khoan extends Fragment {
    ThuThuDao dao;
    ArrayList<thuthu> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frm_tao_tai_khoan, container, false);

        EditText edmatt = view.findViewById(R.id.addmatt);
        EditText edhoten = view.findViewById(R.id.addhotentt);
        EditText edmatkhau = view.findViewById(R.id.addmatkhau);
        EditText edrematkhau = view.findViewById(R.id.addrematkhau);

        view.findViewById(R.id.btnaddthuthu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matt = edmatt.getText().toString();
                String hoten = edhoten.getText().toString();
                String matkhau = edmatkhau.getText().toString();
                String rematkhau = edrematkhau.getText().toString();
                if (matt.isEmpty() || hoten.isEmpty() || matkhau.isEmpty() || rematkhau.isEmpty()){
                    Toast.makeText(getContext(), "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    if(!matkhau.equals(rematkhau)){
                        Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    }else{
                        thuthu tt = new thuthu(matt, hoten, matkhau);

                        dao = new ThuThuDao(getContext(), new DBHelper(getContext()));
                        dao.themTT(tt);

                        list = new ArrayList<>();
                        list.clear();
                        list.addAll(dao.getData());

                        Toast.makeText(getContext(), "Thêm mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        view.findViewById(R.id.btnhuyaddthuthu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edmatt.setText("");
                edhoten.setText("");
                edmatkhau.setText("");
                edrematkhau.setText("");
            }
        });

        return view;
    }
}