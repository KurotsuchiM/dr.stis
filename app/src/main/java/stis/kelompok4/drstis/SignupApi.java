package stis.kelompok4.drstis;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SignupApi {

    @FormUrlEncoded
    @POST("autentikasi/registration")
    Call<SignupResponse> makeRegistration(
            @Field("pengunjung_nama") String namaPengunjung,
            @Field("pengunjung_nim") String nimPengunjung,
            @Field("status") String status,
            @Field("password") String password,
            @Field("konfirmasi_password") String konfirmasiPassword,
            @Field("email") String email,
            @Field("nomor_telpon") String nomorTelpon,
            @Field("jenis_kelamin") String jenisKelamin
    );
}
