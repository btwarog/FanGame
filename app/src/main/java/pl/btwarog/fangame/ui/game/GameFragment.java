package pl.btwarog.fangame.ui.game;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import pl.btwarog.fangame.R;
import pl.btwarog.fangame.base.BaseApplication;
import pl.btwarog.fangame.base.BaseFragment;
import pl.btwarog.fangame.database.model.LocalPlayer;

/**
 * Created by bartlomiejtwarog on 15.05.2017.
 */

public class GameFragment extends BaseFragment<GameFragment> implements GameContract.View {
    public static String TAG = GameFragment.class.getSimpleName();

    @BindView(R.id.players_wrapper)
    LinearLayout playersWrapper;

    @BindView(R.id.player_one)
    LinearLayout playerOne;

    @BindView(R.id.profile_one_image)
    ImageView profileOneImage;

    @BindView(R.id.player_one_name)
    TextView playerOneName;

    @BindView(R.id.player_two)
    LinearLayout playerTwo;

    @BindView(R.id.profile_two_image)
    ImageView profileTwoImage;

    @BindView(R.id.player_two_name)
    TextView playerTwoName;

    @BindView(R.id.progress_layout)
    LinearLayout progressLayout;

    @Inject
    GamePresenter gamePresenter;

    @State
    ViewState viewState;

    @State
    String firstPlayerPoints;

    @State
    String secondPlayerPoints;

    @State
    int correctGuesses = 0;

    @State
    int wrongGuesses = 0;

    public enum ViewState {
        GAME,
        GAME_RESULT
    }

    public static GameFragment createFragment() {
        GameFragment gameFragment = new GameFragment();
        gameFragment.viewState = ViewState.GAME;
        gameFragment.correctGuesses = 0;
        gameFragment.wrongGuesses = 0;
        return gameFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDependencyInjector(this);
        gamePresenter.attachView(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game;
    }

    @Override
    protected void initializeDependencyInjector(GameFragment dashboardFragment) {
        ((BaseApplication) getActivity().getApplication()).getApplictaionComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (savedInstanceState == null) {
            gamePresenter.fetchData();
        }
    }

    @Override
    public void finish() {
        getActivity().finish();
    }

    @Override
    public void setPlayers(LocalPlayer localPlayer, LocalPlayer localPlayer2) {
        hideProgress();
        setFirstPlayerUI(localPlayer);
        setSecondPlayerUI(localPlayer2);
    }

    private void setFirstPlayerUI(LocalPlayer localPlayer) {
        playerOne.setVisibility(View.VISIBLE);
        playerOneName.setText(getString(R.string.full_name, localPlayer.getFirstName(), localPlayer.getLastName()));
        firstPlayerPoints = localPlayer.getPoints();
        Glide.with(getActivity())
                .load(localPlayer.getImageUrl())
                .centerCrop()
                .crossFade()
                .into(profileOneImage);
    }

    private void setSecondPlayerUI(LocalPlayer localPlayer) {
        playerTwo.setVisibility(View.VISIBLE);
        playerTwoName.setText(getString(R.string.full_name, localPlayer.getFirstName(), localPlayer.getLastName()));
        secondPlayerPoints = localPlayer.getPoints();
        Glide.with(getActivity())
                .load(localPlayer.getImageUrl())
                .centerCrop()
                .crossFade()
                .into(profileTwoImage);

    }

    @OnClick(R.id.player_one)
    void onPlayerOneClicked() {
        if (comparePlayersResults(firstPlayerPoints, secondPlayerPoints)) {
            correctGuesses++;
            playerTwo.setVisibility(View.GONE);
            Toast.makeText(getActivity(), getString(R.string.correct_guess), Toast.LENGTH_SHORT).show();
        } else {
            wrongGuesses++;
            playerOne.setVisibility(View.GONE);
            Toast.makeText(getActivity(), getString(R.string.wrong_guess), Toast.LENGTH_SHORT).show();
        }
        determineNextUI();
    }

    @OnClick(R.id.player_two)
    void onPlayerTwoClicked() {
        if (comparePlayersResults(secondPlayerPoints, firstPlayerPoints)) {
            correctGuesses++;
            playerOne.setVisibility(View.GONE);
            Toast.makeText(getActivity(), getString(R.string.correct_guess), Toast.LENGTH_SHORT).show();
        } else {
            wrongGuesses++;
            playerTwo.setVisibility(View.GONE);
            Toast.makeText(getActivity(), getString(R.string.wrong_guess), Toast.LENGTH_SHORT).show();
        }
        determineNextUI();
    }

    private boolean comparePlayersResults(String pointsOneString, String pointsTwoString) {
        BigDecimal pointsOne = new BigDecimal(pointsOneString);
        BigDecimal pointsTwo = new BigDecimal(pointsTwoString);
        //if both players points are equal then user selected correct value - my asumption

        return pointsOne.compareTo(pointsTwo) >= 1;
    }

    private void determineNextUI() {
        if (correctGuesses < 10) {
            new AlertDialog.Builder(getContext())
                    .setTitle(getString(R.string.dialog_info))
                    .setMessage(getString(R.string.dialog_info_message, correctGuesses, wrongGuesses))
                    .setPositiveButton(getString(R.string.ok), infoDialogOnClickListener)
                    .show();

        } else {
            viewState = ViewState.GAME_RESULT;
            float result = (float) correctGuesses / ((float) correctGuesses + (float) wrongGuesses);
            new AlertDialog.Builder(getContext())
                    .setTitle(getString(R.string.dialog_game_end))
                    .setMessage(getString(R.string.dialog_game_end_message,new DecimalFormat("#.##").format(result)))
                    .setPositiveButton(getString(R.string.ok), endDialogOnClickListener)
                    .show();
        }
    }

    @Override
    public void showProgress() {
        playerOne.setVisibility(View.GONE);
        playerTwo.setVisibility(View.GONE);
        progressLayout.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressLayout.setVisibility(View.GONE);
    }

    AlertDialog.OnClickListener infoDialogOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            gamePresenter.drawPlayers();
        }
    };

    AlertDialog.OnClickListener endDialogOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            getActivity().finish();
        }
    };
}
