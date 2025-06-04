package dev.java10x.CadastroDeNinjas.controller.ninja;

import dev.java10x.CadastroDeNinjas.entity.ninja.Ninja;
import dev.java10x.CadastroDeNinjas.service.ninja.NinjaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService service;

    public NinjaController(NinjaService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(description = "Cadastrar os Ninjas")
    public Ninja create(@RequestParam String name, @RequestParam String email, @RequestParam int age){
        return service.create(name, email);
    }

}
