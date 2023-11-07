package com.example.todostectest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class InternetError extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_error);

    }

    public void VoltarOnClick(View view) {
        ManipulaTela.verificaTela.setTelaAberta(false);
        ManipulaTela.verificaTela.setStoppedRunnable(true);
        finish();
    }

}
