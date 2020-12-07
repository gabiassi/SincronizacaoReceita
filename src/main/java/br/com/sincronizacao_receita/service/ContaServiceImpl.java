package br.com.sincronizacao_receita.service;

import br.com.sincronizacao_receita.model.Conta;
import br.com.sincronizacao_receita.utils.DbcUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContaServiceImpl implements ContaService {

    //TODO: Nesta classe vou simular servicos relacionado a contas considerando JPA e nao vou ter tempo
    // mas criaria um @Repository para a tabela conta

    //private final ContaRepository contaRepository;
    private List<Conta> contas;

    public ContaServiceImpl() {
        //TODO: Como nao vou ter tempo de implementar o JPA e criar Repository vou trabalhar somente com um array criado em construtor
            /*ContaRepository contaRepository) {
        this.contaRepository = contaRepository;*/

        //TODO: para o teste como estamos instanciando a lista e garantindo que vai estar diferente de null, vou pular alguns testes que evitam nullPointer
        contas = new ArrayList<>();
        double saldo = 52.25;

        Conta c1 = new Conta(1L, "000X", "955-1", saldo, "A");
        Conta c2 = new Conta(2L, "000X", "985-1", saldo, "A");
        Conta c3 = new Conta(3L, "000X", "925-1", saldo, "A");

        contas.add(c1);
        contas.add(c2);
        contas.add(c3);

    }

    @Override
    public List<Conta> findByAgenciaConta(String agencia, String conta) {
        //TODO: Como nao vou ter tempo de implementar o JPA e criar Repository vou trabalhar somente com um array criado em construtor
        //if (agencia != null && !agencia.isEmpty() && conta != null && !conta.isEmpty()) {
        //    return contaRepository.findByAgenciaConta(agencia, conta);
        //}

        List<Conta> ret = new ArrayList<Conta>();

        //TODO: Se for passado agencia e conta null podemos retornar uma lista vazia
        //if (agencia == null && conta == null)
        //   return ret;

        if (!DbcUtil.isEmpty(contas)) { //TODO: metodo que utilizo para simplificar as verificacoes de isEmpty
            for (Conta con : contas) {
                //TODO: Como agencia e conta seriam colunas NotEmpty vamos considerar que nunca serao nulas
                if ((agencia == null || (agencia != null && con.getAgencia().equals(agencia))) &&
                        (conta == null || (conta != null && con.getConta().equals(conta)))) {
                    ret.add(DbcUtil.clone(con)); //TODO: metodo que utilizo para clonar objetos e nao acabar alterando o original por engano
                }
            }
        }

        return ret;
    }

    @Override
    public Optional<Conta> findById(Long id) {
        //TODO: Como nao vou ter tempo de implementar o JPA e criar Repository vou trabalhar somente com um array criado em construtor
        //return contaRepository.findById(id);

        Conta ret = null;
        Conta idPar = new Conta(id); //TODO: Com o metodo equals implementado podemos comparar facilmente assim

        if (id != null && !DbcUtil.isEmpty(contas)) {
            for (Conta con : contas) {
                if (idPar.equals(con))
                    ret = DbcUtil.clone(con);
            }
        }

        return Optional.ofNullable(ret);
    }

    @Override
    public Conta create(Conta conta) {
        //TODO: Como nao vou ter tempo de implementar o JPA e criar Repository vou trabalhar somente com um array criado em construtor
        // return contaRepository.save(conta);
        //TODO: Sem a implementacao do JPA nao vai persistir os dados e no treinamento podemos incluir valores null, mas com o JPA funcionando
        // isso nao vai acontecer
        //TODO: Aqui podemos criar tratativas de erro mais especificas de erros no JPA porem como vou so criar de exemplo utilizando um array so vamos adc
        if (conta != null)
            contas.add(conta);

        return conta;
    }

    @Override
    public void delete(Long id) {
        //TODO: Como nao vou ter tempo de implementar o JPA e criar Repository vou trabalhar somente com um array criado em construtor
        //contaRepository.deleteById(id);

        //TODO: Com o metodo equals implementado podemos comparar facilmente assim
        Conta idPar = new Conta(id);

        if (!DbcUtil.isEmpty(contas) && contas.contains(idPar))
            contas.remove(idPar);

    }
}
