package com.example.todostectest.API;

public class UserData {

    private String cnome;
    private String cusername;
    private String csenha;
    private String ctelefone;
    private String cemail;
    private int ncdpronome;
    private int ncdgenero;
    private int ncdsexualidade;
    private String cdescricao;
    private String clinkfoto;

    public UserData(String cnome, String cusername, String csenha, String ctelefone, String cemail, int ncdpronome, int ncdgenero, int ncdsexualidade, String cdescricao, String clinkfoto) {
        this.cnome = cnome;
        this.cusername = cusername;
        this.csenha = csenha;
        this.ctelefone = ctelefone;
        this.cemail = cemail;
        this.ncdpronome = ncdpronome;
        this.ncdgenero = ncdgenero;
        this.ncdsexualidade = ncdsexualidade;
        this.cdescricao = cdescricao;
        this.clinkfoto = clinkfoto;
    }
}
