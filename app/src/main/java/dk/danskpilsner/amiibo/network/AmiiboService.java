package dk.danskpilsner.amiibo.network;

import dk.danskpilsner.amiibo.models.AmiiboList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AmiiboService {

    /**
     * Get all amiibos based on their game series
     * @param gameSeries can be ID or name, e.g. "The Legend of Zelda"
     * @return list of amiibo
     */
    @GET("amiibo/")
    Call<AmiiboList> getAmiibosFromSeries(@Query("gameseries") String gameSeries);
}
