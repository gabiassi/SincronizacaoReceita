package br.com.sincronizacao_receita.dto;

import java.io.Serializable;

public class ContaDTO implements Serializable {

    private String agencia;
    private String conta;
    private double saldo;
    private String status;

    public ContaDTO() {
    }

    public ContaDTO(String agencia,
                     String conta,
                     double saldo,
                     String status) {
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
        this.status = status;
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

    @Override
    public String toString() {
        return "ContaDTO{" +
                "agencia='" + agencia + '\'' +
                ", conta='" + conta + '\'' +
                ", saldo=" + saldo +
                ", status='" + status + '\'' +
                '}';
    }
}
