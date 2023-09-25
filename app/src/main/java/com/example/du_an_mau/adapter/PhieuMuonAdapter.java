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
import com.example.du_an_mau.R;
import com.example.du_an_mau.model.phieumuon;

import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolderPM>{
    private Context context;
    private ArrayList<phieumuon> list;
    PhieuMuonDao dao;

    public PhieuMuonAdapter(Context context, ArrayList<phieumuon> list, PhieuMuonDao dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    @NonNull
    @Override
    public ViewHolderPM onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_phieumuon, parent, false);
        return new ViewHolderPM(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPM holder, int position) {
        holder.maphieu.setText(String.valueOf(list.get(position).getMapm()));
        holder.mathanhvienfkpm.setText(String.valueOf(list.get(position).getTentv()));
        holder.masachfkpm.setText(String.valueOf(list.get(position).getTensach()));
        holder.tienthue.setText(String.valueOf(list.get(position).getTienthue()));
        String trangthai = "";
        if(list.get(position).getTrangthai() == 1){
            trangthai = "Đã trả sách";
        }else{
            trangthai = "Chưa trả sách";
        }
        holder.trangthai.setText(trangthai);
        holder.ngaythue.setText(list.get(position).getNgaythue());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderPM extends RecyclerView.ViewHolder{
        TextView maphieu, mathanhvienfkpm, masachfkpm, tienthue, trangthai, ngaythue;
        Button sua, xoa;
        public ViewHolderPM(@NonNull View itemView) {
            super(itemView);
            maphieu = itemView.findViewById(R.id.maphieu);
            mathanhvienfkpm = itemView.findViewById(R.id.mathanhvienfkpm);
            masachfkpm = itemView.findViewById(R.id.masachfkpm);
            tienthue = itemView.findViewById(R.id.tienthue);
            trangthai = itemView.findViewById(R.id.trangthai);
            ngaythue = itemView.findViewById(R.id.ngaythue);
            sua = itemView.findViewById(R.id.suaPhieuMuon);
            xoa = itemView.findViewById(R.id.xoaPhieuMuon);
        }
    }
}
