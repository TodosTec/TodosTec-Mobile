package com.example.todostectest.CadastroEmpresa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todostectest.API.ApiMobile;
import com.example.todostectest.API.verificaAPI;
import com.example.todostectest.CadastroUsuario.CadastroUserTelefoneUsuario;
import com.example.todostectest.CadastroUsuario.ValidaSMSUsuario;
import com.example.todostectest.R;
import com.example.todostectest.Tools.MaskFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroEmpresaTelefone extends AppCompatActivity {

    EditText iptNomeEmpresa;
    EditText iptUsername;
    EditText iptTelefone;
    Button btContinuar;
    TextView txtNomeRestante;
    TextView txtUsernameRestante;
    TextView txtTelefoneRestante;
    String SemFormatacaoTelefone;
    boolean isValid = true;
    boolean isValidTelefone = false;
    boolean isValidUser = false;
    String EmailUsuario;
    String SenhaUsuario;
    verificaAPI RespostaAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_empresa_telefone);

        iptNomeEmpresa = findViewById(R.id.iptDescricaoEmpresa);
        iptUsername = findViewById(R.id.iptUsernameEmpresa);
        iptTelefone = findViewById(R.id.iptTelefoneEmpresa);
        btContinuar = findViewById(R.id.btnContinuarEmpresa);

        txtNomeRestante = findViewById(R.id.txtFeedbackNome);
        txtUsernameRestante = findViewById(R.id.txtFeedbackUsername);
        txtTelefoneRestante = findViewById(R.id.txtFeedbackDescricao);

        Intent intent = getIntent();
        EmailUsuario = intent.getStringExtra("EmailUsuario");
        SenhaUsuario = intent.getStringExtra("SenhaUsuario");

        MaskFormatter maskFormatter = new MaskFormatter("(##) #####-####", iptTelefone);
        iptTelefone.addTextChangedListener(maskFormatter);

        iptNomeEmpresa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (iptNomeEmpresa.getText().toString().trim().isEmpty()){
                    txtNomeRestante.setText("Campo Obrigatório");
                    isValid = false;
                    iptNomeEmpresa.setBackgroundResource(R.drawable.edittext_background_red);
                    txtNomeRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }
                else{
                    txtNomeRestante.setText("");
                    iptNomeEmpresa.setBackgroundResource(R.drawable.edittext_background);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        iptUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String username = charSequence.toString();
                boolean isValid = isValidUsername(username);

                if (iptUsername.getText().toString().trim().isEmpty()){
                    txtUsernameRestante.setText("Campo Obrigatório");
                    isValid = false;
                    iptUsername.setBackgroundResource(R.drawable.edittext_background_red);
                    txtUsernameRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }
                else{
                    txtUsernameRestante.setText("");
                    iptUsername.setBackgroundResource(R.drawable.edittext_background);
                }

                if (!isValid) {
                    txtUsernameRestante.setText("Username inválido");
                    isValid = false;
                    iptUsername.setBackgroundResource(R.drawable.edittext_background_red);
                    txtUsernameRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtUsernameRestante.setText("");
                    iptUsername.setBackgroundResource(R.drawable.edittext_background);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        iptTelefone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (iptTelefone.getText().toString().trim().isEmpty()) {
                    txtTelefoneRestante.setText("Campo Obrigatório");
                    isValid = false;
                    iptTelefone.setBackgroundResource(R.drawable.edittext_background_red);
                    txtTelefoneRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtTelefoneRestante.setText("");
                    iptTelefone.setBackgroundResource(R.drawable.edittext_background);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        btContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SemFormatacaoTelefone = maskFormatter.RemoveMask();
                isValid = true;

                if (iptNomeEmpresa.getText().toString().trim().isEmpty()) {
                    txtNomeRestante.setText("Campo Obrigatório");
                    isValid = false;
                    iptNomeEmpresa.setBackgroundResource(R.drawable.edittext_background_red);
                    txtNomeRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtNomeRestante.setText("");
                    iptNomeEmpresa.setBackgroundResource(R.drawable.edittext_background);
                }

                if (iptUsername.getText().toString().trim().isEmpty()) {
                    txtUsernameRestante.setText("Campo Obrigatório");
                    isValid = false;
                    iptUsername.setBackgroundResource(R.drawable.edittext_background_red);
                    txtUsernameRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtUsernameRestante.setText("");
                    iptUsername.setBackgroundResource(R.drawable.edittext_background);
                }

                if (iptTelefone.getText().toString().trim().isEmpty()) {
                    txtTelefoneRestante.setText("Campo Obrigatório");
                    isValid = false;
                    iptTelefone.setBackgroundResource(R.drawable.edittext_background_red);
                    txtTelefoneRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtTelefoneRestante.setText("");
                    iptTelefone.setBackgroundResource(R.drawable.edittext_background);
                }

                if (!isValidUsername(iptUsername.getText().toString())){
                    txtUsernameRestante.setText("Username inválido");
                    isValid = false;
                    iptUsername.setBackgroundResource(R.drawable.edittext_background_red);
                    txtUsernameRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }
                else {
                    txtUsernameRestante.setText("");
                    iptUsername.setBackgroundResource(R.drawable.edittext_background);
                }
                if (!maskFormatter.isMaskMatching()){
                    txtTelefoneRestante.setText("Telefone Inválido");
                    isValid = false;
                    iptTelefone.setBackgroundResource(R.drawable.edittext_background_red);
                    txtTelefoneRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }
                else{
                    txtTelefoneRestante.setText("");
                    iptTelefone.setBackgroundResource(R.drawable.edittext_background);
                }
                if (isValid) {
                    rotaUserNameTelefone();
                }
            }
        });
    }

    private boolean isValidUsername(String username) {
        return username.matches("^[a-z-0-9_]+$");
    }

    public void VoltarOnClick(View view) {
        finish();
    }

    public void rotaUserNameTelefone(){
        ProgressBar loadingProgressBar = findViewById(R.id.loadingProgressBar);
        loadingProgressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-3wfy.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiMobile apiService = retrofit.create(ApiMobile.class);

        String userName = iptUsername.getText().toString();

        Call<verificaAPI> call = apiService.verificaUserName(userName);

        call.enqueue(new Callback<verificaAPI>() {
            @Override
            public void onResponse(Call<verificaAPI> call, Response<verificaAPI> response) {
                if (response.isSuccessful()) {
                    RespostaAPI = response.body();
                    if (RespostaAPI.getMensagem().equals("Username liberado")){
                        isValidUser = true;
                        AbrirTelaValidaSMS();
                    }
                    else{
                        txtUsernameRestante.setText("Username já cadastrado");
                        iptUsername.setBackgroundResource(R.drawable.edittext_background_red);
                        txtUsernameRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                        isValidUser = false;
                    }
                } else {
                    Toast.makeText(CadastroEmpresaTelefone.this, "Erro na resposta da API: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<verificaAPI> call, Throwable t) {
                Toast.makeText(CadastroEmpresaTelefone.this, "Erro ao conectar ao servidor: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        String telefone = SemFormatacaoTelefone;

        Call<verificaAPI> call2 = apiService.verificaTelefone(telefone);

        call2.enqueue(new Callback<verificaAPI>() {
            @Override
            public void onResponse(Call<verificaAPI> call, Response<verificaAPI> response) {
                RespostaAPI = response.body();
                if (RespostaAPI.getMensagem().equals("Telefone liberado")){
                    isValidTelefone = true;
                    AbrirTelaValidaSMS();
                }
                else{
                    txtTelefoneRestante.setText("Telefone já cadastrado");
                    iptTelefone.setBackgroundResource(R.drawable.edittext_background_red);
                    txtTelefoneRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                    isValidTelefone = false;
                }
            }

            @Override
            public void onFailure(Call<verificaAPI> call, Throwable t) {
                Toast.makeText(CadastroEmpresaTelefone.this, "Erro ao conectar ao servidor: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void AbrirTelaValidaSMS(){
        if (isValidUser && isValidTelefone) {
            ProgressBar loadingProgressBar = findViewById(R.id.loadingProgressBar);
            Intent intent = new Intent(CadastroEmpresaTelefone.this, CadastroEmpresaSMS.class);
            intent.putExtra("EmailUsuario", EmailUsuario);
            intent.putExtra("SenhaUsuario", SenhaUsuario);
            intent.putExtra("NomeCompleto", iptNomeEmpresa.getText().toString());
            intent.putExtra("Username", iptUsername.getText().toString());
            intent.putExtra("Telefone", SemFormatacaoTelefone);
            intent.putExtra("SemFormatacaoTelefone", SemFormatacaoTelefone);
            loadingProgressBar.setVisibility(View.INVISIBLE);
            isValidUser = false;
            isValidTelefone = false;
            startActivity(intent);
        }
    }
}
