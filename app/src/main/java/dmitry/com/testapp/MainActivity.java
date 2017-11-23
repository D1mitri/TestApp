package dmitry.com.testapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmitry.com.testapp.user.UserFragment;
import dmitry.com.testapp.usersList.UsersListFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private final String TAG = getClass().getSimpleName();
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bottom_navigation.setOnNavigationItemSelectedListener(this);
        if (getSupportFragmentManager().findFragmentById(R.id.container) == null)
            replaceFragment(UserFragment.newInstance());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_user:
                Log.d(TAG, "action_add_user");
                replaceFragment(UserFragment.newInstance());
                return true;
            case R.id.action_list_users:
                Log.d(TAG, "action_list_users");
                replaceFragment(UsersListFragment.newInstance());
                return true;
        }
        return false;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();
    }
}
