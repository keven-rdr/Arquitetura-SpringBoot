package dev.java10x.CadastroDeNinjas.model.ninja;

import dev.java10x.CadastroDeNinjas.model.missions.MissionResponse;

import java.time.LocalDate;

public record NinjaResponse(
        String id,
        String name,
        String email,
        LocalDate age,
        MissionResponse missions
) {
}
