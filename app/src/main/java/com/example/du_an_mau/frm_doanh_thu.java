package com.example.du_an_mau;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.du_an_mau.DAO.DoanhThuDao;
import com.example.du_an_mau.DAO.PhieuMuonDao;
import com.example.du_an_mau.database.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class frm_doanh_thu extends Fragment {
    Button btnTuNgay, btnDenNgay, btnDoanhThu;
    EditText edTuNgay, edDenNgay;
    TextView tvDoanhThu;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    int mYear, mMonth, mDay;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frm_doanh_thu, container, false);
        edTuNgay = view.findViewById(R.id.ed_tuNgay);
        edDenNgay = view.findViewById(R.id.ed_denNgay);
        tvDoanhThu = view.findViewById(R.id.tv_DoanhThu);
        btnTuNgay = view.findViewById(R.id.btn_tuNgay);
        btnDenNgay = view.findViewById(R.id.btn_denNgay);
        btnDoanhThu = view.findViewById(R.id.btn_ThongKe);

        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getContext(),
                        0, mDateTuNgay, mYear, mMonth, mDay);
                d.show();
            }
        });

        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getContext(),
                        0, mDateDenNgay, mYear, mMonth, mDay);
                d.show();
            }
        });

        String tungay = edTuNgay.getText().toString();
        String denngay = edDenNgay.getText().toString();

        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = edTuNgay.getText().toString();
                String denNgay = edDenNgay.getText().toString();
                DoanhThuDao dao = new DoanhThuDao(getContext(), new DBHelper(getContext()));
                tvDoanhThu.setText("Doanh thu: "+dao.getDoanhThu(tungay, denngay));
            }
        });

        return view;
    }

    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar calendar = new GregorianCalendar(mYear, mMonth, mDay);
            edTuNgay.setText(sdf.format(calendar.getTime()));
        }
    };

    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar calendar = new GregorianCalendar(mYear, mMonth, mDay);
            edDenNgay.setText(sdf.format(calendar.getTime()));
        }
    };
}