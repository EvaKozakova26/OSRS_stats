package com.example.osrsstats.osrsApiClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OsrsAPIGrandExcahngeInterface {

    /**
     * return GE item details by given ID
     * @param itemId id of GE item
     * @return GE item
     */
    @GET("/api/catalogue/detail.json?item=itemId")
    Call<ResponseBody> getItemDetails(@Query("itemId") int itemId);

}
