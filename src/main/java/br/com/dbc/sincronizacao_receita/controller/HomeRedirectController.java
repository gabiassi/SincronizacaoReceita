package br.com.dbc.sincronizacao_receita.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class HomeRedirectController {

    public HomeRedirectController() {
    }

    @GetMapping
    public ModelAndView sincRaiz() {
        //TODO: Somente para redirecionar para /info vai retornar informacoes do servidor/servicos
        return new ModelAndView("redirect:/info");
    }

    @GetMapping(value = "/info")
    public ResponseEntity info() {
        //TODO: Somente para criar um metodo get simulando informacoes do servico
        return ResponseEntity.ok("TESTE DO GET \n Servidor online");
    }
}
