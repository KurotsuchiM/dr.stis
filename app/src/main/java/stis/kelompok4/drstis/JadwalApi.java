package stis.kelompok4.drstis;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JadwalApi {

    @GET("Reservasi_buat/list_tanggal")
    Call<List<JadwalResponse>> getJadwal();
}
