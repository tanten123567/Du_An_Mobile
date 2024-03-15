package fpoly.cp17302_3.duanchinh.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import fpoly.cp17302_3.duanchinh.DAO.ThanhVienDAO;
import fpoly.cp17302_3.duanchinh.DAO.VeDao;
import fpoly.cp17302_3.duanchinh.Model.Ve;
import fpoly.cp17302_3.duanchinh.R;

public class Fragment_DatVe extends Fragment {
    VeDao veDao;
    View view;
    ArrayList<String> list;
    ArrayList<Ve> listVe;
    int sl = 0;
    String temp = "";
    String tenphim, theloai, ngay, gio,hinhanh;
    ThanhVienDAO thanhVienDAO;
    CheckBox A1,A2,A3,A4,A5;
    CheckBox B1,B2,B3,B4,B5;
    CheckBox C1,C2,C3,C4,C5;
    CheckBox D1,D2,D3,D4,D5;
    CheckBox E1,E2,E3,E4,E5;
    ImageView ivIMG;

    SharedPreferences sharedPreferences;
    TextView txtGhe,txtTongGia, txtGio, txtNgay, txtTongSoLuong, txtgia, txtsl, tv, txtTenPhim, txtTheLoai, txtTime;
    Button btn_DatVe;
    String id;
    int idmtv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.khachhang_fragment_datve, container, false);
        sharedPreferences = getActivity().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        anXa();
        thanhVienDAO = new ThanhVienDAO(getActivity());
        tenphim=getArguments().getString("ten");
        theloai=getArguments().getString("loai");
        gio = getArguments().getString("gio");
        ngay = getArguments().getString("ngay");
        hinhanh = getArguments().getString("hinhanh");
        id = getArguments().getString("matv");

        Glide.with(getContext()).load(hinhanh).into(ivIMG);
        txtTenPhim.setText(tenphim);
        txtTheLoai.setText(theloai);
        txtTime.setText(gio);
        tv.setText(ngay);
        Log.d("TAG", "onCreateView: " + hinhanh);

        ghechecked();

        Listener();

        btn_DatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veDao = new VeDao(getContext());
                StringBuilder stringBuilder = new StringBuilder();
                for (String a : list){
                    stringBuilder.append(a).append("\t");
                  btn_DatVe.setEnabled(false);
                }
                //Glide.with(context).load(list.get(position).getHinhanh()).into(holder.image);

                String Gio = txtTime.getText().toString();
                String Ngay = tv.getText().toString();
                String Ghe = stringBuilder.toString();
                String tenP = txtTenPhim.getText().toString();
                String Gia = txtgia.getText().toString();
                String SoLuong = txtsl.getText().toString();
                boolean check = false;
                if(Ghe.equalsIgnoreCase("") || Gio.equalsIgnoreCase("")){
                    Toast.makeText(getContext(), "Chưa đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                }else{
                    check = veDao.themve(Ghe, SoLuong, Gia, Gio, Ngay);
                }

                if (check == true) {
                    btn_DatVe.setEnabled(false);
                    Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }





    public void tang(){
        ++sl;
        checkgia(sl);
        txtsl.setText(sl + "");
    }
    public void giam(){
        --sl;
        checkgia(sl);
        txtsl.setText(sl+"");
    }
    public void checkgia(int slve){
        txtgia.setText(45 * slve + ".000 VNĐ");
    }
    public void ghechecked(){
        veDao = new VeDao(getContext());
        listVe = veDao.getDSVe();
        ArrayList<String> listghedb = new ArrayList<>();
        for(Ve listve: listVe){
            listghedb.add(String.format("%s", listve.getSoghe())+ listve.getThoigiandat());
        }
        for (int i = 0;i<listghedb.size();i++){
            if(listghedb.get(i).contains("A1") && listghedb.get(i).contains(gio)
            ){
                A1.setEnabled(false);
                A1.setTextColor(Color.RED);
                A1.setButtonTintList(ColorStateList.valueOf(Color.RED));
            }else {
                A1.setEnabled(true);
            }
        }
        for (int i = 0;i<listghedb.size();i++) {
            if (listghedb.get(i).contains("A2") && listghedb.get(i).contains(gio)) {
                A2.setEnabled(false);
                A2.setTextColor(Color.RED);
                A2.setButtonTintList(ColorStateList.valueOf(Color.RED));
            } else {
                A2.setEnabled(true);
            }
        }
        for (int i = 0;i<listghedb.size();i++) {
            if(listghedb.get(i).contains("A3")&& listghedb.get(i).contains(gio)){
                A3.setEnabled(false);
                A3.setTextColor(Color.RED);
                A3.setButtonTintList(ColorStateList.valueOf(Color.RED));
            }else {
                A3.setEnabled(true);
            }
        }

        for (int i = 0;i<listghedb.size();i++) {
            if(listghedb.get(i).contains("A4") && listghedb.get(i).contains(gio)){
                A4.setTextColor(Color.RED);
                A4.setButtonTintList(ColorStateList.valueOf(Color.RED));
                A4.setEnabled(false);
            }else {
                A4.setEnabled(true);
            }
        }

        for (int i = 0;i<listghedb.size();i++) {
            if (listghedb.get(i).contains("A5") && listghedb.get(i).contains(gio)) {
                A5.setTextColor(Color.RED);
                A5.setButtonTintList(ColorStateList.valueOf(Color.RED));
                A5.setEnabled(false);
            } else {
                A5.setEnabled(true);
            }
        }
        for (int i = 0;i<listghedb.size();i++) {
            if(listghedb.get(i).contains("B1") && listghedb.get(i).contains(gio)){
                B1.setTextColor(Color.RED);
                B1.setButtonTintList(ColorStateList.valueOf(Color.RED));
                B1.setEnabled(false);
            }else {
                B1.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if(listghedb.get(i).contains("B2") && listghedb.get(i).contains(gio)){
                B2.setTextColor(Color.RED);
                B2.setButtonTintList(ColorStateList.valueOf(Color.RED));
                B2.setEnabled(false);
            }else {
                B2.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if(listghedb.get(i).contains("B3") && listghedb.get(i).contains(gio)){
                B3.setTextColor(Color.RED);
                B3.setButtonTintList(ColorStateList.valueOf(Color.RED));
                B3.setEnabled(false);
            }else {
                B3.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if( listghedb.get(i).contains("B4") && listghedb.get(i).contains(gio)){
                B4.setTextColor(Color.RED);
                B4.setButtonTintList(ColorStateList.valueOf(Color.RED));
                B4.setEnabled(false);
            }else {
                B4.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if(listghedb.get(i).contains("B5") && listghedb.get(i).contains(gio)){
                B5.setTextColor(Color.RED);
                B5.setButtonTintList(ColorStateList.valueOf(Color.RED));
                B5.setEnabled(false);
            }else {
                B5.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if(listghedb.get(i).contains("C1") && listghedb.get(i).contains(gio)){
                C1.setTextColor(Color.RED);
                C1.setButtonTintList(ColorStateList.valueOf(Color.RED));
                C1.setEnabled(false);
            }else {
                C1.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if(listghedb.get(i).contains("C2") && listghedb.get(i).contains(gio)){
                C2.setTextColor(Color.RED);
                C2.setButtonTintList(ColorStateList.valueOf(Color.RED));
                C2.setEnabled(false);
            }else {
                C2.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if(listghedb.get(i).contains("C3") && listghedb.get(i).contains(gio)){
                C3.setTextColor(Color.RED);
                C3.setButtonTintList(ColorStateList.valueOf(Color.RED));
                C3.setEnabled(false);
            }else {
                C3.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if(listghedb.get(i).contains("C4") && listghedb.get(i).contains(gio)){
                C4.setTextColor(Color.RED);
                C4.setButtonTintList(ColorStateList.valueOf(Color.RED));
                C4.setEnabled(false);
            }else {
                C4.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if(listghedb.get(i).contains("C5") && listghedb.get(i).contains(gio)){
                C5.setTextColor(Color.RED);
                C5.setButtonTintList(ColorStateList.valueOf(Color.RED));
                C5.setEnabled(false);
            }else {
                C5.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if(listghedb.get(i).contains("D1") && listghedb.get(i).contains(gio)){
                D1.setTextColor(Color.RED);
                D1.setButtonTintList(ColorStateList.valueOf(Color.RED));
                D1.setEnabled(false);
            }else {
                D1.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if( listghedb.get(i).contains("D2") && listghedb.get(i).contains(gio)){
                D2.setTextColor(Color.RED);
                D2.setButtonTintList(ColorStateList.valueOf(Color.RED));
                D2.setEnabled(false);
            }else {
                D2.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if(listghedb.get(i).contains("D3") && listghedb.get(i).contains(gio)){
                D3.setTextColor(Color.RED);
                D3.setButtonTintList(ColorStateList.valueOf(Color.RED));
                D3.setEnabled(false);
            }else {
                D3.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if(listghedb.get(i).contains("D4") && listghedb.get(i).contains(gio)){
                D4.setTextColor(Color.RED);
                D4.setButtonTintList(ColorStateList.valueOf(Color.RED));
                D4.setEnabled(false);
            }else {
                D4.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if( listghedb.get(i).contains("D5") && listghedb.get(i).contains(gio)){
                D5.setTextColor(Color.RED);
                D5.setButtonTintList(ColorStateList.valueOf(Color.RED));
                D5.setEnabled(false);
            }else {
                D5.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if( listghedb.get(i).contains("E1") && listghedb.get(i).contains(gio)){
                E1.setTextColor(Color.RED);
                E1.setButtonTintList(ColorStateList.valueOf(Color.RED));
                E1.setEnabled(false);
            }else {
                E1.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if( listghedb.get(i).contains("E2") && listghedb.get(i).contains(gio)){
                E2.setTextColor(Color.RED);
                E2.setButtonTintList(ColorStateList.valueOf(Color.RED));
                E2.setEnabled(false);
            }else {
                E2.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if( listghedb.get(i).contains("E3") && listghedb.get(i).contains(gio)){
                E3.setTextColor(Color.RED);
                E3.setButtonTintList(ColorStateList.valueOf(Color.RED));
                E3.setEnabled(false);
            }else {
                E3.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if( listghedb.get(i).contains("E4") && listghedb.get(i).contains(gio)){
                E4.setTextColor(Color.RED);
                E4.setButtonTintList(ColorStateList.valueOf(Color.RED));
                E4.setEnabled(false);
            }else {
                E4.setEnabled(true);
            }}
        for (int i = 0;i<listghedb.size();i++) {
            if( listghedb.get(i).contains("E5") && listghedb.get(i).contains(gio)){
                E5.setTextColor(Color.RED);
                E5.setButtonTintList(ColorStateList.valueOf(Color.RED));
                E5.setEnabled(false);
            }else {
                E5.setEnabled(true);
            }}
    }


    private void Listener(){
        list = new ArrayList<>();
        CompoundButton.OnCheckedChangeListener listener1 = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int i = 1;
                if (isChecked){
                    i++;
                    tang();
                    list.add(buttonView.getText() + "");
                }else {
                    list.remove(buttonView.getText());
                    giam();
                }

            }
        };
        A1.setOnCheckedChangeListener(listener1);
        A2.setOnCheckedChangeListener(listener1);
        A3.setOnCheckedChangeListener(listener1);
        A4.setOnCheckedChangeListener(listener1);
        A5.setOnCheckedChangeListener(listener1);
        B1.setOnCheckedChangeListener(listener1);
        B2.setOnCheckedChangeListener(listener1);
        B3.setOnCheckedChangeListener(listener1);
        B4.setOnCheckedChangeListener(listener1);
        B5.setOnCheckedChangeListener(listener1);
        C1.setOnCheckedChangeListener(listener1);
        C2.setOnCheckedChangeListener(listener1);
        C3.setOnCheckedChangeListener(listener1);
        C4.setOnCheckedChangeListener(listener1);
        C5.setOnCheckedChangeListener(listener1);
        D1.setOnCheckedChangeListener(listener1);
        D2.setOnCheckedChangeListener(listener1);
        D3.setOnCheckedChangeListener(listener1);
        D4.setOnCheckedChangeListener(listener1);
        D5.setOnCheckedChangeListener(listener1);
        E1.setOnCheckedChangeListener(listener1);
        E2.setOnCheckedChangeListener(listener1);
        E3.setOnCheckedChangeListener(listener1);
        E4.setOnCheckedChangeListener(listener1);
        E5.setOnCheckedChangeListener(listener1);
    }


    private void anXa(){

        ivIMG = view.findViewById(R.id.ivIMG);
        txtTime = view.findViewById(R.id.txtTime);
        tv = view.findViewById(R.id.txtSelectday);
        txtsl = view.findViewById(R.id.txtsl);
        txtgia = view.findViewById(R.id.txtgia);


        txtTenPhim = view.findViewById(R.id.txtTenPhim);
        txtTheLoai = view.findViewById(R.id.txtTheLoai);


        A1 = view.findViewById(R.id.A1);
        A2 = view.findViewById(R.id.A2);
        A3 = view.findViewById(R.id.A3);
        A4 = view.findViewById(R.id.A4);
        A5 = view.findViewById(R.id.A5);
        B1 = view.findViewById(R.id.B1);
        B2 = view.findViewById(R.id.B2);
        B3 = view.findViewById(R.id.B3);
        B4 = view.findViewById(R.id.B4);
        B5 = view.findViewById(R.id.B5);
        C1 = view.findViewById(R.id.C1);
        C2 = view.findViewById(R.id.C2);
        C3 = view.findViewById(R.id.C3);
        C4 = view.findViewById(R.id.C4);
        C5 = view.findViewById(R.id.C5);
        D1 = view.findViewById(R.id.D1);
        D2 = view.findViewById(R.id.D2);
        D3 = view.findViewById(R.id.D3);
        D4 = view.findViewById(R.id.D4);
        D5 = view.findViewById(R.id.D5);
        E1 = view.findViewById(R.id.E1);
        E2 = view.findViewById(R.id.E2);
        E3 = view.findViewById(R.id.E3);
        E4 = view.findViewById(R.id.E4);
        E5 = view.findViewById(R.id.E5);

        btn_DatVe = view.findViewById(R.id.btnDatVe);

        txtTongGia = view.findViewById(R.id.txtTongGia);
        txtTongSoLuong = view.findViewById(R.id.txtTongSoLuong);
        txtGio = view.findViewById(R.id.txtGio);
        txtNgay = view.findViewById(R.id.txtNgay);
        txtGhe = view.findViewById(R.id.txtGhe);
    }
}
