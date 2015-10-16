package com.heineck.myhttprequestsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.heineck.myhttprequestsample.MyRest.MyRestTask;
import com.heineck.myhttprequestsample.MyRest.OnRestTaskListener;

public class MainActivity extends AppCompatActivity {

    TextView lblResponseCode = null;
    TextView lblResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblResponseCode = (TextView)findViewById(R.id.lblResponseCode);
        lblResult = (TextView)findViewById(R.id.lblResult);

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

        rest.execute("https://maps.googleapis.com/maps/api/geocode/json?address=Winnetka", "");

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
}
