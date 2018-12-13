package stis.kelompok4.drstis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReservasiActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListIndex;
    private HashMap<String, Reservasi> listReservasi;
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
                if(lastExpandedPosition != -1 && groupPosition != lastExpandedPosition){
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;

            }
        });
    }

    private void init() {
        this.expandableListView = (ExpandableListView) findViewById(R.id.expandable_list_view);
        this.listReservasi = getReservasi();
        this.expandableListIndex = new ArrayList<>(listReservasi.keySet());
        this.expandableListAdapter = new CustomExpandableListAdapter(this, expandableListIndex, listReservasi);
    }

    private HashMap<String, Reservasi> getReservasi(){
        HashMap<String, Reservasi> listReservasi = new HashMap<>();

        /**
         * ini cuma contoh buat listnya
         * i: adalah jumlah barisnya mau berapa banya
         */
        for (int i=1; i<15; i++){
            listReservasi.put(i+"", new Reservasi("selesai", "disetujui", "2 Desember 2018", "09.00-09.30", "Sakit kepala", "dr. Sehat Utami"));
        }

        return listReservasi;
    }
}
