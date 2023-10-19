package com.example.todostectest.CadastroUsuario;

import static android.graphics.Color.parseColor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todostectest.API.ApiMobile;
import com.example.todostectest.API.UserData;
import com.example.todostectest.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroUsuarioFotoPerfil extends AppCompatActivity {

    TextView txtPerfil;
    GridView gridView;
    ImageAdapter adapter;
    ImageView lastSelectedImageView;
    String EmailUsuario;
    String SenhaUsuario;
    String NomeCompleto;
    String Username;
    String Telefone;

    String Descricao;
    int OrientacaoSexual;
    int Genero;
    int Pronomes;
    Button btnContinuar;
    String selectedImageUrl;
    Boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario_foto_perfil);

        Intent intent = getIntent();
        EmailUsuario = intent.getStringExtra("EmailUsuario");
        SenhaUsuario = intent.getStringExtra("SenhaUsuario");
        NomeCompleto = intent.getStringExtra("NomeCompleto");
        Username = intent.getStringExtra("Username");
        Telefone = intent.getStringExtra("Telefone");
        Descricao = intent.getStringExtra("Descricao");
        OrientacaoSexual = intent.getIntExtra("OrientacaoSexual",0);
        Genero = intent.getIntExtra("Genero", 0);
        Pronomes = intent.getIntExtra("Pronomes", 0);

        btnContinuar = findViewById(R.id.btnContinuar);

        txtPerfil = findViewById(R.id.txtperfil);

        gridView = findViewById(R.id.gridView);
        adapter = new ImageAdapter(this);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView img = (ImageView) view;

                if (img == lastSelectedImageView) {
                    img.setBackgroundColor(Color.TRANSPARENT);
                    lastSelectedImageView = null; // Nenhum item selecionado
                    txtPerfil.setText("Seleção cancelada!");
                    selectedImageUrl = null;
                    isValid = false;
                } else {
                    if (lastSelectedImageView != null) {
                        lastSelectedImageView.setBackgroundColor(Color.TRANSPARENT);
                    }

                    String message = "Imagem " + (position + 1) + " selecionada!";
                    img.setBackgroundColor(getResources().getColor(R.color.purple_700));
                    txtPerfil.setText(message);
                    isValid = true;
                    lastSelectedImageView = img;
                    selectedImageUrl = adapter.getImageUrl(position);
                }
            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isValid = true;

                if (isValid) {
                    UserData userData = new UserData(
                            NomeCompleto,
                            Username,
                            SenhaUsuario,
                            Telefone,
                            EmailUsuario,
                            Pronomes,
                            Genero,
                            OrientacaoSexual,
                            Descricao,
                            selectedImageUrl
                    );

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://api-3wfy.onrender.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ApiMobile apiService = retrofit.create(ApiMobile.class);

                    Call<Void> call = apiService.cadastrarUsuario(userData);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Intent intent = new Intent(CadastroUsuarioFotoPerfil.this, ConclusaoCadastroUsuario.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(CadastroUsuarioFotoPerfil.this, "Erro ao cadastrar usuário.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(CadastroUsuarioFotoPerfil.this, "Erro ao comunicar com o servidor.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    public void VoltarOnClick(View view) {
        finish();
    }
}
