package raum.muchbeer.materialdesign.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    Flowable<User> getUser();

    @Query("SELECT * FROM users")
    Flowable<List<User>> getListOfUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     long insertUserMvvm(User user);

    @Delete
     void deleteUserMvvm(User user);

    @Update
     void updateUser(User user);

    @Query("DELETE FROM Users")
    void deleteAllUsers();
}