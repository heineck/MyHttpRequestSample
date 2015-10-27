package com.heineck.myhttprequestsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.heineck.myhttprequestsample.MyRest.MyRestResponse;
import com.heineck.myhttprequestsample.MyRest.MyRestTask;
import com.heineck.myhttprequestsample.MyRest.OnRestTaskListener;

public class MainActivity extends AppCompatActivity {

    TextView lblResponseCode = null;
    TextView lblResult = null;

    BroadcastReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblResponseCode = (TextView)findViewById(R.id.lblResponseCode);
        lblResult = (TextView)findViewById(R.id.lblResult);

        networkChangeReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // do stuff...

               getInternetConnectionState(context);

            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();



        registerReceiver(networkChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")); // register the receiver

    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(networkChangeReceiver); // unregister the receiver
    }

    public void onBtnRequestWithProgress(View v) {

        MyRestTask rest = new MyRestTask(this, new OnRestTaskListener() {
            @Override
            public void onPreExecute() {

                lblResponseCode.setText("response code:");
                lblResult.setText("");

            }

            @Override
            public void onSuccess(String result) {

                lblResponseCode.setText("response code: 200");
                lblResult.setText(result);

            }

            @Override
            public void onError(int responseCode, String message) {

                lblResponseCode.setText("response code: " + responseCode);
                lblResult.setText(message);

            }
        });
        rest.setProgressBarEnabled(true);
        rest.execute("https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452", "");


    }

    public void onBtnRequestWithoutProgress(View v) {

        MyRestTask rest = new MyRestTask(this);

        rest.setOnRestTaskListener(new OnRestTaskListener() {
            @Override
            public void onPreExecute() {

                lblResponseCode.setText("response code:");
                lblResult.setText("");

            }

            @Override
            public void onSuccess(String result) {

                lblResponseCode.setText("response code: 200");
                lblResult.setText(result);

            }

            @Override
            public void onError(int responseCode, String message) {

                lblResponseCode.setText("response code: " + responseCode);
                lblResult.setText(message);

            }
        });

        rest.execute("http://192.168.222.15/source/gospel/current-release/index.php/news_api/getAllCategories", "");

    }

    public void onBtnRequestWithError(View v) {

        MyRestTask rest = new MyRestTask(this, new OnRestTaskListener() {
            @Override
            public void onPreExecute() {

                lblResponseCode.setText("response code:");
                lblResult.setText("");

            }

            @Override
            public void onSuccess(String result) {

                lblResponseCode.setText("response code: 200");
                lblResult.setText(result);

            }

            @Override
            public void onError(int responseCode, String message) {

                lblResponseCode.setText("response code: " + responseCode);
                lblResult.setText(message);

            }
        });
        rest.execute("https://www.googleapis.com/discoverys/v1/apis", "");

    }

    private void getInternetConnectionState(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                Log.d("NET", "CONNECTED WI_FI");
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                Log.d("NET", "CONNECTED MOBILE");
            }
        } else {
            // not connected to the internet
            Log.d("NET", "NOT CONNECTED");
        }

    }
}
