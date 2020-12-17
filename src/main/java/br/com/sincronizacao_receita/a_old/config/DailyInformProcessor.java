package br.com.sincronizacao_receita.a_old.config;

import br.com.sincronizacao_receita.dto.ContaDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class DailyInformProcessor implements ItemProcessor<ContaDTO, ContaDTO> {

    //@Autowired
    //private CNPJFormatter cnpjFormatter;

    @Override
    public ContaDTO process(ContaDTO dailyInform) throws Exception {
        System.out.println("INI PROCESS");

       // dailyInform.setCnpj(cnpjFormatter.unformat(dailyInform.getCnpj()));
        System.out.println(dailyInform.getAgencia() + "#" + dailyInform.getConta());

        System.out.println("FIM PROCESS");
        return dailyInform;
    }
}
