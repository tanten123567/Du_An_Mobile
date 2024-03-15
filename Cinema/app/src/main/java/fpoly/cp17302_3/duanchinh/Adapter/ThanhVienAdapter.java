package fpoly.cp17302_3.duanchinh.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.cp17302_3.duanchinh.DAO.ThanhVienDAO;
import fpoly.cp17302_3.duanchinh.Model.ThanhVien;
import fpoly.cp17302_3.duanchinh.R;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder>{
    private Context context;
    private ArrayList<ThanhVien> list;
    private ThanhVienDAO thanhVienDAO;
    ImageView ivDel;
    SharedPreferences sharedPreferences;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.khachhang_item_thanhvien, parent, false);

        return new ViewHolder(view);
    }

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list, ThanhVienDAO thanhVienDAO) {
        this.context = context;
        this.list = list;
        this.thanhVienDAO = thanhVienDAO;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        sharedPreferences = context.getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String matv = sharedPreferences.getString("matv","");
        String sdt = sharedPreferences.getString("sodienthoai", "");
        String email = sharedPreferences.getString("email","");
        String hoten = sharedPreferences.getString("hoten", "");
        String matkhau = sharedPreferences.getString("matkhau","");
        String loaitaikhoan = sharedPreferences.getString("loaitaikhoan", "");

        holder.txtMaTV.setText("Mã Thành Viên: " + list.get(position).getMatv());
        holder.txtSDT.setText("Số Điện Thoại: " + list.get(position).getSdt());
        holder.txtEmail.setText("Email: " + list.get(position).getEmail());
        holder.txtHoTen.setText("Họ Tên: " +list.get(position).getTentv());
        holder.txtMatKhau.setText("Mật Khẩu: " + list.get(position).getMatkhau());
        holder.txtLoaiTK.setText("Loại Tài Khoản: " + list.get(position).getLoaitaikhoan());
        list = thanhVienDAO.getDSThanhVien();
        // xpa thanh vien
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = thanhVienDAO.xoaThanhVien(list.get(holder.getAdapterPosition()).getMatv());
                if (check == true){
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    loadData();
                }else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView txtMaTV, txtSDT, txtEmail, txtHoTen, txtMatKhau, txtLoaiTK;
        ImageView ivEdit, ivDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaTV = itemView.findViewById(R.id.txtMaTV);
            txtSDT = itemView.findViewById(R.id.txtSDT);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtHoTen = itemView.findViewById(R.id.txtHoTen);
            txtMatKhau = itemView.findViewById(R.id.txtMatKhau);
            txtLoaiTK = itemView.findViewById(R.id.txtLoaiTaiKhoan);
            ivDel = itemView.findViewById(R.id.ivDelTV);

        }
    }
    private void loadData(){
        list.clear();
        list = thanhVienDAO.getDSThanhVien();
        notifyDataSetChanged();
    }
}
