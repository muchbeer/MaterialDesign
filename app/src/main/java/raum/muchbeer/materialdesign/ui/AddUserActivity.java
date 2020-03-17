package raum.muchbeer.materialdesign.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import raum.muchbeer.materialdesign.MainActivity;
import raum.muchbeer.materialdesign.R;
import raum.muchbeer.materialdesign.adapter.UserAdapter;
import raum.muchbeer.materialdesign.datasource.Injection;
import raum.muchbeer.materialdesign.db.User;
import raum.muchbeer.materialdesign.db.UserDatabase;
import raum.muchbeer.materialdesign.viewmodel.UserViewModel;
import raum.muchbeer.materialdesign.viewmodel.ViewModelFactory;

public class AddUserActivity extends AppCompatActivity {

    private static final String LOG_TAG = AddUserActivity.class.getSimpleName();

    public static final String EXTRA_NEW_USER = "raum.muchbeer.materialdesign.ui.EXTRA_GET_NEW";

    private UserViewModel userViewModel;
    private UserDatabase userAppDatabase;
    private ViewModelFactory mViewModelFactory;

    private EditText getUsername, getSchool, getDescription, getSchoolPlace;

    private Button addNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String new_User = intent.getStringExtra(EXTRA_NEW_USER);
        if (new_User.equals("new_user")) {
            getSupportActionBar().setTitle(" Add Schoool ");
        } else {
            getSupportActionBar().setTitle("Edit School");
        }

        getUsername = findViewById(R.id.edt_username);
        getSchool = findViewById(R.id.edt_schoolname);
        getSchoolPlace = findViewById(R.id.edt_place);
        getDescription = findViewById(R.id.edt_description);

        mViewModelFactory = Injection.provideViewModelFactory(this);
        userViewModel = new ViewModelProvider(this, mViewModelFactory).get(UserViewModel.class);

        addNew = findViewById(R.id.button);

        addNew.setOnClickListener(view -> {
            //   addAndEditContacts(false, null, -1);
            if (!getUsername.getText().toString().isEmpty() && !getSchool.getText().toString().isEmpty()
                    && !getSchoolPlace.getText().toString().isEmpty() && !getDescription.getText().toString().isEmpty()) {
                addAndEditContacts();
                Intent intentNew = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentNew);
            }
            else {
                Toast.makeText(getApplicationContext(), "missing field required", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addAndEditContacts() {
        String image_name = "https://firebasestorage.googleapis.com/v0/b/androidcertification.appspot.com/o/cars_picture%2Fimage%3A92447?alt=media&token=6e83b1d8-1fce-405c-80e5-f6bf3cb01b1e";

        createUser(getUsername.getText().toString(), getSchool.getText().toString(),
                getSchoolPlace.getText().toString(), image_name, getDescription.getText().toString()  );


    }

    private void createUser(final String userName, final String userSchool, final String userPlace
                            , final String userImage, final String userDescription) {
        userViewModel.newUser(userName,userSchool,userPlace, userImage, userDescription);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        userViewModel.clear();
    }
}
