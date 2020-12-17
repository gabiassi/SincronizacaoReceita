package br.com.sincronizacao_receita.config.reader;

import br.com.sincronizacao_receita.dto.ContaDTO;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LerContasReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<ContaDTO> lerContasReader(
            @Value("#{jobParameters['contasResource']}") Resource contasResource){

        return new FlatFileItemReaderBuilder<ContaDTO>()
                .name("lerContasReader")
                .linesToSkip(1)
                .resource(contasResource)
                .delimited()
                .delimiter(";")
                .names(new String[]{"agencia","conta","saldo","status"})
                .targetType(ContaDTO.class)
                .build();

    }

}
