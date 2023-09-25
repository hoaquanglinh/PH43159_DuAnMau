package com.example.du_an_mau;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_mau.DAO.ThanhVienDao;
import com.example.du_an_mau.adapter.ThanhVienAdapter;
import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.thanhvien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frm_thanh_vien extends Fragment {
    RecyclerView recyclerView;
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
        recyclerView = view.findViewById(R.id.recyclerViewTV);
        fac = view.findViewById(R.id.facTV);

        dao = new ThanhVienDao(getActivity(), new DBHelper(getActivity()));
        list = dao.getData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new ThanhVienAdapter(getContext(), list, dao);
        recyclerView.setAdapter(adapter);

        return view;
    }
}