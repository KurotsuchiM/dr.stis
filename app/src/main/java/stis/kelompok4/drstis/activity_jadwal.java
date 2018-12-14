package stis.kelompok4.drstis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Boolean.FALSE;

public class activity_jadwal extends AppCompatActivity implements ActivityJadwalAdapter.ItemClickListener
{

    private boolean selectedTimeStatusAvailable = false;
    private String selectedDate;
    private TextView selectedDateText;
    private ActivityJadwalAdapter adapter;
    private String selectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        selectedDateText = (TextView) findViewById(R.id.selectedDate) ;
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Button pesanJadwal = findViewById(R.id.jadwal_button);

        //ArrayList<String> listWaktu = new ArrayList<>();
        String listWaktu[] = new String[15];
        listWaktu[0] = "09:00";
        listWaktu[1] = "09:30";
        listWaktu[2] = "10:00";
        listWaktu[3] = "10:30";
        listWaktu[4] = "11:00";
        listWaktu[5] = "11:30";
        listWaktu[6] = "12:00";
        listWaktu[7] = "12:30";
        listWaktu[8] = "13:00";
        listWaktu[9] = "13:30";
        listWaktu[10] = "14:00";
        listWaktu[11] = "14:30";
        listWaktu[12] = "15:00";
        listWaktu[13] = "15:30";
        listWaktu[14] = "16:00";


        RecyclerView listView = (RecyclerView) findViewById(R.id.jadwal_list_itself);
        listView.setLayoutManager(new GridLayoutManager(this, 3));
        //listView.setLayoutManager(new LinearLayoutManager((this)));

        this.adapter = new ActivityJadwalAdapter(this,listWaktu);
        adapter.setClickListener(this);

        listView.setAdapter(adapter);

        if(bundle != null)
        {
            setSelectedDate((String) bundle.get("Tanggal"));
            selectedDateText.setText(getSelectedDate());
        }

        pesanJadwal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setSelectedTime("");

                Intent intent1 = new Intent(getApplicationContext(), FormulirActivity.class);
                intent1.putExtra("Jam", getSelectedTime());
                intent1.putExtra("Tanggal", getSelectedDate());

                startActivity(intent1);

            }
        });

    }

    @Override
    public void onItemClick(View view, int position)
    {
        if(isSelectedTimeStatusAvailable())
        {
            Toast.makeText(this, "Jam " + adapter.getItem(position) + " tersedia untuk dipesan.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Jam " + adapter.getItem(position) + " tidak tersedia, mohon pilih jam yang lain.", Toast.LENGTH_SHORT).show();
        }

    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public boolean isSelectedTimeStatusAvailable() {
        return selectedTimeStatusAvailable;
    }

    public void setSelectedTimeStatusAvailable(boolean selectedTimeStatus) {
        this.selectedTimeStatusAvailable = selectedTimeStatus;
    }

    public String getSelectedTime() {
        return selectedTime;
    }

    public void setSelectedTime(String selectedTime) {
        this.selectedTime = selectedTime;
    }
}
