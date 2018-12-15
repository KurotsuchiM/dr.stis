package stis.kelompok4.drstis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormulirResponse {

    @SerializedName("pesan")
    @Expose
    private String pesan;

    @SerializedName("data")
    @Expose
    private Boolean data;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public Boolean getData() {
        return data;
    }

    public void setData(Boolean data) {
        this.data = data;
    }
}
