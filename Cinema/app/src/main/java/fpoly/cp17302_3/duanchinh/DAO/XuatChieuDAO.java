package fpoly.cp17302_3.duanchinh.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.cp17302_3.duanchinh.DataBase.DBHelper;
import fpoly.cp17302_3.duanchinh.Model.XuatChieu;



public class XuatChieuDAO {
    DBHelper dbHelper;
    public XuatChieuDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<XuatChieu> getDSXuatChieu(){
        ArrayList<XuatChieu> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM XUATCHIEU", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new XuatChieu(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themXuatChieu(String ngaychieu, String thoigianchieu){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ngaychieu", ngaychieu);
        contentValues.put("thoigianchieu", thoigianchieu);
        long check = sqLiteDatabase.insert("XUATCHIEU", null, contentValues);
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean xoaXuatChieu(int maxuatchieu){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("XUATCHIEU", "maxuatchieu = ?", new String[]{String.valueOf(maxuatchieu)});
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean capNhatXuatChieu(XuatChieu xuatChieu){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ngaychieu", xuatChieu.getNgaychieu());
        contentValues.put("thoigianchieu", xuatChieu.getThoigianchieu());

        long check = sqLiteDatabase.update("XUATCHIEU", contentValues, "maxuatchieu = ?", new String[]{String.valueOf(xuatChieu.getMaxuatchieu())});
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }
}
