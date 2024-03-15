package fpoly.cp17302_3.duanchinh.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import fpoly.cp17302_3.duanchinh.DAO.ThanhVienDAO;
import fpoly.cp17302_3.duanchinh.DAO.VeDao;
import fpoly.cp17302_3.duanchinh.Model.Phim;
import fpoly.cp17302_3.duanchinh.Model.Ve;
import fpoly.cp17302_3.duanchinh.R;

public class KhachHang_GioHang_Adapter extends RecyclerView.Adapter<KhachHang_GioHang_Adapter.ViewHolder>{
    Context context;
    ArrayList<Ve> list;
    VeDao veDao;
    ThanhVienDAO thanhVienDAO;
    public KhachHang_GioHang_Adapter(Context context, ArrayList<Ve> list, VeDao veDao) {
        this.context = context;
        this.list = list;
        this.veDao = veDao;
        thanhVienDAO = new ThanhVienDAO(context.getApplicationContext());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.khachhang_item_giohang, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtSoGhe.setText("" + list.get(position).getSoghe());
        holder.txtGia.setText("" + list.get(position).getGiave());
        holder.txtSoLuong.setText("" + list.get(position).getSoluong());
        holder.txtThoiGianDat.setText("" + list.get(position).getThoigiandat());
        holder.txtNgayChieu.setText(""+ list.get(position).getNgaychieu());
        holder.txtTenPhim.setText(""+list.get(position).getTenphim());
        holder.btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Vé đặt rồi không hủy được bạn nhé :D", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder{
        TextView txtSoGhe, txtSoLuong, txtGia, txtThoiGianDat, txtNgayChieu, txtTenPhim, txtTheLoai;

        Button btn_huy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSoGhe = itemView.findViewById(R.id.txtSoGhe);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            txtGia = itemView.findViewById(R.id.txtGia);
            txtThoiGianDat = itemView.findViewById(R.id.txtThoiGianDat);
            txtNgayChieu = itemView.findViewById(R.id.txtNgayChieu);

            txtTenPhim = itemView.findViewById(R.id.txtTP);
            txtTheLoai = itemView.findViewById(R.id.txtTheLoai);


            btn_huy = itemView.findViewById(R.id.btn_huy);

        }
    }
    public void loadData(){
        list.clear();
        list = veDao.getDSVe();
        notifyDataSetChanged();
    }

}
