import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        quanLySinhVien(scanner);

        try {
            Connection conn = DataConection.getConnection();
            if (conn != null) {
                System.out.println("Kết nối thành công!");
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Kết nối thất bại: " + e.getMessage());
        }
    }

        private static void quanLySinhVien(Scanner scanner) {
            int choice;
            do {
                System.out.println("\nQuản lý danh sách sinh viên:");
                System.out.println("1. Thêm sinh viên");
                System.out.println("2. Sửa sinh viên");
                System.out.println("3. Xóa sinh viên");
                System.out.println("4. Hiển thị danh sách sinh viên");
                System.out.println("5. Quay lại");
                System.out.print("Lựa chọn của bạn: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Nhập mã số: ");
                        int maSo = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nhập họ tên: ");
                        String hoTen = scanner.nextLine();
                        System.out.print("Nhập ngày sinh (yyyy-MM-dd): ");
                        String ngaySinh = scanner.nextLine();
                        System.out.print("Nhập giới tính (Nam/Nữ): ");
                        String gioiTinh = scanner.nextLine();
                        System.out.print("Nhập lớp: ");
                        String lop = scanner.nextLine();
                        SinhVienDAO.themSinhVien(new SinhVien(maSo, hoTen, LocalDate.parse(ngaySinh), gioiTinh, lop));
                        break;
                    case 2:
                        System.out.print("Nhập mã số sinh viên cần sửa: ");
                        maSo = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nhập họ tên mới: ");
                        hoTen = scanner.nextLine();
                        System.out.print("Nhập ngày sinh mới (yyyy-MM-dd): ");
                        ngaySinh = scanner.nextLine();
                        System.out.print("Nhập giới tính mới (Nam/Nữ): ");
                        gioiTinh = scanner.nextLine();
                        System.out.print("Nhập lớp mới: ");
                        lop = scanner.nextLine();
                        SinhVienDAO.suaSinhVien(new SinhVien(maSo, hoTen, LocalDate.parse(ngaySinh), gioiTinh, lop));
                        break;
                    case 3:
                        System.out.print("Nhập mã số sinh viên cần xóa: ");
                        maSo = scanner.nextInt();
                        SinhVienDAO.xoaSinhVien(maSo);
                        break;
                    case 4:
                        System.out.println("Danh sách sinh viên:");
                        List<SinhVien> danhSach = SinhVienDAO.layDanhSachSinhVien();
                        for (SinhVien sv : danhSach) {
                            System.out.println(sv);
                        }
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } while (choice != 5);
        }
    }


