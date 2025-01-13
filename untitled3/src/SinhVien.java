import java.time.LocalDate;
public class SinhVien {
    int maSo;
    String hoTen;
    LocalDate ngaySinh;
    String gioiTinh;
    String lop;

    public SinhVien(int maSo, String hoTen, LocalDate ngaySinh, String gioiTinh, String lop) {
        this.maSo = maSo;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.lop = lop;
    }

    @Override
    public String toString() {
        return "Mã số: " + maSo + ", Họ tên: " + hoTen + ", Ngày sinh: " + ngaySinh +
                ", Giới tính: " + gioiTinh + ", Lớp: " + lop;
    }
}
