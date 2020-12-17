package br.com.sincronizacao_receita.a_old.config;


import br.com.sincronizacao_receita.dto.ContaDTO;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class DailyInformFieldSetMapper implements FieldSetMapper<ContaDTO> {

        @Override
        public ContaDTO mapFieldSet(FieldSet fieldSet) throws BindException {
            System.out.println("INI Set MAPPER");

            final ContaDTO dailyInform = new ContaDTO();
            dailyInform.setAgencia(fieldSet.readString("agencia"));
            dailyInform.setConta(fieldSet.readString("conta"));
            dailyInform.setSaldo(fieldSet.readDouble("saldo"));
            dailyInform.setStatus(fieldSet.readString("status"));

            System.out.println(dailyInform.getAgencia() + "#" + dailyInform.getConta());
            System.out.println("FIM Set MAPPER");
            //dailyInform.setNetWorth(fieldSet.readBigDecimal("VL_PATRIM_LIQ"));
            //dailyInform.setTotalDeposits(fieldSet.readBigDecimal("CAPTC_DIA"));

            //dailyInform.setConta(fieldSet.readDate("DT_COMPTC")
            //        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            /*dailyInform.setCnpj(fieldSet.readString("CNPJ_FUNDO"));
            dailyInform.setReferenceDate(fieldSet.readDate("DT_COMPTC")
                    .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            dailyInform.setTotalValue(fieldSet.readBigDecimal("VL_TOTAL"));
            dailyInform.setQuotaValue(fieldSet.readBigDecimal("VL_QUOTA"));
            dailyInform.setNetWorth(fieldSet.readBigDecimal("VL_PATRIM_LIQ"));
            dailyInform.setTotalDeposits(fieldSet.readBigDecimal("CAPTC_DIA"));
            dailyInform.setTotalWithdrawals(fieldSet.readBigDecimal("RESG_DIA"));
            dailyInform.setNumberOfQuotaHolders(fieldSet.readLong("NR_COTST"));*/
            return dailyInform;
        }
    }

