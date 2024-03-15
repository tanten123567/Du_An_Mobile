package fpoly.cp17302_3.duanchinh.TaiKhoan.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import fpoly.cp17302_3.duanchinh.TaiKhoan.Fragment.Fragment_DangKy;
import fpoly.cp17302_3.duanchinh.TaiKhoan.Fragment.Fragment_DangNhap;

public class Adapter_DangNhap extends FragmentStateAdapter {
    public Adapter_DangNhap(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Fragment_DangNhap();
            case 1:
                return new Fragment_DangKy();
            default:
                return new Fragment_DangNhap();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
