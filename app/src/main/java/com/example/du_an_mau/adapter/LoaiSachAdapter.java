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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau.DAO.LoaiSachDao;
import com.example.du_an_mau.R;
import com.example.du_an_mau.model.loaisach;

import java.util.ArrayList;

public class LoaiSachAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<loaisach> list;
    LoaiSachDao dao;

    public LoaiSachAdapter(Context context, ArrayList<loaisach> list, LoaiSachDao dao) {
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
        convertView = inflater.inflate(R.layout.layout_loaisach, parent, false);

        TextView maloai = convertView.findViewById(R.id.maloai);
        TextView tenls = convertView.findViewById(R.id.tenloaisach);
        Button xoals = convertView.findViewById(R.id.xoaloaisach);

        maloai.setText(String.valueOf(list.get(position).getMaloai()));
        tenls.setText(list.get(position).getTenloai());

        xoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = list.get(position).getMaloai();
                        dao.xoaLS(id);
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
                loaisach ls = list.get(position);
                update(ls);
                return false;
            }
        });
        return convertView;
    }
    public void update(loaisach ls){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View convertView = inflater.inflate(R.layout.item_update_loai_sach, null);
        builder.setView(convertView);
        Dialog dialog = builder.create();
        dialog.show();

        EditText udtenloai = convertView.findViewById(R.id.udtenloai);

        udtenloai.setText(ls.getTenloai());

        convertView.findViewById(R.id.btnupdateLS).setOnClickListener(v -> {
            String tenloai = udtenloai.getText().toString();

            if(tenloai.isEmpty()){
                Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
            }else{
                loaisach sp1 = new loaisach(ls.getMaloai(), tenloai);

                dao.suaLS(sp1);
                list.clear();
                list.addAll(dao.getData());
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        convertView.findViewById(R.id.btnHuyLS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
