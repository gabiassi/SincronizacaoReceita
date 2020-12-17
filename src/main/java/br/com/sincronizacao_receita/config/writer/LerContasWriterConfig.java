package br.com.sincronizacao_receita.config.writer;

import br.com.sincronizacao_receita.dto.ContaDTO;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LerContasWriterConfig {

    @Bean
    public ItemWriter<ContaDTO> lerContasWriter() {
        return itens -> itens.forEach(System.out::println);

       /* return itens -> {
            for (Conta it:itens) {
              if(it.getConta().equals("40011-1"))
    throw new Exception("ERRO");
              else
                  System.out.println(it);

            }
        };
        */
    }
}