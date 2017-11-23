package dmitry.com.testapp.usersList.presenter;

import android.content.Context;

import java.util.List;

import dmitry.com.testapp.models.User;
import dmitry.com.testapp.repository.UserRepository;
import dmitry.com.testapp.usersList.view.UsersListView;

public class UsersListPresenterImpl implements UsersListPresenter {

    private Context mContext;
    private UsersListView mUsersListView;

    public UsersListPresenterImpl(Context context, UsersListView usersListView) {
        this.mContext = context;
        this.mUsersListView = usersListView;
    }

    @Override
    public void loadUsers() {
        List<User> users = UserRepository.getInstance(mContext).loadUsers();
        mUsersListView.showUsers(users);
    }
}
