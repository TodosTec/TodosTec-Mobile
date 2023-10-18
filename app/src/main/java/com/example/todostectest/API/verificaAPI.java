package com.example.todostectest.API;

import com.google.gson.annotations.SerializedName;

public class verificaAPI {

    @SerializedName("mensagem")
    private String mensagem;

    @SerializedName("status")
    private String status;

    public String getMensagem() {
        return mensagem;
    }

    public String getStatus() {
        return status;
    }

}
