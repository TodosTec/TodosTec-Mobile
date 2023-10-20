package com.example.todostectest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.todostectest.CadastroUsuario.CadastroEmailSenhaUsuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();

                myRef.child("produto").child("001").child("descr").setValue("Celular NOKIA");

                Intent intent = new Intent(TelaInicial.this, TelaWebView.class);
                startActivity(intent);
            }
        });
    }
}