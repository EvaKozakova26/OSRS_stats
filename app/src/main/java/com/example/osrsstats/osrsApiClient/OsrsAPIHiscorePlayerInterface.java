package com.example.osrsstats.osrsApiClient;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OsrsAPIHiscorePlayerInterface {

    /**
     * return hiscore by player name
     * @param name name of a player
     * @return hiscore
     */
    @GET("index_lite.ws")
    Call<ResponseBody> getHiscoreByPlayerName(@Query("player") String name); //TODO misto ResponseBody vracet uz objekt (idealne HiScore)
}
