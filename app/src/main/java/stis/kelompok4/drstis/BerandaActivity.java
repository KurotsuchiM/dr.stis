package stis.kelompok4.drstis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BerandaActivity extends AppCompatActivity {

    Boolean isLogin = false;
    TextView logoutTextView;
    Button cekJadwalButton, loginButton, reservasiButton, signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        TextView nama = (TextView) findViewById(R.id.nama);


        /**
         * Kita cek apakah ada Bundle atau tidak
         */
        if(getIntent().getExtras()!=null) {
            /**
             * Jika Bundle ada, ambil data dari Bundle
             */
            Bundle bundle = getIntent().getExtras();
            nama.setText("Halo " + bundle.getString("data3"));
            isLogin = bundle.getBoolean("data4");

        }

        signUpButton = (Button) findViewById(R.id.signUpButton);
        cekJadwalButton = (Button) findViewById(R.id.cekJadwalButton);
        loginButton = (Button) findViewById(R.id.loginButton);
        reservasiButton = (Button) findViewById(R.id.reservasiButton);
        logoutTextView = (TextView) findViewById(R.id.logoutTextView);


        if(isLogin){
            loginButton.setVisibility(View.GONE);
            signUpButton.setVisibility(View.GONE);
            logoutTextView.setVisibility(View.VISIBLE);
            cekJadwalButton.setVisibility(View.VISIBLE);
            reservasiButton.setVisibility(View.VISIBLE);

        }else{
            loginButton.setVisibility(View.VISIBLE);
            signUpButton.setVisibility(View.VISIBLE);
            logoutTextView.setVisibility(View.GONE);
            cekJadwalButton.setVisibility(View.GONE);
            reservasiButton.setVisibility(View.GONE);
        }

        cekJadwalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, CalendarActivity.class);
                BerandaActivity.this.startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, LoginActivity.class);
                BerandaActivity.this.startActivity(intent);
            }
        });

        reservasiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, ReservasiActivity.class);
                BerandaActivity.this.startActivity(intent);
            }
        });

        logoutTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(BerandaActivity.this, BerandaActivity.class);
                BerandaActivity.this.startActivity(intent);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, SignupActivity.class);
                BerandaActivity.this.startActivity(intent);
            }
        });


    }
}

