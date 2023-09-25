package com.example.du_an_mau;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_mau.DAO.LoaiSachDao;
import com.example.du_an_mau.DAO.PhieuMuonDao;
import com.example.du_an_mau.adapter.LoaiSachAdapter;
import com.example.du_an_mau.adapter.PhieuMuonAdapter;
import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.loaisach;
import com.example.du_an_mau.model.phieumuon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frm_phieu_muon extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton fac;
    loaisach ls;
    PhieuMuonDao dao;
    PhieuMuonAdapter adapter;
    ArrayList<phieumuon> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frm_phieu_muon, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewPM);
        fac = view.findViewById(R.id.facPhieuMuon);

        dao = new PhieuMuonDao(getActivity(), new DBHelper(getActivity()));
        list = dao.getData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new PhieuMuonAdapter(getContext(), list, dao);
        recyclerView.setAdapter(adapter);

        return view;
    }
}