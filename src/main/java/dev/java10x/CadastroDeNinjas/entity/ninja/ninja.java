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
    public String name;

    @Column(name = "email")
    public String email;

    @Column(name = "age")
    public int age;

    public ninja() {
    }

    public ninja(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

}
