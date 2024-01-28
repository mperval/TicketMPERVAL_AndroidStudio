package service;

import java.util.List;

import models.Details;
import models.Ticket;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
    @GET("api2/detailTickets/{id}")
    Call<List<Details>> getDetailsTicket(@Path("id")Integer id);

    /**
     * Publica un nuevo ticket en el servidor.
     *
     * @param ticket El objeto Ticket que se enviará en el cuerpo de la solicitud, debe ir con el ID a 0.
     * @return Una llamada de Retrofit que contiene el Ticket creado, con el ID autoasignado.
     */
    @POST("api/ticket/")
    Call<Ticket> postTicket(@Body Ticket ticket);

    /**
     * Actualiza un ticket existente en el servidor.
     *
     * @param ticketId El ID del ticket que se actualizará.
     * @param ticket   El objeto Ticket actualizado que se enviará en el cuerpo de la solicitud.
     * @return Una llamada de Retrofit que contiene el Ticket actualizado.
     */
    @PUT("api/ticket/{id}")
    Call<Ticket> putTicket(@Path("id") int ticketId, @Body Ticket ticket);

    /**
     * Elimina un ticket del servidor.
     *
     * @param ticketId El ID del ticket que se eliminará.
     * @return Una llamada de Retrofit sin cuerpo de respuesta, indicando éxito o fallo.
     */
    @DELETE("api/ticket/{id}")
    Call<Void> deleteTicket(@Path("id") int ticketId);


}
