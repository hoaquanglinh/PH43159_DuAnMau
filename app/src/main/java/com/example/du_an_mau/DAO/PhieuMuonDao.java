package com.example.du_an_mau.DAO;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.phieumuon;
import com.example.du_an_mau.model.sach;

import java.util.ArrayList;

public class PhieuMuonDao {
    Context context;
    DBHelper dbHelper;

    public PhieuMuonDao(Context context, DBHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }

    public ArrayList<phieumuon> getData() {
        ArrayList<phieumuon> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery("select pm.mapm, pm.matt, pm.matv, tv.hotentv, pm.masach, sc.tensach, pm.tienthue, pm.trangthai, pm.ngay from phieumuon pm, thanhvien tv, sach sc where pm.matv = tv.matv and pm.masach = sc.masach", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    list.add(new phieumuon(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getString(3),
                            cursor.getInt(4),
                            cursor.getString(5),
                            cursor.getDouble(6),
                            cursor.getInt(7),
                            cursor.getString(8)
                    ));
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);
        }
        return list;
    }

    public void themPM(phieumuon pm){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("mapm", pm.getMapm());
        values.put("matt", pm.getMatt());
        values.put("matv", pm.getMatv());
        values.put("masach", pm.getMasach());
        values.put("tienthue", pm.getTienthue());
        values.put("trangthai", pm.getTrangthai());
        values.put("ngay", pm.getNgaythue());

        long check = db.insert("phieumuon", null, values);

        if (check > 0) {
            Toast.makeText(context, "Thêm thành công",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Thêm thất bại",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void suaPM(phieumuon pm){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("matt", pm.getMatt());
        values.put("matv", pm.getMatv());
        values.put("masach", pm.getMasach());
        values.put("tienthue", pm.getTienthue());
        values.put("trangthai", pm.getTrangthai());
        values.put("ngay", pm.getNgaythue());

        long check = db.update("phieumuon", values, "mapm =?", new String[]{String.valueOf(pm.getMapm())});

        if (check > 0) {
            Toast.makeText(context, "Thêm thành công",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Thêm thất bại",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void xoaPM(int mapm){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        long check = db.delete("phieumuon", "mapm = ?", new String[]{String.valueOf(mapm)});

        if (check > 0){
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
