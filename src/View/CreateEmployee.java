package View;

import Model.*;

import Model.Admin;
import Model.Cashier;
import Model.Database;
import Model.Employee;
import Model.Option;
import Model.Storekeeper;

import java.util.Scanner;

public class CreateEmployee implements Option {
    @Override
    public void oper(Employee user, Scanner s, Database database) {
        System.out.println("Enter First Name:");
        String firstName = s.nextLine();
        System.out.println("Enter Last Name:");
        String lastName = s.nextLine();
        System.out.println("Enter Email :");
        String email = s.nextLine();
        System.out.println("Enter Phone Number:");
        String phoneNumber = s.nextLine();
        System.out.println("Enter Address:");
        String address = s.nextLine();
        System.out.println("Enter Salary:");
        double salary = s.nextDouble();
        s.nextLine();
        System.out.println("Enter Password:");
        String password = s.nextLine();
        System.out.println("Enter department: (0.Admi 1.Cahier 2.Storekeeper): ");
        int department = s.nextInt();

        Employee e = switch (department) {
            case 0 -> new Admin();
            case 1 -> new Cashier();
            case 2 -> new Storekeeper();
            default -> {
                System.out.println("Invalid department selected");
                yield new Cashier();
            }
        };

        e.setFirstName(firstName);
        e.setLastName(lastName);
        e.setEmail(email);
        e.setAddress(address);
        e.setPassword(password);
        e.setSalary(salary);
        e.setPhoneNumber(phoneNumber);

        new Controller.CreateEmployee(e, database);
    }

    @Override
    public String getOption() {
        return "Add New Employee";
    }
}
