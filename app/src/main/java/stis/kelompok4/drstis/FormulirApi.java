package stis.kelompok4.drstis;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FormulirApi {
    @FormUrlEncoded
    @POST("formulir")
    Call<FormulirResponse> sendFormulir(
            @Field("tanggal") String tanggal,
            @Field("nama") String nama,
            @Field("jam") String jam,
            @Field("keluhan") String keluhan
    );
}
