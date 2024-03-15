package fpoly.cp17302_3.duanchinh.TaiKhoan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import fpoly.cp17302_3.duanchinh.DataBase.DBHelper;
import fpoly.cp17302_3.duanchinh.MainActivity_Admin;
import fpoly.cp17302_3.duanchinh.MainActivity_KhachHang;
import fpoly.cp17302_3.duanchinh.R;
import fpoly.cp17302_3.duanchinh.TaiKhoan.Adapter.Adapter_DangNhap;

public class DangNhap_Activity extends AppCompatActivity {
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        DBHelper dbHelper = new DBHelper(this);

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        dbHelper.close();

        Adapter_DangNhap adapter = new Adapter_DangNhap(this);
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Đăng Nhập");
                        break;
                    case 1:
                        tab.setText("Đăng Ký");
                        break;
                }
            }
        }).attach();
    }

    public void Chuyen_Admin(){
        i = new Intent(DangNhap_Activity.this, MainActivity_Admin.class);
        startActivity(i);
    }
    public void Chuyen_KhachHang(){
        i = new Intent(DangNhap_Activity.this, MainActivity_KhachHang.class);
        startActivity(i);
        Log.d("TAG", "Chuyen_KhachHang: ");
    }
}