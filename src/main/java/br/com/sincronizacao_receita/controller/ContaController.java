package br.com.sincronizacao_receita.controller;

import br.com.sincronizacao_receita.dto.ContaDTO;
import br.com.sincronizacao_receita.model.Conta;
import br.com.sincronizacao_receita.service.ContaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;
    private final ModelMapper modelMapper;

    public ContaController(ContaService contaService, ModelMapper modelMapper) {
        this.contaService = contaService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<ContaDTO> getContas(
            @RequestParam(required = false) String agencia, @RequestParam(required = false) String conta) {
        //TODO: Com o servico desta forma sera acessado por /contas/?agencia=xxx&conta=yyy
        return contaService.findByAgenciaConta(agencia, conta).stream()
                .map(con -> modelMapper.map(con, ContaDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/consultar/{id}")
    public ResponseEntity getContaPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(contaService.findById(id));
    }


    @DeleteMapping("/remover/{id}")
    public ResponseEntity remover(@PathVariable Long id) {
        contaService.delete(id);
        return ResponseEntity.noContent().build(); //TODO: Sem retorno
        //return ResponseEntity.ok(HttpEntity.EMPTY); //TODO: Retorna json com body vazio
    }


    @PostMapping
    public ResponseEntity create(@RequestBody Conta conta) throws URISyntaxException {
        if (conta == null) {
            return null;
        }

        Conta contaNew = contaService.create(conta);
        return ResponseEntity.created(new URI(String.format("/contas/%d", contaNew.getId())))
                .build();
    }
}
