package dev.java10x.CadastroDeNinjas.model.ninja;

import dev.java10x.CadastroDeNinjas.model.missions.MissionRequest;

import java.time.LocalDate;


public record NinjaRequest(
        String name,
        String email,
        LocalDate age,
        String missions
) {
}
