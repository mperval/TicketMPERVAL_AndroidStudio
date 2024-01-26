package models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DetailsTicket implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
}
