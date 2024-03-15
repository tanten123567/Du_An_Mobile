package fpoly.cp17302_3.duanchinh.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fpoly.cp17302_3.duanchinh.DAO.ThanhVienDAO;
import fpoly.cp17302_3.duanchinh.R;
import fpoly.cp17302_3.duanchinh.TaiKhoan.DangNhap_Activity;

public class Fragment_TaiKhoan extends Fragment {
    Context context;
    SharedPreferences sharedPreferences;
    TextView txtTen, txtSoDienThoai, txtEmail, txtDoiMK, txtDangXuat,txtLienhe,mtxtidTK;
    View view;

    EditText edtOldPass, edtNewPass, edtReNewPass;
    EditText edthoten , edtemail , edtnoidung;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_taikhoan, container, false);
        sharedPreferences = getActivity().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        txtTen = view.findViewById(R.id.txtTen);
        txtSoDienThoai = view.findViewById(R.id.txtSoDienThoai);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtDoiMK = view.findViewById(R.id.txtDoiMK);
        txtDangXuat = view.findViewById(R.id.txtDangXuat);
        txtLienhe = view.findViewById(R.id.txtLienHe);
        mtxtidTK =view.findViewById(R.id.txtid);

        String ten = sharedPreferences.getString("tentv", "");
        String sdt = sharedPreferences.getString("sodienthoai", "");
        String email = sharedPreferences.getString("email", "");
        String id = sharedPreferences.getString("matv", "");


        txtTen.setText(ten);
        txtSoDienThoai.setText(sdt);
        txtEmail.setText(email);
        mtxtidTK.setText(id);
        txtDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaLog();
            }
        });
        txtLienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDiaLogLienHe();
            }
        });

        txtDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DangNhap_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        return view;
    }

    private void showDiaLogLienHe(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_lienhe, null);
        edtemail = view.findViewById(R.id.edtemail);
        edtnoidung = view.findViewById(R.id.edtnoidung);
        edthoten = view.findViewById(R.id.edthoten);

        builder.setView(view);

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("Gửi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String hoten = edthoten.getText().toString();
                String email = edtemail.getText().toString();
                String noidung = edtnoidung.getText().toString();
                if (hoten.equals("")||email.equals("")||noidung.equals("")){
                    Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Cảm ơn bạn đã phản hồi cho chúng tôi", Toast.LENGTH_SHORT).show();
                    edthoten.setText("");
                    edtemail.setText("");
                    edtnoidung.setText("");
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void showDiaLog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_doimatkhau, null);
        edtOldPass = view.findViewById(R.id.edtOldPass);
        edtNewPass = view.findViewById(R.id.edtNewPass);
        edtReNewPass = view.findViewById(R.id.edtReNewPass);
        builder.setView(view);
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setNegativeButton("Cập Nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
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
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



}
