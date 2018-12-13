package stis.kelompok4.drstis;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "";
    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    private TextInputLayout emailInput;
    private TextInputLayout passwordInput;
    private Button loginButton;
    private String emailString, passwordString;
    private TextView daftarButton, lupaPasswordButton;
    private boolean isLogin = false;

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

        this.emailString = emailInput.getEditText().getText().toString();
        this.passwordString = passwordInput.getEditText().getText().toString();
    }

    private void startNext(){
        String messageNext = emailString;
        Intent intent = new Intent(this, BerandaActivity.class);
        intent.putExtra(EXTRA_MESSAGE, messageNext);
        startActivity(intent);
    }

    private boolean validasiEmail(){
        this.emailString = emailInput.getEditText().getText().toString();
        if(emailString.isEmpty()){
            emailInput.setError("Email tidak boleh kosong");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()){
            emailInput.setError("Email tidak valid");
            return false;
        }else{
            if(emailString.contains("stis.ac.id")){
                emailInput.setError(null);
                return true;
            }else{
                emailInput.setError("Gunakan email STIS");
                return false;
            }
        }
    }

    private boolean validasiPassword(){
        this.passwordString = passwordInput.getEditText().getText().toString();
        if(passwordString.isEmpty()){
            passwordInput.setError("Password tidak boleh kosong");
            return false;
        }else if(PASSWORD_PATTERN.matcher(passwordString).matches()){
            passwordInput.setError("Password lemah");
            return false;
        }else {
            passwordInput.setError(null);
            return true;
        }
    }

    private void makeLogin(){
        if(!validasiEmail() | !validasiPassword()){
            return;
        }




        //TODO: Bikin retofit login ke webservice login
        Retrofit instance = getInstance("https://localhost/ci-restserver-master/api/autentikasi/");
        LoginApi loginApi = instance.create(LoginApi.class);

        Call<List<LoginResponse>> call = loginApi.createLogin("alfian@stis.ac.id", "somepass");

        call.enqueue(new Callback<List<LoginResponse>>() {
            @Override
            public void onResponse(Call<List<LoginResponse>> call, Response<List<LoginResponse>> response) {
                if(!response.isSuccessful()){
                    //TODO: response tidak sukses
                    isLogin = false;
                }
                List<LoginResponse> loginResponseList = response.body();

                for(LoginResponse loginResponse : loginResponseList){
                    isLogin = loginResponse.isStatus_akun();
                }
            }

            @Override
            public void onFailure(Call<List<LoginResponse>> call, Throwable t) {
                isLogin = false;
            }
        });

        if (isLogin){
            Toast.makeText(getApplicationContext(), "Berhasil login", Toast.LENGTH_SHORT).show();

        }else {
            Toast toast = Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    private Retrofit getInstance(String baseURL){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
