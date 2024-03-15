package fpoly.cp17302_3.duanchinh.DAO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.cp17302_3.duanchinh.DataBase.DBHelper;
import fpoly.cp17302_3.duanchinh.Model.Phim;


public class TrangChu_DAO {
    DBHelper database;
    public TrangChu_DAO(Context context) {
        database = new DBHelper(context);
    }

    public ArrayList<Phim> getDSPhim() {
        ArrayList<Phim> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PHIM", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new Phim(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        return list;
    }





    public boolean xoaPhim(int maphim){
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();

        long check = sqLiteDatabase.delete("PHIM", "maphim = ?", new String[]{String.valueOf(maphim)});
        if (check == -1){
            return false;
            //0: Xóa thất bại
        }else {
            return true;
            //1: Xóa thành công
        }
    }


    public boolean themPhim(String tenphim, String hinhanh/*,String tenloai*/, int maloai, int maxuatchieu){
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
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

}

