package dev.java10x.CadastroDeNinjas.model.ninja;

import java.time.LocalDate;

public record NinjaRequest(
        String name,
        String email,
        LocalDate age
) {
}
