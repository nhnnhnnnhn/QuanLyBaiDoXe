import java.sql.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingManager {

    public void addVehicle(String licensePlate) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO vehicles (license_plate) VALUES (?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, licensePlate);
            pstmt.executeUpdate();
            System.out.println("Vehicle added to the parking lot.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void vehicleExit(String licensePlate) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT entry_time FROM vehicles WHERE license_plate = ? AND exit_time IS NULL";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, licensePlate);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Timestamp entryTime = rs.getTimestamp("entry_time");
                Timestamp exitTime = Timestamp.valueOf(LocalDateTime.now());
                double fee = calculateFee(entryTime, exitTime);

                String updateSql = "UPDATE vehicles SET exit_time = ?, fee = ? WHERE license_plate = ? AND exit_time IS NULL";
                PreparedStatement updatePstmt = conn.prepareStatement(updateSql);
                updatePstmt.setTimestamp(1, exitTime);
                updatePstmt.setDouble(2, fee);
                updatePstmt.setString(3, licensePlate);
                updatePstmt.executeUpdate();
                System.out.println("Vehicle exited. Fee: " + fee);
            } else {
                System.out.println("Vehicle not found or already exited.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private double calculateFee(Timestamp entryTime, Timestamp exitTime) {
        long hours = ChronoUnit.HOURS.between(entryTime.toLocalDateTime(), exitTime.toLocalDateTime());
        return hours * 2.0;  // 2.0 là giá cho mỗi giờ đỗ xe
    }
}
