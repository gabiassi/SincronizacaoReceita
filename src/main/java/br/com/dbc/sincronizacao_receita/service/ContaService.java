package br.com.dbc.sincronizacao_receita.service;

import br.com.dbc.sincronizacao_receita.model.Conta;

import java.util.List;
import java.util.Optional;

public interface ContaService {

    public List<Conta> findByAgenciaConta(String agencia, String conta);

    public Optional<Conta> findById(Long id);

    public Conta create(Conta conta);

    public void delete(Long id);

}
