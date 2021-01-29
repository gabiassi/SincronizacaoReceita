package br.com.sincronizacao_receita.model;

import java.io.Serializable;

public class Conta implements Serializable {

    protected String agencia;
    protected String conta;
    protected double saldo;
    protected String status;

    public Conta() {
    }

    public Conta(String agencia,
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
