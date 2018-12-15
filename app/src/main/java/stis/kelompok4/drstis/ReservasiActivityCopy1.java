package stis.kelompok4.drstis;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ReservasiActivityCopy1 extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListIndex;
    private HashMap<String, Reservasi> listReservasi;
    private int lastExpandedPosition = -1;
    private String nimPengunjung ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservasi);

        init();

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

        loadData();
    }

    /**
     * Dijalankan saat onCreate. Bertujuan menginisiasi semua field yang ada.
     */
    private void init() {
        this.expandableListView = findViewById(R.id.expandable_list_view);
        this.listReservasi = getReservasi();
        this.expandableListIndex = new ArrayList<>(listReservasi.keySet());
        this.expandableListAdapter = new CustomExpandableListAdapter(this, expandableListIndex, listReservasi);
    }

    /**
     * Dijalankan saat inisiasi.
     * @return HashMap String key, value berupa objek Reservasi.
     */
    private HashMap<String, Reservasi> getReservasi() {

        Retrofit retrofit = RetrofitAdapter.getInstance()
                .getRetrofitAdapter("https://dr-polstat.000webhostapp.com/index.php/api/");
        ReservasiApi reservasiApi = retrofit.create(ReservasiApi.class);

        Call<List<Reservasi>> call = reservasiApi.getReservasi("16.9395");
        call.enqueue(new Callback<List<Reservasi>>() {
            @Override
            public void onResponse(Call<List<Reservasi>> call, Response<List<Reservasi>> response) {
                HashMap<String, Reservasi> lReservasi = new HashMap<>();
                Gson gson = new Gson();
                String debugHere = gson.toJson(response.body());
                Log.d("ANDRO2_DOKTER", debugHere);
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Error disini: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Reservasi> list = response.body();
                for (Reservasi item : list){
                    lReservasi.put(item.hashCode()+"", item);
                }
            }

            @Override
            public void onFailure(Call<List<Reservasi>> call, Throwable t) {

                Log.d("ANDRO1_DOKTER", t.getMessage());
                t.printStackTrace();

                Toast.makeText(getApplicationContext(), "Error disitu: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });

        /**
         * ini cuma contoh buat listnya
         * i: adalah jumlah barisnya mau berapa banya
         */
//        for (int i = 1; i < 15; i++) {
//            listReservasi.put(i + "", new Reservasi("selesai", "disetujui", "2 Desember 2018", "09.00-09.30", "Sakit kepala", "dr. Sehat Utami"));
//        }

        HashMap<String, Reservasi> lReservasi = new HashMap<>();
        return lReservasi;
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.SHARED_PREFS, MODE_PRIVATE);
        this.nimPengunjung = sharedPreferences.getString(LoginActivity.TEXT_NIM, "");
    }
}
