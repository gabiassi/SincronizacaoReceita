package br.com.dbc.sincronizacao_receita.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"agencia", "conta"})})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Conta implements Serializable {

    //TODO: Nao daria tempo mas para implementar a parte de comunicacao com banco como se tivessemos a tabela conta no banco de dados.
    // Claro que nao teriamos servicos fornecendo dados de contas assim tao abertamente como exemplificados neste teste. Mas para mostrar
    // alguns servicos de GET e simular JPA vou criar com as notacoes e tudo mesmo nao interagindo com banco de dados
    //TODO: utilizo a JsonIgnoreProperties para ignorar campos/colunas vazias em json (quando utilizado)
    //TODO: utilizei a UniqueConstraint imaginando uma tabela com PK(ID) + UK da agencia e conta somente para criar servicos e mostrar conhecimentos
    // que, talvez, na pratica deste teste nao fosse necessario. Mas quero mostrar algumas coisas que ja tive contato no dia a dia
    //TODO: para criar e utilizar as funcoes/notacoes da classe precisei adicionar a dependencia do JPA e validation do springframework no pom

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String agencia;

    @NotEmpty
    private String conta;

    private double saldo;

    @NotEmpty
    private String status;

    public Conta() {
    }

    public Conta(Long id) {
        this.id = id;
    }

    public Conta(Long id, String agencia,
                  String conta,
                  double saldo,
                  String status) {
        this.id = id;
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta1 = (Conta) o;
        return Objects.equals(id, conta1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
