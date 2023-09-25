package com.example.du_an_mau.DAO;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.sach;
import com.example.du_an_mau.model.thanhvien;

import java.util.ArrayList;

public class ThanhVienDao {
    Context context;
    DBHelper dbHelper;

    public ThanhVienDao(Context context, DBHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }

    public ArrayList<thanhvien> getData(){
        ArrayList<thanhvien> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{
            Cursor cursor = db.rawQuery("select * from thanhvien", null);
            if(cursor.getCount()>0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    list.add(new thanhvien(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2)
                    ));
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){
            Log.i(TAG,"Lỗi", e);
        }
        return list;
    }

    public void themTV(thanhvien tv){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("matv", tv.getMatv());
        values.put("hotentv", tv.getHoTen());
        values.put("namsinh", tv.getNamSinh());

        long check = db.insert("thanhvien", null, values);

        if (check > 0) {
            Toast.makeText(context, "Thêm thành công",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Thêm thất bại",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void suaTV(thanhvien tv){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("hotentv", tv.getHoTen());
        values.put("namsinh", tv.getNamSinh());

        long check = db.update("thanhvien", values, "matv =?", new String[]{String.valueOf(tv.getMatv())});

        if (check > 0)
            Toast.makeText(context, "Sửa thành công",
                    Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "Sửa thất bại",
                Toast.LENGTH_SHORT).show();
    }

    public void xoatv(int matv){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        long check = db.delete("thanhvien", "matv = ?", new String[]{String.valueOf(matv)});

        if (check > 0){
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
