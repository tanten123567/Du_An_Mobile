package fpoly.cp17302_3.duanchinh.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


import fpoly.cp17302_3.duanchinh.Adapter.Admin_TrangChu_Adapter;
import fpoly.cp17302_3.duanchinh.DAO.PhimDAO;
import fpoly.cp17302_3.duanchinh.DAO.TheLoaiDAO;
import fpoly.cp17302_3.duanchinh.DAO.TrangChu_DAO;
import fpoly.cp17302_3.duanchinh.DAO.XuatChieuDAO;
import fpoly.cp17302_3.duanchinh.Model.Phim;
import fpoly.cp17302_3.duanchinh.Model.TheLoai;
import fpoly.cp17302_3.duanchinh.Model.XuatChieu;
import fpoly.cp17302_3.duanchinh.R;


public class Fragment_TrangChu_Admin extends Fragment {
    int maxuatchieu;
    Spinner spinnerTheLoai, spnGio1,spnNgay;
    PhimDAO phimDAO;
    RecyclerView recyclerPhim;
    ArrayList<Phim> list;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    ImageView edthinhanh;
    StorageReference storageRef;
    String hinhAnh = null;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangchu_admin, container, false);
        storage = FirebaseStorage.getInstance("gs://duanchinh-569e7.appspot.com");
        // Create a storage reference from our app
        storageRef = storage.getReference();
        phimDAO = new PhimDAO(getContext());
        recyclerPhim = view.findViewById(R.id.RecyclerView);


        list = phimDAO.getDSPhim();
        loadData();
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatAdd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });
        return view;
    }

    private ArrayList<HashMap<String , Object>> getDS(){
        ArrayList<Phim> list = phimDAO.getDSPhim();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (Phim phim : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maphim", phim.getMaphim());
            hs.put("tenphim", phim.getTenphim());
            hs.put("theloai", phim.getMaloai());
            listHM.add(hs);
        }
        return listHM;
    }
    private ArrayList<HashMap<String, Object>> getDSLoai(){
        TheLoaiDAO theLoaiDAO = new TheLoaiDAO(getContext());
        ArrayList<TheLoai> list = theLoaiDAO.getDSTheLoai();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (TheLoai loai : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maloai", loai.getMaloai());
            hs.put("tenloai", loai.getTenloai());
            listHM.add(hs);
        }
        return listHM;
    }
    private ArrayList<HashMap<String, Object>> getDSGio(){
        XuatChieuDAO xuatChieuDAO = new XuatChieuDAO(getContext());
        ArrayList<XuatChieu> list = xuatChieuDAO.getDSXuatChieu();
        ArrayList<HashMap<String, Object>> listHM1 = new ArrayList<>();
        for (XuatChieu xuatChieu : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maxuatchieu", xuatChieu.getMaxuatchieu());
            hs.put("thoigianchieu", xuatChieu.getThoigianchieu());
            hs.put("ngaychieu", xuatChieu.getNgaychieu());
            listHM1.add(hs);
        }
        return  listHM1;
    }

    private void loadData(){
        ArrayList<Phim> list = phimDAO.getDSPhim();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext() , 2);
        recyclerPhim.setLayoutManager(gridLayoutManager);
        recyclerPhim.setFocusable(false);
        recyclerPhim.setNestedScrollingEnabled(false);


        Admin_TrangChu_Adapter adapter = new Admin_TrangChu_Adapter(getContext(), list, getDS(), phimDAO);
        recyclerPhim.setAdapter(adapter);


    }
    private void xincapquyen() {

        Boolean allowed = ActivityCompat
                .checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
        if (allowed) {
            Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickIntent.setType("image/*");
            upLoadImg.launch(pickIntent);
        } else {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
        }



    }
    ActivityResultLauncher<Intent> upLoadImg= registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result != null) {
                        Uri imageUri = result.getData().getData();
                        Glide.with(getContext())
                                .load(imageUri)
                                .into(edthinhanh);

                    }
                }
            }
    );
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 101: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    pickIntent.setType("image/*");
                    upLoadImg.launch(pickIntent);
                } else {

                }
                break;
            }
            default:
                break;
        }
    }

    private void showdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themphim, null);
        builder.setView(view);
        EditText edtTenPhim = view.findViewById(R.id.edtTenPhim);
        edthinhanh = view.findViewById(R.id.ivHinhAnh);
        spinnerTheLoai = view.findViewById(R.id.spnTheLoai);
        SimpleAdapter simpleAdapterLoai = new SimpleAdapter(
                getContext(),
                getDSLoai(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1}
        );
        spinnerTheLoai.setAdapter(simpleAdapterLoai);
        spnGio1 = view.findViewById(R.id.spnGio1);

        SimpleAdapter simpleAdapterGio = new SimpleAdapter(
                getContext(),
                getDSGio(),
                android.R.layout.simple_list_item_1,
                new String[]{"thoigianchieu"},
                new int[]{android.R.id.text1}
        );

        spnGio1.setAdapter(simpleAdapterGio);

        spnNgay = view.findViewById(R.id.spnNgay);
        SimpleAdapter simpleAdapterNgay = new SimpleAdapter(
                getContext(),
                getDSGio(),
                android.R.layout.simple_list_item_1,
                new String[]{"ngaychieu"},
                new int[]{android.R.id.text1}

        );
        spnNgay.setAdapter(simpleAdapterNgay);

        edthinhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xincapquyen();
            }
        });
        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // Get the data from an ImageView as bytes
                Calendar calendar = Calendar.getInstance();

                StorageReference mountainsRef = storageRef.child("ImagePhim" + calendar.getTimeInMillis() + ".png");
                edthinhanh.setDrawingCacheEnabled(true);
                edthinhanh.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) edthinhanh.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] data = baos.toByteArray();

                UploadTask uploadTask = mountainsRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl();
                        Log.d("Tag", "onSuccess: " + downloadUri);
                        mountainsRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String tenphim = edtTenPhim.getText().toString();
                                HashMap<String, Object> hs = (HashMap<String, Object>) spinnerTheLoai.getSelectedItem();
                                int maloai = (int) hs.get("maloai");
                                HashMap<String, Object> hsGio1 = (HashMap<String, Object>) spnGio1.getSelectedItem();
                                maxuatchieu = (int) hsGio1.get("maxuatchieu");
                                hinhAnh = uri + "";
                                if (tenphim.equals("")||hinhAnh.equals("")){
                                    Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                                }else {
                                    boolean check = phimDAO.themPhim(tenphim, hinhAnh, maloai, maxuatchieu);
                                    if (check == true) {
                                        Toast.makeText(getContext(), "Thêm ảnh thành công", Toast.LENGTH_SHORT).show();
                                        loadData();
                                    } else {
                                        Toast.makeText(getContext(), "Thêm ảnh thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                    }
                });



            }
        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {



            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}