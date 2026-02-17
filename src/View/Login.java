package View;

import Model.Database;
import Model.Employee;
import Model.Option;

import java.util.Scanner;

public class Login implements Option {

    @Override
    public void oper(Employee user, Scanner s, Database database) {
        System.out.println("Welcome to supermarket management system");
        System.out.println("Please enter your email:");
        String email = s.nextLine();
        System.out.println("Please enter your password:");
        String password = s.nextLine();
        Controller.Login login = new Controller.Login(email, password, database);
        if (login.isLoggedIn()) {
            Employee employee = login.getUser();
            System.out.println("Welcome " + employee.getFirstName() + " " + employee.getLastName());
            employee.showList(s, database);
        }else {
            System.out.println("Wrong email or password");
            System.out.println("Try aging later");

        }
    }
    @Override
    public String getOption() {
        return "Login";
    }
}
