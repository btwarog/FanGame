package pl.btwarog.fangame.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import icepick.Icepick;
import pl.btwarog.fangame.common.listeners.OnBackPressedListener;

/**
 * Created by bartlomiejtwarog on 09.05.2017.
 */

public abstract class BaseFragment extends Fragment implements OnBackPressedListener {

    ViewGroup mContentView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mContentView = (ViewGroup) inflater.inflate(getLayoutId(), container, false);

        ButterKnife.bind(this, mContentView);

        ((BaseActivity) getActivity()).setOnBackPressedListener(this);

        return mContentView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    public boolean onBackPressed() {
        return false;
    }

    public void setActionBarTitle(String title) {
        if (((BaseActivity) getActivity()).getSupportActionBar() != null) {
            ((BaseActivity) getActivity()).getSupportActionBar().setTitle(title);
        }
    }
}