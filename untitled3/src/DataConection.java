import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;


public class DataConection {
    public static Connection getConnection() throws SQLException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("sa");  // Tên đăng nhập SQL Server
        ds.setPassword("123456789"); // Mật khẩu SQL Server
        ds.setServerName("LAPTOP-LO8CF4TD"); // Tên server
        ds.setPortNumber(1433); // Cổng TCP/IP mặc định
        ds.setDatabaseName("QuanLySinhVien"); // Tên database
        ds.setEncrypt(true); // Bật mã hóa SSL
        ds.setTrustServerCertificate(true); // Bỏ qua xác thực chứng chỉ SSL

        return ds.getConnection(); // Trả về đối tượng Connection
    }
}


