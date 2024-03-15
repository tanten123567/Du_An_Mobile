package fpoly.cp17302_3.duanchinh.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.cp17302_3.duanchinh.DataBase.DBHelper;
import fpoly.cp17302_3.duanchinh.Model.Phim;

public class PhimDAO {
    DBHelper dbHelper;
    SharedPreferences sharedPreferences;
    public PhimDAO(Context context){
        dbHelper = new DBHelper(context);
        sharedPreferences = context.getSharedPreferences("DATVE", Context.MODE_PRIVATE);
    }

    public ArrayList<Phim> getDSPhim(){
        ArrayList<Phim> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT p.maphim ,p.tenphim, p.hinhanh, tl.tenloai, xc.thoigianchieu, xc.ngaychieu FROM PHIM p , THELOAI tl, XUATCHIEU xc WHERE p.maloai = tl.maloai AND xc.maxuatchieu = p.maxuatchieu", null);
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            do {
                list.add(new Phim(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3), cursor.getString(4), cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themPhim(String tenphim, String hinhanh/*,String tenloai*/, int maloai, int maxuatchieu){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT p.tenphim, p.hinhanh, tl.maloai, xc.maxuatchieu FROM PHIM p , THELOAI tl, XUATCHIEU xc WHERE p.maloai = tl.maloai AND xc.maxuatchieu = p.maxuatchieu", null);


        ContentValues contentValues = new ContentValues();
        contentValues.put("tenphim", tenphim);
        contentValues.put("hinhanh", hinhanh);
        //contentValues.put("tenloai", tenloai);
        contentValues.put("maloai", maloai );
        contentValues.put("maxuatchieu", maxuatchieu);
        long check = sqLiteDatabase.insert("PHIM", null, contentValues);
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }


    public boolean xoaPhim(int maphim){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        long check = sqLiteDatabase.delete("PHIM", "maphim = ?", new String[]{String.valueOf(maphim)});
        if (check == -1){
            return false;
            //0: Xóa thất bại
        }else {
            return true;
            //1: Xóa thành công
        }
    }

    public boolean capNhatPhim(Phim phim){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenphim", phim.getTenphim());
        contentValues.put("hinhanh", phim.getHinhanh());
        long check = sqLiteDatabase.update("PHIM", contentValues, "maphim = ?", new String[]{String.valueOf(phim.getMaphim())});
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean CheckPhim(int maphim){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PHIM WHERE MAPHIM=?",new String[]{String.valueOf(maphim)});
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("maphim",cursor.getInt(0));
            editor.putString("tenphim",cursor.getString(1));
            editor.putString("hinhanh",cursor.getString(2));
            editor.putString("tenloai",cursor.getString(3));
            editor.putString("thoigianchieu",cursor.getString(4));
            editor.putString("ngaychieu",cursor.getString(5));
            editor.putInt("maxuatchieu",cursor.getInt(6));
            editor.putInt("maloai",cursor.getInt(7));
            editor.commit();
            return true;
        }
        else {
            return false;
        }
    }
}
