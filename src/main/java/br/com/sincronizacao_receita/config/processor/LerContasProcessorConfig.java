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
        return this::enviaSefaz;
    }

    public ContaRet enviaSefaz(Conta conta) {
        ReceitaService receitaService = new ReceitaService();

        boolean ret;

        try {
            ret = receitaService.atualizarConta(conta.getAgencia(), conta.getConta(), conta.getSaldo(), conta.getStatus());
        } catch (InterruptedException e) {
            ret = false;
        }

        return new ContaRet(conta, ret);
    }
}