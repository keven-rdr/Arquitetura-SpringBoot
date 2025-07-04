package dev.java10x.CadastroDeNinjas.service.ninja;

import dev.java10x.CadastroDeNinjas.entity.ninja.Ninja;
import dev.java10x.CadastroDeNinjas.model.ninja.INinjaMapper;
import dev.java10x.CadastroDeNinjas.model.ninja.NinjaRequest;
import dev.java10x.CadastroDeNinjas.model.ninja.NinjaResponse;
import dev.java10x.CadastroDeNinjas.repository.mission.MissionRepository;
import dev.java10x.CadastroDeNinjas.repository.ninja.NinjaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NinjaService implements INinjaService {

    private final NinjaRepository repository;
    private final INinjaMapper mapper;
    private final MissionRepository missionRepository;


    @Transactional(rollbackOn = Exception.class)
    public NinjaResponse create(NinjaRequest request) {
        Ninja ninja = mapper.toEntity(request);

        // Se veio missão, tenta associar
        if (request.missions() != null && request.missions().id() != null) {
            var mission = missionRepository.findById(request.missions().id())
                    .orElseThrow(() -> new RuntimeException("Missão não encontrada"));
            ninja.setMissions(mission);
        }

        repository.save(ninja);
        return mapper.toResponse(ninja);
    }


    public List<NinjaResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public NinjaResponse getById(String id) {
        if (id == null || id.isEmpty()) {
            throw new RuntimeException("Ninja não encontrado");
        }

        Ninja ninja = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missão não encontrada"));
        return mapper.toResponse(ninja);
    }


    @Transactional(rollbackOn = Exception.class)
    public NinjaResponse update(String id, NinjaRequest request) {
        if (id == null || id.isEmpty()) {
            throw new RuntimeException("ID do ninja não informado");
        }

        Ninja ninja = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ninja não encontrado"));

        // Atualiza missão se tiver nova
        if (request.missions() != null && request.missions().id() != null) {
            var mission = missionRepository.findById(request.missions().id())
                    .orElseThrow(() -> new RuntimeException("Missão não encontrada"));
            ninja.setMissions(mission);
        }

        ninja.setName(request.name());
        ninja.setEmail(request.email());
        ninja.setAge(request.age());

        repository.save(ninja);

        return mapper.toResponse(ninja);
    }

    public void delete(String id) {
        if (id == null || id.isEmpty()) {
            throw new RuntimeException("ID do ninja não pode ser nulo ou vazio");
        }

        Ninja ninja = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ninja não encontrado"));

        repository.delete(ninja);
    }


}
