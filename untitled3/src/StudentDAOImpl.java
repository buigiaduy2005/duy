import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class StudentDAOImpl implements IStudentDAO {
    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO SinhVien (MaSo, HoTen, NgaySinh, GioiTinh, Lop) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DataConection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setDate(3, Date.valueOf(student.getDateOfBirth()));
            stmt.setString(4, student.getGender());
            stmt.setString(5, student.getClassName());

            stmt.executeUpdate();
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "UPDATE SinhVien SET HoTen = ?, NgaySinh = ?, GioiTinh = ?, Lop = ? WHERE MaSo = ?";
        try (Connection conn = DataConection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setDate(2, Date.valueOf(student.getDateOfBirth()));
            stmt.setString(3, student.getGender());
            stmt.setString(4, student.getClassName());
            stmt.setInt(5, student.getId());

            stmt.executeUpdate();
            System.out.println("Student updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM SinhVien WHERE MaSo = ?";
        try (Connection conn = DataConection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.executeUpdate();
            System.out.println("Student deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM SinhVien";

        try (Connection conn = DataConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("MaSo"),
                        rs.getString("HoTen"),
                        rs.getDate("NgaySinh").toLocalDate(),
                        rs.getString("GioiTinh"),
                        rs.getString("Lop")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<StudentWithTotalCredits> getStudentsWithTotalCredits() {
        List<StudentWithTotalCredits> students = new ArrayList<>();
        String sql = "SELECT sv.MaSo, sv.HoTen, SUM(mh.SoTinChi) AS TongTinChi " +
                "FROM SinhVien sv " +
                "JOIN Diem d ON sv.MaSo = d.MaSo " +
                "JOIN MonHoc mh ON d.MaMonHoc = mh.MaMonHoc " +
                "GROUP BY sv.MaSo, sv.HoTen";

        try (Connection conn = DataConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                StudentWithTotalCredits student = new StudentWithTotalCredits(
                        rs.getInt("MaSo"),
                        rs.getString("HoTen"),
                        rs.getInt("TongTinChi")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void displayStudentGrades() {
        String sql = "SELECT sv.MaSo, sv.HoTen, mh.TenMonHoc, d.DiemThi " +
                "FROM Diem d " +
                "JOIN SinhVien sv ON d.MaSo = sv.MaSo " +
                "JOIN MonHoc mh ON d.MaMonHoc = mh.MaMonHoc";

        try (Connection conn = DataConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Subject: %s | Grade: %.2f%n",
                        rs.getInt("MaSo"), rs.getString("HoTen"),
                        rs.getString("TenMonHoc"), rs.getFloat("DiemThi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
