package dev.java10x.CadastroDeNinjas.service.missions;

import dev.java10x.CadastroDeNinjas.entity.missions.Missions;
import dev.java10x.CadastroDeNinjas.entity.ninja.Ninja;
import dev.java10x.CadastroDeNinjas.model.missions.IMissionMapper;
import dev.java10x.CadastroDeNinjas.model.missions.MissionRequest;
import dev.java10x.CadastroDeNinjas.model.missions.MissionResponse;
import dev.java10x.CadastroDeNinjas.model.ninja.NinjaSimpleResponse;
import dev.java10x.CadastroDeNinjas.repository.mission.MissionRepository;
import dev.java10x.CadastroDeNinjas.repository.ninja.NinjaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MissionsService implements IMissionService {

    private final MissionRepository repository;
    private final IMissionMapper mapper;
    private final NinjaRepository ninjaRepository;

    public MissionResponse create(MissionRequest request) {
        Missions mission = mapper.toEntity(request);

        // Processa ninjas se informados
        if (request.ninjas() != null && !request.ninjas().isEmpty()) {
            List<Ninja> ninjas = findNinjasByIds(request.ninjas());

            // Associa a missão aos ninjas (lado proprietário da relação)
            Missions finalMission = mission;
            ninjas.forEach(ninja -> ninja.setMissions(finalMission));
            mission.setNinjas(ninjas);
        }

        mission = repository.save(mission);
        return mapper.toResponse(mission);
    }

    public List<MissionResponse> getAll() {
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

        // Agora completed pode ser null para updates parciais
        if (request.completed() != null) {
            existingMission.setCompleted(request.completed());
        }

        // Atualiza ninjas se informados
        if (request.ninjas() != null) {
            // Remove associações antigas
            if (existingMission.getNinjas() != null) {
                existingMission.getNinjas().forEach(ninja -> ninja.setMissions(null));
            }

            // Adiciona novas associações
            List<Ninja> ninjas = findNinjasByIds(request.ninjas());
            ninjas.forEach(ninja -> ninja.setMissions(existingMission));
            existingMission.setNinjas(ninjas);
        }

        Missions updatedMission = repository.save(existingMission);
        return mapper.toResponse(updatedMission);
    }

    private List<Ninja> findNinjasByIds(List<NinjaSimpleResponse> ninjaResponses) {
        List<String> ninjaIds = ninjaResponses.stream()
                .map(NinjaSimpleResponse::id)
                .toList();

        List<Ninja> foundNinjas = ninjaRepository.findAllById(ninjaIds);

        if (foundNinjas.size() != ninjaIds.size()) {
            throw new RuntimeException("Um ou mais ninjas com os IDs informados não foram encontrados.");
        }

        return foundNinjas;
    }
}