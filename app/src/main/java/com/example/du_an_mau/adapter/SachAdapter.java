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
import com.example.du_an_mau.DAO.SachDao;
import com.example.du_an_mau.R;
import com.example.du_an_mau.model.phieumuon;
import com.example.du_an_mau.model.sach;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolderSach>{

    private Context context;
    private ArrayList<sach> list;
    SachDao dao;

    public SachAdapter(Context context, ArrayList<sach> list, SachDao dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    @NonNull
    @Override
    public ViewHolderSach onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_sach, null);
        return new ViewHolderSach(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSach holder, int position) {
        holder.masach.setText(String.valueOf(list.get(position).getMasach()));
        holder.tens.setText(list.get(position).getTensach());
        holder.giathue.setText(String.valueOf(list.get(position).getGiaThue()));
        holder.maloaifksach.setText(list.get(position).getTenLoai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolderSach extends RecyclerView.ViewHolder{
        TextView masach, tens, giathue, maloaifksach;
        Button suas, xoas;
        public ViewHolderSach(@NonNull View itemView) {
            super(itemView);
            masach = itemView.findViewById(R.id.masach);
            tens = itemView.findViewById(R.id.tensach);
            giathue = itemView.findViewById(R.id.giathue);
            maloaifksach = itemView.findViewById(R.id.maloaifksach);
            suas = itemView.findViewById(R.id.suasach);
            xoas = itemView.findViewById(R.id.xoasach);
        }
    }
}
