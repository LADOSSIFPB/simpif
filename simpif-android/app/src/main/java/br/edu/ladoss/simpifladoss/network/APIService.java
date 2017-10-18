package br.edu.ladoss.simpifladoss.network;

import java.util.List;

import br.edu.ladoss.simpifladoss.models.Attendee;
import br.edu.ladoss.simpifladoss.models.Order;
import br.edu.ladoss.simpifladoss.models.User;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

public interface APIService {

    @GET("checkin/attendees/{codigo}")
    Call<Void> checkin(@Header("Authorization") String accessKey, @Path("codigo") String codigo);

    @GET("checkin/orders")
    Call<List<Order>> orders(@Header("Authorization") String accessKey);

    @GET("checkin/orders/references/{orderReference}")
    Call<List<Attendee>> order(@Header("Authorization") String accessKey, @Path("orderReference") String orderRef);

    @POST("checkin/login")
    Call<String> login(@Body User user);

}