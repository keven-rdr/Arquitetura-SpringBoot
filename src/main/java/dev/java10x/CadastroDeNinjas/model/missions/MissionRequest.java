package dev.java10x.CadastroDeNinjas.model.missions;


import dev.java10x.CadastroDeNinjas.model.ninja.NinjaSimpleResponse;

import java.util.List;

public record MissionRequest(

        String name,
        String difficultyMission,
        Boolean completed,
        List<NinjaSimpleResponse> ninjas
) {
}
