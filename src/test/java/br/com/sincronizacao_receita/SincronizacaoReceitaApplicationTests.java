package br.com.sincronizacao_receita;

import br.com.sincronizacao_receita.config.job.LerContasJobConfig;
import br.com.sincronizacao_receita.config.processor.LerContasProcessorConfig;
import br.com.sincronizacao_receita.config.reader.LerContasReaderConfig;
import br.com.sincronizacao_receita.config.step.LerContasStepConfig;
import br.com.sincronizacao_receita.config.writer.LerContasWriterConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {LerContasJobConfig.class, LerContasStepConfig.class, LerContasReaderConfig.class, LerContasProcessorConfig.class, LerContasWriterConfig.class})
@PropertySource("classpath:application.properties")
@AutoConfigureDataJdbc
@ActiveProfiles("test")
@SpringBatchTest
@SpringBootTest
class SincronizacaoReceitaApplicationTests {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	public void test() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("contasResource", "file:files/testes/contas_test_sucess.csv")
				.addString("arquivoSaida", "file:files/testes/contas_ret_test_sucess2.csv").toJobParameters();
		JobExecution jobExecution = this.jobLauncherTestUtils.launchJob(jobParameters);
		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

		StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();
		assertEquals(BatchStatus.COMPLETED, stepExecution.getStatus());
		assertEquals(7, stepExecution.getReadCount());
		assertEquals(7, stepExecution.getWriteCount());
	}

	@Test
	public void testError() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("contasResource", "file:files/testes/contas_erro.csv")
				.addString("arquivoSaida", "file:files/testes/contas_ret_erro.csv").toJobParameters();
		JobExecution jobExecution = this.jobLauncherTestUtils.launchJob(jobParameters);
		assertEquals(BatchStatus.FAILED, jobExecution.getStatus());

		StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();
		assertEquals(BatchStatus.FAILED, stepExecution.getStatus());
	}

}
