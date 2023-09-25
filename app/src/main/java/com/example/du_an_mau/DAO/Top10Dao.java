package com.example.du_an_mau.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.sach;

import java.util.ArrayList;

public class Top10Dao {
    Context context;
    DBHelper dbHelper;

    public Top10Dao(Context context, DBHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }

    public ArrayList<sach> getTop10(){
        ArrayList<sach> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT pm.masach, sc.tensach, COUNT(pm.masach) " +
                "FROM phieumuon pm, sach sc " +
                "WHERE pm.masach = sc.masach " +
                "GROUP BY pm.masach, sc.tensach " +
                "ORDER BY COUNT(pm.masach) " +
                "DESC LIMIT 10", null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new sach(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
