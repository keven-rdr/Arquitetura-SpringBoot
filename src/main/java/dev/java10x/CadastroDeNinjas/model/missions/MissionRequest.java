package dev.java10x.CadastroDeNinjas.model.missions;

import dev.java10x.CadastroDeNinjas.model.ninja.NinjaResponse;

import java.util.List;

public record MissionRequest(
        String name,
        String difficultyMission,
        boolean completed
) {
}
