package dmitry.com.testapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dmitry.com.testapp.R;
import dmitry.com.testapp.models.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private Context context;
    private List<User> users;

    public UsersAdapter(Context context, List<User> usersList) {
        this.context = context;
        this.users = usersList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUser;

        public ViewHolder(View v) {
            super(v);
            tvUser = v.findViewById(R.id.tvUser);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = users.get(position);
        holder.tvUser.setText(context.getString(R.string.users_list_item,
                user.getUsername(),
                user.getEmail(),
                user.getFormattedBirthday()));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
