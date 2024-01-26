package service;

import java.util.List;

import models.DetailsTicket;
import models.Ticket;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GoldenRaceApiService {
    /**
     * Obtiene una lista de tickets.
     *
     * @return Un objeto {@code Call} que representa la solicitud API para obtener información de tickets.
     */
    @GET("api/tickets")
    Call<List<Ticket>> getTicket();
    /**
     * Obtiene los detalles de un ticket específico.
     *
     * @param id El identificador único del ticket.
     * @return Una llamada asíncrona {@code Call} que devolverá los detalles del ticket.
     */
    @GET("/tickets/{id}/detailsTickets")
    Call<List<DetailsTicket>> getDetailsTicket(@Path("id") String id);
}
