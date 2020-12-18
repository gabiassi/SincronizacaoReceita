package br.com.sincronizacao_receita.a_old.config;


import br.com.sincronizacao_receita.dto.ContaDTO;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class ContaFieldSetMapper implements FieldSetMapper<ContaDTO> {

    @Override
    public ContaDTO mapFieldSet(FieldSet fieldSet) throws BindException {
        System.out.println("INI Set MAPPER");

        final ContaDTO conta = new ContaDTO();
        conta.setAgencia(fieldSet.readString("agencia"));
        conta.setConta(fieldSet.readString("conta"));
        conta.setSaldo(fieldSet.readDouble("saldo"));
        conta.setStatus(fieldSet.readString("status"));

        System.out.println(conta.getAgencia() + "#" + conta.getConta() + "#" + conta.getSaldo() + "#" + conta.getStatus());
        System.out.println("FIM Set MAPPER");

        return conta;

        //dailyInform.setNetWorth(fieldSet.readBigDecimal("VL_PATRIM_LIQ"));
        //dailyInform.setTotalDeposits(fieldSet.readBigDecimal("CAPTC_DIA"));

        //dailyInform.setConta(fieldSet.readDate("DT_COMPTC")
        //        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        //dailyInform.setNumberOfQuotaHolders(fieldSet.readLong("NR_COTST"));

    }
}