package stis.kelompok4.drstis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SignupActivity extends AppCompatActivity {

    private EditText addEmail;
    private EditText addNamalengkap;
    private EditText addPassword;
    private EditText addConfPassword;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeSignup();
            }
        });


    }

    private void init() {
        this.addEmail = findViewById(R.id.add_Email);
        this.addNamalengkap = findViewById(R.id.add_Namalengkap);
        this.addPassword = findViewById(R.id.add_Password);
        this.addConfPassword = findViewById(R.id.add_ConfPassword);
        this.btnSignup = findViewById(R.id.btn_Signup);
    }

    private void makeSignup(){
        //TODO : nah saya gak ngerti lagi kwkwk
    }



}
