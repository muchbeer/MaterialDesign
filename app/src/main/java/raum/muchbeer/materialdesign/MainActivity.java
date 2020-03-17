package raum.muchbeer.materialdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import raum.muchbeer.materialdesign.adapter.UserAdapter;
import raum.muchbeer.materialdesign.datasource.Injection;
import raum.muchbeer.materialdesign.db.User;
import raum.muchbeer.materialdesign.db.UserDatabase;
import raum.muchbeer.materialdesign.ui.AddUserActivity;
import raum.muchbeer.materialdesign.viewmodel.UserViewModel;
import raum.muchbeer.materialdesign.viewmodel.ViewModelFactory;

import static raum.muchbeer.materialdesign.ui.AddUserActivity.EXTRA_EDIT_POSITION;
import static raum.muchbeer.materialdesign.ui.AddUserActivity.EXTRA_NEW_USER;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private UserViewModel userViewModel;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private ArrayList<User> userList =  new ArrayList<>();
    private ViewModelFactory mViewModelFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" School Logs ");

        mViewModelFactory = Injection.provideViewModelFactory(this);
        userViewModel = new ViewModelProvider(this, mViewModelFactory).get(UserViewModel.class);


        recyclerView = findViewById(R.id.recycler_view_users);
        userAdapter = new UserAdapter(this, userList, MainActivity.this);

        userViewModel.getAllUsers().observe(this, users -> {
            userList.clear();
            userList.addAll(users);
            Log.d(LOG_TAG, "The vaLLUE is: "+ users);
            init();
            userAdapter.notifyDataSetChanged();
        });

        FloatingActionButton fab =  findViewById(R.id.fab);

        fab.setOnClickListener(view -> {
            // addAndEditContacts(false, null, -1);
            Intent addNewSchool = new Intent(getApplicationContext(), AddUserActivity.class);
            String newUser = "new_user";
            int position = -1;
            addNewSchool.putExtra(EXTRA_EDIT_POSITION, position);
            startActivity(addNewSchool);
        });


    }

    private void init() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userAdapter);   }

    private void deleteUser(final User user, int position) {
        userViewModel.deleteUser(user);
    }

    private void updateUser(final String userName, final String userSchool, final String userPlace,
                            String userDescription, int position) {
        final User user = userList.get(position);

        user.setUserName(userName);
        user.setUserSchool(userSchool);
        user.setUserPlace(userPlace);
        user.setmUserImage("link here");
        user.setmUserDescription(userDescription);
        userViewModel.updateUser(user);    }

    @Override
    protected void onStop() {
        super.onStop();
        userViewModel.clear();
    }
}
