package Controller;
import Model.Database;
import Model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangePassword {
    public void changePassword(String password, Employee user, Database database) {

        String sql = "UPDATE employees SET Password = ? WHERE ID = ?";

        try (Connection connection = database.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1 , password);
            ps.setInt(2, user.getID());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                user.setPassword(password);
                System.out.println("Password changed Successfully");
            }else {
                System.out.println("Password NOT changed");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
