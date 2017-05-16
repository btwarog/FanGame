package pl.btwarog.fangame.ui.game;

import android.os.Bundle;

import pl.btwarog.fangame.R;
import pl.btwarog.fangame.base.BaseActivity;
import pl.btwarog.fangame.ui.dashboard.DashboardFragment;

public class GameActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_container;
    }

    @Override
    protected void setUserInterface(Bundle savedInstanceState) {
        super.setUserInterface(savedInstanceState);


        GameFragment gameFragment = (GameFragment) getSupportFragmentManager().findFragmentByTag(DashboardFragment.TAG);

        if (gameFragment == null) {
            gameFragment = GameFragment.createFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, gameFragment, GameFragment.TAG)
                    .commit();
        }
    }
}
