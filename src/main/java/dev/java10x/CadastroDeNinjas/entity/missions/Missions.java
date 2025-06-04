package dev.java10x.CadastroDeNinjas.entity.missions;

import dev.java10x.CadastroDeNinjas.entity.ninja.Ninja;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_missoes")
public class Missions {

    @Id
    private String id;
    private String name;
    private String difficultyMission;
    private boolean completed;

    //uma missão pode ter vários ninjas
    @OneToMany(mappedBy = "missions")
    private List<Ninja> ninja;
}
