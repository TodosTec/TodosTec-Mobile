package com.example.todostectest.CadastroEmpresa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todostectest.API.ApiMobile;
import com.example.todostectest.API.CompanyData;
import com.example.todostectest.API.UserData;
import com.example.todostectest.CadastroUsuario.CadastroDescricaoUsuario;
import com.example.todostectest.CadastroUsuario.ConclusaoCadastroUsuario;
import com.example.todostectest.ManipulaTela;
import com.example.todostectest.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroEmpresaDescricao extends AppCompatActivity {

    EditText iptDescricao;
    TextView txtRestante;
    Button btnContinuar;
    String EmailUsuario;
    String SenhaUsuario;
    String NomeCompleto;
    String Username;
    String Telefone;
    String Link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_empresa_descricao);
        iptDescricao = findViewById(R.id.iptDescricaoEmpresa);
        txtRestante = findViewById(R.id.txtFeedbackDescricao);
        btnContinuar = findViewById(R.id.btnContinuarEmpresa);

        Intent intent = getIntent();
        EmailUsuario = intent.getStringExtra("EmailUsuario");
        SenhaUsuario = intent.getStringExtra("SenhaUsuario");
        NomeCompleto = intent.getStringExtra("NomeCompleto");
        Username = intent.getStringExtra("Username");
        Telefone = intent.getStringExtra("Telefone");
        Link = intent.getStringExtra("Link");

        ManipulaTela.verificaTela.setVerificaCadastro(false);

        iptDescricao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int caracteresRestantes = 500 - charSequence.length();
                txtRestante.setText(caracteresRestantes + " Caracteres restantes");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (iptDescricao.getText().toString().trim().isEmpty()) {
                    txtRestante.setText("Campo Obrigatório");
                    iptDescricao.setBackgroundResource(R.drawable.edittext_background_red);
                    txtRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtRestante.setText("");
                    iptDescricao.setBackgroundResource(R.drawable.edittext_background);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isValid = true;

                if (iptDescricao.getText().toString().trim().isEmpty()) {
                    txtRestante.setText("Campo Obrigatório");
                    iptDescricao.setBackgroundResource(R.drawable.edittext_background_red);
                    txtRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                    isValid = false;
                } else {
                    txtRestante.setText("");
                    iptDescricao.setBackgroundResource(R.drawable.edittext_background);
                }

                if (isValid) {
                    ProgressBar loadingProgressBar = findViewById(R.id.loadingProgressBar);
                    loadingProgressBar.setVisibility(View.VISIBLE);

                    CompanyData companyData = new CompanyData(
                            NomeCompleto,
                            Username,
                            SenhaUsuario,
                            Telefone,
                            EmailUsuario,
                            iptDescricao.getText().toString(),
                            Link
                    );

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://api-3wfy.onrender.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ApiMobile apiService = retrofit.create(ApiMobile.class);

                    Call<Void> call = apiService.cadastrarEmpresa(companyData);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful() && !ManipulaTela.verificaTela.isVerificaCadastro()) {

                                Intent intent = new Intent(CadastroEmpresaDescricao.this, ConclusaoCadastroEmpresa.class);
                                loadingProgressBar.setVisibility(View.INVISIBLE);
                                ManipulaTela.verificaTela.setVerificaCadastro(true);
                                startActivity(intent);
                            } else {
                                Log.i("Erro_Api","Erro ao cadastrar usuário.");
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(CadastroEmpresaDescricao.this, "Erro ao comunicar com o servidor.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    public void VoltarOnClick(View view) {
        finish();
    }
}