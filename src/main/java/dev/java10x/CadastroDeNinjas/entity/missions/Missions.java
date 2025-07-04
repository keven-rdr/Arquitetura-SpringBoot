package dev.java10x.CadastroDeNinjas.entity.missions;

import dev.java10x.CadastroDeNinjas.entity.ninja.Ninja;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_missoes", schema = "ninja")
public class Missions {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false)
    @Comment("Identificador Único")
    private String id;

    @Column(name = "name")
    @Comment("Nome da Missão")
    private String name;

    @Column(name = "difficulty_mission")
    @Comment("Dificuldade da Missão")
    private String difficultyMission;

    @Column(name = "completed")
    @Comment("Status da Missão")
    private boolean completed;

    @OneToMany(mappedBy = "missions")
    private List<Ninja> ninjas;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
