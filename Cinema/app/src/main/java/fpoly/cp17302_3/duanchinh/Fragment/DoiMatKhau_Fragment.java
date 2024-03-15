package fpoly.cp17302_3.duanchinh.Fragment;

import android.content.Context;
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
import fpoly.cp17302_3.duanchinh.DataBase.DBHelper;
import fpoly.cp17302_3.duanchinh.R;

public class DoiMatKhau_Fragment extends Fragment {
    EditText edtOldPass, edtNewPass, edtReNewPass;
    Button btnCapNhat;
    DBHelper dbHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doimatkhau, container, false);

        edtOldPass = view.findViewById(R.id.edtOldPass);
        edtNewPass = view.findViewById(R.id.edtNewPass);
        edtReNewPass = view.findViewById(R.id.edtReNewPass);
        btnCapNhat = view.findViewById(R.id.btnCapNhat);
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = edtOldPass.getText().toString();
                String newPass = edtNewPass.getText().toString();
                String reNewPass = edtReNewPass.getText().toString();

                if (newPass.equals(reNewPass)){
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
                    String sdt = sharedPreferences.getString("sodienthoai", "");

                    ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
                    boolean check = thanhVienDAO.capNhatMatKhau(sdt, oldPass, newPass);
                    if (check == true){
                        Toast.makeText(getContext(), "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Nhập mật khẩu không trùng với nhau", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

}
