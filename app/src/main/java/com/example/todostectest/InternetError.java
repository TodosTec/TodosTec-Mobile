package com.example.todostectest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;


public class InternetError extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_error);

    }

    public void VoltarOnClick(View view) {
        Teste.verificaTela.setTelaAberta(false);
        Teste.verificaTela.setStoppedRunnable(true);
        finish();
    }

}
