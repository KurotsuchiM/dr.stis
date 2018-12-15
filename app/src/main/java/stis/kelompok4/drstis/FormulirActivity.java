package stis.kelompok4.drstis;

import android.content.Intent;
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
    private TextView tanggalInput;
    private TextView jamInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir);

        init();

    }

    private void init()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null)
        {
            setSelectedDate((String) bundle.getString("Tanggal"));
            setSelectedTIme((String) bundle.getString("Jam"));

            if(getSelectedTIme().equalsIgnoreCase("") || (getSelectedTIme() == null))
            {
                setSelectedTIme("Tanggal Belum Diisi");
            }

            tanggalInput = (TextView) findViewById(R.id.tanggal_input);
            tanggalInput.setText(getSelectedDate());
            jamInput = (TextView) findViewById(R.id.jam_input);
            jamInput.setText(getSelectedTIme());
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
}