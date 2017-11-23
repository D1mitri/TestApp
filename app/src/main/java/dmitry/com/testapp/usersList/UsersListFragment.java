package dmitry.com.testapp.usersList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmitry.com.testapp.R;
import dmitry.com.testapp.adapters.UsersAdapter;
import dmitry.com.testapp.models.User;
import dmitry.com.testapp.usersList.presenter.UsersListPresenter;
import dmitry.com.testapp.usersList.presenter.UsersListPresenterImpl;
import dmitry.com.testapp.usersList.view.UsersListView;

public class UsersListFragment extends Fragment implements UsersListView {

    @BindView(R.id.rvUsers)
    RecyclerView rvUsers;

    private UsersAdapter adapter;
    private List<User> usersList = new ArrayList<>();
    private UsersListPresenter usersListPresenter;

    public UsersListFragment() {
        // Required empty public constructor
    }

    public static UsersListFragment newInstance() {
        return new UsersListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        usersListPresenter = new UsersListPresenterImpl(getContext(), this);
        adapter = new UsersAdapter(getContext(), usersList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users_list, container, false);
        ButterKnife.bind(this, view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvUsers.setLayoutManager(mLayoutManager);
        rvUsers.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.title_list_users);
        if (usersList.size() == 0)
            usersListPresenter.loadUsers();
    }

    @Override
    public void showUsers(List<User> users) {
        usersList.addAll(users);
        adapter.notifyDataSetChanged();
    }

}
