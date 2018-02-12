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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    // Arrays that contaim de information in the news feed.
    String[] feedFotoPerfil;
    String[] feedNome;
    String[] feedObjetivo;
    String[] feedPostagemImagem;
    String[] feedDataRefeicao;
    String[] feedCalorias;

    ListView newsFeed;
    String jsonData;

    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://api.tecnonutri.com.br/api/v4/feed";
        JSONArray jsonArray;

        newsFeed = (ListView) findViewById(R.id.newsFeed);
        connection = new Connection();
        connection.setUrl(url);

        try {
            jsonData = connection.getJSONData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// Class to make connection with the database in the url
// This class has the potential to return a JSONArray
class Connection{
    String url;
    String jsonData;

    public String getJSONData() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<String> future = executor.submit(new Callable<String>(){
            @Override
            public String call(){
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    Response responses = client.newCall(request).execute();
                    String jsonData = responses.body().string().toString();

                    return jsonData;

                    /*JSONArray jsonArray = jsonObject[0].getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                    }*/
                } catch (Exception e) {
                    Log.d("debugPrint","Cant get string");
                    return "";
                }
            }
        });
        jsonData = future.get();
        future.isDone();
        return jsonData;
    }

    public void setUrl(String url){
        this.url = url;
    }
}
