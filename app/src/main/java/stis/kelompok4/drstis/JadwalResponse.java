package stis.kelompok4.drstis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JadwalResponse {

    @SerializedName("reservasi_tanggal")
    @Expose
    private String reservasiTanggal;

    @SerializedName("reservasi_jam")
    @Expose
    private String reservasiJam;

    public String getReservasiTanggal() {
        return reservasiTanggal;
    }

    public void setReservasiTanggal(String reservasiTanggal) {
        this.reservasiTanggal = reservasiTanggal;
    }

    public String getReservasiJam() {
        return reservasiJam;
    }

    public void setReservasiJam(String reservasiJam) {
        this.reservasiJam = reservasiJam;
    }
}
