package dmitry.com.testapp.user.presenter;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import dmitry.com.testapp.R;
import dmitry.com.testapp.models.User;
import dmitry.com.testapp.repository.UserRepository;
import dmitry.com.testapp.user.view.UserView;

public class UserPresenterImpl implements UserPresenter {

    private static final String DATE_MASK = "dd-MM-yyyy";

    private UserView userView;
    private Context mContext;

    public UserPresenterImpl(Context context, UserView userView) {
        this.mContext = context;
        this.userView = userView;
    }

    @Override
    public void saveUser() {
        String username = userView.getName();
        String email = userView.getEmail();

        if (username.isEmpty()) {
            userView.showErrorMessage(mContext.getString(R.string.empty_username));
        } else if (email.isEmpty()) {
            userView.showErrorMessage(mContext.getString(R.string.empty_email));
        } else {
            userView.showSuccessMessage(mContext.getString(R.string.user_saved)
                    + "\n" + username
                    + "\n" + email
                    + "\n" + userView.getBirthday().getTime());

            UserRepository.getInstance(mContext)
                    .addUserToDatabase(new User(username,
                            email,
                            userView.getBirthday().getTime()));
        }
    }

    @Override
    public void showBirthdayDate(Calendar date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_MASK, Locale.getDefault());
        userView.showBirthday(mContext.getString(R.string.birthday, sdf.format(date.getTime())));
    }

    @Override
    public void changeBirthdayDate() {
        userView.openDatePicker();
    }
}
