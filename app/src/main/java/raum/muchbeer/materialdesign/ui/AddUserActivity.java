package raum.muchbeer.materialdesign.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    public static final String EXTRA_EDIT_POSITION = "raum.muchbeer.materialdesign.ui.EXTRA_EDIT_USER";
    public static final String EXTRA_EDIT_USER = "raum.muchbeer.materialdesign.ui.EXTRA_GET_NEW_USER";
    public static final String EXTRA_NEW_SCHOOL = "raum.muchbeer.materialdesign.ui.EXTRA_GET_NEW_SCHOOL";
    public static final String EXTRA_NEW_PLACE = "raum.muchbeer.materialdesign.ui.EXTRA_GET_NEW_PLACE";
    public static final String EXTRA_NEW_DESCRIPTION = "raum.muchbeer.materialdesign.ui.EXTRA_GET_NEW_DESCRIPTION";
    public static final String EXTRA_NEW_IMAGE = "raum.muchbeer.materialdesign.ui.EXTRA_GET_NEW_IMAGE";


    private UserViewModel userViewModel;
    private UserDatabase userAppDatabase;
    private ViewModelFactory mViewModelFactory;

    private EditText getUsername, getSchool, getDescription, getSchoolPlace;
    private String saveUsername, saveSchool, saveDescription, saveSchoolPlace, saveImage;

    private Button addNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();


     //  saveImage = intent.getStringExtra(EXTRA_NEW_IMAGE);

        int position_user = intent.getIntExtra(EXTRA_EDIT_POSITION, -3);
   //  checkEditAndNew(position_user);

        getUsername = findViewById(R.id.edt_username);
        getSchool = findViewById(R.id.edt_schoolname);
        getSchoolPlace = findViewById(R.id.edt_place);
        getDescription = findViewById(R.id.edt_description);

        mViewModelFactory = Injection.provideViewModelFactory(this);
        userViewModel = new ViewModelProvider(this, mViewModelFactory).get(UserViewModel.class);

        if(position_user ==-1) {
            getSupportActionBar().setTitle(" Add New School ");


        } else if ( position_user >= 0) {
            getSupportActionBar().setTitle("Edit School");
            User retrieveUser = intent.getParcelableExtra(EXTRA_EDIT_USER);

            saveUsername = retrieveUser.getUserName();
            saveSchool = retrieveUser.getUserSchool();
            saveSchoolPlace = retrieveUser.getUserPlace();
            saveDescription = retrieveUser.getUserDescription();


            Log.d(LOG_TAG, "The value on Detail activity: " + saveUsername);
            getUsername.setText(saveUsername, TextView.BufferType.EDITABLE);
            getSchool.setText(saveSchool, TextView.BufferType.EDITABLE);
            getSchoolPlace.setText(saveSchoolPlace, TextView.BufferType.EDITABLE);
            getDescription.setText(saveDescription, TextView.BufferType.EDITABLE);
        }

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

    private void addAndEditContacts() {
        //add new item
        String image_name = "https://firebasestorage.googleapis.com/v0/b/androidcertification.appspot.com/o/cars_picture%2Fimage%3A92447?alt=media&token=6e83b1d8-1fce-405c-80e5-f6bf3cb01b1e";

        createUser(getUsername.getText().toString(), getSchool.getText().toString(),
                getSchoolPlace.getText().toString(), image_name, getDescription.getText().toString()  );
    }

    private void checkEditAndNew(int position) {
        if(position ==-1) {
            getSupportActionBar().setTitle(" Add New School ");


        } else if ( position >= 0) {
//            getSupportActionBar().setTitle("Edit School");
            Log.d(LOG_TAG, "The value on Detail activity: " + saveUsername);
            getUsername.setText(saveUsername, TextView.BufferType.EDITABLE);
            getSchool.setText(saveSchool, TextView.BufferType.EDITABLE);
            getSchoolPlace.setText(saveSchoolPlace, TextView.BufferType.EDITABLE);
            getDescription.setText(saveDescription, TextView.BufferType.EDITABLE);
        }
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
