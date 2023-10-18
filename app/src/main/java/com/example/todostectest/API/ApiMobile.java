package com.example.todostectest.API;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiMobile {
    @GET("api/todostec/selecionar/email/{cemail}")
    Call<verificaAPI> verificaEmail(@Path("cemail") String email);
    @GET("api/todostec/selecionar/telefone/{telefone}")
    Call<verificaAPI> verificaTelefone(@Path("telefone") String telefone);
    @GET("api/todostec/selecionar/verificarUsername/{username}")
    Call<verificaAPI> verificaUserName(@Path("username") String username);

    @POST("api/todostec/inserir")
    Call<Void> cadastrarUsuario(@Body UserData userData);

    @POST("api/todostec/inserir")
    Call<Void> cadastrarEmpresa(@Body CompanyData companyData);

}

