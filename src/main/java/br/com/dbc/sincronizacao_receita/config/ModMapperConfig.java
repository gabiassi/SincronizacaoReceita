package br.com.dbc.sincronizacao_receita.config;

import br.com.dbc.sincronizacao_receita.dto.ContaDTO;
import br.com.dbc.sincronizacao_receita.model.Conta;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModMapperConfig {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Conta.class, ContaDTO.class);

        //TODO: Aqui poderiamos setar valores de relacionamentos ManyToOne em campos do DTO, por exemplo.
        // Abaixo um exemplo caso tivesse outra tabela como Agencia. Na Conta.java teriamos um atributo 'Agencia agencia;'
        // e em ContaDTO.java o atributo 'String agencia' (numero da agencia)
        //modelMapper.createTypeMap(Conta.class, ContaDTO.class)
        //  .addMapping(c -> c.getAgencia().getAgencia(), ContaDTO::setAgencia)

        return modelMapper;
    }
}
