package com.example.todostectest.CadastroEmpresa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todostectest.CadastroUsuario.ConclusaoCadastroUsuario;
import com.example.todostectest.ManipulaTela;
import com.example.todostectest.R;
import com.example.todostectest.TelaInicial;

public class ConclusaoCadastroEmpresa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclusao_cadastro_empresa);
        ManipulaTela.verificaTela.setVerificaCadastro(false);
    }

    public void cadastroOnClick(View view){
        Intent intent = new Intent(ConclusaoCadastroEmpresa.this, TelaInicial.class);
        startActivity(intent);
    };
}