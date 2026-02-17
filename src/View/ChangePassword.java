package View;

import Model.Database;
import Model.Employee;
import Model.Option;

import java.util.Scanner;

public class ChangePassword implements Option {
    @Override
    public void oper(Employee user, Scanner s, Database database) {
        System.out.print("Enter old Password: ");
        String oldPass = s.nextLine();

        if (!oldPass.equals(user.getPassword())) {
            System.out.println("Incorrect password \n Try again later");
            return;
        }

        System.out.print("Enter new Password: ");
        String newPass = s.nextLine();
        System.out.print("Confirm Password: ");
        String confirmPass = s.nextLine();

        if (!newPass.equals(confirmPass)) {
            System.out.println("Passwords doesn't match \n Try again later");
            return;
        }
        Controller.ChangePassword controller = new Controller.ChangePassword();
        controller.changePassword(confirmPass, user,database);
    }

    @Override
    public String getOption() {
        return "Change Password";
    }
}
