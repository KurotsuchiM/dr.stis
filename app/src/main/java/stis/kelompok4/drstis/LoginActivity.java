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

    TextInputLayout userIn;
    TextInputLayout passIn;
    private Button loginButton;
    private EditText usernameInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userIn = findViewById(R.id.username_Input);
        loginButton = findViewById(R.id.loginButton);
//        usernameInput = findViewById(R.id.username_Input);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNext();
            }
        });

    }

    private String unpassLock(){
        return  userIn.getEditText().getText().toString();
    }

    private void startNext(){
        String messageNext = unpassLock();
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, messageNext);
        startActivity(intent);
    }
}
