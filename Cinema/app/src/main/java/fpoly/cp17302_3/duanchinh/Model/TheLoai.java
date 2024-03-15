package fpoly.cp17302_3.duanchinh.Model;

public class TheLoai {
    private int maloai;
    private String tenloai;

    public TheLoai(int maloai, String tenloai) {
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public TheLoai(String tenloai) {
        this.tenloai = tenloai;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}
