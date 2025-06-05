package dev.java10x.CadastroDeNinjas.entity.ninja;

import dev.java10x.CadastroDeNinjas.entity.missions.Missions;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tb_cadastro")
public class Ninja {

    @Id
    @Column(name = "id", nullable = false)
    @Comment("Identificador Único")
    private String id;

    @Column(name = "name")
    @Comment("Nome do Ninja")
    private String name;

    @Column(name = "email")
    @Comment("Email do Ninja")
    private String email;

    @Column(name = "age")
    @Comment("Idade do ninja")
    private LocalDate age;

    @ManyToOne
    private Missions missions;


}
