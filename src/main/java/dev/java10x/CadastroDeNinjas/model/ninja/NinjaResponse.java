package dev.java10x.CadastroDeNinjas.model.ninja;

import dev.java10x.CadastroDeNinjas.model.missions.MissionSimpleResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record NinjaResponse(
        String id,
        String name,
        String email,
        LocalDate age,
        MissionSimpleResponse missions,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
