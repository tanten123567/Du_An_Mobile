package fpoly.cp17302_3.duanchinh.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import fpoly.cp17302_3.duanchinh.Adapter.KhachHang_DangChieu_Adapter;
import fpoly.cp17302_3.duanchinh.DAO.PhimDAO;
import fpoly.cp17302_3.duanchinh.Model.Phim;
import fpoly.cp17302_3.duanchinh.R;

public class Fragment_DangChieu_KhachHang extends Fragment {
    RecyclerView recyclerDangChieuKH;
    PhimDAO phimDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.khachhang_fragment_dangchieu, container, false);

        recyclerDangChieuKH = view.findViewById(R.id.recyclerDangChieuKH);
        phimDAO = new PhimDAO(getContext());
        loadData();

        return view;
    }

    private void loadData() {
        ArrayList<Phim> list = phimDAO.getDSPhim();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerDangChieuKH.setLayoutManager(linearLayoutManager);


        KhachHang_DangChieu_Adapter adapter = new KhachHang_DangChieu_Adapter(list, getContext(), phimDAO, getDS());
        recyclerDangChieuKH.setAdapter(adapter);

    }
    private ArrayList<HashMap<String, Object>> getDS() {
        ArrayList<Phim> list = phimDAO.getDSPhim();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (Phim phim : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maphim", phim.getMaphim());
            hs.put("tenphim", phim.getTenphim());
            hs.put("hinhanh", phim.getHinhanh());
            hs.put("thoigianchieu", phim.getThoigianchieu());
            hs.put("ngaychieu", phim.getNgaychieu());
            listHM.add(hs);
        }
        return listHM;
    }

}
