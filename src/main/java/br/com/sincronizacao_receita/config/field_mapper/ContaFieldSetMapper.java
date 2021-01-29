package br.com.sincronizacao_receita.config.field_mapper;


import br.com.sincronizacao_receita.model.Conta;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.BindException;

public class ContaFieldSetMapper extends BeanWrapperFieldSetMapper<Conta> {

    public ContaFieldSetMapper(ConversionService cs, Class clazz) {
        this.setConversionService(cs);
        this.setTargetType(clazz);
    }

    @Override
    public Conta mapFieldSet(FieldSet fieldSet) throws BindException {
        final Conta conta = new Conta();
        conta.setAgencia(fieldSet.readString("agencia"));
        conta.setConta(fieldSet.readString("conta"));
        conta.setSaldo(fieldSet.readDouble("saldo"));
        conta.setStatus(fieldSet.readString("status"));

        return conta;
    }

}