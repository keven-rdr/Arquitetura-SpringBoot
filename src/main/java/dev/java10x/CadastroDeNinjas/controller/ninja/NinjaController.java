package dev.java10x.CadastroDeNinjas.controller.ninja;

import dev.java10x.CadastroDeNinjas.entity.ninja.Ninja;
import dev.java10x.CadastroDeNinjas.model.ninja.NinjaRequest;
import dev.java10x.CadastroDeNinjas.model.ninja.NinjaResponse;
import dev.java10x.CadastroDeNinjas.service.ninja.NinjaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}")
    @Operation(description = "Busca o Ninja pelo Id")
    @ResponseStatus(HttpStatus.OK)
    public NinjaResponse getById(@PathVariable String id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(description = "Alterar o Ninja")
    public NinjaResponse update(@PathVariable String id, @RequestBody NinjaRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deletar um Ninja")
    public void delete(@PathVariable String id){
        service.delete(id);
    }


}
