package raum.muchbeer.materialdesign.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import raum.muchbeer.materialdesign.datasource.UserDataSource;
import raum.muchbeer.materialdesign.db.User;

public class UserViewModel extends ViewModel {

    private static final String LOG_TAG = UserViewModel.class.getSimpleName();
    private final UserDataSource mDataSource;

    private User mUser;

    public UserViewModel(UserDataSource mDataSource) {
        this.mDataSource = mDataSource;
    }



    public LiveData<List<User>> getAllUsers(){
        return     mDataSource.getUserLiveData(); }


    public Flowable<String> getSingleUser() {
        return mDataSource.getUser().
                        map(user -> {
                            mUser = user;
                            Log.d(LOG_TAG, "thHE username is: " + mUser.getUserName());
                            return mUser.getUserName();
                        });
    }
    public void newUser(String userName, String userSchool, String userPlace, String userImage, String userDescription){
                mDataSource.newUser(userName,userSchool,userPlace, userImage, userDescription); }


    public void updateUser(User user){ mDataSource.updateUser(user);  }

    public void deleteUser(User user){  mDataSource.deleteUser(user); }

    public void clear(){  mDataSource.clear(); }

}
