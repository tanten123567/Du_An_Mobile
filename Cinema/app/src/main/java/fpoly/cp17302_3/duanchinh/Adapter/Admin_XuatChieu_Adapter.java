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

import fpoly.cp17302_3.duanchinh.DAO.XuatChieuDAO;
import fpoly.cp17302_3.duanchinh.Model.ItemClickXuatChieu;
import fpoly.cp17302_3.duanchinh.Model.XuatChieu;
import fpoly.cp17302_3.duanchinh.R;


public class Admin_XuatChieu_Adapter extends RecyclerView.Adapter<Admin_XuatChieu_Adapter.ViewHolder>{
    private Context context;
    private ArrayList<XuatChieu> list;
    private ItemClickXuatChieu itemClickXuatChieu;


    public Admin_XuatChieu_Adapter(Context context, ArrayList<XuatChieu> list, ItemClickXuatChieu itemClickXuatChieu) {
        this.context = context;
        this.list = list;
        this.itemClickXuatChieu = itemClickXuatChieu;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.admin_item_xuatchieu, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtNgayChieu.setText("Ngày Chiếu: " + list.get(position).getNgaychieu());
        holder.txtThoiGianChieu.setText("Thời Gian Chiếu: " + list.get(position).getThoigianchieu());
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuatChieuDAO xuatChieuDAO = new XuatChieuDAO(context);
                boolean check = xuatChieuDAO.xoaXuatChieu(list.get(holder.getAdapterPosition()).getMaxuatchieu());
                if (check == true){
                    list.clear();
                    list = xuatChieuDAO.getDSXuatChieu();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickXuatChieu.onClickXuatChieu(list.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    TextView txtNgayChieu, txtThoiGianChieu;
    ImageView ivDel, ivEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNgayChieu = itemView.findViewById(R.id.txtNgayChieu);
            txtThoiGianChieu = itemView.findViewById(R.id.txtThoiGianChieu);
            ivDel = itemView.findViewById(R.id.ivDel);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }

}
