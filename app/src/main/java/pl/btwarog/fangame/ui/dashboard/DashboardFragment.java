package pl.btwarog.fangame.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import pl.btwarog.fangame.R;
import pl.btwarog.fangame.base.BaseApplication;
import pl.btwarog.fangame.base.BaseFragment;
import pl.btwarog.fangame.database.model.LocalPlayer;
import pl.btwarog.fangame.ui.game.GameActivity;
import pl.btwarog.fangame.ui.intro.IntroActivity;

/**
 * Created by bartlomiejtwarog on 15.05.2017.
 */

public class DashboardFragment extends BaseFragment<DashboardFragment> implements DashboardContract.View {
    public static String TAG = DashboardFragment.class.getSimpleName();

    @Inject
    DashboardPresenter dashboardPresenter;

    @BindView(R.id.players_list)
    RecyclerView playersList;

    @BindView(R.id.start_game_btn)
    Button startGameBtn;

    DashboardListAdapter dashboardListAdapter;

    public static DashboardFragment createFragment() {
        return new DashboardFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDependencyInjector(this);
        dashboardPresenter.attachView(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected void initializeDependencyInjector(DashboardFragment dashboardFragment) {
        ((BaseApplication) getActivity().getApplication()).getApplictaionComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        playersList.setLayoutManager(new LinearLayoutManager(getActivity()));

        dashboardListAdapter = new DashboardListAdapter();

        playersList.setAdapter(dashboardListAdapter);


        if (savedInstanceState == null) {
            dashboardPresenter.fetchData();
        }
    }

    @Override
    public void finish() {
        Intent intent = new Intent(getActivity(), IntroActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void setAdapterData(List<LocalPlayer> localPlayers) {
        dashboardListAdapter.setData(localPlayers);
    }

    @OnClick(R.id.start_game_btn)
    void onStartBtnClicked() {
        getActivity().startActivity(new Intent(getActivity(), GameActivity.class));
    }
}
