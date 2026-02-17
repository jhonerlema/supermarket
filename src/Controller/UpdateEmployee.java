package Controller;

import Model.Database;
import Model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UpdateEmployee {
    public  UpdateEmployee(Employee e, Database database) {
        String sql = """
                UPDATE employees
                SET FirstName = ?,
                    LastName = ?,
                    Email = ?,
                    PhoneNumber = ?,
                    Address = ?,
                    Salary = ?
                WHERE ID = ?
                """;

        try (Connection connection = database.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, e.getFirstName());
            ps.setString(2, e.getLastName());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getPhoneNumber());
            ps.setString(5, e.getAddress());
            ps.setDouble(6, e.getSalary());
            ps.setInt(7, e.getID());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Successfully updated employee "+e.getFirstName()+" "+e.getLastName());
            } else  {
                System.out.println("Failed to update employee "+e.getFirstName()+" "+e.getLastName());
            }

        }catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
