package com.example.todostectest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.todostectest.CadastroEmpresa.CadastroEmailSenhaEmpresa;
import com.example.todostectest.CadastroUsuario.CadastroEmailSenhaUsuario;

public class EscolhaCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_cadastro);

        Button btnEmpresa = findViewById(R.id.btnEmpresa);
        Button btnUsuario = findViewById(R.id.btnUsuario);

        btnEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EscolhaCadastro.this, CadastroEmailSenhaEmpresa.class);
                startActivity(intent);

            }
        });

        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EscolhaCadastro.this, CadastroEmailSenhaUsuario.class);
                startActivity(intent);
            }
        });

    }
    public void VoltarOnClick(View view) {
        finish();
    }

}