package com.example.todostectest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class TelaInicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        Button btnCadastro = findViewById(R.id.btnEmpresa);
        Button btnLogin = findViewById(R.id.btnUsuario);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaInicial.this, EscolhaCadastro.class);
                startActivity(intent);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verificaRede()){
                Intent intent = new Intent(TelaInicial.this, TelaWebView.class);
                startActivity(intent);
                }
                else {
                    startActivity(new Intent(TelaInicial.this, InternetError.class));
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (ManipulaTela.verificaTela.isInternetError()){
            if (!ManipulaTela.verificaTela.isIsOpenInternetError()) {
                Intent intent = new Intent(TelaInicial.this, InternetError.class);
                ManipulaTela.verificaTela.setInternetError(false);
                ManipulaTela.verificaTela.setIsOpenInternetError(true);
                startActivity(intent);
            }
        }
    }
    private boolean verificaRede() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }
}