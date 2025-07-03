package dev.java10x.CadastroDeNinjas.model.ninja;


import dev.java10x.CadastroDeNinjas.model.missions.MissionSimpleResponse;

import java.time.LocalDate;


public record NinjaRequest(
        String name,
        String email,
        LocalDate age,
        MissionSimpleResponse missions
) {
}
