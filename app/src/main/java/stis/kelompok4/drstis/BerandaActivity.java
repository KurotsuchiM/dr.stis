package stis.kelompok4.drstis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class BerandaActivity extends AppCompatActivity {

    Boolean isLogin = false;
    TextView logoutTextView;
    Button cekJadwalButton, loginButton, reservasiButton;
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
            nama.setText(bundle.getString("data3"));
            isLogin = bundle.getBoolean("data4");

        }

        cekJadwalButton = (Button) findViewById(R.id.cekJadwalButton);
        loginButton = (Button) findViewById(R.id.loginButton);
        reservasiButton = (Button) findViewById(R.id.reservasiButton);
        logoutTextView = (TextView) findViewById(R.id.logoutTextView);


        if(isLogin){
            loginButton.setVisibility(View.GONE);
            logoutTextView.setVisibility(View.VISIBLE);
            reservasiButton.setVisibility(View.VISIBLE);

        }else{
            loginButton.setVisibility(View.VISIBLE);
            logoutTextView.setVisibility(View.GONE);
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

        logoutTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(BerandaActivity.this, BerandaActivity.class);
                BerandaActivity.this.startActivity(intent);
            }
        });

    }
}
//=======
//package stis.kelompok4.drstis;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.Button;
//import android.content.Intent;
//import android.view.View;
//
//public class BerandaActivity extends AppCompatActivity {
//
//    Button cekJadwalButton, reservasiButton;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_beranda);
//
//        cekJadwalButton = (Button) findViewById(R.id.cekJadwalButton);
//        reservasiButton = (Button) findViewById(R.id.reservasiButton);
//
//        cekJadwalButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(BerandaActivity.this, CekJadwalActivity.class);
//                BerandaActivity.this.startActivity(intent);
//                BerandaActivity.this.finish();
//            }
//        });
//
//        reservasiButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(BerandaActivity.this, ReservasiActivity.class);
//                BerandaActivity.this.startActivity(intent);
//                BerandaActivity.this.finish();
//            }
//        });
//
//    }
//}
//>>>>>>> d3cca5eee947c37be4033707d3de7e65b37f31b1
