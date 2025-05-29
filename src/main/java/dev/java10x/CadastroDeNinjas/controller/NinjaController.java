package dev.java10x.CadastroDeNinjas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class NinjaController {

        @GetMapping("/boas-vindas")
        public String boasVindas(){
            return "Essa é minha primeira mensagem nessa rota";
        }
}
