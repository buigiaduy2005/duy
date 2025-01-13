import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class SinhVienDAO {
    public static void themSinhVien(SinhVien sv) {
        String sql = "INSERT INTO SinhVien (MaSo, HoTen, NgaySinh, GioiTinh, Lop) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DataConection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sv.maSo);
            stmt.setString(2, sv.hoTen);
            stmt.setDate(3, Date.valueOf(sv.ngaySinh)); // Chuyển đổi LocalDate -> java.sql.Date
            stmt.setString(4, sv.gioiTinh);
            stmt.setString(5, sv.lop);
            stmt.executeUpdate();
            System.out.println("Thêm sinh viên thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sửa sinh viên
    public static void suaSinhVien(SinhVien sv) {
        String sql = "UPDATE SinhVien SET HoTen = ?, NgaySinh = ?, GioiTinh = ?, Lop = ? WHERE MaSo = ?";

        try (Connection conn = DataConection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sv.hoTen);
            stmt.setDate(2, java.sql.Date.valueOf(sv.ngaySinh)); // Chuyển LocalDate sang java.sql.Date
            stmt.setString(3, sv.gioiTinh);
            stmt.setString(4, sv.lop);
            stmt.setInt(5, sv.maSo);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Sửa sinh viên thành công!");
            } else {
                System.out.println("Không tìm thấy sinh viên với mã số: " + sv.maSo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi sửa sinh viên.");
        }
    }

    // Xóa sinh viên
    public static void xoaSinhVien(int maSo) {
        String sql = "DELETE FROM SinhVien WHERE MaSo = ?";

        try (Connection conn = DataConection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maSo);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Xóa sinh viên thành công!");
            } else {
                System.out.println("Không tìm thấy sinh viên với mã số: " + maSo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi xóa sinh viên.");
        }
    }

    // Lấy danh sách sinh viên
    public static List<SinhVien> layDanhSachSinhVien() {
        List<SinhVien> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM SinhVien";
        try (Connection conn = DataConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                SinhVien sv = new SinhVien(
                        rs.getInt("MaSo"),
                        rs.getString("HoTen"),
                        rs.getDate("NgaySinh").toLocalDate(), // Chuyển đổi java.sql.Date -> LocalDate
                        rs.getString("GioiTinh"),
                        rs.getString("Lop")
                );
                danhSach.add(sv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }
}


