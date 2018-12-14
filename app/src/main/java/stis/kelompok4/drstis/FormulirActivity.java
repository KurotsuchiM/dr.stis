package stis.kelompok4.drstis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Retrofit;

public class FormulirActivity extends AppCompatActivity {
    private Button submitButton;
    private TextView mTextView;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir);

        init();

    }

    private void init(){
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
                .getRetrofitAdapter("https://localhost/");


    }

}