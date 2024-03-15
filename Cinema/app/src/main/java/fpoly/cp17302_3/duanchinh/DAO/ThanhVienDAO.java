package fpoly.cp17302_3.duanchinh.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.cp17302_3.duanchinh.DataBase.DBHelper;
import fpoly.cp17302_3.duanchinh.Model.ThanhVien;

public class ThanhVienDAO {
    DBHelper dbHelper;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public ThanhVienDAO(Context context){
        dbHelper = new DBHelper(context);
        sharedPreferences = context.getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
    }

    public ArrayList<ThanhVien> getDSThanhVien(){
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THANHVIEN", null );
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new ThanhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean checkDangNhap(String SDT, String matkhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THANHVIEN WHERE sodienthoai = ? And matkhau = ?", new String[]{SDT, matkhau});
        if (cursor.getCount() !=0) {
            cursor.moveToFirst();

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("matv", String.valueOf(cursor.getInt(0)));
            editor.putString("sodienthoai", cursor.getString(1));
            editor.putString("email", cursor.getString(2));
            editor.putString("hoten", cursor.getString(3));
            editor.putString("matkhau", cursor.getString(4));
            editor.putString("loaitaikhoan", cursor.getString(5));
            editor.commit();

            return true;
        }else{
            return false;
        }
    }

    public boolean themThanhVien(String SDT, String Email, String HoTen, String MatKhau, String LoaiTaiKhoan){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sodienthoai", SDT);
        contentValues.put("email", Email);
        contentValues.put("tentv", HoTen);
        contentValues.put("matkhau", MatKhau);
        contentValues.put("loaitaikhoan", LoaiTaiKhoan);

        editor = sharedPreferences.edit();
        editor.putString("tentv", HoTen);
        editor.putString("sodienthoai", SDT);
        editor.putString("email",Email);
        editor.commit();

        long check = sqLiteDatabase.insert("THANHVIEN", null, contentValues);

        //Log.d("TAG>>:",""+check);
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean xoaThanhVien(int matv){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("THANHVIEN", "matv = ?", new String[]{String.valueOf(matv)});
        if(check == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean capNhatThanhVien(String Matv,String SDT, String Email, String HoTen, String MatKhau, String LoaiTaiKhoan){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("sodienthoai", SDT);
        contentValues.put("Email", Email);
        contentValues.put("tentv", HoTen);
        contentValues.put("matkhau", MatKhau);
        contentValues.put("loaitaikhoan", LoaiTaiKhoan);

        long check = sqLiteDatabase.update("THANHVIEN", contentValues, "matv = ?", new String[]{Matv});
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean capNhatMatKhau(String sdt, String oldPass, String newPass){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sodienthoai, matkhau FROM THANHVIEN WHERE sodienthoai = ? AND matkhau = ?", new String[]{sdt, oldPass});
        if (cursor.getCount() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau", newPass);
            long check = sqLiteDatabase.update("THANHVIEN", contentValues, "sodienthoai = ?", new String[]{sdt});
            if (check == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }
}
