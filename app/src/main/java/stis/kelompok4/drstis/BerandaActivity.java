package stis.kelompok4.drstis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class BerandaActivity extends AppCompatActivity {

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
                Intent intent = new Intent(BerandaActivity.this, CekJadwalActivity.class);
                BerandaActivity.this.startActivity(intent);
                BerandaActivity.this.finish();
            }
        });

        reservasiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, ReservasiActivity.class);
                BerandaActivity.this.startActivity(intent);
                BerandaActivity.this.finish();
            }
        });

    }
}
