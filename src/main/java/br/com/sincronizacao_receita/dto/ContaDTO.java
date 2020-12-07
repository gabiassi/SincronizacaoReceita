package br.com.sincronizacao_receita.dto;

import java.io.Serializable;

public class ContaDTO implements Serializable {

    //TODO: aqui como vamos atualizar contas com base em arquivo vou utilizar o DTO e os model's somente para JPA
    private Long id;
    private String agencia;
    private String conta;
    private double saldo;
    private String status;
    private Boolean retorno;

    public ContaDTO() {
    }

    public ContaDTO(String agencia,
                     String conta,
                     double saldo,
                     String status,
                     Boolean retorno) {
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
        this.status = status;
        this.retorno = retorno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getRetorno() {
        return retorno;
    }

    public void setRetorno(Boolean retorno) {
        this.retorno = retorno;
    }
}
