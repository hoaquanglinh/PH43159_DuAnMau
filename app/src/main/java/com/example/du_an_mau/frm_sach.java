package com.example.du_an_mau;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.AlteredCharSequence;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.du_an_mau.DAO.LoaiSachDao;
import com.example.du_an_mau.DAO.SachDao;
import com.example.du_an_mau.adapter.SachAdapter;
import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.loaisach;
import com.example.du_an_mau.model.sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class frm_sach extends Fragment {
    ListView listViewSach;
    FloatingActionButton fac;
    sach ls;
    SachDao dao;
    SachAdapter adapter;
    ArrayList<sach> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frm_sach, container, false);

        listViewSach = view.findViewById(R.id.listViewSach);
        fac = view.findViewById(R.id.facSach);

        dao = new SachDao(getActivity(), new DBHelper(getActivity()));
        list = dao.getData();

        adapter = new SachAdapter(getContext(), list, dao);
        listViewSach.setAdapter(adapter);

        fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSach();
            }
        });

        return view;
    }

    public void addSach(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_them_sach, null);

        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        Spinner spnLS = view.findViewById(R.id.spnLS);
        EditText edaddTen = view.findViewById(R.id.addtensach);
        EditText edaddGiaThue = view.findViewById(R.id.addgiathue);

        getDataLS(spnLS);

        view.findViewById(R.id.btnaddSach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> hsTV = (HashMap<String, Object>) spnLS.getSelectedItem();
                int maloai = (int) hsTV.get("maloai");

                String giaThueSach = edaddGiaThue.getText().toString();

                String tenSach = edaddTen.getText().toString();

                if (tenSach.isEmpty()||giaThueSach.isEmpty()){
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        Double giaThue = Double.parseDouble(giaThueSach);

                        if (giaThue < 0) {
                            Toast.makeText(getContext(), "Giá thuê phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                        } else {
                            sach sach = new sach(tenSach, giaThue, maloai);

                            dao.themSach(sach);
                            list.clear();
                            list.addAll(dao.getData());
                            adapter.notifyDataSetChanged();

                            dialog.dismiss();
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Giá thuê phải là số", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        view.findViewById(R.id.btnHuyAddSach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edaddTen.setText("");
                edaddGiaThue.setText("");
                spnLS.setSelection(0);
            }
        });
    }

    public  void getDataLS(Spinner spinner){
        LoaiSachDao loaiSachDao = new LoaiSachDao(getActivity(), new DBHelper(getActivity()));
        ArrayList<loaisach> listLS = loaiSachDao.getData();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();


        for (loaisach ls : listLS){
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("maloai", ls.getMaloai());
            hashMap.put("tenloai", ls.getTenloai());

            listHM.add(hashMap);
        }
        SimpleAdapter adapterLS = new SimpleAdapter(getContext(), listHM, android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1});

        spinner.setAdapter(adapterLS);
    }
}