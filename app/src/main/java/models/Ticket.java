package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Ticket implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("creationDate")
    @Expose
    private String dataTime;
    @SerializedName("totalAmount")
    @Expose
    private String total;
    @SerializedName("version")
    @Expose
    private String version;

    private List<Details> detailsList;

    public List<Details> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<Details> detailsList) {
        this.detailsList = detailsList;
    }

    public Ticket() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
                "id=" + id +
                ", dataTime='" + dataTime + '\'' +
                ", total='" + total + '\'' +
                ", version='" + version + '\'' +
                ", detailsList=" + detailsList +
                '}';
    }
}
