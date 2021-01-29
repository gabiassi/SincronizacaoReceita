package br.com.sincronizacao_receita.config.processor;

import br.com.sincronizacao_receita.model.Conta;
import br.com.sincronizacao_receita.model.ContaRet;
import br.com.sincronizacao_receita.service.ReceitaService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LerContasProcessorConfig {
    @Bean
    public ItemProcessor<Conta, ContaRet> lerContasProcessor() {
        return conta -> enviaSefaz(conta);
    }

    public ContaRet enviaSefaz(Conta conta) {
        ReceitaService receitaService = new ReceitaService();

        boolean ret;

        try {
            ret = receitaService.atualizarConta(conta.getAgencia(), conta.getConta(), conta.getSaldo(), conta.getStatus());
        } catch (InterruptedException e) {
            //TODO: Aqui podemos tratar os erros de acordo com a necessidade e/ou funcionalidade do servico,
            // neste caso mostro no log e continua proxima linha/arquivo
            ret = false;
        }

        ContaRet contaRet = new ContaRet(conta, ret);

        return contaRet;
    }
}