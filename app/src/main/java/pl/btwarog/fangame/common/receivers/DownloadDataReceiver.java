package pl.btwarog.fangame.common.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import pl.btwarog.fangame.common.Constants;
import pl.btwarog.fangame.common.listeners.OnBroadcastRecieveListener;

import static pl.btwarog.fangame.common.Constants.EXTENDED_DATA_STATUS;

/**
 * Created by bartlomiejtwarog on 14.05.2017.
 */

public class DownloadDataReceiver extends BroadcastReceiver {

    private OnBroadcastRecieveListener onBroadcastRecieveListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (onBroadcastRecieveListener != null && intent.hasExtra(EXTENDED_DATA_STATUS)) {
            Constants.Status status = (Constants.Status) intent.getSerializableExtra(EXTENDED_DATA_STATUS);
            onBroadcastRecieveListener.onBroadcastReceived(status);
        }
    }

    public void setListener(OnBroadcastRecieveListener onBroadcastRecieveListener) {
        this.onBroadcastRecieveListener = onBroadcastRecieveListener;
    }
}
