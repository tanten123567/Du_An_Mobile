package fpoly.cp17302_3.duanchinh.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "DATVE", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbTheLoai = "create table THELOAI (maloai integer primary key autoincrement, tenloai text)";
        db.execSQL(dbTheLoai);

        String dbXuatChieu = "create table XUATCHIEU(maxuatchieu integer primary key autoincrement, ngaychieu text, thoigianchieu text)";
        db.execSQL(dbXuatChieu);

        String dbPhim = "create table PHIM(maphim integer primary key autoincrement, tenphim text, hinhanh text, maloai integer references THELOAI(maloai), maxuatchieu integer references XUATCHIEU(maxuatchieu))";
        db.execSQL(dbPhim);

        String dbThanhVien = "create table THANHVIEN(matv integer primary key autoincrement,sodienthoai text, email text, tentv text, matkhau text, loaitaikhoan text)";
        db.execSQL(dbThanhVien);

        String dbVe = "create table VE(mave integer primary key autoincrement, soghe text,soluong text, giave text, thoigiandat text, ngaychieu text, maxuatchieu integer references XUATCHIEU(maxuatchieu), maphim integer references PHIM(maphim) ,matv integer references THANHVIEN(matv))";
        db.execSQL(dbVe);


        db.execSQL("INSERT INTO THELOAI VALUES (1,'Kinh dị') , (2 , 'Tinh cam')");
        db.execSQL("INSERT INTO XUATCHIEU VALUES (1,'02/09/2023', '9h30') , (2 , '01/08/2023' , '10h')");
        db.execSQL("INSERT INTO THANHVIEN VALUES (1,'1', 'abc@gmail.com', 'Vy', '1', 'admin'), (2,'2', 'bcd@gmail.com', 'Tan', '2', 'khach')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS THELOAI");
            db.execSQL("DROP TABLE IF EXISTS PHIM");
            db.execSQL("DROP TABLE IF EXISTS XUATCHIEU");
            db.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            db.execSQL("DROP TABLE IF EXISTS VE");
            onCreate(db);
        }
    }
}
