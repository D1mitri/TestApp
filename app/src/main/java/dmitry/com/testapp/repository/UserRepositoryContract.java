package dmitry.com.testapp.repository;

import java.util.List;

import dmitry.com.testapp.models.User;

public abstract class UserRepositoryContract {

    public void addUserToDatabase(User user) {
        throw new UnsupportedOperationException();
    }

    public List<User> loadUsers() {
        throw new UnsupportedOperationException();
    }

}
