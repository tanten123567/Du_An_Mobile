package fpoly.cp17302_3.duanchinh.TaiKhoan.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fpoly.cp17302_3.duanchinh.DAO.ThanhVienDAO;
import fpoly.cp17302_3.duanchinh.R;
import fpoly.cp17302_3.duanchinh.TaiKhoan.DangNhap_Activity;

public class Fragment_DangNhap extends Fragment {
    ThanhVienDAO thanhVienDAO;
    EditText edtSdt, edtPass;
    Button btnLogin;
    String loaitaikhoan;
    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangnhap, container, false);


        edtSdt = view.findViewById(R.id.edtSDT);
        edtPass = view.findViewById(R.id.edtPass);
        btnLogin = view.findViewById(R.id.btnLogin);

        thanhVienDAO = new ThanhVienDAO(getContext());
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdt = edtSdt.getText().toString();
                String pass = edtPass.getText().toString();

                //Toast.makeText(getContext(), ""+ sdt + pass, Toast.LENGTH_SHORT).show();
                sharedPreferences = getActivity().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);

                if (sdt.length() == 0 || pass.length() == 0){
                    Toast.makeText(getContext(), "Vui Lòng Không Để Rỗng", Toast.LENGTH_SHORT).show();
                }else {
                    if (thanhVienDAO.checkDangNhap(sdt, pass)){
                        loaitaikhoan = sharedPreferences.getString("loaitaikhoan", "");
                        Toast.makeText(getContext(), ""+loaitaikhoan, Toast.LENGTH_SHORT).show();
                        if (loaitaikhoan.equalsIgnoreCase("admin")){
                            DangNhap_Activity activity = ((DangNhap_Activity)getActivity());
                            activity.Chuyen_Admin();
                        }else {
                            DangNhap_Activity activity = ((DangNhap_Activity)getActivity());
                            activity.Chuyen_KhachHang();
                        }
                    }else {
                        Toast.makeText(getContext(), "Dang Nhap Khong Thanh Cong", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        return view;
    }
}
