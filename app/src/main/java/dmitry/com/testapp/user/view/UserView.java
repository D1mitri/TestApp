package dmitry.com.testapp.user.view;

import java.util.Calendar;

public interface UserView {

    String getName();

    String getEmail();

    Calendar getBirthday();

    void showBirthday(String birthday);

    void openDatePicker();

    void showErrorMessage(String message);

    void showSuccessMessage(String message);
}
