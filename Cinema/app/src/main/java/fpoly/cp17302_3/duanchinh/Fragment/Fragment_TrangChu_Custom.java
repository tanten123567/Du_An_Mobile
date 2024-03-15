package fpoly.cp17302_3.duanchinh.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;


import fpoly.cp17302_3.duanchinh.Adapter.CusTom_TrangChu_Adapter;
import fpoly.cp17302_3.duanchinh.DAO.PhimDAO;
import fpoly.cp17302_3.duanchinh.DAO.TrangChu_DAO;
import fpoly.cp17302_3.duanchinh.Model.Phim;
import fpoly.cp17302_3.duanchinh.R;


public class Fragment_TrangChu_Custom extends Fragment {

    PhimDAO phimDAO;
    RecyclerView recyclerPhim;
    ArrayList<Phim> list;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangchu_custom, container, false);

        // Create a storage reference from our app
        phimDAO = new PhimDAO(getContext());
        recyclerPhim = view.findViewById(R.id.RecyclerView);

        loadData();

        return view;
    }
    private ArrayList<HashMap<String , Object>> getDS(){
        ArrayList<Phim> list = phimDAO.getDSPhim();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (Phim phim : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maphim", phim.getMaphim());
            hs.put("tenphim", phim.getTenphim());
            hs.put("theloai", phim.getMaloai());
            hs.put("thoigianchieu", phim.getThoigianchieu());
            hs.put("ngaychieu", phim.getNgaychieu());
            listHM.add(hs);
        }
        return listHM;
    }


    private void loadData() {
        ArrayList<Phim> list = phimDAO.getDSPhim();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerPhim.setLayoutManager(gridLayoutManager);
        recyclerPhim.setFocusable(false);
        recyclerPhim.setNestedScrollingEnabled(false);


        CusTom_TrangChu_Adapter adapter = new CusTom_TrangChu_Adapter(getContext(), list, getDS(), phimDAO);
        recyclerPhim.setAdapter(adapter);


    }
}