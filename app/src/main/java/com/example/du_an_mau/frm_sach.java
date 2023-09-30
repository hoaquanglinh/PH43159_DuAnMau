package com.example.du_an_mau;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.du_an_mau.DAO.SachDao;
import com.example.du_an_mau.adapter.SachAdapter;
import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frm_sach extends Fragment {
    ListView listViewSach;
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

        listViewSach = view.findViewById(R.id.listViewSach);
        fac = view.findViewById(R.id.facSach);

        dao = new SachDao(getActivity(), new DBHelper(getActivity()));
        list = dao.getData();

        adapter = new SachAdapter(getContext(), list, dao);
        listViewSach.setAdapter(adapter);

        return view;
    }
}