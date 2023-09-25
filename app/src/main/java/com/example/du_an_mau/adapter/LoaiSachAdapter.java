package com.example.du_an_mau.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau.DAO.LoaiSachDao;
import com.example.du_an_mau.R;
import com.example.du_an_mau.model.loaisach;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {
    private Context context;
    private ArrayList<loaisach> list;
    LoaiSachDao dao;

    public LoaiSachAdapter(Context context, ArrayList<loaisach> list, LoaiSachDao dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_loaisach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.maloai.setText(String.valueOf(list.get(position).getMaloai()));
        holder.tenls.setText(list.get(position).getTenloai());

//        holder.xoals.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle("Thông báo");
//                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        int id = list.get(holder.getAdapterPosition()).getMaloai();
//                        dao.xoaLS(id);
//                        list.clear();
//                        list.addAll(dao.danhsach());
//                        notifyDataSetChanged();
//                    }
//                });
//
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });

//        holder.suals.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loaisach ls = list.get(holder.getAdapterPosition());
//                update(ls);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView maloai, tenls;
        Button suals, xoals;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            maloai = itemView.findViewById(R.id.maloai);
            tenls = itemView.findViewById(R.id.tenloaisach);
            suals = itemView.findViewById(R.id.sualoaisach);
            xoals = itemView.findViewById(R.id.xoaloaisach);
        }
    }
}
