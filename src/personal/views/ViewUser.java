package personal.views;

import personal.controllers.UserController;
import personal.model.*;

import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }
    public ViewUser(){}

    public void run() {

        Commands com = Commands.NONE;
        String id;
        String command;
        FileOperation fileOperation;
        Repository repository;

        String mode = prompt("Введите режим сохранения файла (1: текстовый(,) 2: текстовый(;) 3:json -> ");
        while (!mode.equals("1") && (!mode.equals("2")) && (!mode.equals("3"))){
            mode = prompt("Введите режим сохранения файла (1: текстовый (,) 2: текстовый(;) 3:json -> ");
        }
        switch (mode){
            case  "1":
                System.out.println("Вы выбрали режим сохранения в текстовый файл (через запятую)");
                fileOperation = new FileOperationImpl("users.txt");
                repository = new RepositoryFile(fileOperation);
                this.userController = new UserController(repository);
                break;
            case  "2":
                System.out.println("Вы выбрали режим сохранения в текстовый файл (через точку с запятой и строку разделитель)");
                fileOperation = new FileOperationImpl("users2.txt");
                repository = new SemiColonRepositoryFile(fileOperation);
                this.userController = new UserController(repository);
                break;

            case  "3":
                System.out.println("Вы выбрали режим сохранения в формате JSON");
                fileOperation = new FileOperationImpl("users.json");
                repository = new JsonRepositoryFile(fileOperation);
                this.userController = new UserController(repository);
                break;
        }
        while (true) {


            command = prompt("Введите команду: ");
            while (!commandValidate(command.toUpperCase())) {
                command = prompt("Введите команду: ");

            }

            com = Commands.valueOf(command.toUpperCase());

            if (com == Commands.EXIT) return;
            try {
                switch (com) {
                    case CREATE:
                        userController.saveUser(userCreate());

                        break;
                    case READ:
                        if (userController.recordsExist()) {
                            id = prompt("Идентификатор пользователя: ");
                            try {
                                User user = userController.readUser(id);
                                System.out.println(user);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;


                    case LIST:
                        if (userController.recordsExist())
                            for (User item : userController.readAll()) {
                                System.out.println(item + "\n");
                            }
                        break;

                    case UPDATE:
                        if (userController.recordsExist()) {
                            id = prompt("Введите id для редактирования: ");
                            userController.idExists(id);
                            userController.updateUser(id, userCreate());
                        }

                        break;

                    case DELETE:
                        if (userController.recordsExist()) {
                            id = prompt("Введите id для удаления: ");
                            userController.idExists(id);
                            userController.deleteUser(id);
                        }
                        break;

                }
            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private User userCreate() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        return (new User(firstName, lastName, phone));
    }

    private boolean commandValidate(String command) {
        try {
            Commands.valueOf(command);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Command not found");
            return false;
        }
    }
}
