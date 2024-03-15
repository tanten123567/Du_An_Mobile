package fpoly.cp17302_3.duanchinh.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import fpoly.cp17302_3.duanchinh.MainActivity_KhachHang;
import fpoly.cp17302_3.duanchinh.Model.Phim;
import fpoly.cp17302_3.duanchinh.R;

public class KhachHang_DatVe_Adapter extends RecyclerView.Adapter<KhachHang_DatVe_Adapter.ViewHolder>{
    ArrayList<Phim> list;
    Context context;
    PhimDAO phimDAO;
    ArrayList<HashMap<String, Object>> ds;
    SharedPreferences sharedPreferences ;

    public KhachHang_DatVe_Adapter(ArrayList<Phim> list, Context context, PhimDAO phimDAO, ArrayList<HashMap<String, Object>> ds) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getHinhanh()).into(holder.ivPhim);
        holder.txtTenPhim.setText("Ten Phim: " + list.get(position).getTenphim());
        holder.txtTheLoai.setText("The Loai:" + list.get(position).getTenloai());
        holder.txtGio.setText("" + list.get(position).getThoigianchieu());
        holder.txtNgay.setText("" + list.get(position).getNgaychieu());
        //sharedPreferences = context.getSharedPreferences("HINHANH",Context.MODE_PRIVATE);
        holder.btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("Get Total");

                intent.putExtra("hinhanh" , list.get(position).getHinhanh());
                intent.putExtra("ten",list.get(position).getTenphim());
                intent.putExtra("loai",list.get(position).getTenloai());
                intent.putExtra("ngay", list.get(position).getNgaychieu());
                intent.putExtra("gio", list.get(position).getThoigianchieu());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                Log.d(">>>TAGDATA",""+list.get(position).getTenphim()+"\n"+list.get(position).getTenloai()+"\n"+list.get(position).getNgaychieu()+"\n"+list.get(position).getThoigianchieu());
                MainActivity_KhachHang activity = (MainActivity_KhachHang) context;
                activity.ChuyenFG(list.get(position).getTenphim(),list.get(position).getTenloai(), list.get(position).getNgaychieu(), list.get(position).getThoigianchieu(),list.get(position).getHinhanh());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenPhim,txtTheLoai,txtGio,txtNgay;
        ImageView ivPhim;
        Button btnDatVe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTheLoai = itemView.findViewById(R.id.txtTheLoai);
            txtGio = itemView.findViewById(R.id.txtGio);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtTenPhim = itemView.findViewById(R.id.txtTenPhim);
            ivPhim = itemView.findViewById(R.id.ivPhim);
            btnDatVe = itemView.findViewById(R.id.btnDatVe);
        }
    }
    private void loadData(){
        list.clear();
        list = phimDAO.getDSPhim();
        notifyDataSetChanged();
    }
}
