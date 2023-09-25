package com.example.du_an_mau;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_mau.DAO.SachDao;
import com.example.du_an_mau.adapter.SachAdapter;
import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frm_sach extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton fac;
    sach ls;
    SachDao dao;
    SachAdapter adapter;
    ArrayList<sach> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frm_sach, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewSach);
        fac = view.findViewById(R.id.facSach);

        dao = new SachDao(getActivity(), new DBHelper(getActivity()));
        list = dao.getData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new SachAdapter(getContext(), list, dao);
        recyclerView.setAdapter(adapter);

        return view;
    }
}