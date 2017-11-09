package br.edu.ladoss.simpifladoss.network;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ConnectionServer {

    private static final String URL_BASE = "http://ingressos.ifpb.edu.br:8443/attendize/api/"; //http://10.1.134.157:5000/attendize/api/
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