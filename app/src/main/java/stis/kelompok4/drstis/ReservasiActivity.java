package stis.kelompok4.drstis;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ReservasiActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListIndex;
    private HashMap<String, Reservasi> listReservasi = new HashMap<>();
    private int lastExpandedPosition = -1;

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


        ambilDataReservasi();

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

        HashMap<String, Reservasi> listHash = new HashMap<>();

        Reservasi obj1 = new Reservasi("Alfian", "16.8990", "198129187298172", "dr. Sehat Utami", "2018-12-14", "09.00", "Sakit kepala");
        Reservasi obj2 = new Reservasi("Alfian", "16.8990", "198129187298172", "dr. Sehat Utami", "2018-12-14", "09.00", "Sakit kepala");
        Reservasi obj3 = new Reservasi("Alfian", "16.8990", "198129187298172", "dr. Sehat Utami", "2018-12-14", "09.00", "Sakit kepala");

        listHash.put(obj1.hashCode()+"", obj1);
        listHash.put(obj2.hashCode()+"", obj2);
        listHash.put(obj3.hashCode()+"", obj3);

        return listHash;
    }

    private void ambilDataReservasi(){
        Retrofit retrofit = RetrofitAdapter.getInstance()
                .getRetrofitAdapter("https://dr-polstat.000webhostapp.com/index.php/api/");
        ReservasiApi reservasiApi = retrofit.create(ReservasiApi.class);

        final List<Reservasi> mReservasiList = new ArrayList<>();

        Call<List<Reservasi>> call = reservasiApi.getReservasi("16.9395");
        call.enqueue(new Callback<List<Reservasi>>() {
            @Override
            public void onResponse(Call<List<Reservasi>> call, Response<List<Reservasi>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(ReservasiActivity.this.getApplicationContext(), "Code: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(ReservasiActivity.this.getApplicationContext(), "Bisa", Toast.LENGTH_SHORT).show();

                List<Reservasi> reservasiList = response.body();
                for (Reservasi rese : reservasiList){
                    Log.d("LOG_doc_nama", rese.getDokterNama());
                    Log.d("LOG_doc_nip", rese.getDokterNip());
                    Log.d("LOG_doc_keluhan", rese.getKeluhan());
                    Log.d("LOG_doc_nama_pengunjung", rese.getPengunjungNama());
                    Log.d("LOG_doc_nim_pengunjung", rese.getPengunjungNim());
                }
//                mReservasiList.addAll(reservasiList);


            }

            @Override
            public void onFailure(Call<List<Reservasi>> call, Throwable t) {
                Toast.makeText(ReservasiActivity.this.getApplicationContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT);
            }
        });

        for (Reservasi rese : mReservasiList){
            Log.d("LOG_NAMA", rese.getPengunjungNama());
        }

    }

}
