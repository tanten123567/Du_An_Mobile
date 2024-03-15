package fpoly.cp17302_3.duanchinh.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import fpoly.cp17302_3.duanchinh.DAO.PhimDAO;
import fpoly.cp17302_3.duanchinh.DAO.TrangChu_DAO;
import fpoly.cp17302_3.duanchinh.Model.Phim;
import fpoly.cp17302_3.duanchinh.R;


public class Admin_TrangChu_Adapter extends RecyclerView.Adapter<Admin_TrangChu_Adapter.ViewHolder>{
    private Context context;
    private ArrayList<Phim> list;
    private ArrayList<HashMap<String, Object>> ds;
    private PhimDAO trangChu_dao;


    public Admin_TrangChu_Adapter(Context context, ArrayList<Phim> list, ArrayList<HashMap<String, Object>> ds, PhimDAO trangChu_dao) {
        this.context = context;
        this.list = list;
        this.ds = ds;
        this.trangChu_dao = trangChu_dao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.trangchu_item_phim, parent, false);



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(list.get(position).getHinhanh()).into(holder.image);

        holder.tenphim.setText(""+list.get(position).getTenphim());
        holder.image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                view = inflater.inflate(R.layout.dialog_xoa, null);
                builder.setView(view);
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        trangChu_dao.xoaPhim(list.get(holder.getAdapterPosition()).getMaphim());
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return false;
            }
        });


    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image ;
        TextView tenphim ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            tenphim = itemView.findViewById(R.id.TenPhim);


        }
    }




    public void loadData(){
        list.clear();
        list = trangChu_dao.getDSPhim();
        notifyDataSetChanged();

    }




}
