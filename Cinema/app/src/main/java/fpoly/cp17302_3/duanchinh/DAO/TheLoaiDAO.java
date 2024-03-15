package fpoly.cp17302_3.duanchinh.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.cp17302_3.duanchinh.DataBase.DBHelper;
import fpoly.cp17302_3.duanchinh.Model.TheLoai;

public class TheLoaiDAO {
    DBHelper dbHelper;
    public TheLoaiDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<TheLoai> getDSTheLoai(){
        ArrayList<TheLoai> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THELOAI", null);

        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new TheLoai(cursor.getInt(0), cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themTheLoai(String tenloai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai",tenloai );
        long check = sqLiteDatabase.insert("THELOAI", null, contentValues);
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean xoaTheLoai(int maloai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THELOAI WHERE maloai =?", new String[]{String.valueOf(maloai)});

            long check = sqLiteDatabase.delete("THELOAI", "maloai = ?", new String[]{String.valueOf(maloai)});
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean capNhatTheLoai(TheLoai theLoai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai", theLoai.getTenloai());
        long check = sqLiteDatabase.update("THELOAI",contentValues, "maloai = ?", new String[]{String.valueOf(theLoai.getMaloai())});
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }
}
