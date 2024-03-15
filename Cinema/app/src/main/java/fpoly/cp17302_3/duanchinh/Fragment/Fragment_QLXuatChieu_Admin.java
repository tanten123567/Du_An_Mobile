package fpoly.cp17302_3.duanchinh.Fragment;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.cp17302_3.duanchinh.Adapter.Admin_XuatChieu_Adapter;
import fpoly.cp17302_3.duanchinh.DAO.XuatChieuDAO;
import fpoly.cp17302_3.duanchinh.Model.ItemClickXuatChieu;
import fpoly.cp17302_3.duanchinh.Model.XuatChieu;
import fpoly.cp17302_3.duanchinh.R;

public class Fragment_QLXuatChieu_Admin extends Fragment {
    RecyclerView recyclerXuatChieu;
    XuatChieuDAO xuatChieuDAO;
    EditText edtNgayChieu, edtThoiGianChieu;
    int maxuatchieu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlxuatchieu, container, false);
        recyclerXuatChieu = view.findViewById(R.id.recyclerXuatChieu);
        edtNgayChieu = view.findViewById(R.id.edtNgayChieu);
        edtThoiGianChieu = view.findViewById(R.id.edtThoiGianChieu);
        Button btnThem = view.findViewById(R.id.btnThem);
        Button btnSua = view.findViewById(R.id.btnSua);

        xuatChieuDAO = new XuatChieuDAO(getContext());
        loadData();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ngaychieu = edtNgayChieu.getText().toString();
                String thoigianchieu = edtThoiGianChieu.getText().toString();
                if (ngaychieu.equals("")||thoigianchieu.equals("")){
                    Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if (xuatChieuDAO.themXuatChieu(ngaychieu, thoigianchieu)) {
                        loadData();
                        edtNgayChieu.setText("");
                        edtThoiGianChieu.setText("");
                    } else {
                        Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ngaychieu = edtNgayChieu.getText().toString();
                String thoigianchieu = edtThoiGianChieu.getText().toString();
                if (ngaychieu.equals("")||thoigianchieu.equals("")){
                    Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    XuatChieu xuatChieu = new XuatChieu(maxuatchieu, ngaychieu, thoigianchieu);
                    if (xuatChieuDAO.capNhatXuatChieu(xuatChieu)) {
                        loadData();
                        edtNgayChieu.setText("");
                        edtThoiGianChieu.setText("");
                        //Toast.makeText(getContext(), ""+ tenloai, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Thay đổi thông tin thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }
    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerXuatChieu.setLayoutManager(linearLayoutManager);
        ArrayList<XuatChieu> list = xuatChieuDAO.getDSXuatChieu();
        Admin_XuatChieu_Adapter adapter = new Admin_XuatChieu_Adapter(getContext(), list, new ItemClickXuatChieu(){
            @Override
            public void onClickXuatChieu(XuatChieu xuatChieu) {
                edtNgayChieu.setText(xuatChieu.getNgaychieu());
                edtThoiGianChieu.setText(xuatChieu.getThoigianchieu());
                maxuatchieu = xuatChieu.getMaxuatchieu();
            }
        });
        recyclerXuatChieu.setAdapter(adapter);
    }
}
