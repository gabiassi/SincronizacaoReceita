package br.com.sincronizacao_receita.service;

import org.springframework.web.multipart.MultipartFile;

public interface SincronzacaoReceitaService {
    MultipartFile atualizarContas(MultipartFile file);
}
