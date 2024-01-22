package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NamedAPIResourceList {
    @SerializedName("tickets")
    @Expose
    private List<Ticket> tickets;
    @SerializedName("detailsTicket")
    @Expose
    private String detailsTicket;
    @SerializedName("profile")
    @Expose
    private String profile;

    public NamedAPIResourceList() {
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
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
