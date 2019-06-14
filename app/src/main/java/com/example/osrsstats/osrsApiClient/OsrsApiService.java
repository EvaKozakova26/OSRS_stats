package com.example.osrsstats.osrsApiClient;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OsrsApiService implements OsrsApiConfiguration {

    private OsrsAPIHiscorePlayerInterface service;
    private Call<ResponseBody> retrofitCall;
    private Response<ResponseBody> response;

    public OsrsApiService() {
        //basic retrofit configuration
        buildRetrofit();
    }

    @SuppressLint("StaticFieldLeak") //temp solution
    public void getHiscoreByPlayerName(String name) {
        new AsyncTask<String, Void, ResponseBody>() {
            @Override
            protected ResponseBody doInBackground(String... strings) {
                retrofitCall = service.getHiscoreByPlayerName(strings[0]);
                try {
                    response =  retrofitCall.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(ResponseBody responseBody) {
                //TODO volat (setovat) proměnnou
                try {
                    String hiscore = response.body() != null ? response.body().string() : null; // to avoid NPE
                    System.out.println(hiscore);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                super.onPostExecute(responseBody);
            }
        }.execute(name);

    }

    private void buildRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_PLAYER_HISCORE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(OsrsAPIHiscorePlayerInterface.class);
    }

}
