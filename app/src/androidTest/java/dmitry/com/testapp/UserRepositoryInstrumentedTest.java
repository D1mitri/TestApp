package dmitry.com.testapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import dmitry.com.testapp.models.User;
import dmitry.com.testapp.repository.RealmUserRepository;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class UserRepositoryInstrumentedTest {

    private final String TAG = getClass().getSimpleName();
    private RealmUserRepository repository;

    @Before
    public void setup() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        repository = RealmUserRepository.getInstance(appContext);
    }

    @Test
    public void addUsers() throws Exception {
        int usersCount = 10;

        for (int i = 0; i < usersCount; i++) {
            User user = new User("Name", "email", new Date());
            user.setId(i);
            repository.addUserToDatabase(user);
        }

        int usersInDbCount = repository.loadUsers().size();

        assertEquals(usersCount, usersInDbCount);
    }

    @Test
    public void readDatabase() throws Exception {
        List<User> users = repository.loadUsers();
        for (User user : users) {
            Log.d(TAG, user.toString());
        }
    }
}
