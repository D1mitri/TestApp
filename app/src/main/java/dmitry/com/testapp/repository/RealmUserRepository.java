package dmitry.com.testapp.repository;

import android.content.Context;
import android.util.Log;

import java.util.List;

import dmitry.com.testapp.models.User;
import io.realm.Realm;

public class RealmUserRepository extends UserRepositoryContract {

    private static final String TAG = "RealmUserRepository";
    private static RealmUserRepository repository;
    private final Realm realm;

    private RealmUserRepository(Context context) {
        realm = Realm.getDefaultInstance();
    }

    public synchronized static RealmUserRepository getInstance(Context context) {
        if (repository == null) {
            repository = new RealmUserRepository(context);
        }
        return repository;
    }

    @Override
    public void addUserToDatabase(User user) {
        Log.d(TAG, "add user to database");
        realm.beginTransaction();
        Number maxValue = realm.where(User.class).max("id");
        long pk = (maxValue != null) ? maxValue.intValue() + 1 : 0;
        user.setId(pk);
        realm.copyToRealm(user);
        realm.commitTransaction();
    }

    @Override
    public List<User> loadUsers() {
        Log.d(TAG, "load users");
        return realm.where(User.class).findAll();
    }

}
