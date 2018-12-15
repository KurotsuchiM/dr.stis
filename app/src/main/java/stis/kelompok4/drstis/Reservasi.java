package stis.kelompok4.drstis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reservasi {

    @SerializedName("pengunjung_nama")
    @Expose
    private String pengunjungNama;

    @SerializedName("pengunjung_nim")
    @Expose
    private String pengunjungNim;

    @SerializedName("dokter_nip")
    @Expose
    private String dokterNip;

    @SerializedName("dokter_nama")
    @Expose
    private String dokterNama;

    @SerializedName("reservasi_tanggal")
    @Expose
    private String reservasiTanggal;

    @SerializedName("reservasi_jam")
    @Expose
    private String reservasiJam;

    @SerializedName("keluhan")
    @Expose
    private String keluhan;


    public Reservasi(String pengunjungNama, String pengunjungNim, String dokterNip, String dokterNama, String reservasiTanggal, String reservasiJam, String keluhan) {
        this.pengunjungNama = pengunjungNama;
        this.pengunjungNim = pengunjungNim;
        this.dokterNip = dokterNip;
        this.dokterNama = dokterNama;
        this.reservasiTanggal = reservasiTanggal;
        this.reservasiJam = reservasiJam;
        this.keluhan = keluhan;
    }

    public String getPengunjungNama() {
        return pengunjungNama;
    }

    public void setPengunjungNama(String pengunjungNama) {
        this.pengunjungNama = pengunjungNama;
    }

    public String getPengunjungNim() {
        return pengunjungNim;
    }

    public void setPengunjungNim(String pengunjungNim) {
        this.pengunjungNim = pengunjungNim;
    }

    public String getDokterNip() {
        return dokterNip;
    }

    public void setDokterNip(String dokterNip) {
        this.dokterNip = dokterNip;
    }

    public String getDokterNama() {
        return dokterNama;
    }

    public void setDokterNama(String dokterNama) {
        this.dokterNama = dokterNama;
    }

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

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }
}
