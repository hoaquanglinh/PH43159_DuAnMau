package com.example.du_an_mau.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau.DAO.PhieuMuonDao;
import com.example.du_an_mau.DAO.SachDao;
import com.example.du_an_mau.R;
import com.example.du_an_mau.model.phieumuon;
import com.example.du_an_mau.model.sach;

import java.util.ArrayList;

public class SachAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<sach> list;
    SachDao dao;

    TextView masach, tens, giathue, maloaifksach;
    Button xoas;

    public SachAdapter(Context context, ArrayList<sach> list, SachDao dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.layout_sach, null);

        masach = convertView.findViewById(R.id.masach);
        tens = convertView.findViewById(R.id.tensach);
        giathue = convertView.findViewById(R.id.giathue);
        maloaifksach = convertView.findViewById(R.id.maloaifksach);
        xoas = convertView.findViewById(R.id.xoasach);

        masach.setText(String.valueOf(list.get(position).getMasach()));
        tens.setText(list.get(position).getTensach());
        giathue.setText(String.valueOf(list.get(position).getGiaThue()));
        maloaifksach.setText(list.get(position).getTenLoai());

        return convertView;
    }
}
