package com.example.du_an_mau.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau.DAO.LoaiSachDao;
import com.example.du_an_mau.DAO.PhieuMuonDao;
import com.example.du_an_mau.DAO.SachDao;
import com.example.du_an_mau.R;
import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.loaisach;
import com.example.du_an_mau.model.phieumuon;
import com.example.du_an_mau.model.sach;

import java.util.ArrayList;
import java.util.HashMap;

public class SachAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<sach> list;
    SachDao dao;

    TextView masach, tens, giathue, maloaifksach;
    Button xoas;

    ArrayList<loaisach> listLS;

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

        xoas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = list.get(position).getMasach();
                        dao.xoaSach(id);
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
                sach sach = list.get(position);
                updateSach(sach);
                return false;
            }
        });

        return convertView;
    }

    public void updateSach(sach s){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View convertView = inflater.inflate(R.layout.item_update_sach, null);
        builder.setView(convertView);
        Dialog dialog = builder.create();
        dialog.show();

        Spinner spnudLS = convertView.findViewById(R.id.spnudLS);
        EditText edudTen = convertView.findViewById(R.id.udtensach);
        EditText edudGiaThue = convertView.findViewById(R.id.udgiathue);

        getDataLS(spnudLS);

        spnudLS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listLS.get(position).getMaloai() == s.getMaloai()) {
                    spnudLS.setSelection(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

//        spnudLS.setSelection(maloai);
        edudTen.setText(s.getTensach());
        edudGiaThue.setText(String.valueOf(s.getGiaThue()));

        convertView.findViewById(R.id.btnUdSach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> hsTV = (HashMap<String, Object>) spnudLS.getSelectedItem();
                int maloai = (int) hsTV.get("maloai");

                String tenSach = edudTen.getText().toString();
                Double giaThue = Double.parseDouble(edudGiaThue.getText().toString());

                sach sach = new sach(s.getMasach(), tenSach, giaThue, maloai);

                dao.suaSach(sach);
                list.clear();
                list.addAll(dao.getData());
                notifyDataSetChanged();

                dialog.dismiss();
            }
        });

        convertView.findViewById(R.id.btnHuyUdSach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spnudLS.setSelection(0);
                edudTen.setText("");
                edudGiaThue.setText("");
            }
        });
    }

    public  void getDataLS(Spinner spinner){
        LoaiSachDao loaiSachDao = new LoaiSachDao(context, new DBHelper(context));
        listLS = loaiSachDao.getData();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (loaisach ls : listLS){
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("maloai", ls.getMaloai());
            hashMap.put("tenloai", ls.getTenloai());

            listHM.add(hashMap);
        }
        SimpleAdapter adapterLS = new SimpleAdapter(context, listHM, android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1});

        spinner.setAdapter(adapterLS);
    }
}
