package stis.kelompok4.drstis;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginApi {

//    @FormUrlEncoded
    @Headers("X-API-KEY: coba")
    @POST("Autentikasi/login")
    Call<LoginResponse> createLogin(
            @Field("email") String email,
            @Field("password") String password
    );
}
