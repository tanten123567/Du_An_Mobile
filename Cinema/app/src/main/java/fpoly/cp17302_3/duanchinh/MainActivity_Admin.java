package fpoly.cp17302_3.duanchinh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

import fpoly.cp17302_3.duanchinh.Fragment.DoiMatKhau_Fragment;
import fpoly.cp17302_3.duanchinh.Fragment.Fragment_DangChieu_Admin;
import fpoly.cp17302_3.duanchinh.Fragment.Fragment_DangChieu_KhachHang;
import fpoly.cp17302_3.duanchinh.Fragment.Fragment_DatVe;
import fpoly.cp17302_3.duanchinh.Fragment.Fragment_GioHang;
import fpoly.cp17302_3.duanchinh.Fragment.Fragment_QLTaiKhoan_Admin;
import fpoly.cp17302_3.duanchinh.Fragment.Fragment_QLTheLoai_Admin;
import fpoly.cp17302_3.duanchinh.Fragment.Fragment_QLXuatChieu_Admin;
import fpoly.cp17302_3.duanchinh.Fragment.Fragment_TaiKhoan;
import fpoly.cp17302_3.duanchinh.Fragment.Fragment_TrangChu_Admin;
import fpoly.cp17302_3.duanchinh.TaiKhoan.DangNhap_Activity;

public class MainActivity_Admin extends AppCompatActivity {
    DrawerLayout drawerLayout;
    String tenphim, theloai, ngay, gio;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        LocalBroadcastManager.getInstance(MainActivity_Admin.this).registerReceiver(mMessageReceiver,
                new IntentFilter("Get Total")); //?get total
        Toolbar toolbar = findViewById(R.id.toolbar);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.framelayout, new Fragment_TrangChu_Admin())
                .commit();
        FrameLayout frameLayout = findViewById(R.id.framelayout);
        NavigationView navigationView = findViewById(R.id.navigation);
        drawerLayout = findViewById(R.id.drawerlayout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // thiết lập hiển thị icon trên action bar
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        //navigation item click
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.mHome:
                        fragment = new Fragment_TrangChu_Admin();
                        break;
                    case R.id.mDangChieu:
                        fragment = new Fragment_DangChieu_Admin();
                        break;
                    case R.id.mQLTaiKhoan:
                        fragment = new Fragment_QLTaiKhoan_Admin();
                        break;

                    case R.id.mTheLoai:
                        fragment = new Fragment_QLTheLoai_Admin();
                        break;
                    case R.id.mXuatChieu:
                        fragment = new Fragment_QLXuatChieu_Admin();
                        break;
                    case  R.id.mDoiMK:
                        fragment = new DoiMatKhau_Fragment();
                        break;
                    case R.id.mThoat:
                        Intent intent = new Intent(MainActivity_Admin.this, DangNhap_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    default:
                        fragment = new Fragment_TrangChu_Admin();
                        break;
                }

                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.framelayout, fragment)
                            .commit();
                    toolbar.setTitle(item.getTitle());
                }
                drawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // khoi dong 
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    public void ChuyenFG(String tenphim, String theloai, String gio, String ngay) {
        bundle = new Bundle();
        bundle.putString("ten", tenphim);
        bundle.putString("loai", theloai);
        bundle.putString("gio", gio);
        bundle.putString("ngay", ngay);
        Fragment_DatVe datVeFragment = new Fragment_DatVe();
        datVeFragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.framelayout, datVeFragment)
                .commit();
        Log.d(">>>TAG: ","Chuyen Fragment");
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            tenphim=intent.getStringExtra("ten");
            theloai=intent.getStringExtra("loai");
            gio = intent.getStringExtra("gio");
            ngay = intent.getStringExtra("ngay");


            Log.d(">>>TAGDATAMAIN",""+tenphim+"\n"+theloai+ngay+"\n"+gio);
            ChuyenFG(tenphim,theloai, gio, ngay);

        }
    };
    // chon nut back chi thoát navigation
    public void onBackPressed()
    {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }



}