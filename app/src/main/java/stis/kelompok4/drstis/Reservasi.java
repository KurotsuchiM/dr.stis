package stis.kelompok4.drstis;

public class Reservasi {
    private String statusSelesai, statusDisetujui, tanggal, jam, keluhan, dokterName;

    public Reservasi(String statusSelesai, String statusDisetujui, String tanggal, String jam, String keluhan, String dokterName) {
        this.statusSelesai = statusSelesai;
        this.statusDisetujui = statusDisetujui;
        this.tanggal = tanggal;
        this.jam = jam;
        this.keluhan = keluhan;
        this.dokterName = dokterName;
    }

    public String getStatusSelesai() {
        return statusSelesai;
    }

    public String getStatusDisetujui() {
        return statusDisetujui;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getJam() {
        return jam;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public String getDokterName() {
        return dokterName;
    }
}
