package br.edu.ladoss.simpifladoss.network;

import java.util.List;

import br.edu.ladoss.simpifladoss.models.Apresentacao;
import br.edu.ladoss.simpifladoss.models.Attendee;
import br.edu.ladoss.simpifladoss.models.Cronograma;
import br.edu.ladoss.simpifladoss.models.Evento;
import br.edu.ladoss.simpifladoss.models.Order;
import br.edu.ladoss.simpifladoss.models.User;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

public interface APIService {

    @GET("attendize/api/checkin/attendees/{codigo}")
    Call<String> checkin(@Header("Authorization") String accessKey, @Path("codigo") String codigo);

    @GET("attendize/api/checkin/orders")
    Call<List<Order>> orders(@Header("Authorization") String accessKey);

    @GET("attendize/api/checkin/orders/references/{orderReference}")
    Call<List<Attendee>> order(@Header("Authorization") String accessKey, @Path("orderReference") String orderRef);

    @POST("attendize/api/checkin/login")
    Call<String> login(@Body User user);

    @GET("checkin/api/eventos")
    Call<List<Evento>> eventos();

    @GET("checkin/api/eventos/{id}/apresentacoes")
    Call<List<Apresentacao>> apresentacoes(@Path("id") int idEvento);
}