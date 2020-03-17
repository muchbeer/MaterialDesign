package raum.muchbeer.materialdesign.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Immutable model class for a User
 */
@Entity(tableName = "users")
public class User implements Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userid")
    private Integer mId;

    @ColumnInfo(name = "username")
    private String mUserName;

    @ColumnInfo(name = "school_name")
    private String mUserSchool;

    @ColumnInfo(name = "school_placename")
    private String mUserPlace;

    @ColumnInfo(name = "school_image")
    private String mUserImage;

    @ColumnInfo(name = "school_description")
    private String mUserDescription;


/*    public User( Integer mId, String mUserName, String mUserSchool, String mUserPlace,
                String mUserImage, String userDescription) {
       this.mId = mId;
        this.mUserName = mUserName;
        this.mUserSchool = mUserSchool;
        this.mUserPlace = mUserPlace;
        this.mUserImage = mUserImage;
        this.mUserDescription = userDescription;   }*/


    public User(String mUserName, String mUserSchool, String mUserPlace,
                    String mUserImage, String userDescription) {
        this.mUserName = mUserName;
        this.mUserSchool = mUserSchool;
        this.mUserPlace = mUserPlace;
        this.mUserImage = mUserImage;
        this.mUserDescription = userDescription;   }

    protected User(Parcel in) {
        mId = in.readInt();
        mUserName = in.readString();
        mUserSchool = in.readString();
        mUserPlace = in.readString();
        mUserImage = in.readString();
        mUserDescription = in.readString();  }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) { return new User[size];   }
    };

    public String getUserSchool() {return mUserSchool;}

    public String getUserPlace() {return mUserPlace;}

    public Integer getId() { return mId; }

    public String getUserName() {  return mUserName; }

    public String getUserImage() {   return mUserImage;   }

    public String getUserDescription() {  return mUserDescription;  }

    public void setmUserImage(String mUserImage) { this.mUserImage = mUserImage; }

    public void setmUserDescription(String mUserDescription) { this.mUserDescription = mUserDescription;  }


    public void setId(@NonNull Integer mId) { this.mId = mId; }

    public void setUserName(String mUserName) { this.mUserName = mUserName; }

    public void setUserSchool(String mUserSchool) { this.mUserSchool = mUserSchool; }

    public void setUserPlace(String mUserPlace) { this.mUserPlace = mUserPlace; }

    @Override
    public int describeContents() { return 0;  }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mUserName);
        dest.writeString(mUserSchool);
        dest.writeString(mUserPlace);
        dest.writeString(mUserImage);
        dest.writeString(mUserDescription);
    }
}
