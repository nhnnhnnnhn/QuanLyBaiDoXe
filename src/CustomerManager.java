import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerManager {

    public void addCustomer(String name, String phone, String licensePlate) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO customers (name, phone, license_plate) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, licensePlate);
            pstmt.executeUpdate();
            System.out.println("Customer added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getCustomerInfo(String licensePlate) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM customers WHERE license_plate = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, licensePlate);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Phone: " + rs.getString("phone"));
            } else {
                System.out.println("Customer not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
