package dev.java10x.CadastroDeNinjas.repository.mission;


import dev.java10x.CadastroDeNinjas.entity.missions.Missions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Missions, String> {
}
