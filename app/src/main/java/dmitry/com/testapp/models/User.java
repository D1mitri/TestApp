package dmitry.com.testapp.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    @PrimaryKey
    private long id;
    private String username;
    private String email;
    private Date birthday;

    public User() {

    }

    public User(String name, String email, Date date) {
        this.username = name;
        this.email = email;
        this.birthday = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFormattedBirthday() {
        return new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(birthday);
    }

    @Override
    public String toString() {
        return "id: [" + id + "]; name: " + username + "; email: " + email + "; birthday: " + getFormattedBirthday();
    }
}
