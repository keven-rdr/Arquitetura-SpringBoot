package dev.java10x.CadastroDeNinjas.controller.ninja;

import dev.java10x.CadastroDeNinjas.entity.ninja.Ninja;
import dev.java10x.CadastroDeNinjas.model.ninja.NinjaRequest;
import dev.java10x.CadastroDeNinjas.model.ninja.NinjaResponse;
import dev.java10x.CadastroDeNinjas.service.ninja.NinjaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService service;

    public NinjaController(NinjaService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(description = "Cadastrar os Ninjas")
    public NinjaResponse create(@RequestBody NinjaRequest request){
        return service.create(request);
    }

    @GetMapping("/all")
    @Operation(description = "Busca todas os Ninjas")
    public List<NinjaResponse> getAll(){
        return service.getAll();
    }

}
