import java.util.List;
public interface IStudentDAO {
    void addStudent(Student student); // Thêm sinh viên
    void updateStudent(Student student); // Cập nhật thông tin sinh viên
    void deleteStudent(int studentId); // Xóa sinh viên
    List<Student> getAllStudents(); // Lấy danh sách tất cả sinh viên
    List<StudentWithTotalCredits> getStudentsWithTotalCredits(); // Lấy thông tin sinh viên kèm tổng tín chỉ
    void displayStudentGrades(); // Hiển thị điểm thi của sinh viên
}
