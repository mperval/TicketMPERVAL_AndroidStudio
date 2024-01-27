package service;

import java.util.List;

import models.Details;
import models.Ticket;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GoldenRaceApiService {
    /**
     * Obtiene una lista de tickets.
     *
     * @return Un objeto {@code Call} que representa la solicitud API para obtener información de tickets.
     */
    @GET("api/tickets")
    Call<List<Ticket>> getTicket();
    /**
     * Obtiene una lista de detalles de un ticket en específico.
     *
     * @param id El identificador único del ticket.
     * @return Una llamada asíncrona {@code Call} que devolverá los detalles del ticket.
     */
    @GET("tickets/{id}/detailTickets")
    Call<List<Details>> getDetailsTicket(@Path("id") String id);
}
