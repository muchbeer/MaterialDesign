package raum.muchbeer.materialdesign.datasource;

import android.content.Context;

import raum.muchbeer.materialdesign.db.UserDatabase;
import raum.muchbeer.materialdesign.viewmodel.ViewModelFactory;

public class Injection {

    public static UserDataSource provideUserDataSource(Context context) {
        UserDatabase database = UserDatabase.getInstance(context);
        return new LocalUserDataSource(database.userDao());
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        UserDataSource dataSource = provideUserDataSource(context);

        return new ViewModelFactory(dataSource);
    }
}
