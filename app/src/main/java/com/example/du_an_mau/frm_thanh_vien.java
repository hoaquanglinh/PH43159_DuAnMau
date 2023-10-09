package com.example.du_an_mau;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.du_an_mau.DAO.ThanhVienDao;
import com.example.du_an_mau.adapter.ThanhVienAdapter;
import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.thanhvien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frm_thanh_vien extends Fragment {
    ListView listViewTV;
    FloatingActionButton fac;
    thanhvien tv;
    ThanhVienDao dao;
    ThanhVienAdapter adapter;
    ArrayList<thanhvien> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frm_thanh_vien, container, false);
        listViewTV = view.findViewById(R.id.listViewTV);
        fac = view.findViewById(R.id.facTV);

        dao = new ThanhVienDao(getActivity(), new DBHelper(getActivity()));
        list = dao.getData();

        adapter = new ThanhVienAdapter(getContext(), list, dao);
        listViewTV.setAdapter(adapter);

        fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thanhvien tv = new thanhvien();
                addthanhvien(tv);
            }
        });

        return view;
    }

    public void addthanhvien(thanhvien tv){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        View convertView = inflater.inflate(R.layout.item_them_thanh_vien, null);

        builder.setView(convertView);
        Dialog dialog = builder.create();
        dialog.show();

        EditText addtentv = convertView.findViewById(R.id.addhotentv);
        EditText addnamsinh = convertView.findViewById(R.id.addnamsinhtv);

        convertView.findViewById(R.id.btnaddTV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentv = addtentv.getText().toString();
                String namsinhtv = addnamsinh.getText().toString();

                if (tentv.isEmpty() || namsinhtv.isEmpty()){
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        int namsinh = Integer.parseInt(namsinhtv);

                        if (namsinh <= 0){
                            Toast.makeText(getContext(), "Năm sinh phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                        }else{
                            thanhvien tv = new thanhvien(tentv, namsinh);

                            dao.themTV(tv);
                            list.clear();
                            list.addAll(dao.getData());
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }catch (NumberFormatException e){
                        Toast.makeText(getContext(), "Năm sinh phải là số", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        convertView.findViewById(R.id.btnHuyAddTV).setOnClickListener(v -> {
            dialog.dismiss();
        });
    }
}