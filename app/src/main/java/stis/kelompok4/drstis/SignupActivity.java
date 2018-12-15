package stis.kelompok4.drstis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
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

    public static final String SU_NIM = "anonim";
    public static final String SU_STATUS = "Mahasiswa";
    public static final String SU_NO_TELPON = "Mahasiswa";
    public static final String SU_JENIS_KELAMIN = "Mahasiswa";

    private EditText addEmail;
    private EditText addNamalengkap;
    private EditText addPassword;
    private EditText addConfPassword;
    private Button btnSignup;
    private TextView errorPlaceholder;

    private String email, namaLengkap, password, konfirmasiPassword;

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
        this.errorPlaceholder = findViewById(R.id.error_placeholder);

        this.email = addEmail.getText().toString();
        this.namaLengkap = addNamalengkap.getText().toString();
        this.password = addPassword.getText().toString();
        this.konfirmasiPassword = addConfPassword.getText().toString();
    }

    private void makeSignup(){
        //TODO:[x] nah saya gak ngerti lagi kwkwk
        Log.d("DEBUG_ANDRO_EMAIL", email);
        Log.d("DEBUG_ANDRO_PASS", password);
        Log.d("DEBUG_ANDRO_KONF", konfirmasiPassword);

        /**
         * Lakukan validasi terlebih dahulu
         */
        if(!validasiEmail()){
            return;
        }
        if (!validasiPassword()){
            return;
        }

        /**
         * membuat retrofit sebagai pembuat HttpRequest
         */
        Retrofit retrofit = RetrofitAdapter.getInstance()
                .getRetrofitAdapter("https://dr-polstat.000webhostapp.com/index.php/api/");
        SignupApi signupApi = retrofit.create(SignupApi.class);

        Call<SignupResponse> call = signupApi.makeRegistration(namaLengkap,
                SU_NIM,SU_STATUS,
                password,
                konfirmasiPassword,
                email,
                SU_NO_TELPON,
                SU_JENIS_KELAMIN);

        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "User sudah terdaftar atau terjadi kesalahan", Toast.LENGTH_SHORT);
                    return;
                }
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                Toast.makeText(SignupActivity.this, "Berhasil mendaftar", Toast.LENGTH_SHORT).show();
                SignupActivity.this.startActivity(intent);
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "Failure: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean validasiEmail(){
        try {
            this.email = addEmail.getText().toString();
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            return false;
        }
        if(email.isEmpty()){
            addEmail.setError("Email tidak boleh kosong");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            addEmail.setError("Email tidak valid");
            return false;
        }else{
            if(email.contains("stis.ac.id")){
                addEmail.setError(null);
                return true;
            }else{
                addEmail.setError("Gunakan email STIS");
                return false;
            }
        }
    }

    private boolean validasiPassword(){
        this.password = addPassword.getText().toString();
        this.konfirmasiPassword = addConfPassword.getText().toString();
        if(password.equals(konfirmasiPassword)){
            errorPlaceholder.setText("password tidak sama");
            return true;
        }else{
            errorPlaceholder.setText("");
            return false;
        }
    }


}
