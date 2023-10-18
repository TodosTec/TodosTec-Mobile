package com.example.todostectest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.todostectest.CadastroUsuario.CadastroEmailSenhaUsuario;

public class TelaInicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        Button btnCadastro = findViewById(R.id.btnCadastro);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaInicial.this, CadastroEmailSenhaUsuario.class);
                startActivity(intent);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaInicial.this, TelaWebView.class);
                startActivity(intent);
            }
        });
    }
}