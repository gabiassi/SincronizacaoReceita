package br.com.dbc.sincronizacao_receita.utils;

import br.com.dbc.sincronizacao_receita.dto.ContaDTO;
import br.com.dbc.sincronizacao_receita.service.ReceitaService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe com alguns metodos utilitarios que podem ser utilizados em varias classes
 */
public class DbcFileUtil {
    private static final Logger logger = LoggerFactory.getLogger(DbcFileUtil.class);

    /*@RequestMapping(value="/getcontent/file", method=RequestMapping.post)
    public MultipartFile getMultipartAsFileAsObject() {

        File file = new File("src/test/resources/input.docx");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",file.getName(),
                "application/docx", IOUtils.toByteArray(input));

        return multipartFile
    }*/

   /* private void att(MultipartFile file){

//        try {

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            logger.error("Erro ao ler arquivo: " + file.getName() + " ERRO:" + DbcUtil.getStackTrace(e));
        }


        Scanner scanner = new Scanner(inputStream);

       // BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
       // Reader reader = Files.newBufferedReader(Paths.get(parPath));
       // } catch (Exception e) {
       //     logger.error("Erro ao ler arquivo: " + file.getName() + " ERRO:" + DbcUtil.getStackTrace(e));
       // }
    }*/

    /**
     * Metodo para fazer validacoes se prosegue para processar contas de um arquivo
     *
     * @return boolean
     */
    public static boolean isValidaProcessaContas(String nomeArquivo, List<ContaDTO> contasList) {
        if (contasList == null || contasList.size() == 0) {
            logger.error("Erro no arquivo [" + nomeArquivo + "]. Nenhuma conta encontrada no arquivo. ");
            return false;
        }
        return true;
    }

    /**
     * Metodo que retorna se deve tentar incluir na lista tipada ContasDTO
     *
     * @param line: linha do arquivo
     * @return boolean
     */
    private static boolean isValidaTentaAddConta(String line) {
        //TODO: As validacoes podem ser melhoradas de acordo com a necessidade
        // as opcoes nao validadas podem gerar erro no servico ou no processamento e sera registrado em log

        // linha cabecalho pode ser usado o index tambem

        if (line == null || line.toLowerCase().contains("agencia;conta;"))
            return false;

        return line.trim().length() != 0;  // linha vazia
    }

    /**
     * Faz a leitura do arquivo csv com os dados de contas e armazena em uma lista tipada
     */
    public static List<ContaDTO> leArquivoMultipart(MultipartFile file) { //, String parPath) {
        List<ContaDTO> contasList = new ArrayList<ContaDTO>();

        //TODO: neste metodo utilizei somente classes do java para ler o arquivo
        try {

            InputStream inputStream;
            try {
                inputStream = file.getInputStream();
            } catch (IOException e) {
                logger.error("Erro ao ler arquivo: " + file.getName() + " ERRO:" + DbcUtil.getStackTrace(e));
                return contasList;
            }

            //Scanner read = new Scanner(new File(parPath));
            Scanner read = new Scanner(inputStream);
            read.useDelimiter("\n");
            String line;

            while (read.hasNext()) {
                line = read.nextLine();
                if (!isValidaTentaAddConta(line))
                    continue;

                String[] lineTemp = (line + ";").split(";");

                ContaDTO conta = new ContaDTO();
                conta.setAgencia(lineTemp[0]);
                conta.setConta(lineTemp[1]);

                //TODO: Aqui dependendo da necessidade tambem e possivel incluir
                // tratamento de erro para especificar o campo saldo
                double vlr;
                if (lineTemp[2] != null && !lineTemp[2].isEmpty())
                    vlr = Double.parseDouble(DbcUtil.formatDecimal(lineTemp[2]));
                else
                    vlr = 0;
                conta.setSaldo(vlr);
                conta.setStatus(lineTemp[3]);

                contasList.add(conta);

            }
            read.close();

            //TODO: De acordo com a necessidade do sistema e possivel melhorar a tratativa passando a linha
            // e posicao que gerou erro. Aqui vamos apenas dar o erro no log e ir para proximo arquivo.
            // Tambem dependemos da forma de tratamento de erros do sistema/empresa se vai ser controlado por
            // mensageria / tabelas / server.log
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer a leitura do arquivo. Verifique o layout do mesmo. " + DbcUtil.getStackTrace(e));
        }

        return contasList;
    }

