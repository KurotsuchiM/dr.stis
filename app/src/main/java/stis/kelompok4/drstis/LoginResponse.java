package stis.kelompok4.drstis;

public class LoginResponse {
    private boolean status_akun;
    private String pesan;
    private Pengunjung data;

    public LoginResponse(boolean status_akun, String pesan, Pengunjung data) {
        this.status_akun = status_akun;
        this.pesan = pesan;
        this.data = data;
    }

    public boolean isStatus_akun() {
        return status_akun;
    }

    public String getPesan() {
        return pesan;
    }

    public Pengunjung getData() {
        return data;
    }
}
