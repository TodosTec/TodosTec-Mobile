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
import com.example.todostectest.API.UserData;
import com.example.todostectest.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroDescricaoUsuario extends AppCompatActivity {

    EditText iptDescricao;
    TextView txtRestante;
    Button btnContinuar;
    String EmailUsuario;
    String SenhaUsuario;
    String ConfirmarSenha;
    String NomeCompleto;
    String Username;
    String Telefone;
    int OrientacaoSexual;
    int Genero;
    int Pronomes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_descricao_usuario);

        iptDescricao = findViewById(R.id.iptDescricaoUsuario);
        txtRestante = findViewById(R.id.txtperfil);
        btnContinuar = findViewById(R.id.btnContinuar);

        Intent intent = getIntent();
        EmailUsuario = intent.getStringExtra("EmailUsuario");
        SenhaUsuario = intent.getStringExtra("SenhaUsuario");
        ConfirmarSenha = intent.getStringExtra("ConfirmarSenha");
        NomeCompleto = intent.getStringExtra("NomeCompleto");
        Username = intent.getStringExtra("Username");
        Telefone = intent.getStringExtra("Telefone");
        OrientacaoSexual = intent.getIntExtra("OrientacaoSexual",0);
        Genero = intent.getIntExtra("Genero", 0);
        Pronomes = intent.getIntExtra("Pronomes", 0);

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
                  Intent intent = new Intent(CadastroDescricaoUsuario.this, CadastroUsuarioFotoPerfil.class);
                  intent.putExtra("EmailUsuario", EmailUsuario);
                  intent.putExtra("SenhaUsuario", SenhaUsuario);
                  intent.putExtra("NomeCompleto", NomeCompleto);
                  intent.putExtra("Username", Username);
                  intent.putExtra("Telefone", Telefone);
                  intent.putExtra("OrientacaoSexual", OrientacaoSexual);
                  intent.putExtra("Genero", Genero);
                  intent.putExtra("Pronomes", Pronomes);
                  intent.putExtra("Descricao", iptDescricao.getText().toString());
                  startActivity(intent);
              }
          }
      });
    }

    public void VoltarOnClick(View view) {
        finish();
    }
}
