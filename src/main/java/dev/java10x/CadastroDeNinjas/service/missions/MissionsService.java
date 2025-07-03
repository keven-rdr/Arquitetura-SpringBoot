package dev.java10x.CadastroDeNinjas.service.missions;

import dev.java10x.CadastroDeNinjas.entity.missions.Missions;
import dev.java10x.CadastroDeNinjas.entity.ninja.Ninja;
import dev.java10x.CadastroDeNinjas.model.missions.IMissionMapper;
import dev.java10x.CadastroDeNinjas.model.missions.MissionRequest;
import dev.java10x.CadastroDeNinjas.model.missions.MissionResponse;
import dev.java10x.CadastroDeNinjas.model.ninja.NinjaResponse;
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

    public MissionResponse create(MissionRequest request) {
        Missions mission = new Missions();
        mission.setId(UUID.randomUUID().toString());

        // Define os campos se vierem preenchidos
        mission.setName(request.name());
        mission.setDifficultyMission(request.difficultyMission());
        mission.setCompleted(request.completed());

        // Define os ninjas, se houver
        if (request.ninjas() != null && !request.ninjas().isEmpty()) {
            List<String> ids = request.ninjas().stream()
                    .map(Ninja::getId)
                    .toList();
            List<Ninja> ninjas = ninjaRepository.findAllById(ids);
            mission.setNinja(ninjas);
        }

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

        Missions existingMission = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missão não encontrada"));

        // Atualiza apenas os campos informados
        if (request.name() != null) {
            existingMission.setName(request.name());
        }

        if (request.difficultyMission() != null) {
            existingMission.setDifficultyMission(request.difficultyMission());
        }

        // `boolean` não aceita null, então apenas sobrescreve
        existingMission.setCompleted(request.completed());

        if (request.ninjas() != null && !request.ninjas().isEmpty()) {
            List<String> ids = request.ninjas().stream()
                    .map(Ninja::getId)
                    .toList();
            List<Ninja> ninjas = ninjaRepository.findAllById(ids);
            existingMission.setNinja(ninjas);
        }

        Missions updatedMission = repository.save(existingMission);
        return mapper.toResponse(updatedMission);
    }
}
