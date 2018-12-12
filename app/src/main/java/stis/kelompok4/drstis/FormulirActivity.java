package stis.kelompok4.drstis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class FormulirActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir);

        mEditText = (EditText) findViewById(R.id.keluhan_input);
        mTextView = (TextView) findViewById(R.id.keluhan_textCounter);

        mEditText.addTextChangedListener(mTextWatcher);
    }


    private TextView mTextView;
    private EditText mEditText;

    private final TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mTextView.setText(String.valueOf(s.length())  + "/150");
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}