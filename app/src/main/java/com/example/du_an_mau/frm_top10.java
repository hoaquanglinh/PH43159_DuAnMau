package com.example.du_an_mau;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.du_an_mau.DAO.Top10Dao;
import com.example.du_an_mau.adapter.Top10Adapter;
import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.sach;

import java.util.ArrayList;

public class frm_top10 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frm_top10, container, false);

        RecyclerView recyclerView10 = view.findViewById(R.id.recyclerViewTop10);

        Top10Dao dao = new Top10Dao(getContext(), new DBHelper(getContext()));
        ArrayList<sach> list = dao.getTop10();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView10.setLayoutManager(linearLayoutManager);

        Top10Adapter adapter = new Top10Adapter(getContext(), list);
        recyclerView10.setAdapter(adapter);

        return view;
    }
}