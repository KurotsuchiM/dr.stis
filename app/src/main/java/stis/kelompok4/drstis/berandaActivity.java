package stis.kelompok4.drstis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class berandaActivity extends AppCompatActivity {

    Button cekJadwalButton, reservasiButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        cekJadwalButton = (Button) findViewById(R.id.cekJadwalButton);
        reservasiButton = (Button) findViewById(R.id.reservasiButton);

        cekJadwalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(berandaActivity.this, cekJadwalActivity.class);
                berandaActivity.this.startActivity(intent);
                berandaActivity.this.finish();
            }
        });

        reservasiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(berandaActivity.this, ReservasiActivity.class);
                berandaActivity.this.startActivity(intent);
                berandaActivity.this.finish();
            }
        });

    }
}
