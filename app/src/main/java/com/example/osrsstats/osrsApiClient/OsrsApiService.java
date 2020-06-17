package com.example.osrsstats.osrsApiClient;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.osrsstats.ComponentUpdateCallback;
import com.example.osrsstats.enums.PlayerMode;
import com.example.osrsstats.utils.IntegerExtractorHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OsrsApiService implements OsrsApiConfiguration {

    private Logger logger = Logger.getLogger(OsrsApiService.class.getName());

    private OsrsAPIHiscorePlayerInterface service;
    private Call<ResponseBody> retrofitCall;
    private Response<ResponseBody> response;

    // interface, diky kteremu se dostanu do aktivity a updatuju text
    private ComponentUpdateCallback componentUpdateCallback;


    public OsrsApiService(ComponentUpdateCallback componentUpdateCallback, String mode) {
        this.componentUpdateCallback = componentUpdateCallback;

        //basic retrofit configuration
        buildRetrofit(mode);
    }

    @SuppressLint("StaticFieldLeak") //temp solution
    public void getHiscoreByPlayerName(String name) {
        new AsyncTask<String, Void, ResponseBody>() {
            @Override
            protected ResponseBody doInBackground(String... strings) {
                logger.info("Calling service to retrieve Hiscore data has started");
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
                try {
                    logger.info("Calling service to retrieve Hiscore data has finished");
                    String hiscoreInString = response.body() != null ? response.body().string() : ""; // to avoid NPE
                    List<Integer> hiscores = IntegerExtractorHelper.extractInt(hiscoreInString);
                    if (componentUpdateCallback != null) {
                        logger.info("Updating Hiscore data");
                        componentUpdateCallback.updateHiscoreData(hiscores);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                super.onPostExecute(responseBody);
            }
        }.execute(name);

    }

    private void buildRetrofit(String mode) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        String baseUrl = getBaseUrl(mode);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(OsrsAPIHiscorePlayerInterface.class);
    }

    private String getBaseUrl(String mode) {
        if (mode.equals(PlayerMode.IRONMAN.name())) {
            return API_BASE_PLAYER_HISCORE_IRON_URL;
        } else if (mode.equals(PlayerMode.BASIC.name())) {
            return API_BASE_PLAYER_HISCORE_URL;
        }
        return API_BASE_PLAYER_HISCORE_URL;
    }

}
