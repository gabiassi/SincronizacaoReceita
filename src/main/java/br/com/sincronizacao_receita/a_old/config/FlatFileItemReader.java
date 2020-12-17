package br.com.sincronizacao_receita.a_old.config;

import br.com.sincronizacao_receita.dto.ContaDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

public class FlatFileItemReader<T>  {
    @Bean
    public FlatFileItemReader<ContaDTO> fileItemReader(@Value("${input}") Resource resource) {

        System.out.println("INI READER");

        FlatFileItemReader<ContaDTO> fileItemReader = new FlatFileItemReader<ContaDTO>();

        /*fileItemReader.setResource(resource);
        fileItemReader.setEncoding("ISO-8859-3");
        fileItemReader.setName("CSV-Reader");
        fileItemReader.setLinesToSkip(1);
        fileItemReader.setLineMapper(lineMapper());
         */


        System.out.println("FIM READER");

        return fileItemReader;
    }
}