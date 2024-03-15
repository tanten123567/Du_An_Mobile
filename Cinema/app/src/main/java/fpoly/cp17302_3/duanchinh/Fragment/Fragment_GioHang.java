package fpoly.cp17302_3.duanchinh.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
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

import fpoly.cp17302_3.duanchinh.Adapter.KhachHang_GioHang_Adapter;
import fpoly.cp17302_3.duanchinh.DAO.ThanhVienDAO;
import fpoly.cp17302_3.duanchinh.DAO.VeDao;
import fpoly.cp17302_3.duanchinh.Model.ThanhVien;
import fpoly.cp17302_3.duanchinh.Model.Ve;
import fpoly.cp17302_3.duanchinh.R;

public class Fragment_GioHang extends Fragment {
    VeDao veDao;
    RecyclerView recyclerGioHang;
    ThanhVienDAO thanhVienDAO;
    SharedPreferences sharedPreferences;
    String loaitaikhoan;
  //  ArrayList<Ve> list = new ArrayList<Ve>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giohang, container, false);
        thanhVienDAO = new ThanhVienDAO(getContext());
        recyclerGioHang = view.findViewById(R.id.recyclerGioHang);
        veDao = new VeDao(getContext());
       // list = checkKH();
        loadData();

        sharedPreferences = getActivity().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        loaitaikhoan = sharedPreferences.getString("loaitaikhoan", "");

        return view;
    }


    private void loadData() {
       //ArrayList<Ve> list = checkKH();
         ArrayList<Ve> list = veDao.getDSVe();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerGioHang.setLayoutManager(linearLayoutManager);
        KhachHang_GioHang_Adapter adapter = new KhachHang_GioHang_Adapter(getContext(), list, veDao);
        recyclerGioHang.setAdapter(adapter);
    }
    public ArrayList<Ve> checkKH()
    {
        ArrayList<Ve> vedao =veDao.getDSVe();
        ArrayList<Ve> listVe = new ArrayList<Ve>();

            for (Ve ve: vedao) {
                if(loaitaikhoan.contains(ve.getMatv()))
                {
                    listVe.add(ve);
                }
            }


        return listVe;
    }
}
