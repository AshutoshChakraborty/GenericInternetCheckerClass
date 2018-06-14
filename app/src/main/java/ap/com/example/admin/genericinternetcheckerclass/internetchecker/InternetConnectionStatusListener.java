package ap.com.example.admin.genericinternetcheckerclass.internetchecker;

public interface InternetConnectionStatusListener {
    void onIsConnected();

    void onIsNotConnected();

    void onConnectionChanged(boolean isConnected);

}
