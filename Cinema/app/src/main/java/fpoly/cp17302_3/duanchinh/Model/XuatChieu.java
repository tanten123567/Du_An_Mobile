package fpoly.cp17302_3.duanchinh.Model;

public class XuatChieu {
    private int maxuatchieu;
    private String ngaychieu;
    private String thoigianchieu;
    private String maphim;




    public XuatChieu(int maxuatchieu, String ngaychieu, String thoigianchieu) {
        this.maxuatchieu = maxuatchieu;
        this.ngaychieu = ngaychieu;
        this.thoigianchieu = thoigianchieu;
    }

    public int getMaxuatchieu() {
        return maxuatchieu;
    }

    public void setMaxuatchieu(int maxuatchieu) {
        this.maxuatchieu = maxuatchieu;
    }

    public String getNgaychieu() {
        return ngaychieu;
    }

    public void setNgaychieu(String ngaychieu) {
        this.ngaychieu = ngaychieu;
    }

    public String getThoigianchieu() {
        return thoigianchieu;
    }

    public void setThoigianchieu(String thoigianchieu) {
        this.thoigianchieu = thoigianchieu;
    }

    public String getMaphim() {
        return maphim;
    }

    public void setMaphim(String maphim) {
        this.maphim = maphim;
    }
}
