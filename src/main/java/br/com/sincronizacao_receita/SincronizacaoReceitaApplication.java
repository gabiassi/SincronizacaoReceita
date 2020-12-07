package br.com.sincronizacao_receita;

import br.com.sincronizacao_receita.dto.ContaDTO;
import br.com.sincronizacao_receita.utils.DbcFileUtil;
import br.com.sincronizacao_receita.utils.DbcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SincronizacaoReceitaApplication {
	/*
   Cenário de Negócio:
   Todo dia útil por volta das 6 horas da manhã um colaborador da retaguarda do Sicredi recebe e
    organiza as informações de contas para enviar ao Banco Central. Todas agencias e cooperativas
    enviam arquivos Excel à Retaguarda. Hoje o Sicredi já possiu mais de 4 milhões de contas ativas.
   Esse usuário da retaguarda exporta manualmente os dados em um arquivo CSV para ser enviada para a Receita Federal, antes as 10:00 da manhã na abertura das agências.

   Requisito:
   Usar o "serviço da receita" (fake) para processamento automático do arquivo.

   Funcionalidade:
   0. Criar uma aplicação SprintBoot standalone. Exemplo: java -jar SincronizacaoReceita <input-file>
   1. Processa um arquivo CSV de entrada com o formato abaixo.
   2. Envia a atualização para a Receita através do serviço (SIMULADO pela classe ReceitaService).
   3. Retorna um arquivo com o resultado do envio da atualização da Receita. Mesmo formato adicionando o resultado em uma nova coluna.


   Formato CSV:
   agencia;conta;saldo;status
   0101;12225-6;100,00;A
   0101;12226-8;3200,50;A
   3202;40011-1;-35,12;I
   3202;54001-2;0,00;P
   3202;00321-2;34500,00;B
   ...

   */

    private static final Logger logger = LoggerFactory.getLogger(SincronizacaoReceitaApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(SincronizacaoReceitaApplication.class, args);

        //TODO: Como em nenhum momento menciona criar microservices, nao utilizei as notacoes e nem deixei disponivel servicoes
        // apenas fiz funcionar um jar da forma solicitada:
        // 0. Criar uma aplicação SprintBoot standalone. Exemplo: java -jar SincronizacaoReceita <input-file>
        //TODO: Como foi solicitado para criar uma aplicacao a ser executada pelo jar e nenhum momento falou em implementar
        // api Rest vou considerar que o item 3 seja gerar outro arquivo com o retorno da atualizacao em uma nova coluna
        //  "3. Retorna um arquivo com o resultado do envio da atualização da Receita. Mesmo formato adicionando o resultado em uma nova coluna."
        //TODO: Pela descricao do que fazer nao parece um requisito alterar a ReceitaService.java. O ideal seria ter o
        // servico como WS e utilizar o controller
        //TODO: Neste teste vou considerar que o controle de erros/logs serao feitos apenas pelo log do servico,
        // entao ao gerar algum erro printar no log
        //TODO: Tambem vou considerar o exemplo de chamada do servico na descricao da tarefa
        // eu poderia criar a classe ReceitaService e rodar como um WebService realmente (utilizando
        // @RestController, @RequestMapping, @PostMapping, HttpEntity, ResponseEntity, etc). O ideal
        // seria ter o servico como WS e utilizar o controller, mas desta forma utilizarei o main e dividir em metodos
        //TODO: Tanto a nomenclatura de metodos quanto de objetos eu seguiria o padrao definido pela empresa, neste caso nao
        // conheco

        logger.info("** INICIO **");

        if (isValidaInicio(args))
            processaListaArquivos(args);

        logger.info("** FIM **");
    }


    private static void processaListaArquivos(String[] args) {
        // Tambem poderiamos tratar apenas um arquivo de acordo com a necessidade
        for (int i = 0; i < args.length; i++) {
            logger.info("Ler arquivo " + i + "=[" + args[i] + "].");

            List<ContaDTO> contasList;
            try {
                contasList = DbcFileUtil.leArquivo(args[i]);
            } catch (Exception e) {
                //TODO: Aqui podemos tratar os erros de acordo com a necessidade,
                // neste caso mostro no log e continua proximo arquivo
                logger.error("Erro no arquivo [" + args[i] + "]. " + DbcUtil.getStackTrace(e));
                continue;
            }

            if (DbcFileUtil.isValidaProcessaContas(args[i], contasList))
                processaListaContas(args[i], contasList);
        }
    }

    /**
     * Metodo para fazer validacoes antes do inicio do processo
     *
     * @return boolean
     */
    private static boolean isValidaInicio(String[] args) {
        if (args == null || args.length == 0) {
            logger.error("Nenhum arquivo de contas informado!");
            return false;
        }
        return true;
    }

    private static void processaListaContas(String nomeArq, List<ContaDTO> contasList) {
        // Exemplo como chamar o "serviço" do Banco Central.
        // ReceitaService receitaService = new ReceitaService();
        // receitaService.atualizarConta

        List<ContaDTO> contasListRet = DbcFileUtil.processaListaContas(nomeArq, contasList);
        DbcFileUtil.gravarArquivoRet(nomeArq, contasListRet);

    }

}