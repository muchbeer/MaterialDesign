package raum.muchbeer.materialdesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import raum.muchbeer.materialdesign.MainActivity;
import raum.muchbeer.materialdesign.R;
import raum.muchbeer.materialdesign.db.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private ArrayList<User> userList;
    private MainActivity userActivity;

    public UserAdapter(Context context, ArrayList<User> userList, MainActivity userActivity) {
        this.context = context;
        this.userList = userList;
        this.userActivity = userActivity;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View itemView = LayoutInflater.from(parent.getContext()).inflate(
               R.layout.user_item,
               parent,
               false
       );

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        final User user = userList.get(position);

        holder.userName.setText(user.getUserName());
        holder.userPlace.setText(user.getUserPlace());
        holder.userSchool.setText(user.getUserSchool());
       // holder.user

        // Set the image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .setDefaultRequestOptions(defaultOptions)
                .load(user.getUserDescription())
                .into(((UserViewHolder)holder).userImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView userName, userSchool, userPlace,userDescription;
       // public ImageView userImage;
        private CircleImageView userImage;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.txtUsername);
            userSchool = itemView.findViewById(R.id.txtUserSchool);
            userPlace = itemView.findViewById(R.id.txtUserplace);
            userImage = itemView.findViewById(R.id.imageView);
          //  userDescription = itemView.findViewById(R.id.txt)
        }
    }
}
