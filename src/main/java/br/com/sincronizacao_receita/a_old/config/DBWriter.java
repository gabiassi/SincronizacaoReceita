package br.com.sincronizacao_receita.a_old.config;

import br.com.sincronizacao_receita.dto.ContaDTO;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<ContaDTO> {

    private final DailyInformRepository dailyInformRepository;

    @Autowired
    public DBWriter(DailyInformRepository dailyInformRepository) {
        this.dailyInformRepository = dailyInformRepository;
    }

    @Override
    public void write(List<? extends ContaDTO> list) {
        System.out.println("INI WRITE");

                //dailyInformRepository.saveAll(list);
        for (ContaDTO conta: list) {
            System.out.println(conta.getAgencia() + "#" + conta.getConta());
        }

        System.out.println("FIM WRITE");
    }
}