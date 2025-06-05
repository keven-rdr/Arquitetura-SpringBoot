package dev.java10x.CadastroDeNinjas.controller.missions;

import dev.java10x.CadastroDeNinjas.model.missions.MissionRequest;
import dev.java10x.CadastroDeNinjas.model.missions.MissionResponse;
import dev.java10x.CadastroDeNinjas.service.missions.MissionsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missions")
public class MissionController {

    private final MissionsService service;

    public MissionController(MissionsService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(description = "Criar uma nova Missão")
    public MissionResponse createMission(@RequestBody MissionRequest request) {
        return service.create(request);
    }

    @GetMapping
    @Operation(description = "Busca todas as Missões")
    public List<MissionResponse> getAll() {
        return service.getAll();
    }


}
