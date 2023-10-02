package com.example.du_an_mau.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau.DAO.PhieuMuonDao;
import com.example.du_an_mau.DAO.SachDao;
import com.example.du_an_mau.DAO.ThanhVienDao;
import com.example.du_an_mau.R;
import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.phieumuon;
import com.example.du_an_mau.model.sach;
import com.example.du_an_mau.model.thanhvien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class PhieuMuonAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<phieumuon> list;
    PhieuMuonDao dao;

    TextView maphieu, mathanhvienfkpm, masachfkpm, tienthue, trangthai, ngaythue;
    Button xoa;
    ArrayList<sach> listS;

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

        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = list.get(position).getMapm();
                        dao.xoaPM(id);
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
                phieumuon pm = list.get(position);
                updatePM(pm);

                return false;
            }
        });

        return convertView;
    }

    private void getDataThanhVien(Spinner spnTV) {
        ThanhVienDao thanhVienDao = new ThanhVienDao(context, new DBHelper(context));
        ArrayList<thanhvien> listTV = thanhVienDao.getData();

        ArrayList<HashMap<String, Object>> listHashMap = new ArrayList<>();

        for (thanhvien tv : listTV) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("matv", tv.getMatv());
            hashMap.put("hotentv", tv.getHoTen());

            listHashMap.add(hashMap);
        }

        SimpleAdapter adapterTV = new SimpleAdapter(context, listHashMap, android.R.layout.simple_list_item_1,
                new String[]{"hotentv"},
                new int[]{android.R.id.text1});

        spnTV.setAdapter(adapterTV);
    }

    private void getDataSach(Spinner spnSach) {
        ArrayList<HashMap<String, Object>> listHashMap = new ArrayList<>();

        for (sach s : listS) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("masach", s.getMasach());
            hashMap.put("tensach", s.getTensach());
            hashMap.put("giathue", s.getGiaThue());
            listHashMap.add(hashMap);
        }

        SimpleAdapter adapterSach = new SimpleAdapter(context, listHashMap, android.R.layout.simple_list_item_1,
                new String[]{"tensach"},
                new int[]{android.R.id.text1});

        spnSach.setAdapter(adapterSach);
    }

    public void updatePM(phieumuon pm){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View convertView = inflater.inflate(R.layout.item_update_phieu_muon, null);
        builder.setView(convertView);
        Dialog dialog = builder.create();
        dialog.show();

        Spinner spnUdTV = convertView.findViewById(R.id.spnThanhVienPM);
        Spinner spnUdSach = convertView.findViewById(R.id.spnSachPM);
        TextView tvTien = convertView.findViewById(R.id.tvUdTien);
        EditText edudngaythue = convertView.findViewById(R.id.edudngayThue);
        CheckBox chkTT = convertView.findViewById(R.id.chkudTrangThai);
        TextView trangThai = convertView.findViewById(R.id.udtrangthai);
        Button update = convertView.findViewById(R.id.btnUdPM);
        Button cacel = convertView.findViewById(R.id.btnHuyUdPM);


        SachDao sachDao = new SachDao(context, new DBHelper(context));
        listS = sachDao.getData();

        getDataThanhVien(spnUdTV);
        getDataSach(spnUdSach);

        spnUdTV.setSelection(pm.getMatv() - 1);
        spnUdSach.setSelection(pm.getMasach() - 1);

        tvTien.setText(String.valueOf(pm.getTienthue()));

        chkTT.setChecked(pm.getTrangthai() == 1);

        edudngaythue.setText(pm.getNgaythue());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> hsTV = (HashMap<String, Object>) spnUdTV.getSelectedItem();
                int matv = (int) hsTV.get("matv");

                HashMap<String, Object> hsSach = (HashMap<String, Object>) spnUdSach.getSelectedItem();
                int masach = (int) hsSach.get("masach");

                Double tienThue = Double.valueOf(tvTien.getText().toString());

                String ngaythueStr = edudngaythue.getText().toString();

                boolean trangThaiBool = chkTT.isChecked();
                int trangthai = trangThaiBool ? 1 : 0;

                phieumuon pm1 = new phieumuon(pm.getMapm(), matv, masach, tienThue, trangthai, ngaythueStr);

                dao.suaPM(pm1);
                list.clear();
                list.addAll(dao.getData());
                notifyDataSetChanged();

                dialog.dismiss();
            }
        });

        cacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spnUdTV.setSelection(0);
                spnUdSach.setSelection(0);

                tvTien.setText("");

                edudngaythue.setText("");

                trangthai.setText("");

                chkTT.setChecked(false);
            }
        });
    }
}
