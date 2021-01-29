package br.com.sincronizacao_receita.config.writer;

import br.com.sincronizacao_receita.model.ContaRet;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.Writer;

@Configuration
public class LerContasWriterConfig {

    @StepScope
    @Bean
    public FlatFileItemWriter<ContaRet> lerContasWriter(
            @Value("#{jobParameters['arquivoSaida']}") Resource arquivoSaida) {

        return new FlatFileItemWriterBuilder<ContaRet>()
                .name("lerContasWriter")
                .resource(arquivoSaida)
                .delimited()
                .delimiter(";")
                .names("agencia","conta","saldo","status", "resultado")
                .headerCallback(new FlatFileHeaderCallback() {
                    @Override
                    public void writeHeader(Writer writer) throws IOException {
                        writer.write("agencia;conta;saldo;status;resultado");
                    }
                })
                .build();

    }
}