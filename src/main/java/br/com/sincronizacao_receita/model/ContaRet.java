package br.com.sincronizacao_receita.model;

import java.io.Serializable;

public class ContaRet extends Conta implements Serializable {

    private boolean resultado;

    public ContaRet() {
    }

    public ContaRet(Conta ct, boolean resultado) {
        agencia = ct.getAgencia();
        conta = ct.getConta();
        saldo = ct.getSaldo();
        status = ct.getStatus();
        this.resultado = resultado;
    }

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "ContaRetDTO{" +
                "agencia='" + agencia + '\'' +
                ", conta='" + conta + '\'' +
                ", saldo=" + saldo +
                ", status='" + status + '\'' +
                ", resultado=" + resultado +
                '}';
    }
}
