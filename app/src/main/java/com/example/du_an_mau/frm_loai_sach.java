package com.example.du_an_mau;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_mau.DAO.LoaiSachDao;
import com.example.du_an_mau.adapter.LoaiSachAdapter;
import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.loaisach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frm_loai_sach extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton fac;
    loaisach ls;
    LoaiSachDao dao;
    LoaiSachAdapter adapter;
    ArrayList<loaisach> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View converView = inflater.inflate(R.layout.fragment_frm_loai_sach, container, false);

        recyclerView = converView.findViewById(R.id.recyclerViewLS);
        fac = converView.findViewById(R.id.facLS);

        dao = new LoaiSachDao(getActivity(), new DBHelper(getActivity()));
        list = dao.getData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new LoaiSachAdapter(getContext(), list, dao);
        recyclerView.setAdapter(adapter);

//        fac.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loaisach ls = new loaisach();
//                addloaisach(ls);
//            }
//        });
        return converView;
    }
}