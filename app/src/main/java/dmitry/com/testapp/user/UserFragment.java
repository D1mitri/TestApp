package dmitry.com.testapp.user;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmitry.com.testapp.R;
import dmitry.com.testapp.user.presenter.UserPresenter;
import dmitry.com.testapp.user.presenter.UserPresenterImpl;
import dmitry.com.testapp.user.view.UserView;

public class UserFragment extends Fragment implements UserView, View.OnClickListener {

    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.tvBirthday)
    TextView tvBirthday;
    @BindView(R.id.btnChangeDate)
    Button btnChangeDate;
    @BindView(R.id.btnSave)
    Button btnSave;

    private UserPresenter presenter;
    private Calendar birthday;

    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter = new UserPresenterImpl(getContext(), this);
        birthday = Calendar.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, view);
        btnChangeDate.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.title_add);
        presenter.showBirthdayDate(birthday);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnChangeDate:
                presenter.changeBirthdayDate();
                break;
            case R.id.btnSave:
                presenter.saveUser();
                break;
        }
    }

    @Override
    public String getName() {
        return etUsername.getText().toString();
    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString();
    }

    @Override
    public Calendar getBirthday() {
        return birthday;
    }

    @Override
    public void showBirthday(String birthday) {
        tvBirthday.setText(birthday);
    }

    @Override
    public void openDatePicker() {
        new DatePickerDialog(getActivity(), birthdayDatePicker,
                birthday.get(Calendar.YEAR),
                birthday.get(Calendar.MONTH),
                birthday.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private DatePickerDialog.OnDateSetListener birthdayDatePicker = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            birthday.set(year, monthOfYear, dayOfMonth);
            presenter.showBirthdayDate(birthday);
        }

    };

}
