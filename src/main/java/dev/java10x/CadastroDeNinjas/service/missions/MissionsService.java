package dev.java10x.CadastroDeNinjas.service.missions;

import dev.java10x.CadastroDeNinjas.entity.missions.Missions;
import dev.java10x.CadastroDeNinjas.model.missions.IMissionMapper;
import dev.java10x.CadastroDeNinjas.model.missions.MissionRequest;
import dev.java10x.CadastroDeNinjas.model.missions.MissionResponse;
import dev.java10x.CadastroDeNinjas.repository.mission.MissionRepository;
import dev.java10x.CadastroDeNinjas.repository.ninja.NinjaRepository;
import org.springframework.stereotype.Service;

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
}
