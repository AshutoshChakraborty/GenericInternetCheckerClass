package ap.com.example.admin.genericinternetcheckerclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import ap.com.example.admin.genericinternetcheckerclass.internetchecker.InternetConnectionChecker;
import ap.com.example.admin.genericinternetcheckerclass.internetchecker.InternetConnectionStatusListener;

public class MainActivity extends AppCompatActivity {

    private InternetConnectionChecker internetConnectionChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        internetConnectionChecker = new InternetConnectionChecker(this, new InternetConnectionStatusListener() {
            @Override
            public void onIsConnected() {
                Toast.makeText(MainActivity.this, "Is connected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onIsNotConnected() {
                Toast.makeText(MainActivity.this, "Is not connected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onConnectionChanged(boolean isConnected) {
                if (isConnected) {
                    Toast.makeText(MainActivity.this, "Is connected", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Is not connected", Toast.LENGTH_SHORT).show();

                }

            }
        }).checkConnection();
    }

    @Override
    protected void onPause(){
        internetConnectionChecker.UnregisterReciver();
        super.onPause();
    }
}
