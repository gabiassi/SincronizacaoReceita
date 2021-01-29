package br.com.sincronizacao_receita.config.reader;

import br.com.sincronizacao_receita.config.cs.ContasConversionService;
import br.com.sincronizacao_receita.config.field_mapper.ContaFieldSetMapper;
import br.com.sincronizacao_receita.model.Conta;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LerContasReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<Conta> lerContasReader(
            @Value("#{jobParameters['contasResource']}") Resource contasResource) {

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(";");
        lineTokenizer.setNames("agencia", "conta", "saldo", "status");
        lineTokenizer.setStrict(false);

        ContaFieldSetMapper map = new ContaFieldSetMapper(new ContasConversionService(), Conta.class);

        FlatFileItemReader<Conta> flat = new FlatFileItemReaderBuilder<Conta>()
                .name("lerContasReader")
                .linesToSkip(1)
                .resource(contasResource)
                .lineTokenizer(lineTokenizer)
                .fieldSetMapper(map)
                .build();

        return flat;

    }

}
