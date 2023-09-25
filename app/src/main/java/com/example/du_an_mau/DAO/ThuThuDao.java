package com.example.du_an_mau.DAO;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.du_an_mau.database.DBHelper;
import com.example.du_an_mau.model.thanhvien;
import com.example.du_an_mau.model.thuthu;

import java.util.ArrayList;

public class ThuThuDao {
    Context context;
    DBHelper dbHelper;

    public ThuThuDao(Context context, DBHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }

    public boolean checkDangNhap(String matt, String matkhau){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from thuthu where matt=? and matkhau=?", new String[]{matt, matkhau});

        if(cursor.getCount() != 0){
            return true;
        }else{
            return false;
        }
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

    public void themTT(thuthu tt){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("matt", tt.getMaTT());
        values.put("hotentt", tt.getHoTenTT());
        values.put("matkhau", tt.getMatKhau());

        long check = db.insert("thuthu", null, values);

        if (check > 0) {
            Toast.makeText(context, "Thêm thành công",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Thêm thất bại",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void suaTT(thuthu tt){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("hotentt", tt.getHoTenTT());
        values.put("matkhau", tt.getMatKhau());

        long check = db.update("thuthu", values, "matt =?", new String[]{String.valueOf(tt.getMaTT())});

        if (check > 0)
            Toast.makeText(context, "Sửa thành công",
                    Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "Sửa thất bại",
                Toast.LENGTH_SHORT).show();
    }

    public void xoaTT(int matt){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        long check = db.delete("thuthu", "matt = ?", new String[]{String.valueOf(matt)});

        if (check > 0){
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
