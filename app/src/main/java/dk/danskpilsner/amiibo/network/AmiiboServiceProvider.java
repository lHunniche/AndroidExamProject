package dk.danskpilsner.amiibo.network;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static dk.danskpilsner.amiibo.config.AmiiboApiConfig.BASE_URL;

public class AmiiboServiceProvider {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    private static AmiiboService service = retrofit.create(AmiiboService.class);

    public static AmiiboService getService() {
        return service;
    }
}
