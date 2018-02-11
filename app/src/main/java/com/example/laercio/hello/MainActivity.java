package com.example.laercio.hello;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    ListView newsFeed;
    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String url = "http://api.tecnonutri.com.br/api/v4/feed";

        try {
            final OkHttpClient client = new OkHttpClient();

            final Request request = new Request.Builder()
                    .url(url)
                    .build();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Response responses = client.newCall(request).execute();
                        String jsonData = responses.body().string().toString();
                        JSONObject Jobject = new JSONObject(jsonData);
                        JSONArray Jarray = Jobject.getJSONArray("items");
                        Log.d("printHEHE", (Jarray.length() + ""));
                        for (int i = 0; i < Jarray.length(); i++) {
                            JSONObject object = Jarray.getJSONObject(i);
                            Log.d("printHEHE", object.get("profile").toString());
                        }
                    } catch (Exception e) {
                        Log.d("printHEHE","Nao deu nao");
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            Log.d("HelloWorld", "deu nao 2");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
