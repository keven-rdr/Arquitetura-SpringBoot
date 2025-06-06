package dev.java10x.CadastroDeNinjas.service.missions;

import dev.java10x.CadastroDeNinjas.entity.missions.Missions;
import dev.java10x.CadastroDeNinjas.model.missions.IMissionMapper;
import dev.java10x.CadastroDeNinjas.model.missions.MissionRequest;
import dev.java10x.CadastroDeNinjas.model.missions.MissionResponse;
import dev.java10x.CadastroDeNinjas.repository.mission.MissionRepository;
import dev.java10x.CadastroDeNinjas.repository.ninja.NinjaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MissionsService implements IMissionService{

    private final MissionRepository repository;
    private final IMissionMapper mapper;
    private final NinjaRepository ninjaRepository;

    public MissionsService(MissionRepository repository, IMissionMapper mapper, NinjaRepository ninjaRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.ninjaRepository = ninjaRepository;
    }

    public MissionResponse create(MissionRequest request){
        Missions mission = mapper.toEntity(request);
        mission.setId(UUID.randomUUID().toString());

        mission = repository.save(mission);
        return mapper.toResponse(mission);
    }


    public List<MissionResponse> getAll(){
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public Missions findMissionById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missão não encontrada: " + id));
    }

    public MissionResponse getById(String id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Missão não encontrada"));
    }

    public void delete(String id) {
       if (id == null || !repository.existsById(id)) {
           throw new RuntimeException("ID não encontrado");
       }
        repository.deleteById(id);
    }

    public MissionResponse update(String id, MissionRequest request) {
        if (id == null || !repository.existsById(id)) {
            throw new RuntimeException("Missão com o ID informado não foi encontrada");
        }

        // Recupera a missão existente do banco
        Missions existingMission = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missão não encontrada"));

        // Atualiza os campos básicos
        existingMission.setName(request.name());
        existingMission.setDifficultyMission(request.difficultyMission());
        existingMission.setCompleted(request.completed());

        // Atualiza os ninjas, se forem fornecidos
        if (request.ninjas() != null && !request.ninjas().isEmpty()) {
            List<Ninja> ninjas = ninjaRepository.findAllById(
                    request.ninjas().stream()
                            .map(NinjaResponse::id)
                            .toList()
            );
            existingMission.setNinjas(ninjas);
        }

        // Salva a missão com as alterações
        Missions updatedMission = repository.save(existingMission);
        return mapper.toResponse(updatedMission);
    }






}
