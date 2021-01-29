package br.com.sincronizacao_receita.config.step;

import br.com.sincronizacao_receita.model.Conta;
import br.com.sincronizacao_receita.model.ContaRet;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LerContasStepConfig {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Value( "${app.chunkSize}" )
    private Integer chunkSize;

    @Bean
    public Step lerContasStep(ItemReader<Conta> lerContasReader,
                              ItemProcessor<Conta, ContaRet> lerContasProcessor,
                              ItemWriter<ContaRet> lerContasWriter) {
        return stepBuilderFactory
                .get("lerContasStep")
                .<Conta, ContaRet>chunk(chunkSize)
                .reader(lerContasReader)
                .processor(lerContasProcessor)
                .writer(lerContasWriter)
                .build();
    }

}
