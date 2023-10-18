package com.example.todostectest.CadastroUsuario;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.todostectest.R;

public class CadastroGeneroPronomeUsuario extends AppCompatActivity {

    private Spinner spinnerOrientacaoSexual;
    private Spinner spinnerGenero;
    private Spinner spinnerPronomes;
    private TextView txtRestante;
    private TextView txtRestante2;
    private TextView txtRestante3;

    String EmailUsuario;
    String SenhaUsuario;
    String NomeCompleto;
    String Username;
    String Telefone;

    int telaAbrindoGenero = 0;
    int telaAbrindoOrienta = 0;
    int telaAbrindoPronome = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_genero_pronome_usuario);

        spinnerOrientacaoSexual = findViewById(R.id.spOrientacao);
        spinnerGenero = findViewById(R.id.spGenero);
        spinnerPronomes = findViewById(R.id.spPronome);

        txtRestante = findViewById(R.id.txtValidaSenhaUsuario);
        txtRestante2 = findViewById(R.id.txtOrientacao);
        txtRestante3 = findViewById(R.id.txtperfil);

        setupSpinnerOrientacaoSexual();
        setupSpinnerGenero();
        setupSpinnerPronomes();

        Intent intent = getIntent();
        EmailUsuario = intent.getStringExtra("EmailUsuario");
        SenhaUsuario = intent.getStringExtra("SenhaUsuario");
        NomeCompleto = intent.getStringExtra("NomeCompleto");
        Username = intent.getStringExtra("Username");
        Telefone = intent.getStringExtra("Telefone");


        spinnerOrientacaoSexual.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerOrientacaoSexual.getSelectedItemPosition() == 0 && telaAbrindoOrienta != 0) {
                    txtRestante2.setText("Campo Obrigatório");
                    spinnerOrientacaoSexual.setBackgroundResource(R.drawable.edittext_background_red);
                    txtRestante2.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtRestante2.setText("");
                    spinnerOrientacaoSexual.setBackgroundResource(R.drawable.edittext_background);
                }

                telaAbrindoOrienta = 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerGenero.getSelectedItemPosition() == 0 && telaAbrindoGenero != 0) {
                    txtRestante.setText("Campo Obrigatório");
                    spinnerGenero.setBackgroundResource(R.drawable.edittext_background_red);
                    txtRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtRestante.setText("");
                    spinnerGenero.setBackgroundResource(R.drawable.edittext_background);
                }
                telaAbrindoGenero = 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerPronomes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerPronomes.getSelectedItemPosition() == 0 && telaAbrindoPronome != 0) {
                    txtRestante3.setText("Campo Obrigatório");
                    spinnerPronomes.setBackgroundResource(R.drawable.edittext_background_red);
                    txtRestante3.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtRestante3.setText("");
                    spinnerPronomes.setBackgroundResource(R.drawable.edittext_background);
                }
                telaAbrindoPronome = 1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void cadastroOnClick(View view) {
        boolean isValid = true;

        if (spinnerOrientacaoSexual.getSelectedItemPosition() == 0) {
            txtRestante.setText("Campo Obrigatório");
            spinnerOrientacaoSexual.setBackgroundResource(R.drawable.edittext_background_red);
            txtRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            isValid = false;
        } else {
            txtRestante.setText("");
            spinnerOrientacaoSexual.setBackgroundResource(R.drawable.edittext_background);
        }

        if (spinnerGenero.getSelectedItemPosition() == 0) {
            txtRestante2.setText("Campo Obrigatório");
            spinnerGenero.setBackgroundResource(R.drawable.edittext_background_red);
            txtRestante2.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            isValid = false;
        } else {
            txtRestante2.setText("");
            spinnerGenero.setBackgroundResource(R.drawable.edittext_background);
        }

        if (spinnerPronomes.getSelectedItemPosition() == 0) {
            txtRestante3.setText("Campo Obrigatório");
            spinnerPronomes.setBackgroundResource(R.drawable.edittext_background_red);
            txtRestante3.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            isValid = false;
        } else {
            txtRestante3.setText("");
            spinnerPronomes.setBackgroundResource(R.drawable.edittext_background);
        }

        int orientacaoSexualPosition = spinnerOrientacaoSexual.getSelectedItemPosition();
        int generoPosition = spinnerGenero.getSelectedItemPosition();
        int pronomesPosition = spinnerPronomes.getSelectedItemPosition();

        if (isValid) {

            Intent intent = new Intent(this, CadastroDescricaoUsuario.class);
            intent.putExtra("EmailUsuario", EmailUsuario);
            intent.putExtra("SenhaUsuario", SenhaUsuario);
            intent.putExtra("NomeCompleto", NomeCompleto);
            intent.putExtra("Username", Username);
            intent.putExtra("Telefone", Telefone);
            intent.putExtra("OrientacaoSexual", orientacaoSexualPosition);
            intent.putExtra("Genero", generoPosition);
            intent.putExtra("Pronomes", pronomesPosition);

            startActivity(intent);
        }
    }

    public void VoltarOnClick(View view) {
        finish();
    }

    private void setupSpinnerOrientacaoSexual() {
        String[] orientacoesSexuais = {"Selecione...", "Heterossexual", "Homossexual", "Bissexual", "Outro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orientacoesSexuais);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrientacaoSexual.setAdapter(adapter);
    }

    private void setupSpinnerGenero() {
        String[] generos = {"Selecione...", "Masculino", "Feminino", "Outro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, generos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenero.setAdapter(adapter);
    }

    private void setupSpinnerPronomes() {
        String[] pronomes = {"Selecione...", "Ele", "Ela", "Outro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pronomes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPronomes.setAdapter(adapter);
    }
}
