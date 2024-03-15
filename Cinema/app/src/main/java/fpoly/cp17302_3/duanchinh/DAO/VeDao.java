package fpoly.cp17302_3.duanchinh.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.cp17302_3.duanchinh.DataBase.DBHelper;
import fpoly.cp17302_3.duanchinh.Model.Ve;


public class VeDao {
    DBHelper dbHelper;
    SharedPreferences sharedPreferences, sharedPreferences1;
    ArrayList<Ve> list;

ThanhVienDAO thanhVienDAO ;
    public VeDao(Context context) {
        dbHelper = new DBHelper(context);
        sharedPreferences = context.getSharedPreferences("GIOHANG", Context.MODE_PRIVATE);
        sharedPreferences1 = context.getSharedPreferences("THONGTIN",Context.MODE_PRIVATE);
    }

    public ArrayList<Ve> getDSVe(){
        list= new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM VE", null);
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            do {
                list.add(new Ve(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4) , cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return list;
    }

//    public boolean themve(String Ghe, String SoLuong, String Gia, String Gio, String Ngay,String matv){
//        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//            contentValues.put("soghe", Ghe);
//            contentValues.put("soluong", SoLuong);
//            contentValues.put("giave", Gia);
//            contentValues.put("thoigiandat", Gio);
//            contentValues.put("ngaychieu", Ngay);
//            contentValues.put("matv", matv);
//            long check = sqLiteDatabase.insert("VE", null, contentValues);
//            if(check==-1)
//                return false;
//            return true;
//
//    }
    public boolean themve(String Ghe, String SoLuong, String Gia, String Gio, String Ngay){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("soghe", Ghe);
        contentValues.put("soluong", SoLuong);
        contentValues.put("giave", Gia);
        contentValues.put("thoigiandat", Gio);
        contentValues.put("ngaychieu", Ngay);
        long check = sqLiteDatabase.insert("VE", null, contentValues);
        if(check==-1)
            return false;
        return true;

    }
    public boolean xoave(int mave){

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

            long check = sqLiteDatabase.delete("VE", "mave = ? ", new String[]{String.valueOf(mave)});
            if (check == -1){
                return false;
            }else {
                return true;
            }
    }
}
