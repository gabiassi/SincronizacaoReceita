package br.com.sincronizacao_receita.a_old;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

//@EnableBatchProcessing
//@SpringBootApplication
public class SincronizacaoReceitaApplication_old implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SincronizacaoReceitaApplication_old.class);

   /* @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
*/

    //@Autowired
    private JobLauncher jobLauncher;

    //@Autowired
    private Job job;

    public static void main(String[] args) {
        SpringApplication.run(SincronizacaoReceitaApplication_old.class, args);

        logger.info("** STARTED **");
    }

    //@Override
    public void run(String... args) throws Exception {
        jobLauncher.run(job, new JobParameters());
    }

/*
    @Bean
    public Step step() {
        return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Hello, World!");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("nomeDoJob").start(step()).build();
    }
    */






/*






























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


     * Metodo para fazer validacoes antes do inicio do processo
     *
     * @return boolean

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
*/
}