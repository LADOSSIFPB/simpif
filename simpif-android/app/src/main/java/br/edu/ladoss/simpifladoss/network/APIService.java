package br.edu.ladoss.simpifladoss.network;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface APIService {

    @GET("checkin/attendees/{codigo}")
    Call<Void> checkin(@Path("codigo") String codigo);

}