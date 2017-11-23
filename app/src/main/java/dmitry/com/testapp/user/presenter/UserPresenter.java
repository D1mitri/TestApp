package dmitry.com.testapp.user.presenter;

import java.util.Calendar;

public interface UserPresenter {

    void saveUser();

    void showBirthdayDate(Calendar date);

    void changeBirthdayDate();
}
