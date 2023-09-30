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
import com.example.du_an_mau.R;
import com.example.du_an_mau.model.phieumuon;

import java.util.ArrayList;

public class PhieuMuonAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<phieumuon> list;
    PhieuMuonDao dao;

    TextView maphieu, mathanhvienfkpm, masachfkpm, tienthue, trangthai, ngaythue;
    Button xoa;
    public PhieuMuonAdapter(Context context, ArrayList<phieumuon> list, PhieuMuonDao dao) {
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
        convertView = inflater.inflate(R.layout.layout_phieumuon, parent, false);

        maphieu = convertView.findViewById(R.id.maphieu);
        mathanhvienfkpm = convertView.findViewById(R.id.mathanhvienfkpm);
        masachfkpm = convertView.findViewById(R.id.masachfkpm);
        tienthue = convertView.findViewById(R.id.tienthue);
        trangthai = convertView.findViewById(R.id.trangthai);
        ngaythue = convertView.findViewById(R.id.ngaythue);
        xoa = convertView.findViewById(R.id.xoaPhieuMuon);

        maphieu.setText(String.valueOf(list.get(position).getMapm()));
        mathanhvienfkpm.setText(String.valueOf(list.get(position).getTentv()));
        masachfkpm.setText(String.valueOf(list.get(position).getTensach()));
        tienthue.setText(String.valueOf(list.get(position).getTienthue()));
        String trangThai = "";
        if(list.get(position).getTrangthai() == 1){
            trangThai = "Đã trả sách";
        }else{
            trangThai = "Chưa trả sách";
        }
        trangthai.setText(trangThai);
        ngaythue.setText(list.get(position).getNgaythue());

        return convertView;
    }



}
