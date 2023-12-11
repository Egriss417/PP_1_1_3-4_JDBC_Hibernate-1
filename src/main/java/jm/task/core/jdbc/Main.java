package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();


        userService.createUsersTable();

        userService.saveUser("Krot", "pot", (byte) 15);
        userService.saveUser("chak", "nor", (byte) 55);
        userService.saveUser("si", "gma", (byte) 75);
        userService.saveUser("Tom", "Ros", (byte) 85);

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();
       // userService.dropUsersTable();
    }
}
