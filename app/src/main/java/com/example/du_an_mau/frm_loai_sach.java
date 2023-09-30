package com.example.du_an_mau;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.du_an_mau.DAO.LoaiSachDao;
import com.example.du_an_mau.adapter.LoaiSachAdapter;
import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.loaisach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frm_loai_sach extends Fragment {
    ListView listViewLS;
    FloatingActionButton fac;
    loaisach ls;
    LoaiSachDao dao;
    LoaiSachAdapter adapter;
    ArrayList<loaisach> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View converView = inflater.inflate(R.layout.fragment_frm_loai_sach, container, false);

        listViewLS = converView.findViewById(R.id.listViewLS);
        fac = converView.findViewById(R.id.facLS);

        dao = new LoaiSachDao(getActivity(), new DBHelper(getActivity()));
        list = dao.getData();

        adapter = new LoaiSachAdapter(getContext(), list, dao);
        listViewLS.setAdapter(adapter);

        fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaisach ls = new loaisach();
                addloaisach(ls);
            }
        });

        return converView;
    }

    public void addloaisach(loaisach ls){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        View convertView = inflater.inflate(R.layout.item_them_loai_sach, null);

        builder.setView(convertView);
        Dialog dialog = builder.create();
        dialog.show();

        EditText addtenLS = convertView.findViewById(R.id.addtenloai);

        convertView.findViewById(R.id.btnaddLS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenls = addtenLS.getText().toString();

                loaisach ls = new loaisach(tenls);

                dao.themLS(ls);
                list.clear();
                list.addAll(dao.getData());
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        convertView.findViewById(R.id.btnHuyAddLS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtenLS.setText("");
            }
        });
    }
}