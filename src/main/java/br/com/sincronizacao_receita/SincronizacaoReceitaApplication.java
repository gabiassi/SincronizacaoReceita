package br.com.sincronizacao_receita;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SincronizacaoReceitaApplication {

    //private static final Logger logger = LoggerFactory.getLogger(SincronizacaoReceitaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SincronizacaoReceitaApplication.class, args);
    }
}