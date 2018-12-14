package stis.kelompok4.drstis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Diajalankan saat membutuhkan Retrofit Adapter.
 * Teknis penggunaan adalah sebagai berikut:
 * Retrofit retrofit = RetrofitAdapter.getInstance().getRetrofitAdapter(String baseURL);
 */
public class RetrofitAdapter {
    private RetrofitAdapter (){}

    /**
     * Helper Class untuk implementasi singleton.
     */
    private static class SingletonHelper{
        public static final RetrofitAdapter INSTANCE = new RetrofitAdapter();
    }

    /**
     * Dijalankan untuk mendapatkan instance dari Retrofit Adapter.
     * @return RetrofitAdapter dengan implementasi singleton.
     */
    public static RetrofitAdapter getInstance(){
        return SingletonHelper.INSTANCE;
    }

    /**
     * Dijalankan saat ingin mendapatkan Retrofit Adapter dari Instance
     * @param baseURL BaseURL dari service yang akan digunakan.
     * @return Retrofit sesuai dengan baseURL.
     */
    public Retrofit getRetrofitAdapter(String baseURL){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
