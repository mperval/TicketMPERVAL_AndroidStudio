package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ticket implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("creationDate")
    @Expose
    private String dataTime;
    @SerializedName("totalAmount")
    @Expose
    private String total;

    public Ticket() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", dataTime='" + dataTime + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
