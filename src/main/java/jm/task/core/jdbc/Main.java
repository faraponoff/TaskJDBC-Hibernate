package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Martynov", (byte) 28);
        userService.saveUser("Vladimir", "Radchenko", (byte) 23);
        userService.saveUser("Vitaly", "Nalivaikin", (byte) 24);
        userService.saveUser("Alexander", "Mysovskiy", (byte) 24);
        for (User u : userService.getAllUsers()) {
            System.out.println(u);
        }
        userService.cleanUsersTable();
        System.out.println("Cleaned");
        for (User u : userService.getAllUsers()) {
            System.out.println(u);
        }
        userService.dropUsersTable();
        System.out.println("Dropped");
    }
}
