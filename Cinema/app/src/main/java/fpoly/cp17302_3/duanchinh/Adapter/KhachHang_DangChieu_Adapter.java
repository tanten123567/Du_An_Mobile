package fpoly.cp17302_3.duanchinh.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import fpoly.cp17302_3.duanchinh.DAO.PhimDAO;
import fpoly.cp17302_3.duanchinh.Model.Phim;
import fpoly.cp17302_3.duanchinh.R;

public class KhachHang_DangChieu_Adapter extends RecyclerView.Adapter<KhachHang_DangChieu_Adapter.ViewHolder>{
    ArrayList<Phim> list;
    Context context;
    PhimDAO phimDAO;
    ArrayList<HashMap<String, Object>> ds;

    public KhachHang_DangChieu_Adapter(ArrayList<Phim> list, Context context, PhimDAO phimDAO, ArrayList<HashMap<String, Object>> ds) {
        this.list = list;
        this.context = context;
        this.phimDAO = phimDAO;
        this.ds = ds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.khachhang_item_datve, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(list.get(position).getHinhanh()).into(holder.ivHinhAnh);
        holder.txtTenPhim.setText("" + list.get(position).getTenphim());
        holder.txtTheLoai.setText("" + list.get(position).getTenloai());
        holder.txtGio.setText("" + list.get(position).getThoigianchieu());
        holder.txtNgay.setText("" + list.get(position).getNgaychieu());

        holder.btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("Get Total");
                intent.putExtra("ten",list.get(position).getTenphim());
                intent.putExtra("loai",list.get(position).getTenloai());
                intent.putExtra("ngay", list.get(position).getNgaychieu());
                intent.putExtra("gio", list.get(position).getThoigianchieu());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


                Log.d(">>>TAGDATA",""+list.get(position).getTenphim()+"\n"+list.get(position).getTenloai()+"\n"+list.get(position).getNgaychieu()+"\n"+list.get(position).getThoigianchieu());



            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenPhim,txtTheLoai,txtGio,txtNgay;
        ImageView ivHinhAnh;
        Button btnDatVe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTheLoai = itemView.findViewById(R.id.TheLoai);
            txtGio = itemView.findViewById(R.id.txtGio);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtTenPhim = itemView.findViewById(R.id.TenPhim);
            ivHinhAnh = itemView.findViewById(R.id.ivHinhAnh);
            btnDatVe = itemView.findViewById(R.id.btnDatVe);
        }
    }
    private void loadData(){
        list.clear();
        list = phimDAO.getDSPhim();
        notifyDataSetChanged();
    }
}
