package Controller;

import Model.Database;
import Model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateEmployee {
    public CreateEmployee(Employee e, Database database) {
        try {
            int rows = insertEmployee(e, database);
            if (rows > 0) {
                System.out.println(" Employee Created Successfully");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private int insertEmployee(Employee e, Database database) throws SQLException {

        String sql = "INSERT INTO employees "
                + "(FirstName, LastName, Email, phoneNumber, Address, Salary, Department, Password) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, e.getFirstName());
            ps.setString(2, e.getLastName());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getPhoneNumber());
            ps.setString(5, e.getAddress());
            ps.setDouble(6, e.getSalary());
            ps.setInt(7, e.getDepartment());
            ps.setString(8, e.getPassword());

            return ps.executeUpdate();
        }
    }
}
