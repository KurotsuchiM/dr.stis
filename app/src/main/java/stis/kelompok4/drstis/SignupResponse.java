package stis.kelompok4.drstis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupResponse {

    @SerializedName("status_akun")
    @Expose
    private Boolean statusAkun;

    @SerializedName("pesan")
    @Expose
    private String pesan;

    @SerializedName("data")
    @Expose
    private Boolean data;

    public Boolean getStatusAkun() {
        return statusAkun;
    }

    public void setStatusAkun(Boolean statusAkun) {
        this.statusAkun = statusAkun;
    }

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
