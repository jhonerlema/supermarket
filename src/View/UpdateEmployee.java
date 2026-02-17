package View;

import Controller.ReadAllEmployees;
import Controller.ReadEmployee;
import Model.Database;
import Model.Employee;
import Model.Option;

import java.util.Scanner;

public class UpdateEmployee implements Option {
    @Override
    public void oper(Employee user, Scanner s, Database database) {
        System.out.print("Enter employee ID (-1 to show all employees): \n");
        int ID = s.nextInt();
        s.nextLine();

        while (ID == -1) {
            new ReadAllEmployees(database).print();
            System.out.print("Enter employee ID (-1 to show all employees): \n");
            ID = s.nextInt();
            s.nextLine();
        }

        ReadEmployee readEmployee = new ReadEmployee();
        Employee emp = readEmployee.readEmployee(ID, database);

        if (emp == null) {
            System.out.println("Employee with ID " + ID + " not found!");
            return;
        }

        System.out.print("Enter first name (-1 to keep " + emp.getFirstName() + "): \n");
        String firstName = s.nextLine();
        if (!firstName.equals("-1")) {
            emp.setFirstName(firstName);
        }
        System.out.print("Enter last name (-1 to keep " + emp.getLastName() + "): \n");
        String lastName = s.nextLine();
        if (!lastName.equals("-1")) {
            emp.setLastName(lastName);
        }
        System.out.print("Enter email (-1 to keep " + emp.getEmail() + "): \n");
        String email = s.nextLine();
        if (!email.equals("-1")) {
            emp.setEmail(email);
        }
        System.out.print("Enter phone number (-1 to keep " + emp.getPhoneNumber() + "): \n");
        String phoneNumber = s.nextLine();
        if (!phoneNumber.equals("-1")) {
            emp.setPhoneNumber(phoneNumber);
        }
        System.out.print("Enter address (-1 to keep " + emp.getAddress() + "): \n");
        String address = s.nextLine();
        if (!address.equals("-1")) {
            emp.setAddress(address);
        }

        System.out.print("Enter salary (-1 to keep " + emp.getSalary() + "): \n");
        double salary = s.nextDouble();
        s.nextLine();
        if (salary!=-1) {
            emp.setSalary(salary);
        }

        new Controller.UpdateEmployee(emp, database);

    }

    @Override
    public String getOption() {
        return "Update Employee's Data";
    }
}
