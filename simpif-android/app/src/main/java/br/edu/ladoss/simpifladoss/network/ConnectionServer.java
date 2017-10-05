package br.edu.ladoss.simpifladoss.network;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ConnectionServer {

    private static final String URL_BASE = "http://10.1.134.157:5000/api/";
    private static APIService service;
    private static ConnectionServer ourInstance = new ConnectionServer();

    public static ConnectionServer getInstance() {
        return ourInstance;
    }

    public APIService getService() {
        return service;
    }

    private ConnectionServer() {
        updateServiceAdress();
    }

    public void updateServiceAdress() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(APIService.class);
    }
}