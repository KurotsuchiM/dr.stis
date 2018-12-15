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
    private List<Reservasi> data2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservasi);

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
     * Dijalankan saat inisiasi.
     * @return HashMap String key, value berupa objek Reservasi.
     */
    private HashMap<String, Reservasi> getReservasi() {

        HashMap<String, Reservasi> listHash = new HashMap<>();

        for (Reservasi rese : this.data2){
            listHash.put(rese.hashCode()+"", rese);
        }

        return listHash;
    }

    private void ambilDataReservasi(){
        Retrofit retrofit = RetrofitAdapter.getInstance()
                .getRetrofitAdapter("https://dr-polstat.000webhostapp.com/index.php/api/");
        ReservasiApi reservasiApi = retrofit.create(ReservasiApi.class);

        Call<List<Reservasi>> call = reservasiApi.getReservasi("16.9395");

        call.enqueue(new Callback<List<Reservasi>>() {
            @Override
            public void onResponse(Call<List<Reservasi>> call, Response<List<Reservasi>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(ReservasiActivity.this.getApplicationContext(), "Code: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(ReservasiActivity.this.getApplicationContext(), "Berhasil mengambil data", Toast.LENGTH_SHORT).show();

                List<Reservasi> reservasiList = response.body();
                for (Reservasi rese : reservasiList){
                    data2.add(rese);
                }

                init();

            }

            @Override
            public void onFailure(Call<List<Reservasi>> call, Throwable t) {
                Toast.makeText(ReservasiActivity.this.getApplicationContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT);
            }
        });

    }

}
