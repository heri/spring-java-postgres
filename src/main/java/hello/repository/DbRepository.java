package hello.repository;

import hello.model.User;

import java.util.List;

public interface DbRepository {
    User getUser(String id);

    User updateUser(User user, String firstName, String lastName);

    List<User> users();
}
