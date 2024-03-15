package fpoly.cp17302_3.duanchinh.Model;

public class Ve {
    private int mave;
    private String thoigiandat;
    private String soghe;
    private String giave;
    private String maxuatchieu;
    private String matv;
    private String ngaychieu;
    private String soluong;
    private int tenphim;
    private String theloai;

    public Ve(int mave, String thoigiandat, String soghe, String giave, String maxuatchieu, String matv, String ngaychieu, String soluong, int tenphim, String theloai) {
        this.mave = mave;
        this.thoigiandat = thoigiandat;
        this.soghe = soghe;
        this.giave = giave;
        this.maxuatchieu = maxuatchieu;
        this.matv = matv;
        this.ngaychieu = ngaychieu;
        this.soluong = soluong;
        this.tenphim = tenphim;
        this.theloai = theloai;
    }
    public Ve(int mave, String soghe, String soluong, String giave, String thoigiandat, String ngaychieu) {
        this.mave = mave;
        this.thoigiandat = thoigiandat;
        this.soghe = soghe;
        this.giave = giave;
        this.ngaychieu = ngaychieu;
        this.soluong = soluong;
    }

    public int getMave() {
        return mave;
    }

    public void setMave(int mave) {
        this.mave = mave;
    }

    public String getThoigiandat() {
        return thoigiandat;
    }

    public void setThoigiandat(String thoigiandat) {
        this.thoigiandat = thoigiandat;
    }

    public String getSoghe() {
        return soghe;
    }

    public void setSoghe(String soghe) {
        this.soghe = soghe;
    }

    public String getGiave() {
        return giave;
    }

    public void setGiave(String giave) {
        this.giave = giave;
    }

    public String getMaxuatchieu() {
        return maxuatchieu;
    }

    public void setMaxuatchieu(String maxuatchieu) {
        this.maxuatchieu = maxuatchieu;
    }

    public String getMatv() {
        return matv;
    }

    public void setMatv(String matv) {
        this.matv = matv;
    }

    public String getNgaychieu() {
        return ngaychieu;
    }

    public void setNgaychieu(String ngaychieu) {
        this.ngaychieu = ngaychieu;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public int getTenphim() {
        return tenphim;
    }

    public void setTenphim(int tenphim) {
        this.tenphim = tenphim;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }
}