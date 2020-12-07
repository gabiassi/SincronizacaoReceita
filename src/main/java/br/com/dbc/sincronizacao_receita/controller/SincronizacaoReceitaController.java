package br.com.dbc.sincronizacao_receita.controller;


import br.com.dbc.sincronizacao_receita.service.SincronizacaoReceitaServiceImpl;
import br.com.dbc.sincronizacao_receita.service.SincronzacaoReceitaService;
import br.com.dbc.sincronizacao_receita.utils.DbcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/sinc")
public class SincronizacaoReceitaController {

    private static final Logger logger = LoggerFactory.getLogger(SincronizacaoReceitaController.class);
    private final SincronzacaoReceitaService sincReceitaService;

    public SincronizacaoReceitaController(SincronzacaoReceitaService sincReceitaService) {
        this.sincReceitaService = sincReceitaService;
    }

    @PostMapping(value = "/atualizar_contas")
    public ResponseEntity atualizarContas(@RequestBody @RequestParam(required = true) MultipartFile file) {
        MultipartFile fileRet = sincReceitaService.atualizarContas(file);

        try {
            ByteArrayResource ret = new ByteArrayResource(fileRet.getBytes());
            return ResponseEntity.ok(ret);
        } catch (IOException e) {
            logger.error("Erro ao retornar arquivo de processamento de atualizacao de contas. ERRO:" + DbcUtil.getStackTrace(e));
        }

        return null;
    }
}
