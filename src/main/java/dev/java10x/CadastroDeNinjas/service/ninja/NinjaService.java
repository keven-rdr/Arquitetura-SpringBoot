package dev.java10x.CadastroDeNinjas.service.ninja;

import dev.java10x.CadastroDeNinjas.entity.ninja.Ninja;
import dev.java10x.CadastroDeNinjas.model.ninja.INinjaMapper;
import dev.java10x.CadastroDeNinjas.model.ninja.NinjaRequest;
import dev.java10x.CadastroDeNinjas.model.ninja.NinjaResponse;
import dev.java10x.CadastroDeNinjas.repository.mission.MissionRepository;
import dev.java10x.CadastroDeNinjas.repository.ninja.NinjaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NinjaService implements INinjaService {

    private final NinjaRepository repository;
    private final INinjaMapper mapper;
    private final MissionRepository missionRepository;

    public NinjaService(NinjaRepository repository, INinjaMapper mapper, MissionRepository missionRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.missionRepository = missionRepository;
    }

    @Transactional(rollbackOn =  Exception.class)
    public NinjaResponse create(NinjaRequest request){
        Ninja ninja = mapper.toEntity(request);
        ninja.setId(UUID.randomUUID().toString());

        var missions = missionRepository.findById(request.missions())
                .orElseThrow(() -> new RuntimeException("Missão não encontrada"));
        ninja.setMissions(missions);

        repository.save(ninja);
        return mapper.toResponse(ninja);
    }

    public List<NinjaResponse> getAll(){
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }


}
