package fpoly.cp17302_3.duanchinh.Adapter;

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
import fpoly.cp17302_3.duanchinh.DAO.TrangChu_DAO;
import fpoly.cp17302_3.duanchinh.MainActivity_KhachHang;
import fpoly.cp17302_3.duanchinh.Model.Phim;
import fpoly.cp17302_3.duanchinh.R;


public class CusTom_TrangChu_Adapter extends RecyclerView.Adapter<CusTom_TrangChu_Adapter.ViewHolder>{
    private Context context;
    private ArrayList<Phim> list;
    private ArrayList<HashMap<String, Object>> ds;
    private PhimDAO trangChu_dao;

    public CusTom_TrangChu_Adapter(Context context, ArrayList<Phim> list, ArrayList<HashMap<String, Object>> ds, PhimDAO trangChu_dao) {
        this.context = context;
        this.list = list;
        this.ds = ds;
        this.trangChu_dao = trangChu_dao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_trangchu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getHinhanh()).into(holder.image);


        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image ;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);

        }
    }


}
