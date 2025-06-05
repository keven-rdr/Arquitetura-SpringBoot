package dev.java10x.CadastroDeNinjas.model.missions;

import dev.java10x.CadastroDeNinjas.model.ninja.NinjaResponse;

import java.time.LocalDateTime;
import java.util.List;

public record MissionResponse(
        String id,
        String name,
        String difficultyMission,
        boolean completed,
        List<NinjaResponse> ninjas,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
