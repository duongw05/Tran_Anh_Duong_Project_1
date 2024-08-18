/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author ADMIN
 */
public class DBConnect {
    public static final String HOSTNAME = "localhost";
    public static final String PORT = "1433";
    public static final String DBNAME = "Du_An_1_Nhom_3_20";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "123456";

    /**
     * Get connection to MSSQL Server
     *
     * @return Connection
     */
    public static Connection getConnection() {

        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://" + HOSTNAME + ":" + PORT + ";"
                + "databaseName=" + DBNAME + ";encrypt=true;trustServerCertificate=true;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(connectionUrl, USERNAME, PASSWORD);
        } // Handle any errors that may have occurred.
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
    
    static String Driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String url = "jdbc:sqlserver://localhost:1433;databaseName=Du_An_1_Nhom_3_20;encrypt=true;trustServerCertificate=true";
    static String user = "sa";
    static String pass = "123456";

    // nạp driver
    static {// dau chay thu di
        try {
            Class.forName(Driver);
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "Lỗi Đọc Cơ Sở Dữ Liệu SQL Servers");
            throw new RuntimeException(ex);
        }
    }

    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, pass);
        PreparedStatement stmt;
        if (sql.trim().startsWith("{")) {
            stmt = conn.prepareCall(sql);
        } else {
            stmt = conn.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }
        return stmt;
    }

    // dùng để thực hiện select hoặc các thủ tục lưu truy vấn dữ liệu
    public static ResultSet query(String sql, Object... args) {
        try {
            PreparedStatement stmt = DBConnect.getStmt(sql, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // dùng để select đối tượng liên quan
    // ví dụ select * from nhanvien where maNV = "maNV01"
    public static Object values(String sql, Object... args) {
        try {
            ResultSet rs = DBConnect.query(sql, args);
            if (rs.next()) {
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // dùng để sử dụng các thao tác như insert, update, delete hoặc thủ lục lưu tác động dữ liệu
    public static int update(String sql, Object... args) {
        try {
            PreparedStatement stmt = DBConnect.getStmt(sql, args);

            try {
                return stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
