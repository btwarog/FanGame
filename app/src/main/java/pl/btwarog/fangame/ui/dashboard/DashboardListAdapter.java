package pl.btwarog.fangame.ui.dashboard;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.btwarog.fangame.R;
import pl.btwarog.fangame.database.model.LocalPlayer;

/**
 * Created by bartlomiejtwarog on 15.05.2017.
 */

public class DashboardListAdapter extends RecyclerView.Adapter<DashboardListAdapter.PlayerViewHolder> {

    private List<LocalPlayer> localPlayers;

    public DashboardListAdapter() {
        localPlayers = new ArrayList<>();
    }


    public void setData(List<LocalPlayer> localPlayers) {
        if (localPlayers != null && localPlayers.size() > 0) {
            this.localPlayers = localPlayers;
            notifyDataSetChanged();
        }
    }


    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dashboard_list_item, parent, false);
        return new PlayerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        if (localPlayers != null && localPlayers.size() > 0) {
            holder.bindTo(localPlayers.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return localPlayers != null ? localPlayers.size() : 0;
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.profile_image)
        ImageView profileImage;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.points)
        TextView points;

        public PlayerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindTo(LocalPlayer localPlayer) {
            name.setText(itemView.getContext().getString(R.string.full_name, localPlayer.getFirstName(), localPlayer.getLastName()));
            points.setText(itemView.getContext().getString(R.string.points,localPlayer.getPoints()));

            if (!TextUtils.isEmpty(localPlayer.getImageUrl())) {
                Glide
                        .with(itemView.getContext())
                        .load(localPlayer.getImageUrl())
                        .centerCrop()
                        .crossFade()
                        .into(profileImage);
            }
        }
    }
}
