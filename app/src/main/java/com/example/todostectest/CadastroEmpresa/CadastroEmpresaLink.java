package com.example.todostectest.CadastroEmpresa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todostectest.CadastroUsuario.ValidaSMSUsuario;
import com.example.todostectest.R;

import java.util.Random;

public class CadastroEmpresaLink extends AppCompatActivity {

    EditText iptLink;
    TextView txtRestante3;
    Button btnContinuar;
    String EmailUsuario;
    String SenhaUsuario;
    String NomeCompleto;
    String Username;
    String Telefone;

    boolean isValid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_empresa_link);

        iptLink = findViewById(R.id.iptLink);
        txtRestante3 = findViewById(R.id.txtFeedbackDescricao);
        btnContinuar = findViewById(R.id.btnContinuarEmpresa);

        Intent intent = getIntent();
        EmailUsuario = intent.getStringExtra("EmailUsuario");
        SenhaUsuario = intent.getStringExtra("SenhaUsuario");
        NomeCompleto = intent.getStringExtra("NomeCompleto");
        Username = intent.getStringExtra("Username");
        Telefone = intent.getStringExtra("Telefone");

        iptLink.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (iptLink.getText().toString().trim().isEmpty()) {
                    txtRestante3.setText("Campo Obrigatório");
                    isValid = false;
                    iptLink.setBackgroundResource(R.drawable.edittext_background_red);
                    txtRestante3.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtRestante3.setText("");
                    iptLink.setBackgroundResource(R.drawable.edittext_background);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (iptLink.getText().toString().trim().isEmpty()) {
                    txtRestante3.setText("Campo Obrigatório");
                    isValid = false;
                    iptLink.setBackgroundResource(R.drawable.edittext_background_red);
                    txtRestante3.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtRestante3.setText("");
                    iptLink.setBackgroundResource(R.drawable.edittext_background);
                }
                if (isValid) {
                    Intent intent = new Intent(CadastroEmpresaLink.this, CadastroEmpresaDescricao.class);
                    intent.putExtra("EmailUsuario", EmailUsuario);
                    intent.putExtra("SenhaUsuario", SenhaUsuario);
                    intent.putExtra("NomeCompleto", NomeCompleto);
                    intent.putExtra("Username", Username);
                    intent.putExtra("Telefone", Telefone);
                    intent.putExtra("Link", iptLink.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    public void VoltarOnClick(View view) {
        finish();
    }
}