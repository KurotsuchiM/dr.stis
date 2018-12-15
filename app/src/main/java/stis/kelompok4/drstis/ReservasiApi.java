package stis.kelompok4.drstis;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ReservasiApi {

    @GET("reservasi_buat/reservasi/{nimReservasi}")
    Call<List<Reservasi>> getReservasi(
            @Path("nimReservasi") String nimResrevasi
    );
}
