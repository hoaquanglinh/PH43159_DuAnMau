package com.example.du_an_mau.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau.database.DBHelper;

public class DoanhThuDao {
    Context context;
    DBHelper dbHelper;

    public DoanhThuDao(Context context, DBHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }

    public int getDoanhThu(String ngaybatdau, String ngayketthuc){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(tienthue) FROM  PHIEUMUON WHERE ngay BETWEEN ? AND ?", new String[]{ngaybatdau, ngayketthuc});
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }
}
