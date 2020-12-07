package br.com.sincronizacao_receita.service;

import br.com.sincronizacao_receita.dto.ContaDTO;
import br.com.sincronizacao_receita.utils.DbcFileUtil;
import br.com.sincronizacao_receita.utils.DbcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SincronizacaoReceitaServiceImpl implements SincronzacaoReceitaService {

    private static final Logger logger = LoggerFactory.getLogger(SincronizacaoReceitaServiceImpl.class);
    private static List<ContaDTO> contasList;

    public SincronizacaoReceitaServiceImpl() {
    }

    @Override
    public MultipartFile atualizarContas(MultipartFile file) {
        if (file == null) {
            logger.info("Nao foi possivel processar. Arquivo nao enviado!");
            return null;
        }

        try {
            contasList = DbcFileUtil.leArquivoMultipart(file);
        } catch (Exception e) {
            //TODO: Aqui podemos tratar os erros de acordo com a necessidade,
            // neste caso mostro no log e continua proximo arquivo
            logger.error("Erro no arquivo [" + file.getName() + "]. " + DbcUtil.getStackTrace(e));
            return null;
        }

        List<ContaDTO> listaContasRet = null;
        if (DbcFileUtil.isValidaProcessaContas(file.getName(), contasList))
            listaContasRet = DbcFileUtil.processaListaContas(file.getName(), contasList);

        //TODO: Pela descricao do requisito este teste me parecia apenas para executar o jar e passar o caminho do arquivo
        // Fiz isso, porem tentei implementar um WS que recebe o file e faz a mesma coisa e retorna o arquivo com a nova coluna
        // Tambem gostaria de ter implementado TDD para os testes, mas nao tive tempo

        MultipartFile fileNew = DbcFileUtil.gravarArquivoMultRet(file.getName(), listaContasRet);
        return fileNew;
    }


}
