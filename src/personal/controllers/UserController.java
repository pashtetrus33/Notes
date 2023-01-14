package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.List;

import static java.util.regex.Pattern.matches;

public class UserController {
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) throws Exception {
        validateUser(user);
        repository.CreateUser(user);
    }

    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        throw new Exception("User not found");
    }

    public List<User> readAll() {
        return repository.getAllUsers();
    }

    public void updateUser(String id, User updatedUser) throws Exception {
        updatedUser.setId(id);
        validateUserID(updatedUser);
        repository.updateUser(updatedUser);
    }

    public void deleteUser(String id){
        User user = repository.getAllUsers().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        System.out.println(user);
        repository.deleteUser(user);
    }

    private void validateUser(User user) throws Exception {
        if (user.getFirstName().contains(" "))
            throw new Exception("Username contains spaces");
        if (user.getLastName().contains(" "))
            throw new Exception("Lastname contains spaces");
        if (!matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", user.getPhone()))
            throw new Exception("Invalid phone format");
    }

    private void validateUserID(User user) throws Exception {
        if (user.getId().isEmpty())
            throw new Exception("ID is empty");
        validateUser(user);
    }

    public void idExists(String id) throws Exception {
        List<User> users = repository.getAllUsers();
        User user = users.stream().filter(p -> p.getId().equals(id)).findAny().orElse(null);
        if (user == null)
            throw new Exception("ID does not Exist");
    }
    public boolean recordsExist(){
        List<User> users = repository.getAllUsers();
        boolean result = users.size() == 0;
        if (result)
        System.out.println("Список пустой.");
        return !result;
    }
}
