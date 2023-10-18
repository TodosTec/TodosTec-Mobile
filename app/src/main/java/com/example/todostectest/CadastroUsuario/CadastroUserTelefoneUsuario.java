package com.example.todostectest.CadastroUsuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todostectest.API.ApiMobile;
import com.example.todostectest.API.verificaAPI;
import com.example.todostectest.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroUserTelefoneUsuario extends AppCompatActivity {

    EditText iptNomeCompleto;
    EditText iptUsername;
    EditText iptTelefone;
    Button btContinuar;
    TextView txtNomeRestante;
    TextView txtUsernameRestante;
    TextView txtTelefoneRestante;
    String SemFormatacaoTelefone;
    boolean isValidTelefone = false;
    boolean isValidUser = false;
    String EmailUsuario;
    String SenhaUsuario;
    String ConfirmarSenha;
    verificaAPI RespostaAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_user_telefone_usuario);

        iptNomeCompleto = findViewById(R.id.iptDescricaoUsuario);
        iptUsername = findViewById(R.id.spOrientacao);
        iptTelefone = findViewById(R.id.spPronome);
        btContinuar = findViewById(R.id.btnContinuar);

        txtNomeRestante = findViewById(R.id.txtValidaSenhaUsuario);
        txtUsernameRestante = findViewById(R.id.txtOrientacao);
        txtTelefoneRestante = findViewById(R.id.txtperfil);

        Intent intent = getIntent();
        EmailUsuario = intent.getStringExtra("EmailUsuario");
        SenhaUsuario = intent.getStringExtra("SenhaUsuario");
        ConfirmarSenha = intent.getStringExtra("ConfirmarSenha");

        iptNomeCompleto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (iptNomeCompleto.getText().toString().isEmpty()){
                    txtNomeRestante.setText("Campo Obrigatório");
                    iptNomeCompleto.setBackgroundResource(R.drawable.edittext_background_red);
                    txtNomeRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }
                else{
                    txtNomeRestante.setText("");
                    iptNomeCompleto.setBackgroundResource(R.drawable.edittext_background);
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

                if (iptUsername.getText().toString().isEmpty()){
                    txtUsernameRestante.setText("Campo Obrigatório");
                    iptUsername.setBackgroundResource(R.drawable.edittext_background_red);
                    txtUsernameRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }
                else{
                    txtUsernameRestante.setText("");
                    iptUsername.setBackgroundResource(R.drawable.edittext_background);
                }

                if (!isValid) {
                    txtUsernameRestante.setText("Username inválido");
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
                if (iptTelefone.getText().toString().isEmpty()) {
                    txtTelefoneRestante.setText("Campo Obrigatório");
                    iptTelefone.setBackgroundResource(R.drawable.edittext_background_red);
                    txtTelefoneRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtTelefoneRestante.setText("");
                    iptTelefone.setBackgroundResource(R.drawable.edittext_background);
                }

                String telefone = charSequence.toString();

                if (telefone.length() > 15) {
                    telefone = telefone.substring(0, 14);
                }

                telefone = telefone.replaceAll("[^\\d]", "");

                StringBuilder formattedTelefone = new StringBuilder();

                for (int j = 0; j < telefone.length(); j++) {
                    formattedTelefone.append(telefone.charAt(j));
                    if (formattedTelefone.length() == 2) {
                        formattedTelefone.insert(0, "(");
                        formattedTelefone.append(") ");
                    } else if (formattedTelefone.length() == 10) {
                        formattedTelefone.append("-");
                    }
                }

                iptTelefone.removeTextChangedListener(this);
                iptTelefone.setText(formattedTelefone.toString());
                iptTelefone.setSelection(formattedTelefone.length());
                iptTelefone.addTextChangedListener(this);

                SemFormatacaoTelefone = telefone;
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        btContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iptNomeCompleto.getText().toString().isEmpty()) {
                    txtNomeRestante.setText("Campo Obrigatório");
                    iptNomeCompleto.setBackgroundResource(R.drawable.edittext_background_red);
                    txtNomeRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtNomeRestante.setText("");
                    iptNomeCompleto.setBackgroundResource(R.drawable.edittext_background);
                }

                if (iptUsername.getText().toString().isEmpty()) {
                    txtUsernameRestante.setText("Campo Obrigatório");
                    iptUsername.setBackgroundResource(R.drawable.edittext_background_red);
                    txtUsernameRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtUsernameRestante.setText("");
                    iptUsername.setBackgroundResource(R.drawable.edittext_background);
                }

                if (iptTelefone.getText().toString().isEmpty()) {
                    txtTelefoneRestante.setText("Campo Obrigatório");
                    iptTelefone.setBackgroundResource(R.drawable.edittext_background_red);
                    txtTelefoneRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtTelefoneRestante.setText("");
                    iptTelefone.setBackgroundResource(R.drawable.edittext_background);
                }

                if (!isValidUsername(iptUsername.getText().toString())){
                    txtUsernameRestante.setText("Username inválido");
                    iptUsername.setBackgroundResource(R.drawable.edittext_background_red);
                    txtUsernameRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }
                else {
                    txtUsernameRestante.setText("");
                    iptUsername.setBackgroundResource(R.drawable.edittext_background);
                }
                rotaUserNameTelefone();
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
                    }
                    else{
                        txtUsernameRestante.setText("Username já cadastrado");
                        iptUsername.setBackgroundResource(R.drawable.edittext_background_red);
                        txtUsernameRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                        isValidUser = false;
                    }
                } else {
                    Toast.makeText(CadastroUserTelefoneUsuario.this, "Erro na resposta da API: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<verificaAPI> call, Throwable t) {
                Toast.makeText(CadastroUserTelefoneUsuario.this, "Erro ao conectar ao servidor: " + t.getMessage(), Toast.LENGTH_LONG).show();
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
                Toast.makeText(CadastroUserTelefoneUsuario.this, "Erro ao conectar ao servidor: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        if (isValidUser && isValidTelefone) {
            Intent intent = new Intent(CadastroUserTelefoneUsuario.this, ValidaSMSUsuario.class);
            intent.putExtra("EmailUsuario", EmailUsuario);
            intent.putExtra("SenhaUsuario", SenhaUsuario);
            intent.putExtra("ConfirmarSenha", ConfirmarSenha);
            intent.putExtra("NomeCompleto", iptNomeCompleto.getText().toString());
            intent.putExtra("Username", iptUsername.getText().toString());
            intent.putExtra("Telefone", SemFormatacaoTelefone);
            intent.putExtra("SemFormatacaoTelefone", SemFormatacaoTelefone);
            startActivity(intent);
        }
    }
}

