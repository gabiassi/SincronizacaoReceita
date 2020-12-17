package br.com.sincronizacao_receita.a_old.config;

import br.com.sincronizacao_receita.dto.ContaDTO;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;

public class LineMapper<T> {
    @Bean
    public DefaultLineMapper<ContaDTO> lineMapper() {
        DefaultLineMapper<ContaDTO> defaultLineMapper = new DefaultLineMapper<ContaDTO>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(";");

        lineTokenizer.setNames("agencia","conta","saldo","status");
                /*"CNPJ_FUNDO",
                "DT_COMPTC",
                "VL_TOTAL",
                "VL_QUOTA",
                "VL_PATRIM_LIQ",
                "CAPTC_DIA",
                "RESG_DIA",
                "NR_COTST");*/
        lineTokenizer.setStrict(false);

        DailyInformFieldSetMapper dailyInformFieldSetMapper = new DailyInformFieldSetMapper();

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(dailyInformFieldSetMapper);

        return defaultLineMapper;
    }
}