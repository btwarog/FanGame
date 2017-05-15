package pl.btwarog.fangame.ui.dashboard;

import android.os.Bundle;

import pl.btwarog.fangame.R;
import pl.btwarog.fangame.base.BaseActivity;

public class DashboardActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_container;
    }

    @Override
    protected void setUserInterface(Bundle savedInstanceState) {
        super.setUserInterface(savedInstanceState);


        DashboardFragment dashboardFragment = (DashboardFragment) getSupportFragmentManager().findFragmentByTag(DashboardFragment.TAG);

        if (dashboardFragment == null) {
            dashboardFragment = DashboardFragment.createFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, dashboardFragment, DashboardFragment.TAG)
                    .commit();
        }
    }
}
