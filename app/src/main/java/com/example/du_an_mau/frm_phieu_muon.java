package com.example.du_an_mau;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.du_an_mau.DAO.LoaiSachDao;
import com.example.du_an_mau.DAO.PhieuMuonDao;
import com.example.du_an_mau.adapter.LoaiSachAdapter;
import com.example.du_an_mau.adapter.PhieuMuonAdapter;
import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.loaisach;
import com.example.du_an_mau.model.phieumuon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class frm_phieu_muon extends Fragment {
    ListView listViewPM;
    FloatingActionButton fac;
    loaisach ls;
    PhieuMuonDao dao;
    PhieuMuonAdapter adapter;
    ArrayList<phieumuon> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frm_phieu_muon, container, false);

        listViewPM = view.findViewById(R.id.listViewPM);
        fac = view.findViewById(R.id.facPhieuMuon);

        dao = new PhieuMuonDao(getActivity(), new DBHelper(getActivity()));
        list = dao.getData();

        adapter = new PhieuMuonAdapter(getContext(), list, dao);
        listViewPM.setAdapter(adapter);

        return view;
    }
}