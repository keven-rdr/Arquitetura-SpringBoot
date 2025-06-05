package dev.java10x.CadastroDeNinjas.service.missions;

import dev.java10x.CadastroDeNinjas.model.missions.MissionRequest;
import dev.java10x.CadastroDeNinjas.model.missions.MissionResponse;

import java.util.List;

public interface IMissionService {

    MissionResponse create (MissionRequest request);

    List<MissionResponse> getAll();
}
