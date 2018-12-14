package stis.kelompok4.drstis;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Pengunjung {

    @SerializedName("pengunjung_nama")
    private String namaPengunjung;

    @SerializedName("pengunjung_nim")
    private String nimPengunjung;

    private String status;

    private String password;

    @SerializedName("konfirmasi_password")
    private String konfirmasiPassword;

    private String email;

    @SerializedName("nomor_telpon")
    private String nomorTelpon;

    @SerializedName("jenis_kelamin")
    private String jenisKelamin;

//    @SerializedName("tanggal_buat")
//    private Date tanggalBuat;
//
//    private Date modifikasi;

    @SerializedName("status_akun")
    private int statusAkun;

    private Date loginTerakhir;

    private Date loginPertama;


//    public Pengunjung(String namaPengunjung, String nimPengunjung, String status, String password, String konfirmasiPassword, String email, String nomorTelpon, String jenisKelamin, Date tanggalBuat, Date modifikasi, int statusAkun, Date loginTerakhir, Date loginPertama) {
//        this.namaPengunjung = namaPengunjung;
//        this.nimPengunjung = nimPengunjung;
//        this.status = status;
//        this.password = password;
//        this.konfirmasiPassword = konfirmasiPassword;
//        this.email = email;
//        this.nomorTelpon = nomorTelpon;
//        this.jenisKelamin = jenisKelamin;
//        this.tanggalBuat = tanggalBuat;
//        this.modifikasi = modifikasi;
//        this.statusAkun = statusAkun;
//        this.loginTerakhir = loginTerakhir;
//        this.loginPertama = loginPertama;
//    }

    public Date getLoginTerakhir() {
        return loginTerakhir;
    }

    public Date getLoginPertama() {
        return loginPertama;
    }

    public String getNamaPengunjung() {
        return namaPengunjung;
    }

    public String getNimPengunjung() {
        return nimPengunjung;
    }

    public String getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public String getKonfirmasiPassword() {
        return konfirmasiPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getNomorTelpon() {
        return nomorTelpon;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

//    public Date getTanggalBuat() {
//        return tanggalBuat;
//    }
//
//    public Date getModifikasi() {
//        return modifikasi;
//    }

    public int getStatusAkun() {
        return statusAkun;
    }
}
