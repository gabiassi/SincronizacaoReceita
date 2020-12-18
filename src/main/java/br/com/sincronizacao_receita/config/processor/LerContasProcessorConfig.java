package br.com.sincronizacao_receita.config.processor;

import br.com.sincronizacao_receita.dto.ContaDTO;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LerContasProcessorConfig implements ItemProcessor<ContaDTO, ContaDTO> {

    //@Autowired
    //private CNPJFormatter cnpjFormatter;

    @Override
    public ContaDTO process(ContaDTO conta) throws Exception {
    /* @StepScope
     @Bean
     public ContaDTO lerContasProcessor(ContaDTO conta) {*/
        System.out.println("INI PROCESS");

       // dailyInform.setCnpj(cnpjFormatter.unformat(dailyInform.getCnpj()));
        System.out.println(conta.getAgencia() + "#" + conta.getConta());

        System.out.println("FIM PROCESS");
        return conta;
    }
}
