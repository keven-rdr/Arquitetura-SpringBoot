package dev.java10x.CadastroDeNinjas.entity.ninja;

import dev.java10x.CadastroDeNinjas.entity.missions.Missions;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tb_cadastro")
public class Ninja {

    @Id
    private String id;
    private String name;
    private String email;
    private LocalDate age;

    //um ninja vai poder ter somente uma missão
    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Missions missions;

}