    /**
     * Faz a leitura do arquivo csv com os dados de contas e armazena em uma lista tipada
     *
     * @param parPath: path do arquivo
     * @return List<ContaDTO>
     */
    public static List<ContaDTO> leArquivo(String parPath) {
        List<ContaDTO> contasList = new ArrayList<ContaDTO>();

        //TODO: neste metodo faco o mesmo que na leArquivoMultipart so que com a Apache commons csv que simplifica algumas coisas
        // como ignorar o cabecalho
        try (
                Reader reader = Files.newBufferedReader(Paths.get(parPath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim()
                        .withRecordSeparator(';')
                        .withDelimiter(';')
                        .withIgnoreEmptyLines())
        ) {
            for (CSVRecord csvRecord : csvParser) {

                //logger.info(csvRecord.get(0) +"#"+csvRecord.get(1));
                if (!(csvRecord.size() >= 4))
                    continue;

                //TODO: com esta api posso acessar as colunas pelo header
                ContaDTO conta = new ContaDTO();

                conta.setAgencia(csvRecord.get("agencia"));
                conta.setConta(csvRecord.get("conta"));

                //TODO: Aqui dependendo da necessidade tambem e possivel incluir
                // tratamento de erro para especificar o campo saldo
                double vlr;
                String strSaldo = csvRecord.get("saldo");
                if (strSaldo != null && !strSaldo.isEmpty())
                    vlr = Double.parseDouble(DbcUtil.formatDecimal(strSaldo));
                else
                    vlr = 0;
                conta.setSaldo(vlr);
                conta.setStatus(csvRecord.get("status"));

                contasList.add(conta);

                //TODO: para logs podemos utilizar tambem csvRecord.getRecordNumber() para saber a linha com erros ou ultima processada
            }

            //TODO: De acordo com a necessidade do sistema e possivel melhorar a tratativa passando a linha
            // e posicao que gerou erro. Aqui vamos apenas dar o erro no log e ir para proximo arquivo.
            // Tambem dependemos da forma de tratamento de erros do sistema/empresa se vai ser controlado por
            // mensageria / tabelas / server.log
        } catch (IOException e) {
            throw new RuntimeException("Erro ao fazer a leitura do arquivo. Verifique o layout do mesmo. " + DbcUtil.getStackTrace(e));
        }

        return contasList;
    }

    public static List<ContaDTO> processaListaContas(String nomeArq, List<ContaDTO> contasList) {
        // Exemplo como chamar o "serviço" do Banco Central.
        ReceitaService receitaService = new ReceitaService();

        List<ContaDTO> listaContasRet = DbcUtil.clone(contasList);

        for (ContaDTO conta : listaContasRet) {
            try {
                logger.info("antes service: " + conta.getAgencia() + "#" + conta.getConta() + "#" + conta.getSaldo() + "#" + conta.getStatus());
                Boolean ret = receitaService.atualizarConta(conta.getAgencia(), conta.getConta(), conta.getSaldo(), conta.getStatus());
                conta.setRetorno(ret);
                logger.info("depois service - ok");
            } catch (InterruptedException e) {
                //TODO: Aqui podemos tratar os erros de acordo com a necessidade e/ou funcionalidade do servico,
                // neste caso mostro no log e continua proxima linha/arquivo
                logger.error("Erro ao chamar servico de atualizacao de contas da Receita. Arquivo [" + nomeArq + "]. Conta:[" + conta.getAgencia() + "#" + conta.getConta() + "]. " + DbcUtil.getStackTrace(e));
                conta.setRetorno(false);
            }
        }

        return listaContasRet;

    }

    public static void gravarArquivoRet(String nomeArq, List<ContaDTO> contasList) {
        String arquivoRet = nomeArq.replace(".csv", "_ret.csv");

        //TODO: Aqui optei por utilizar apache-commons-csv, tambem ja utilizei o opencsv
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(arquivoRet));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withIgnoreEmptyLines().withRecordSeparator('\n').withDelimiter(';')
                     .withHeader("agencia", "conta", "saldo", "status", "retorno"))
        ) {
            if (contasList != null && !contasList.isEmpty())
                for (ContaDTO conta : contasList) {
                    csvPrinter.printRecord(conta.getAgencia(), conta.getConta(), conta.getSaldo(), conta.getStatus(), conta.getRetorno());
                }
            csvPrinter.flush();
        } catch (IOException e) {
            logger.error("Erro ao tentar gerar arquivo de arquivo de retorno [" + arquivoRet + "]. " + DbcUtil.getStackTrace(e));
        }
    }

    public static MultipartFile gravarArquivoMultRet(String nomeArq, List<ContaDTO> contasList) {
        String arquivoRet = nomeArq.replace(".csv", "_ret.csv");

        try {
            //TODO: Aqui optei por utilizar apache-commons-csv, tambem ja utilizei o opencsv
            if (!DbcUtil.isEmpty(contasList)) {
                List<byte[]> listBytes = new ArrayList<>();
                String cab = "agencia;conta;saldo;status;retorno" + '\n';
                listBytes.add(cab.getBytes());

                for (ContaDTO conta : contasList) {
                    String line = conta.getAgencia() + ";" + conta.getConta() + ";" + conta.getSaldo() + ";" + conta.getStatus() + ";" + conta.getRetorno() + '\n';
                    listBytes.add(line.getBytes());
                }

                //List<byte[]> list = Arrays.asList("abc".getBytes(), "def".getBytes());
                byte[] result = listBytes.stream()
                        .collect(
                                () -> new ByteArrayOutputStream(),
                                (b, e) -> {
                                    try {
                                        b.write(e);
                                    } catch (IOException e1) {
                                        throw new RuntimeException(e1);
                                    }
                                },
                                (a, b) -> {
                                }).toByteArray();

                System.out.println(new String(result));

                String contentType = "text/csv";
                MultipartFile resultRet = new MockMultipartFile(arquivoRet,
                        arquivoRet, contentType, result);

                return resultRet;
            }
        } catch (Exception e) {
            logger.error("Erro ao tentar gerar arquivo de retorno [" + arquivoRet + "]. " + DbcUtil.getStackTrace(e));
        }

        return null;
    }
}
