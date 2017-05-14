package pl.btwarog.fangame.ui.intro;

import android.os.Bundle;

import pl.btwarog.fangame.R;
import pl.btwarog.fangame.base.BaseActivity;


/**
 * Created by bartlomiejtwarog on 09.05.2017.
 */

public class IntroActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_container;
    }

    @Override
    protected void setUserInterface(Bundle savedInstanceState) {
        super.setUserInterface(savedInstanceState);

        IntroFragment introFragment = (IntroFragment) getSupportFragmentManager().findFragmentByTag(IntroFragment.TAG);

        if (introFragment == null) {
            introFragment = IntroFragment.createFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, introFragment, IntroFragment.TAG)
                    .commit();
        }

    }
}
