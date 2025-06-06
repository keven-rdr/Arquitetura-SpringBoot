package dev.java10x.CadastroDeNinjas.service.missions;

import dev.java10x.CadastroDeNinjas.entity.missions.Missions;
import dev.java10x.CadastroDeNinjas.model.missions.MissionRequest;
import dev.java10x.CadastroDeNinjas.model.missions.MissionResponse;

import java.util.List;

public interface IMissionService {

    MissionResponse create (MissionRequest request);

    List<MissionResponse> getAll();

    Missions findMissionById(String id);

    MissionResponse getById(String id);

    void delete(String id);

    MissionResponse update(String id, MissionRequest request);
}
