package fpoly.cp17302_3.duanchinh.Model;

public class Phim {
    private int maphim;
    private String tenphim;
    private String hinhanh;
    private String tenloai;
    private String thoigianchieu;
    private String ngaychieu;
    private int maxuatchieu;
    private int maloai;

    public String getThoigianchieu() {
        return thoigianchieu;
    }

    public int getMaxuatchieu() {
        return maxuatchieu;
    }

    public Phim(int maphim, String tenphim, String hinhanh, String tenloai, String thoigianchieu, String ngaychieu) {
        this.maphim = maphim;
        this.tenphim = tenphim;
        this.hinhanh = hinhanh;
        this.tenloai = tenloai;
        this.thoigianchieu = thoigianchieu;
        this.ngaychieu = ngaychieu;
    }

    public String getNgaychieu() {
        return ngaychieu;
    }

    public void setNgaychieu(String ngaychieu) {
        this.ngaychieu = ngaychieu;
    }

    public void setMaxuatchieu(int maxuatchieu) {
        this.maxuatchieu = maxuatchieu;
    }

    public Phim(int maphim, String tenphim, String hinhanh, String tenloai, String thoigianchieu, int maxuatchieu, int maloai) {
        this.maphim = maphim;
        this.tenphim = tenphim;
        this.hinhanh = hinhanh;
        this.tenloai = tenloai;
        this.thoigianchieu = thoigianchieu;
        this.maxuatchieu = maxuatchieu;
        this.maloai = maloai;
    }

    public Phim(int maphim, String tenphim, String hinhanh, String tenloai, String thoigianchieu, String ngaychieu, int maxuatchieu, int maloai) {
        this.maphim = maphim;
        this.tenphim = tenphim;
        this.hinhanh = hinhanh;
        this.tenloai = tenloai;
        this.thoigianchieu = thoigianchieu;
        this.ngaychieu = ngaychieu;
        this.maxuatchieu = maxuatchieu;
        this.maloai = maloai;
    }

    public void setThoigianchieu(String thoigianchieu) {
        this.thoigianchieu = thoigianchieu;
    }

    public Phim(int maphim, String tenphim, String hinhanh, String tenloai, String thoigianchieu) {
        this.maphim = maphim;
        this.tenphim = tenphim;
        this.hinhanh = hinhanh;
        this.tenloai = tenloai;
        this.thoigianchieu = thoigianchieu;
    }

    public Phim(int maphim, String tenphim, String hinhanh, int maxuatchieu, int maloai) {
        this.maphim = maphim;
        this.tenphim = tenphim;
        this.hinhanh = hinhanh;
        this.maxuatchieu = maxuatchieu;
        this.maloai = maloai;
    }

    public Phim(int maphim, String tenphim, String hinhanh, String tenloai) {
        this.maphim = maphim;
        this.tenphim = tenphim;
        this.hinhanh = hinhanh;
        this.tenloai = tenloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public Phim(int maphim, String tenphim, String hinhanh, int maloai) {
        this.maphim = maphim;
        this.tenphim = tenphim;
        this.hinhanh = hinhanh;
        this.maloai = maloai;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public Phim(int maphim, String tenphim, String hinhanh) {
        this.maphim = maphim;
        this.tenphim = tenphim;
        this.hinhanh = hinhanh;
    }

    public int getMaphim() {
        return maphim;
    }

    public void setMaphim(int maphim) {
        this.maphim = maphim;
    }

    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
}
