package models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Details implements Serializable {
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ticketId")
    @Expose
    private Integer ticketId;
    public Details() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Details{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                ", id=" + id +
                ", ticketId=" + ticketId +
                '}';
    }
}
