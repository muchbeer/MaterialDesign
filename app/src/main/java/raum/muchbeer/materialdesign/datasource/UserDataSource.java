package raum.muchbeer.materialdesign.datasource;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import raum.muchbeer.materialdesign.db.User;

public interface UserDataSource {

    /**
     * Gets the user from the data source.
     *
     * @return the user from the data source.
     */
    Flowable<User> getUser();

    MutableLiveData<List<User>> getUserLiveData();

    void newUser(String userName, String userSchool, String userPlace,
                 String userImage, String userDescription);

    /**
     * Inserts the user into the data source, or, if this is an existing user, updates it.
     *
     * @param user the user to be inserted or updated.
     */
    Completable insertOrUpdateUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    void clear();
}
