package com.example.todostectest.CadastroUsuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todostectest.R;
import com.example.todostectest.TelaInicial;

public class ConclusaoCadastroUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclusao_cadastro_usuario);
    }

    public void cadastroOnClick(View view){
        Intent intent = new Intent(ConclusaoCadastroUsuario.this, TelaInicial.class);
        startActivity(intent);
    };
}