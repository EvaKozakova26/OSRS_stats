package com.example.osrsstats.osrsApiClient;

import com.example.osrsstats.model.grandExchange.Item;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OsrsAPIInterface {

    /**
     * return GE item details by given ID
     * @param itemId id of GE item
     * @return GE item
     */
    @GET("/api/catalogue/detail.json?item=itemId")
    Call<Item> getItemDetails(@Query("itemId") int itemId);

}
