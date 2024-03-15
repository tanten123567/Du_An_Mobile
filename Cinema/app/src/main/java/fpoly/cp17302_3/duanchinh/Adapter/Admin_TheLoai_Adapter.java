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

import java.util.ArrayList;

import fpoly.cp17302_3.duanchinh.DAO.TheLoaiDAO;
import fpoly.cp17302_3.duanchinh.Model.ItemClick;
import fpoly.cp17302_3.duanchinh.Model.TheLoai;
import fpoly.cp17302_3.duanchinh.R;

public class Admin_TheLoai_Adapter extends RecyclerView.Adapter<Admin_TheLoai_Adapter.ViewHolder>{
    private Context context;
    private ArrayList<TheLoai> list;
    private ItemClick itemClick;

    public Admin_TheLoai_Adapter(Context context, ArrayList<TheLoai> list, ItemClick itemClick) {
        this.context = context;
        this.list = list;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.khachhang_item_theloai, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTheLoai.setText("Thể Loại: " + list.get(position).getTenloai());
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TheLoaiDAO theLoaiDAO = new TheLoaiDAO(context);
                boolean check = theLoaiDAO.xoaTheLoai(list.get(holder.getAdapterPosition()).getMaloai());
                if (check == true){
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list = theLoaiDAO.getDSTheLoai();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onClickTheLoai(list.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
            TextView txtTheLoai;
            ImageView ivDel, ivEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTheLoai = itemView.findViewById(R.id.txtTenLoai);
            ivDel = itemView.findViewById(R.id.ivDel);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }

}
