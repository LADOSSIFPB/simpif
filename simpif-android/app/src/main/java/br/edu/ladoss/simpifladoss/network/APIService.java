package br.edu.ladoss.simpifladoss.network;

import java.util.List;

import br.edu.ladoss.simpifladoss.models.Attendee;
import br.edu.ladoss.simpifladoss.models.Order;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface APIService {

    @GET("checkin/attendees/{codigo}")
    Call<Void> checkin(@Path("codigo") String codigo);

    @GET("checkin/orders")
    Call<List<Order>> orders();

    @GET("checkin/orders/references/{orderReference}")
    Call<List<Attendee>> order(@Path("orderReference") String orderRef);

}