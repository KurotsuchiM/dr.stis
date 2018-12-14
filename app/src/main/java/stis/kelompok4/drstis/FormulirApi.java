package stis.kelompok4.drstis;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FormulirApi {
    @FormUrlEncoded
    @POST("Reservasi_buat/reservasi/{reservasi_nim}")
    Call<FormulirResponse> sendFormulir(
            @Field("reservasi_tanggal") String tanggalReservasi,
            @Field("reservasi_jam") String jamReservasi,
            @Field("keluhan") String keluhan
    );
}
