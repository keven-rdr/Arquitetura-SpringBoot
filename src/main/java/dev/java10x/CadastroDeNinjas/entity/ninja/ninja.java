package dev.java10x.CadastroDeNinjas.entity.ninja;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_cadastro")
public class ninja {

    @Id
    //IDENTITY para gerar o id automaticamente com numero
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 36)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    public ninja(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

}
