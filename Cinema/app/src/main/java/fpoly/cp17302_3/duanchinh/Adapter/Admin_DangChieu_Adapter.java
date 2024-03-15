package fpoly.cp17302_3.duanchinh.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import fpoly.cp17302_3.duanchinh.DAO.PhimDAO;
import fpoly.cp17302_3.duanchinh.DataBase.DBHelper;
import fpoly.cp17302_3.duanchinh.Model.Phim;
import fpoly.cp17302_3.duanchinh.R;

public class Admin_DangChieu_Adapter extends RecyclerView.Adapter<Admin_DangChieu_Adapter.ViewHolder>{
    ArrayList<Phim> list;
    Context context;
    PhimDAO phimDAO;
    ArrayList<HashMap<String, Object>> ds;


    public Admin_DangChieu_Adapter(ArrayList<Phim> list, Context context, PhimDAO phimDAO, ArrayList<HashMap<String, Object>> ds) {
        this.list = list;
        this.context = context;
        this.phimDAO = phimDAO;
        this.ds = ds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.admin_item_dangchieu, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getHinhanh()).into(holder.ivPhim);
        holder.txtTenPhim.setText("Ten Phim: " + list.get(position).getTenphim());
        holder.txtTheLoai.setText("The Loai:" + list.get(position).getTenloai());
        holder.txtGio1.setText("" + list.get(position).getThoigianchieu());
        holder.txtNgay.setText("" + list.get(position).getNgaychieu());


        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = phimDAO.xoaPhim(list.get(holder.getAdapterPosition()).getMaphim());
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

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTenPhim, txtTheLoai, txtGio1, txtNgay/*, txtGio2, txtGio3*/;
        ImageView ivPhim, ivDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTheLoai = itemView.findViewById(R.id.txtTheLoai);
            txtGio1 = itemView.findViewById(R.id.txtGio1);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            ivDel = itemView.findViewById(R.id.ivDel);
            txtTenPhim = itemView.findViewById(R.id.txtTenPhim);
            ivPhim = itemView.findViewById(R.id.ivPhim);
        }
    }
    private void loadData(){
        list.clear();
        list = phimDAO.getDSPhim();
        notifyDataSetChanged();
    }

}
