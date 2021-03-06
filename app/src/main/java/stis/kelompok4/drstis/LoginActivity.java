package stis.kelompok4.drstis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT_EMAIL = "emailText";
    public static final String TEXT_PASSWORD = "passwordText";
    public static final String TEXT_NAMA = "namaText";
    public static final String TEXT_NIM = "nimText";

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
    private String emailString, passwordString, namaPengunjung, nimPengunjung;
    private TextView daftarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startNext("alfiyan@stis.ac.id","sayalupa","alfian");
                makeLogin();
            }
        });

        daftarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDaftar();
            }
        });

        loadData();
        if (namaPengunjung != ""){
            startNext(emailString, passwordString, namaPengunjung);
        }

    }

    /**
     * Dijalankan saat onCreate. Bertujuan menginisiasi semua field yang ada.
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void init() {
        this.emailInput = findViewById(R.id.email_Input);
        this.passwordInput = findViewById(R.id.password_Input);
        this.loginButton = findViewById(R.id.loginButton);
        this.daftarButton = findViewById(R.id.daftar_button);

        Drawable drawable = getResources().getDrawable(R.drawable.button_bg);
        drawable.setAlpha(200);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.login_bg2);
        linearLayout.setBackground(drawable);

    }

    /**
     * Dijalankan saat button daftar diklik. Bertujuan untuk menjalankan SignupActivity.
     */
    private void goToDaftar() {
        //TODO: Bikin intent ke daftar page
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    /**
     * Dijalankan saat sudah berhasil login. Bertujuan untuk menjalankan BerandaActivity
     * dengan tambahan parameter email, password, nama.
     * @param email Email yang diinputkan pengguna.
     * @param password Password yang diinputkan pengguna.
     * @param nama Nama dari pengguna didapat dari login.
     */
    private void startNext(String email, String password, String nama){
        Bundle bundle = new Bundle();
        bundle.putString("data1", email);
        bundle.putString("data2", password);
        bundle.putString("data3", nama);
        bundle.putBoolean("data4",true);
        Intent intent = new Intent(LoginActivity.this, BerandaActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * Dijalankan saat button login diklik. Memvalidasi emailString.
     * @return true jika emailString valid. Sebaliknya.
     */
    private boolean validasiEmail(){
        try {
            this.emailString = emailInput.getEditText().getText().toString();
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            return false;
        }
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

    /**
     * Dijalankan saat button login diklik. Memvalidasi passwordString.
     * @return true jika passwordString valid. Sebaliknya.
     */
    private boolean validasiPassword(){
        try {
            this.passwordString = passwordInput.getEditText().getText().toString();
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            return false;
        }
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

    /**
     * Dijalankan saat button login diklik. Seluruh logic untuk login ada disini.
     */
    private void makeLogin(){
        if(!validasiEmail() | !validasiPassword()){
            return;
        }

        //TODO: Bikin retofit login ke webservice login
        Retrofit retrofit = RetrofitAdapter.getInstance()
                .getRetrofitAdapter("https://dr-polstat.000webhostapp.com/index.php/api/");
        LoginApi loginApi = retrofit.create(LoginApi.class);

        Call<LoginResponse> call = loginApi.createLogin(emailString, passwordString);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Error disini: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Pengunjung pengunjung = response.body().getData();
                namaPengunjung = pengunjung.getNamaPengunjung();
                nimPengunjung = pengunjung.getNimPengunjung();

                saveData();
                startNext(emailString, passwordString, namaPengunjung);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error disutu: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT_EMAIL, emailString);
        editor.putString(TEXT_PASSWORD, passwordString);
        editor.putString(TEXT_NAMA, namaPengunjung);
        editor.putString(TEXT_NIM, nimPengunjung);

        editor.apply();

        Toast.makeText(getApplicationContext(), nimPengunjung, Toast.LENGTH_SHORT).show();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        emailString = sharedPreferences.getString(TEXT_EMAIL, "");
        passwordString = sharedPreferences.getString(TEXT_PASSWORD, "");
        namaPengunjung = sharedPreferences.getString(TEXT_NAMA, "");
        nimPengunjung = sharedPreferences.getString(TEXT_NIM, "");
    }

}
