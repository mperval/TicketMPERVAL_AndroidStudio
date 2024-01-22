package service;

import models.NamedAPIResourceList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GoldenRaceApiService {
    /**
     * Obtiene una lista de tickets.
     *
     * @return Un objeto {@code Call} que representa la solicitud API para obtener información de tickets.
     */
    @GET("tickets")
    Call<NamedAPIResourceList> getTicket();
    /**
     * Obtiene detalles de un ticket.
     *
     *  @return Un objeto {@code Call} que representa la solicitud API para obtener detalles de un ticket.
     */
    @GET("detailsTicket")
    Call<NamedAPIResourceList> getDetailsTicket();
    /**
     * Obtiene la información de perfil relacionada con los tickets.
     *
     * @return Un objeto {@code Call} que representa la solicitud API para obtener información de perfil de tickets.
     */
    @GET("profile")
    Call<NamedAPIResourceList> getProfileTicket();
}
