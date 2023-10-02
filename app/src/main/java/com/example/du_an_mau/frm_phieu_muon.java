package com.example.du_an_mau;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.du_an_mau.DAO.PhieuMuonDao;
import com.example.du_an_mau.DAO.SachDao;
import com.example.du_an_mau.DAO.ThanhVienDao;
import com.example.du_an_mau.adapter.PhieuMuonAdapter;
import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.loaisach;
import com.example.du_an_mau.model.phieumuon;
import com.example.du_an_mau.model.sach;
import com.example.du_an_mau.model.thanhvien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;

public class frm_phieu_muon extends Fragment {
    ListView listViewPM;
    FloatingActionButton fac;
    loaisach ls;
    PhieuMuonDao dao;
    PhieuMuonAdapter adapter;
    ArrayList<phieumuon> list;

    ArrayList<sach> listS;
    int mYear, mMonth, mDay;

    EditText edaddngaythue;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frm_phieu_muon, container, false);

        listViewPM = view.findViewById(R.id.listViewPM);
        fac = view.findViewById(R.id.facPhieuMuon);

        dao = new PhieuMuonDao(getActivity(), new DBHelper(getActivity()));
        list = dao.getData();

        adapter = new PhieuMuonAdapter(getContext(), list, dao);
        listViewPM.setAdapter(adapter);

        fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        return view;
    }

    public void add() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_them_phieu_muon, null);

        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        Spinner spnTV = view.findViewById(R.id.spnThanhVien);
        Spinner spnSach = view.findViewById(R.id.spnSach);
        TextView tvTien = view.findViewById(R.id.tvTien);
        edaddngaythue = view.findViewById(R.id.edaddngayThue);
        CheckBox checkBox = view.findViewById(R.id.chkSuaTrangThai);
        TextView trangThai = view.findViewById(R.id.addtrangthai);
        Button add = view.findViewById(R.id.btnaddPM);
        Button cancel = view.findViewById(R.id.btnHuyAddPM);


        SachDao sachDao = new SachDao(getActivity(), new DBHelper(getActivity()));
        listS = sachDao.getData();

        getDataThanhVien(spnTV);
        getDataSach(spnSach);

        spnSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sach selectedBook = listS.get(position);
                tvTien.setText(String.valueOf(selectedBook.getGiaThue()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lay ma tt
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_file", Context.MODE_PRIVATE);
                String matt = sharedPreferences.getString("matt", "");

                // lay ma thanh vien
                HashMap<String, Object> hsTV = (HashMap<String, Object>) spnTV.getSelectedItem();
                int matv = (int) hsTV.get("matv");

                // lay ma sach
                HashMap<String, Object> hsSach = (HashMap<String, Object>) spnSach.getSelectedItem();
                int masach = (int) hsSach.get("masach");

                Double tienThue = Double.valueOf(tvTien.getText().toString());

                // lấy ngày thuê
//                Date currentTime = Calendar.getInstance().getTime();
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//                edaddngaythue.setText(simpleDateFormat.format(currentTime));

                String ngaythueStr = edaddngaythue.getText().toString();

                // lấy trạng thái
                boolean trangThaiBool = checkBox.isChecked();
                int trangthai = trangThaiBool ? 1 : 0;

                // tạo đối tượng phiếu mượn mới
                phieumuon pm = new phieumuon();
                pm.setMatt(matt);
                pm.setMatv(matv);
                pm.setMasach(masach);
                pm.setTienthue(tienThue);
                pm.setNgaythue(ngaythueStr);
                pm.setTrangthai(trangthai);

                dao.themPM(pm);
                list.clear();
                list.addAll(dao.getData());
                adapter.notifyDataSetChanged();

                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spnTV.setSelection(0);
                spnSach.setSelection(0);
                tvTien.setText("");
                checkBox.setChecked(false);
                edaddngaythue.setText("");

            }
        });
    }

    private void getDataThanhVien(Spinner spnTV) {
        ThanhVienDao thanhVienDao = new ThanhVienDao(getActivity(), new DBHelper(getActivity()));
        ArrayList<thanhvien> listTV = thanhVienDao.getData();

        ArrayList<HashMap<String, Object>> listHashMap = new ArrayList<>();

        for (thanhvien tv : listTV) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("matv", tv.getMatv());
            hashMap.put("hotentv", tv.getHoTen());

            listHashMap.add(hashMap);
        }

        SimpleAdapter adapterTV = new SimpleAdapter(getContext(), listHashMap, android.R.layout.simple_list_item_1,
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

        SimpleAdapter adapterSach = new SimpleAdapter(getContext(), listHashMap, android.R.layout.simple_list_item_1,
                new String[]{"tensach"},
                new int[]{android.R.id.text1});

        spnSach.setAdapter(adapterSach);
    }
}