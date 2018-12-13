package stis.kelompok4.drstis;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "";

    private TextInputLayout emailInput;
    private TextInputLayout passwordInput;
    private Button loginButton;
    private String emailString, passwordString;

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

    }

    private void init() {
        this.emailInput = findViewById(R.id.email_Input);
        this.passwordInput = findViewById(R.id.password_Input);
        this.loginButton = findViewById(R.id.loginButton);

        this.emailString = emailInput.getEditText().getText().toString();
        this.passwordString = passwordInput.getEditText().getText().toString();
    }

    private void startNext(){
        String messageNext = emailString;
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, messageNext);
        startActivity(intent);
    }
}
