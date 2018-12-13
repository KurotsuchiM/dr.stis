package stis.kelompok4.drstis;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "";

    private TextInputLayout emailInput;
    private TextInputLayout passwordInput;
    private Button loginButton;
    private String emailString, passwordString;
    private TextView daftarButton, lupaPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNext();
            }
        });

        daftarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDaftar();
            }
        });

//        lupaPasswordButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToLupaPass();
//            }
//        });

    }

    private void goToLupaPass() {
        //TODO: Bikin intent ke lupa pass page
    }

    private void goToDaftar() {
        //TODO: Bikin intent ke daftar page
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    private void init() {
        this.emailInput = findViewById(R.id.email_Input);
        this.passwordInput = findViewById(R.id.password_Input);
        this.loginButton = findViewById(R.id.loginButton);
        this.daftarButton = findViewById(R.id.daftar_button);
//        this.lupaPasswordButton = findViewById(R.id.lupa_password_button);

        this.emailString = emailInput.getEditText().getText().toString();
        this.passwordString = passwordInput.getEditText().getText().toString();
    }

    private void startNext(){
        String messageNext = emailString;
        Intent intent = new Intent(this, BerandaActivity.class);
        intent.putExtra(EXTRA_MESSAGE, messageNext);
        startActivity(intent);
    }
}
