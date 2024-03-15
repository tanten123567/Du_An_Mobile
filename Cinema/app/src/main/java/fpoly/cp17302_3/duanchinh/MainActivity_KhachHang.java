package fpoly.cp17302_3.duanchinh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import fpoly.cp17302_3.duanchinh.Fragment.Fragment_DangChieu_KhachHang;
import fpoly.cp17302_3.duanchinh.Fragment.Fragment_DatVe;
import fpoly.cp17302_3.duanchinh.Fragment.Fragment_GioHang;
import fpoly.cp17302_3.duanchinh.Fragment.Fragment_TaiKhoan;
import fpoly.cp17302_3.duanchinh.Fragment.Fragment_TrangChu_Custom;
import fpoly.cp17302_3.duanchinh.databinding.ActivityMainKhachhangBinding;

public class MainActivity_KhachHang extends AppCompatActivity {
    ActivityMainKhachhangBinding binding;
    Bundle bundle;
    String tenphim, theloai, ngay, gio,hinhanh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_khachhang);
        LocalBroadcastManager.getInstance(MainActivity_KhachHang.this).registerReceiver(mMessageReceiver,
                new IntentFilter("Get Total"));
        binding =ActivityMainKhachhangBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new Fragment_TrangChu_Custom());
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.mTrangChu:
                    replaceFragment(new Fragment_TrangChu_Custom());
                    break;
                case R.id.mDatVe:
                    replaceFragment(new Fragment_DangChieu_KhachHang());
                    break;
                case R.id.mGioHang:
                    replaceFragment(new Fragment_GioHang());
                    break;
                case R.id.mTaiKhoan:
                    replaceFragment(new Fragment_TaiKhoan());
                    break;
            }


            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    public void ChuyenFG(String tenphim, String theloai, String gio, String ngay , String hinhanh) {
        bundle = new Bundle();
        bundle.putString("hinhanh" , hinhanh);
        bundle.putString("ten", tenphim);
        bundle.putString("loai", theloai);
        bundle.putString("gio", gio);
        bundle.putString("ngay", ngay);
        Fragment_DatVe datVeFragment = new Fragment_DatVe();
        datVeFragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.frameLayout, datVeFragment)
                .commit();
        Log.d(">>>TAG: ","Chuyen Fragment");
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            tenphim=intent.getStringExtra("ten");
            theloai=intent.getStringExtra("loai");
            gio = intent.getStringExtra("gio");
            ngay = intent.getStringExtra("ngay");
            hinhanh = intent.getStringExtra("hinhanh");


            Log.d(">>>TAGDATAMAIN",""+tenphim+"\n"+theloai+ngay+"\n"+gio);
            ChuyenFG(tenphim,theloai, gio, ngay , hinhanh);
        }
    };
}