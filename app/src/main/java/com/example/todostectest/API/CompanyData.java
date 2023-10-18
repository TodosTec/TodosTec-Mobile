package com.example.todostectest.API;

public class CompanyData {
    private String cnome;
    private String cusername;
    private String csenha;
    private String ctelefone;
    private String cemail;
    private String cdescricao;

    public CompanyData(String cnome, String cusername, String csenha, String ctelefone, String cemail, String cdescricao) {
        this.cnome = cnome;
        this.cusername = cusername;
        this.csenha = csenha;
        this.ctelefone = ctelefone;
        this.cemail = cemail;
        this.cdescricao = cdescricao;
    }
}
