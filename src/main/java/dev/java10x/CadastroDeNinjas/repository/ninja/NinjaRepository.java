package dev.java10x.CadastroDeNinjas.repository.ninja;

import dev.java10x.CadastroDeNinjas.entity.ninja.Ninja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NinjaRepository extends JpaRepository<Ninja, String> {}

