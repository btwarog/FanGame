package pl.btwarog.fangame.ui.intro;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import pl.btwarog.fangame.R;
import pl.btwarog.fangame.base.BaseApplication;
import pl.btwarog.fangame.base.BaseFragment;
import pl.btwarog.fangame.common.Constants;
import pl.btwarog.fangame.common.listeners.OnBroadcastRecieveListener;
import pl.btwarog.fangame.common.receivers.DownloadDataReceiver;
import pl.btwarog.fangame.network.FetchDataService;
import pl.btwarog.fangame.ui.dashboard.DashboardActivity;

/**
 * Created by bartlomiejtwarog on 14.05.2017.
 */

public class IntroFragment extends BaseFragment<IntroFragment> implements IntroContract.View, OnBroadcastRecieveListener {
    public static String TAG = IntroFragment.class.getSimpleName();

    @Inject
    IntroPresenter introPresenter;

    DownloadDataReceiver downloadDataReceiver;

    @BindView(R.id.error_layout)
    LinearLayout errorLayout;

    @BindView(R.id.retry_button)
    Button retryButton;

    @BindView(R.id.progress_layout)
    LinearLayout progressLayout;


    public static IntroFragment createFragment() {
        return new IntroFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDependencyInjector(this);
        introPresenter.attachView(this);
        registerBroadcastReceiver();
    }

    private void registerBroadcastReceiver() {
        downloadDataReceiver = new DownloadDataReceiver();
        downloadDataReceiver.setListener(this);
        IntentFilter statusIntentFilter = new IntentFilter(
                Constants.BROADCAST_ACTION);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(downloadDataReceiver, statusIntentFilter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_intro;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            showProgress();
            introPresenter.fetchData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        downloadDataReceiver.setListener(null);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(downloadDataReceiver);
        introPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void initializeDependencyInjector(IntroFragment introFragment) {
        ((BaseApplication) getActivity().getApplication()).getApplictaionComponent().inject(this);
    }

    @Override
    public void startFetchingRemoteDataService() {
        getActivity().startService(new Intent(getActivity(), FetchDataService.class));
    }

    @Override
    public void startDashboardActivity() {
        startActivity(new Intent(getActivity(), DashboardActivity.class));
        getActivity().finish();
    }

    @Override
    public void onBroadcastReceived(Constants.Status status) {
        if (status == Constants.Status.MSG_SUCCESS) {
            hideError();
            hideProgress();
            startDashboardActivity();
        } else {
            hideProgress();
            showError();
        }
    }

    @OnClick(R.id.retry_button)
    void onRetryClicked() {
        hideError();
        showProgress();
        introPresenter.fetchData();
    }


    private void showProgress() {
        progressLayout.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressLayout.setVisibility(View.GONE);
    }

    private void showError() {
        errorLayout.setVisibility(View.VISIBLE);
    }

    private void hideError() {
        errorLayout.setVisibility(View.GONE);
    }
}
