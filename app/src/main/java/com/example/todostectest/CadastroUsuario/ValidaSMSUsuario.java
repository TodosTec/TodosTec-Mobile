package com.example.todostectest.CadastroUsuario;

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

import com.example.todostectest.R;

import java.util.Random;

public class ValidaSMSUsuario extends AppCompatActivity {

    EditText iptCódigoSMS;
    TextView txtRestante3;
    TextView txtSMS;
    Button btnContinuar;
    String EmailUsuario;
    String SenhaUsuario;
    String NomeCompleto;
    String Username;
    String Telefone;
    String SemFormatacaoTelefone;
    String codigoAleatorio;
    Button btnEnviarSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valida_sms);

        iptCódigoSMS = findViewById(R.id.iptDescricaoUsuario);
        txtSMS       = findViewById(R.id.txtPerguntaUsuario);
        txtRestante3 = findViewById(R.id.txtperfil);
        btnContinuar = findViewById(R.id.btnContinuar);
        btnEnviarSMS = findViewById(R.id.EnviarSMS);

        Intent intent = getIntent();
        EmailUsuario = intent.getStringExtra("EmailUsuario");
        SenhaUsuario = intent.getStringExtra("SenhaUsuario");
        NomeCompleto = intent.getStringExtra("NomeCompleto");
        Username = intent.getStringExtra("Username");
        Telefone = intent.getStringExtra("Telefone");
        SemFormatacaoTelefone = intent.getStringExtra("SemFormatacaoTelefone").substring(2);

        txtSMS.setText("Foi enviado nesse numero de telefone:" + Telefone + " um código de SMS para verificar seu telefone. Insira-o no campo abaixo:");

        codigoAleatorio = gerarCodigoAleatorio(6);

        Toast.makeText(ValidaSMSUsuario.this, "Número de telefone: " + Telefone, Toast.LENGTH_LONG).show();

        iptCódigoSMS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (iptCódigoSMS.getText().toString().isEmpty()) {
                    txtRestante3.setText("Campo Obrigatório");
                    iptCódigoSMS.setBackgroundResource(R.drawable.edittext_background_red);
                    txtRestante3.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtRestante3.setText("");
                    iptCódigoSMS.setBackgroundResource(R.drawable.edittext_background);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnEnviarSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS();
            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ValidaSMSUsuario.this, codigoAleatorio, Toast.LENGTH_LONG).show();
                boolean isValid = true;

                if (iptCódigoSMS.getText().toString().isEmpty()) {
                    txtRestante3.setText("Campo Obrigatório");
                    iptCódigoSMS.setBackgroundResource(R.drawable.edittext_background_red);
                    txtRestante3.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                    isValid = false;
                } else {
                    txtRestante3.setText("");
                    iptCódigoSMS.setBackgroundResource(R.drawable.edittext_background);

                    if (!iptCódigoSMS.getText().toString().equals(codigoAleatorio))
                    {
                        txtRestante3.setText("Códigos diferentes informados");
                        iptCódigoSMS.setBackgroundResource(R.drawable.edittext_background_red);
                        txtRestante3.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                        isValid = false;
                    }
                    else{
                        txtRestante3.setText("");
                        iptCódigoSMS.setBackgroundResource(R.drawable.edittext_background);
                    }
                }
                if (isValid) {
                    Intent intent = new Intent(ValidaSMSUsuario.this, CadastroGeneroPronomeUsuario.class);
                    intent.putExtra("EmailUsuario", EmailUsuario);
                    intent.putExtra("SenhaUsuario", SenhaUsuario);
                    intent.putExtra("NomeCompleto", NomeCompleto);
                    intent.putExtra("Username", Username);
                    intent.putExtra("Telefone", Telefone);
                    startActivity(intent);

                }
            }
        });
    }

    private String gerarCodigoAleatorio(int numeroDigitos) {
        StringBuilder codigo = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < numeroDigitos; i++) {
            codigo.append(random.nextInt(10));
        }

        return codigo.toString();
    }

    private void sendSMS() {
            Intent intent=new Intent(getApplicationContext(), ValidaSMSUsuario.class);
            PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,PendingIntent.FLAG_IMMUTABLE);

            SmsManager sms=SmsManager.getDefault();
            sms.sendTextMessage(SemFormatacaoTelefone, null, "Olá! Seu código de validação para o aplicativo TodosTec é: " + codigoAleatorio, pi,null);
    }



    public void VoltarOnClick(View view) {
        finish();
    }

}
