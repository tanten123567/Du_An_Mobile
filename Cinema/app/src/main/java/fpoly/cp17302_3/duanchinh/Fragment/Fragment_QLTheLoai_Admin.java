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

import fpoly.cp17302_3.duanchinh.Adapter.Admin_TheLoai_Adapter;
import fpoly.cp17302_3.duanchinh.DAO.TheLoaiDAO;
import fpoly.cp17302_3.duanchinh.Model.ItemClick;
import fpoly.cp17302_3.duanchinh.Model.TheLoai;
import fpoly.cp17302_3.duanchinh.R;

public class Fragment_QLTheLoai_Admin extends Fragment {
    RecyclerView recyclerTheLoai;
    TheLoaiDAO theLoaiDAO;
    EditText edtTheLoai;
    int maloai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qltheloai, container, false);
        recyclerTheLoai = view.findViewById(R.id.recyclerTheLoai);
        edtTheLoai = view.findViewById(R.id.edtTheLoai);
        Button btnThem = view.findViewById(R.id.btnThem);
        Button btnSua = view.findViewById(R.id.btnSua);

        theLoaiDAO = new TheLoaiDAO(getContext());
        loadData();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = edtTheLoai.getText().toString();

                if (tenloai.equals("")){
                    Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if (theLoaiDAO.themTheLoai(tenloai)) {
                        loadData();
                        edtTheLoai.setText("");
                    } else {
                        Toast.makeText(getContext(), "Thêm thể loại thất bại thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = edtTheLoai.getText().toString();

                if (tenloai.equals("")){
                    Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    TheLoai theLoai = new TheLoai(maloai,tenloai);
                    if (theLoaiDAO.capNhatTheLoai(theLoai)) {
                        loadData();
                        edtTheLoai.setText("");
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
        recyclerTheLoai.setLayoutManager(linearLayoutManager);
        ArrayList<TheLoai> list = theLoaiDAO.getDSTheLoai();
        Admin_TheLoai_Adapter adapter = new Admin_TheLoai_Adapter(getContext(), list, new ItemClick(){
            @Override
            public void onClickTheLoai(TheLoai theLoai) {
                edtTheLoai.setText(theLoai.getTenloai());
                maloai = theLoai.getMaloai();
            }
        });
        recyclerTheLoai.setAdapter(adapter);
    }
}
