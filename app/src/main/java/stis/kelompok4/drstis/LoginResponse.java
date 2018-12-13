package stis.kelompok4.drstis;

public class LoginResponse {
    private boolean status_akun;
    private String pesan;
    private Object data;

    public boolean isStatus_akun() {
        return status_akun;
    }

    public String getPesan() {
        return pesan;
    }

    public Object getData() {
        return data;
    }
}
