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
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name")
    public String name;

    @Column(name = "email")
    public String email;

    @Column(name = "age")
    public LocalDate age;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "missions_id")
    private Missions missions;



}
