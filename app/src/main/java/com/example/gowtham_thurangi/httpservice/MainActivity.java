package com.example.gowtham_thurangi.httpservice;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        callHttpService();
                    }
                });

            }
        });
    }

    public void callNotification(final View view) {
        Intent notificationIntent = new Intent(this,NotificationActivity.class);
        startActivity(notificationIntent);
    }

    void callHttpService() {

        try {
            String url = "http://10.71.8.219:8080/araksha/register/add";
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            Log.d("permission", ":");
            conn.getPermission();
            //conn.connect();

            String paramsString = "{\"empId\":\"e102\",\"empName\":\"gowtham1\",\"mobileNumber\":123456789,\"parkType\":\"floater\",\"isActive\":true,\"dateRegistered\":\"01/08/2018\"}";

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(paramsString);
            wr.flush();
            wr.close();
            int responsecode = conn.getResponseCode();
            Log.d("data posted :", "responsecode:" + responsecode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
