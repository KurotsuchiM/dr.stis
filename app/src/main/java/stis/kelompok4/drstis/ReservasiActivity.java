package stis.kelompok4.drstis;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ReservasiActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListIndex;
    private HashMap<String, Reservasi> listReservasi = new HashMap<>();
    private int lastExpandedPosition = -1;
    private List<Reservasi> data2 = new ArrayList<>();
    private String nimPengguna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservasi);
        loadData();
        Toast.makeText(getApplicationContext(),nimPengguna,Toast.LENGTH_SHORT).show();
        this.expandableListView = findViewById(R.id.expandable_list_view);

        ambilDataReservasi();

    }

    /**
     * Dijalankan saat onCreate. Bertujuan menginisiasi semua field yang ada.
     */
    private void init() {

        this.listReservasi = getReservasi();

        this.expandableListIndex = new ArrayList<>(listReservasi.keySet());
        this.expandableListAdapter = new CustomExpandableListAdapter(this, expandableListIndex, listReservasi);

        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });
    }

    /**
     * Dijalankan untuk mengubah ArrayList ke HashMap.
     * @return HashMap String key, value berupa objek Reservasi.
     */
    private HashMap<String, Reservasi> getReservasi() {

        HashMap<String, Reservasi> listHash = new HashMap<>();

        for (Reservasi rese : this.data2){
            listHash.put(rese.hashCode()+"", rese);
        }

        return listHash;
    }

    /**
     * Fungsi digunakan untuk mengambil data dari Webservice menggunakan retrofit.
     */
    private void ambilDataReservasi(){
        Retrofit retrofit = RetrofitAdapter.getInstance()
                .getRetrofitAdapter("https://dr-polstat.000webhostapp.com/index.php/api/");
        ReservasiApi reservasiApi = retrofit.create(ReservasiApi.class);

        Call<List<Reservasi>> call = reservasiApi.getReservasi(nimPengguna);

        call.enqueue(new Callback<List<Reservasi>>() {
            @Override
            public void onResponse(Call<List<Reservasi>> call, Response<List<Reservasi>> response) {
                /**
                 * Jika response tidak Sukses akan dikembalikan code responsenya.
                 */
                if (!response.isSuccessful()){
                    Toast.makeText(ReservasiActivity.this.getApplicationContext(), "Code: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(ReservasiActivity.this.getApplicationContext(), "Berhasil mengambil data", Toast.LENGTH_SHORT).show();
//                Toast.makeText(ReservasiActivity.this.getApplicationContext(), nimPengguna, Toast.LENGTH_SHORT).show();

                /**
                 * Ketika sudah didapatkan rsponse yang sukses. response.body disimpan pada field data2
                 */
                List<Reservasi> reservasiList = response.body();
                for (Reservasi rese : reservasiList){
                    data2.add(rese);
                }

                /**
                 * Jika data dari webservice sudah sempurna didapatkan. Baru dilakukan isialisasi componen-componen activity.
                 */
                init();

            }

            @Override
            public void onFailure(Call<List<Reservasi>> call, Throwable t) {
                /**
                 * Jika terjadi kegagalan akan dikembalikan message dari kesalahan.
                 */
                Toast.makeText(ReservasiActivity.this.getApplicationContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT);
            }
        });

    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.SHARED_PREFS, MODE_PRIVATE);
        this.nimPengguna = sharedPreferences.getString(LoginActivity.TEXT_NIM, "");
    }

}
