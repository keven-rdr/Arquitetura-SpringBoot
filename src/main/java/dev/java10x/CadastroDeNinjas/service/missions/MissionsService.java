package dev.java10x.CadastroDeNinjas.service.missions;

import dev.java10x.CadastroDeNinjas.entity.missions.Missions;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MissionsService {

    public Missions create(String name, String difficultyMission, boolean completed){
        Missions missions = new Missions();
        missions.setId(UUID.randomUUID().toString());
        missions.setName(name);
        missions.setDifficultyMission(difficultyMission);
        missions.setCompleted(completed);

        return missions;
    }
}
