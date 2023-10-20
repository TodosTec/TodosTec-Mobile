package com.example.todostectest.CadastroUsuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todostectest.API.ApiMobile;
import com.example.todostectest.API.verificaAPI;
import com.example.todostectest.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroEmailSenhaUsuario extends AppCompatActivity {

    EditText iptEmail;
    EditText iptSenha;
    EditText iptConfirmarSenha;
    TextView txtRestante;
    TextView txtValidaSenha;
    TextView txtValidaSenha2;
    TextView txtValidaSenha3;
    TextView txtRestante3;
    TextView txtCadastroUsuario;
    LinearLayout linearEmail;
    LinearLayout linearSenha;
    LinearLayout linearConfirmar;
    ImageView imgvSenha;
    ImageView imgvConfirmar;
    boolean isValid = true;
    private boolean senhaVisivel = false;

    private verificaAPI RespostaAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_email_senha_usuario);


        //Capturando os inputs da tela
        iptEmail = findViewById(R.id.iptEmailUsuario);
        iptSenha = findViewById(R.id.iptSenhaUsuario);
        iptConfirmarSenha = findViewById(R.id.iptConfirmarUsuario);

        //Capturando os linearLayout
        linearEmail = findViewById(R.id.linearEmail);
        linearSenha = findViewById(R.id.linearSenha);
        linearConfirmar = findViewById(R.id.linearConfirmar);

        //Capturando as imageViews
        imgvSenha =  findViewById(R.id.mostrarSenha);
        imgvConfirmar =  findViewById(R.id.mostrarConfirmar);

        //Capturando os TextViews
        txtRestante = findViewById(R.id.txtFeedback1);
        txtRestante3 = findViewById(R.id.txtperfil);
        txtCadastroUsuario = findViewById(R.id.txtCadastroUsuario);
        txtValidaSenha = findViewById(R.id.txtValidaSenhaUsuario);
        txtValidaSenha2 = findViewById(R.id.txtValidaSenhaUsuario2);
        txtValidaSenha3 = findViewById(R.id.txtValidaSenhaUsuario3);

        //Listener para mostrar a senha do usuário quando clicado no imageview
        imgvSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSenhaVisibility(iptSenha, imgvSenha);
            }
        });

        //Listener para mostrar a senha do usuário quando clicado no imageview
        imgvConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSenhaVisibility(iptConfirmarSenha, imgvConfirmar);
            }
        });

        iptEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                if (iptEmail.getText().toString().isEmpty()) {
                    txtRestante.setText("Campo Obrigatório");
                    linearEmail.setBackgroundResource(R.drawable.edittext_background_red);
                    txtRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtRestante.setText("");
                    linearEmail.setBackgroundResource(R.drawable.edittext_background);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        iptEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {

                    if (iptEmail.getText().toString().isEmpty()) {
                        txtRestante.setText("Campo Obrigatório");
                        linearEmail.setBackgroundResource(R.drawable.edittext_background_red);
                        txtRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                    } else {
                        txtRestante.setText("");
                        linearEmail.setBackgroundResource(R.drawable.edittext_background);
                    }

                    if (isValidEmail(iptEmail.getText().toString())) {
                        txtRestante.setText("");
                        linearEmail.setBackgroundResource(R.drawable.edittext_background);
                    } else {
                        txtRestante.setText("Email inválido");
                        linearEmail.setBackgroundResource(R.drawable.edittext_background_red);
                        txtRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                    }
                }
            }
        });

        iptSenha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!iptSenha.getText().toString().isEmpty()) {
                    validarSenha(iptSenha);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (iptSenha.getText().toString().isEmpty()) {
                    txtValidaSenha.setText("Campo Obrigatório");
                    txtValidaSenha.setTextSize(14);
                    txtValidaSenha2.setText("");
                    txtValidaSenha3.setText("");
                    linearSenha.setBackgroundResource(R.drawable.edittext_background_red);
                    txtValidaSenha.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtValidaSenha.setText("• Deve conter no mínimo 8 caracteres.");
                    txtValidaSenha2.setText("• Deve conter pelo menos uma letra maiúscula.");
                    txtValidaSenha3.setText("• Deve conter um número, uma letra e um caractere especial.");
                    linearSenha.setBackgroundResource(R.drawable.edittext_background);
                }
            }
        });

        iptSenha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (iptEmail.getText().toString().isEmpty()) {
                        txtRestante.setText("Campo Obrigatório");
                        linearSenha.setBackgroundResource(R.drawable.edittext_background_red);

                    } else {
                        txtRestante.setText("");
                        linearSenha.setBackgroundResource(R.drawable.edittext_background);
                    }
                }
            }
        });

        iptConfirmarSenha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (iptConfirmarSenha.getText().toString().isEmpty()) {
                    txtRestante3.setText("Campo Obrigatório");
                    linearConfirmar.setBackgroundResource(R.drawable.edittext_background_red);
                    txtRestante3.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtRestante3.setText("");
                    linearConfirmar.setBackgroundResource(R.drawable.edittext_background);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        txtCadastroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isValid = true;
                if (iptEmail.getText().toString().isEmpty()) {
                    txtRestante.setText("Campo Obrigatório");
                    linearEmail.setBackgroundResource(R.drawable.edittext_background_red);
                    isValid = false;
                } else {
                    txtRestante.setText("");
                    linearEmail.setBackgroundResource(R.drawable.edittext_background);
                }

                if (iptSenha.getText().toString().isEmpty()) {
                    txtValidaSenha.setText("Campo Obrigatório");
                    txtValidaSenha.setTextSize(14);
                    txtValidaSenha2.setText("");
                    txtValidaSenha3.setText("");
                    linearSenha.setBackgroundResource(R.drawable.edittext_background_red);
                    txtValidaSenha.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtValidaSenha.setText("• Deve conter no mínimo 8 caracteres.");
                    txtValidaSenha2.setText("• Deve conter pelo menos uma letra maiúscula.");
                    txtValidaSenha3.setText("• Deve conter um número, uma letra e um caractere especial.");
                    linearSenha.setBackgroundResource(R.drawable.edittext_background);
                    txtValidaSenha.setTextSize(10);
                }

                if (!iptSenha.getText().toString().isEmpty()) {
                    validarSenha(iptSenha);
                }
                if (iptEmail.getText().toString().isEmpty()) {
                    isValid = false;
                    txtRestante.setText("Campo Obrigatório");
                    linearEmail.setBackgroundResource(R.drawable.edittext_background_red);
                    txtRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));

                } else {
                    if (isValidEmail(iptEmail.getText().toString())) {
                        txtRestante.setText("");
                        linearEmail.setBackgroundResource(R.drawable.edittext_background);
                    } else {
                        txtRestante.setText("Email inválido");
                        isValid = false;
                        linearEmail.setBackgroundResource(R.drawable.edittext_background_red);
                        txtRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                    }
                }

                if (iptConfirmarSenha.getText().toString().isEmpty()) {
                    isValid = false;
                    txtRestante3.setText("Campo Obrigatório");
                    linearConfirmar.setBackgroundResource(R.drawable.edittext_background_red);
                    txtRestante3.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    txtRestante3.setText("");
                    linearConfirmar.setBackgroundResource(R.drawable.edittext_background);
                }

                if (isValid) {
                    validarSenha(iptSenha);

                    if (!iptSenha.getText().toString().equals(iptConfirmarSenha.getText().toString())) {
                        txtRestante3.setText("As senhas não correspondem");
                        linearSenha.setBackgroundResource(R.drawable.edittext_background_red);
                        linearConfirmar.setBackgroundResource(R.drawable.edittext_background_red);
                        txtRestante3.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                        isValid = false;
                    } else {
                        txtRestante3.setText("");
                        linearSenha.setBackgroundResource(R.drawable.edittext_background);
                        linearConfirmar.setBackgroundResource(R.drawable.edittext_background);
                    }


                }
                rotaEmail();
            }
        });
    }

    public void cadastroOnClick(View view) {
        isValid = true;
        if (iptEmail.getText().toString().isEmpty()) {
            txtRestante.setText("Campo Obrigatório");
            linearEmail.setBackgroundResource(R.drawable.edittext_background_red);
            isValid = false;
        } else {
            txtRestante.setText("");
            linearEmail.setBackgroundResource(R.drawable.edittext_background);
        }

        if (iptSenha.getText().toString().isEmpty()) {
            txtValidaSenha.setText("Campo Obrigatório");
            txtValidaSenha.setTextSize(14);
            txtValidaSenha2.setText("");
            txtValidaSenha3.setText("");
            linearSenha.setBackgroundResource(R.drawable.edittext_background_red);
            txtValidaSenha.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        } else {
            txtValidaSenha.setText("• Deve conter no mínimo 8 caracteres.");
            txtValidaSenha2.setText("• Deve conter pelo menos uma letra maiúscula.");
            txtValidaSenha3.setText("• Deve conter um número, uma letra e um caractere especial.");
            linearSenha.setBackgroundResource(R.drawable.edittext_background);
            txtValidaSenha.setTextSize(10);
        }

        if (!iptSenha.getText().toString().isEmpty()) {
            validarSenha(iptSenha);
        }
        if (iptEmail.getText().toString().isEmpty()) {
            isValid = false;
            txtRestante.setText("Campo Obrigatório");
            linearEmail.setBackgroundResource(R.drawable.edittext_background_red);
            txtRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));

        } else {
            if (isValidEmail(iptEmail.getText().toString())) {
                txtRestante.setText("");
                linearEmail.setBackgroundResource(R.drawable.edittext_background);
            } else {
                txtRestante.setText("Email inválido");
                isValid = false;
                linearEmail.setBackgroundResource(R.drawable.edittext_background_red);
                txtRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            }
        }

        if (iptConfirmarSenha.getText().toString().isEmpty()) {
            isValid = false;
            txtRestante3.setText("Campo Obrigatório");
            linearConfirmar.setBackgroundResource(R.drawable.edittext_background_red);
            txtRestante3.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        } else {
            txtRestante3.setText("");
            linearConfirmar.setBackgroundResource(R.drawable.edittext_background);
        }

        if (isValid) {
            validarSenha(iptSenha);

            if (!iptSenha.getText().toString().equals(iptConfirmarSenha.getText().toString())) {
                txtRestante3.setText("As senhas não correspondem");
                linearSenha.setBackgroundResource(R.drawable.edittext_background_red);
                linearConfirmar.setBackgroundResource(R.drawable.edittext_background_red);
                txtRestante3.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                isValid = false;
            } else {
                txtRestante3.setText("");
                linearSenha.setBackgroundResource(R.drawable.edittext_background);
                linearConfirmar.setBackgroundResource(R.drawable.edittext_background);
            }
        }
        if(isValid) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api-3wfy.onrender.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiMobile apiService = retrofit.create(ApiMobile.class);

            String email = iptEmail.getText().toString();

            Call<verificaAPI> call = apiService.verificaEmail(email);

            call.enqueue(new Callback<verificaAPI>() {
                @Override
                public void onResponse(Call<verificaAPI> call, Response<verificaAPI> response) {
                    if (response.isSuccessful()) {
                        RespostaAPI = response.body();
                        if (RespostaAPI.getMensagem().equals("Email liberado")) {
                            isValid = true;
                        } else {
                            txtRestante.setText("Email já cadastrado");
                            linearEmail.setBackgroundResource(R.drawable.edittext_background_red);
                            txtRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                            isValid = false;
                        }

                        if (isValid) {
                            String EmailUsuario = iptEmail.getText().toString();
                            String SenhaUsuario = iptSenha.getText().toString();

                            Intent intent = new Intent(CadastroEmailSenhaUsuario.this, CadastroUserTelefoneUsuario.class);
                            intent.putExtra("EmailUsuario", EmailUsuario);
                            intent.putExtra("SenhaUsuario", SenhaUsuario);
                            startActivity(intent);
                        }

                    } else {
                        Toast.makeText(CadastroEmailSenhaUsuario.this, "Erro ao conectar ao servidor: " + response.code(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<verificaAPI> call, Throwable t) {
                    Toast.makeText(CadastroEmailSenhaUsuario.this, "Erro ao conectar ao servidor: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void validarSenha(EditText campo) {
        String senha = campo.getText().toString();
        if (senha.length() < 8) {
            txtValidaSenha.setTextColor(getResources().getColor(R.color.vermelho));
        } else {
            txtValidaSenha.setTextColor(getResources().getColor(R.color.verde));
        }

        if (!contemLetraMaiuscula(senha)) {
            txtValidaSenha2.setTextColor(getResources().getColor(R.color.vermelho));
        } else {
            txtValidaSenha2.setTextColor(getResources().getColor(R.color.verde));
        }

        if (!contemNumeroLetraEspecial(senha)) {
            txtValidaSenha3.setTextColor(getResources().getColor(R.color.vermelho));
        } else {
            txtValidaSenha3.setTextColor(getResources().getColor(R.color.verde));
        }
    }

    private boolean contemLetraMaiuscula(String senha) {
        for (char c : senha.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean contemNumeroLetraEspecial(String senha) {
        boolean contemNumero = false;
        boolean contemEspecial = false;

        for (char c : senha.toCharArray()) {
            if (Character.isDigit(c)) {
                contemNumero = true;
            } else if (!Character.isLetterOrDigit(c)) {
                contemEspecial = true;
            }
        }

        return contemNumero && contemEspecial;
    }

    private void toggleSenhaVisibility(EditText campoSenha, ImageView imgView) {
        senhaVisivel = !senhaVisivel;

        if (senhaVisivel) {
            campoSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            imgView.setImageResource(R.drawable.olhoaberto);
        } else {
            campoSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            imgView.setImageResource(R.drawable.olhofechado);
        }

        campoSenha.setSelection(campoSenha.getText().length());
    }

    private boolean isValidEmail(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void VoltarOnClick(View view) {
        finish();
    }

    public void rotaEmail() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-3wfy.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiMobile apiService = retrofit.create(ApiMobile.class);

        String email = iptEmail.getText().toString();

        Call<verificaAPI> call = apiService.verificaEmail(email);

        call.enqueue(new Callback<verificaAPI>() {
            @Override
            public void onResponse(Call<verificaAPI> call, Response<verificaAPI> response) {
                if (response.isSuccessful()) {
                    RespostaAPI = response.body();
                    if (RespostaAPI.getMensagem().equals("Email liberado")){
                        isValid = true;
                    }
                    else{
                        txtRestante.setText("Email já cadastrado");
                        linearEmail.setBackgroundResource(R.drawable.edittext_background_red);
                        txtRestante.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                        isValid = false;
                    }

                    if (isValid) {
                        String EmailUsuario = iptEmail.getText().toString();
                        String SenhaUsuario = iptSenha.getText().toString();

                        Intent intent = new Intent(CadastroEmailSenhaUsuario.this, CadastroUserTelefoneUsuario.class);
                        intent.putExtra("EmailUsuario", EmailUsuario);
                        intent.putExtra("SenhaUsuario", SenhaUsuario);
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(CadastroEmailSenhaUsuario.this, "Erro ao conectar ao servidor: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<verificaAPI> call, Throwable t) {
                Toast.makeText(CadastroEmailSenhaUsuario.this, "Erro ao conectar ao servidor: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
