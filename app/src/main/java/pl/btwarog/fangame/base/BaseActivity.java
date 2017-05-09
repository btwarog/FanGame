package pl.btwarog.fangame.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.ButterKnife;
import pl.btwarog.fangame.common.listeners.OnBackPressedListener;

/**
 * Created by bartlomiejtwarog on 09.05.2017.
 */
public abstract class BaseActivity extends AppCompatActivity {

    OnBackPressedListener mOnBackPressedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUserInterface();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void setUserInterface() {
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    @LayoutRes
    protected abstract int getLayoutId();

    public void setToolbar(@NonNull Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        mOnBackPressedListener = onBackPressedListener;
    }

    @Override
    public void onBackPressed() {
        if (mOnBackPressedListener != null) {
            if (!mOnBackPressedListener.onBackPressed()) {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}