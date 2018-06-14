package ap.com.example.admin.genericinternetcheckerclass.internetchecker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetConnectionChecker extends BroadcastReceiver {
    public static InternetConnectionStatusListener statusListener;
    private Context context;

    public InternetConnectionChecker() {
        super();
    }

    public InternetConnectionChecker(Context context, InternetConnectionStatusListener statusListener) {
        this.statusListener = statusListener;
        this.context = context;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(this, intentFilter);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        if (statusListener != null) {
            statusListener.onConnectionChanged(isConnected);
        }
    }


    public InternetConnectionChecker checkConnection() {
        boolean isConnected = InternetConnectionChecker.isConnected(context);
        if (isConnected) {
            statusListener.onIsConnected();
        } else {
            statusListener.onIsNotConnected();
        }
        return this;
    }


    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }

    //must call this method from activity.onDestroy()
    public void UnregisterReciver() {
        try {
            context.unregisterReceiver(this);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }


}
