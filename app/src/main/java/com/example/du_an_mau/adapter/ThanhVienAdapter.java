package com.example.du_an_mau.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau.DAO.PhieuMuonDao;
import com.example.du_an_mau.DAO.ThanhVienDao;
import com.example.du_an_mau.R;
import com.example.du_an_mau.model.phieumuon;
import com.example.du_an_mau.model.thanhvien;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolderTV>{
    private Context context;
    private ArrayList<thanhvien> list;
    ThanhVienDao dao;

    public ThanhVienAdapter(Context context, ArrayList<thanhvien> list, ThanhVienDao dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    @NonNull
    @Override
    public ViewHolderTV onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_thanhvien, null);
        return new ViewHolderTV(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTV holder, int position) {
        holder.matv.setText(String.valueOf(list.get(position).getMatv()));
        holder.hotentv.setText(list.get(position).getHoTen());
        holder.namsinhtv.setText(String.valueOf(list.get(position).getNamSinh()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderTV extends RecyclerView.ViewHolder{
        TextView hotentv, matv, namsinhtv;
        Button suatv, xoatv;
        public ViewHolderTV(@NonNull View itemView) {
            super(itemView);
            matv = itemView.findViewById(R.id.matv);
            hotentv = itemView.findViewById(R.id.hotentv);
            namsinhtv = itemView.findViewById(R.id.namsinhtv);
            suatv = itemView.findViewById(R.id.suaTV);
            xoatv = itemView.findViewById(R.id.xoaTV);
        }
    }}
