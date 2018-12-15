package stis.kelompok4.drstis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.Normalizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FormulirActivity extends AppCompatActivity {
    private Button submitButton;
    private TextView mTextView;
    private EditText mEditText;

    private String selectedDate;
    private String selectedTIme;
    private String selectedName;
    private TextView namaInput;
    private TextView tanggalInput;
    private TextView jamInput;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir);

        init();

    }

    private void init()
    {
        sharedPreferences = getSharedPreferences(LoginActivity.SHARED_PREFS, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null)
        {
            setSelectedName(sharedPreferences.getString(LoginActivity.TEXT_NAMA,""));

            // setSelectedDate(sharedPreferences.getString(CalendarActivity.TEXT_TANGGAL,""));
            // setSelectedTIme(sharedPreferences.getString(activity_jadwal.TEXT_JAM,""));
            setSelectedDate((String) bundle.getString("Tanggal"));
            setSelectedTIme((String) bundle.getString("Jam"));

            if(getSelectedTIme().equalsIgnoreCase("") || (getSelectedTIme() == null))
            {
                setSelectedTIme("Jam Belum Dipilih");
            }
            if(getSelectedDate().equalsIgnoreCase("") || (getSelectedDate() == null))
            {
                setSelectedDate("Tanggal Belum Diisi");
            }
            if(getSelectedName().equalsIgnoreCase("") || (getSelectedName() == null))
            {
                setSelectedName("Login Belum Dilakukan");
            }

            tanggalInput = (TextView) findViewById(R.id.tanggal_input);
            tanggalInput.setText(getSelectedDate());
            jamInput = (TextView) findViewById(R.id.jam_input);
            jamInput.setText(getSelectedTIme());
            namaInput = (TextView) findViewById(R.id.nama_input);
            namaInput.setText(getSelectedName());
        }

        mEditText = findViewById(R.id.keluhan_input);
        mTextView = findViewById(R.id.keluhan_textCounter);
        submitButton = findViewById(R.id.submit_button);

        mEditText.addTextChangedListener(mTextWatcher);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFormulir();
            }
        });
    }

    private final TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mTextView.setText(String.valueOf(s.length()) + "/150");
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };

    private void submitFormulir() {
        //TODO: buat retrofit untuk mensubmit formulir ke webservice.
        Retrofit retrofit = RetrofitAdapter.getInstance()
                .getRetrofitAdapter("https://dr-polstat.000webhostapp.com/index.php/api/");
        FormulirApi formulirApi = retrofit.create(FormulirApi.class);

        String tanggalReservasi = "";
        String jamReservasi = "";
        String keluhan = mEditText.getText().toString();

        Call<FormulirResponse> call = formulirApi.sendFormulir(tanggalReservasi, jamReservasi, keluhan);
        call.enqueue(new Callback<FormulirResponse>() {
            @Override
            public void onResponse(Call<FormulirResponse> call, Response<FormulirResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Error di onResponse: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), "Berhasil membuat request",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(FormulirActivity.this, BerandaActivity.class);
                FormulirActivity.this.startActivity(intent);
                FormulirActivity.this.finish();
            }

            @Override
            public void onFailure(Call<FormulirResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error di onFailure: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });


    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getSelectedTIme() {
        return selectedTIme;
    }

    public void setSelectedTIme(String selectedTIme) {
        this.selectedTIme = selectedTIme;
    }

    public String getSelectedName() {
        return selectedName;
    }

    public void setSelectedName(String selectedName) {
        this.selectedName = selectedName;
    }
}