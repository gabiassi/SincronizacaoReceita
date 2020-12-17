package br.com.sincronizacao_receita.config.step;

import br.com.sincronizacao_receita.dto.ContaDTO;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LerContasStepConfig {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step lerContasStep(ItemReader<ContaDTO> lerContasReader,
                                    ItemWriter<ContaDTO> lerContasWriter){
        return stepBuilderFactory
                .get("lerContasStep")
                .<ContaDTO, ContaDTO>chunk(3)
                .reader(lerContasReader)
                //.processor(imprimeParImparProcessor())
                .writer(lerContasWriter)
                .build();
    }

}
