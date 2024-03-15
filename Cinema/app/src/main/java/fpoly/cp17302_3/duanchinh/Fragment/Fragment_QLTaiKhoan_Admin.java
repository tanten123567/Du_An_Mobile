package fpoly.cp17302_3.duanchinh.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.cp17302_3.duanchinh.Adapter.ThanhVienAdapter;
import fpoly.cp17302_3.duanchinh.DAO.ThanhVienDAO;
import fpoly.cp17302_3.duanchinh.Model.ThanhVien;
import fpoly.cp17302_3.duanchinh.R;


public class Fragment_QLTaiKhoan_Admin extends Fragment {
    ThanhVienDAO thanhVienDAO;
    RecyclerView recyclerViewTaiKhoan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qltaikhoan, container, false);
        recyclerViewTaiKhoan = view.findViewById(R.id.recyclerThanhVien);
        thanhVienDAO = new ThanhVienDAO(getContext());


        loadData();
        return view;
    }

    private void loadData() {
        ArrayList<ThanhVien> list = thanhVienDAO.getDSThanhVien();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewTaiKhoan.setLayoutManager(linearLayoutManager);
        ThanhVienAdapter adapter = new ThanhVienAdapter(getContext(), list, thanhVienDAO);
        recyclerViewTaiKhoan.setAdapter(adapter);
    }

}
