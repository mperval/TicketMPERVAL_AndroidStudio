package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NamedAPIResourceList implements Serializable {
    @SerializedName("tickets")
    @Expose
    private Ticket tickets;
    @SerializedName("detailsTicket")
    @Expose
    private String detailsTicket;
    @SerializedName("profile")
    @Expose
    private String profile;

    public NamedAPIResourceList() {
    }

    public Ticket getTickets() {
        return tickets;
    }

    public void setTickets(Ticket tickets) {
        this.tickets = tickets;
    }

    public String getDetailsTicket() {
        return detailsTicket;
    }

    public void setDetailsTicket(String detailsTicket) {
        this.detailsTicket = detailsTicket;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "NamedAPIResourceList{" +
                "tickets=" + tickets +
                ", detailsTicket='" + detailsTicket + '\'' +
                ", profile='" + profile + '\'' +
                '}';
    }
}
