package dmitry.com.testapp.repository;

import android.content.Context;

import java.util.List;

import dmitry.com.testapp.models.User;

public class UserRepository extends UserRepositoryContract {

    private static UserRepository userRepository;
    private UserRepositoryContract realmRepository;

    public synchronized static UserRepository getInstance(Context context) {
        if (userRepository == null) {
            userRepository = new UserRepository(context);
        }
        return userRepository;
    }

    private UserRepository(Context context) {
        realmRepository = RealmUserRepository.getInstance(context);
    }


    @Override
    public void addUserToDatabase(User user) {
        realmRepository.addUserToDatabase(user);
    }

    @Override
    public List<User> loadUsers() {
        return realmRepository.loadUsers();
    }
}
