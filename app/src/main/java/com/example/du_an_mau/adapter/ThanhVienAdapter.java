package com.example.du_an_mau.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau.DAO.PhieuMuonDao;
import com.example.du_an_mau.DAO.ThanhVienDao;
import com.example.du_an_mau.R;
import com.example.du_an_mau.model.phieumuon;
import com.example.du_an_mau.model.thanhvien;

import java.util.ArrayList;

public class ThanhVienAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<thanhvien> list;
    ThanhVienDao dao;

    TextView hotentv, matv, namsinhtv;
    Button xoatv;
    public ThanhVienAdapter(Context context, ArrayList<thanhvien> list, ThanhVienDao dao) {
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
        convertView = inflater.inflate(R.layout.layout_thanhvien, parent, false);

        matv = convertView.findViewById(R.id.matv);
        hotentv = convertView.findViewById(R.id.hotentv);
        namsinhtv = convertView.findViewById(R.id.namsinhtv);
        xoatv = convertView.findViewById(R.id.xoaTV);

        matv.setText(String.valueOf(list.get(position).getMatv()));
        hotentv.setText(list.get(position).getHoTen());
        namsinhtv.setText(String.valueOf(list.get(position).getNamSinh()));

        xoatv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = list.get(position).getMatv();
                        dao.xoatv(id);
                        list.clear();
                        list.addAll(dao.getData());
                        notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                thanhvien tv = list.get(position);
                updatetv(tv);
                return false;
            }
        });
        return convertView;
    }

    public void updatetv(thanhvien tv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View convertView = inflater.inflate(R.layout.item_update_thanh_vien, null);
        builder.setView(convertView);
        Dialog dialog = builder.create();
        dialog.show();

        EditText udhotentv = convertView.findViewById(R.id.udtentv);
        EditText udnamsinhtv = convertView.findViewById(R.id.udnamsinhtv);

        udhotentv.setText(tv.getHoTen());
        udnamsinhtv.setText(String.valueOf(tv.getNamSinh()));

        convertView.findViewById(R.id.btnupdateTV).setOnClickListener(v -> {
            String hotentv = udhotentv.getText().toString();
            int namsinhtv = Integer.parseInt(udnamsinhtv.getText().toString());

            thanhvien tv1 = new thanhvien(tv.getMatv(), hotentv, namsinhtv);

            dao.suaTV(tv1);
            list.clear();
            list.addAll(dao.getData());
            notifyDataSetChanged();
            dialog.dismiss();
        });

        convertView.findViewById(R.id.btnHuyUdTV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                udhotentv.setText("");
                udnamsinhtv.setText("");
            }
        });
    }

}
